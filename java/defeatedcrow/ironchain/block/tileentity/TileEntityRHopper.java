package defeatedcrow.ironchain.block.tileentity;

import java.util.ArrayList;
import java.util.List;

import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile.PipeType;
import cpw.mods.fml.common.Optional;
import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.IronChainLog;
import defeatedcrow.ironchain.block.BlockRHopper;
import defeatedcrow.ironchain.integration.IntegrationBC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

@Optional.InterfaceList({
		@Optional.Interface(iface = "buildcraft.api.transport.IPipeConnection", modid = "BuildCraft|Core"),
})
public class TileEntityRHopper extends TileEntity implements IHopper, IReversalHopper, IPipeConnection {
	protected int transferCooldown = -1;

	public ItemStack[] hopperItems = new ItemStack[this.getHopperSize()];

	// NBT
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		this.hopperItems = new ItemStack[this.getSizeInventory()];

		this.transferCooldown = par1NBTTagCompound.getInteger("TransferCooldown");

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.hopperItems.length) {
				this.hopperItems[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.hopperItems.length; ++i) {
			if (this.hopperItems[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.hopperItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);
		par1NBTTagCompound.setInteger("TransferCooldown", this.transferCooldown);
	}

	// packet
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	// read
	public void setTransferCooldown(int par1) {
		this.transferCooldown = par1;
	}

	public boolean isCoolingDown() {
		return this.transferCooldown > 0;
	}

	public void setItems(ItemStack[] _items) {
		if (_items != null) {
			for (int i = 0; i < this.getSizeInventory(); ++i) {
				if (_items[i] != null) {
					this.hopperItems[i] = _items[i].copy();
				} else {
					this.hopperItems[i] = null;
				}
			}
		}
	}

	// write
	public ItemStack[] getItems() {

		return this.hopperItems;
	}

	public int getCoolTime() {

		return this.transferCooldown;
	}

	// count cooldown
	@Override
	public void updateEntity() {
		if (this.worldObj != null && !this.worldObj.isRemote) {
			--this.transferCooldown;

			if (!this.isCoolingDown()) {
				this.setTransferCooldown(0);
				this.updateHopper();
			}
		}
	}

	public boolean updateHopper() {
		if (this.worldObj != null && !this.worldObj.isRemote) {
			if (!this.isCoolingDown() && BlockRHopper.getIsBlockNotPoweredFromMetadata(this.getBlockMetadata())) {
				// 搬入を先に変更
				boolean flag = suckItemsIntoHopper(this);
				// 搬出
				flag = this.insertItemToInventory() || flag;

				if (flag) {
					this.setTransferCooldown(4);
					this.markDirty();
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

	// for hopper contents
	// RHopperから送り出す
	protected boolean insertItemToInventory() {
		boolean tryInsert = false;

		// BCパイプへの挿入
		if (DCsIronChain.getLoadBC) {
			// 接続しているパイプのリスト（方向）
			ForgeDirection from = ForgeDirection.getOrientation(this.getBlockMetadata()).getOpposite();
			if (this.isReversal() && from == ForgeDirection.UP)
				from = ForgeDirection.DOWN;// 下向きに設置した場合は真上を向いている
			if (!this.isReversal() && from == ForgeDirection.DOWN)
				from = ForgeDirection.UP;
			ArrayList<ForgeDirection> dir = IntegrationBC.getPipeConected(this.worldObj, this.xCoord, this.yCoord,
					this.zCoord, from);

			// fromはメタデータから得た向き（一方向）なので、大抵の場合はサイズ1か0のはず
			if (dir.size() > 0) {
				for (int i = 0; i < this.getSizeInventory(); ++i)// スロット内部をサーチ
				{
					if (this.getStackInSlot(i) != null)// 中身があった
					{
						ItemStack current = this.getStackInSlot(i);
						IronChainLog.debugTrace("Current size: " + current.stackSize);
						ItemStack itemstack[] = this.extractItem(true, from, this.getExtractLimitSize());

						for (int l = 0; l < itemstack.length; l++) {
							IronChainLog.debugTrace("Extracted Item: " + itemstack[l].stackSize + " : "
									+ itemstack[l].getDisplayName());
							if (IntegrationBC.dropIntoPipe(this, dir, itemstack[l])) {
								IronChainLog.debugTrace("Current size after: " + current.stackSize);

								tryInsert = true;
								break;
							}

						}
						this.markDirty();
						return true;
					}
				}
			}
		}

		// バニラホッパーの挙動
		IInventory iinventory = this.getOutputInventory();

		if (iinventory == null || tryInsert)// パイプへの搬出がうまくいった場合は何もしない
		{
			return false;
		} else {
			for (int i = 0; i < this.getSizeInventory(); ++i)// バニラホッパーと同じ挙動
			{
				if (this.getStackInSlot(i) != null) {
					ItemStack itemstack = this.getStackInSlot(i).copy();
					int side = ForgeDirection.OPPOSITES[BlockRHopper.getDirectionFromMetadata(this.getBlockMetadata())];
					// 上面偽装
					int extract = this.canInsertSize(iinventory, itemstack, this.getExtractLimitSize(), 1);

					if (extract > 0) {
						ItemStack insert = this.decrStackSize(i, extract);
						insertStack(iinventory, insert, 1);
						iinventory.markDirty();
						this.markDirty();
						return true;
					}
				}
			}

			return false;
		}
	}

	// RHopperに吸い込む
	public static boolean suckItemsIntoHopper(IHopper par0Hopper) {
		IInventory iinventory = getInventoryAboveHopper(par0Hopper);
		TileEntityRHopper rhopper = null;

		if (par0Hopper instanceof TileEntityRHopper) {
			rhopper = (TileEntityRHopper) par0Hopper;
		}

		if (iinventory != null) {
			byte b0 = 0;// 吸引サイドは0(下部から)に偽装する

			if (iinventory instanceof ISidedInventory) {
				ISidedInventory isidedinventory = (ISidedInventory) iinventory;
				int[] aint = isidedinventory.getAccessibleSlotsFromSide(b0);

				for (int i = 0; i < aint.length; ++i) {
					if (insertStackFromInventory(par0Hopper, iinventory, aint[i], b0)) {
						return true;
					}
				}
			} else {
				int j = iinventory.getSizeInventory();

				for (int k = 0; k < j; ++k) {
					if (insertStackFromInventory(par0Hopper, iinventory, k, b0)) {
						return true;
					}
				}
			}
		} else {
			EntityItem entityitem = getEntityAbove(par0Hopper.getWorldObj(), par0Hopper, par0Hopper.getXPos(),
					par0Hopper.getYPos(), par0Hopper.getZPos());

			if (entityitem != null) {
				return insertStackFromEntity(par0Hopper, entityitem);
			}
		}
		return false;
	}

	// インベントリからのアイテムをホッパーに投入する処理部分
	protected static boolean insertStackFromInventory(IHopper par0Hopper, IInventory par1IInventory, int par2,
			int par3) {
		ItemStack itemstack = par1IInventory.getStackInSlot(par2);
		int extract = 1;
		if (itemstack != null && par0Hopper instanceof TileEntityRHopper) {
			extract = ((TileEntityRHopper) par0Hopper).canInsertSize(par0Hopper, itemstack,
					((TileEntityRHopper) par0Hopper).getExtractLimitSize(), par3);
		}

		if (itemstack != null && canExtractItemFromInventory(par1IInventory, itemstack, par2, par3)) {
			ItemStack itemstack1 = itemstack.copy();
			ItemStack itemstack2 = insertStack(par0Hopper, par1IInventory.decrStackSize(par2, extract), -1);

			if (itemstack2 == null || itemstack2.stackSize <= 0) {
				par1IInventory.markDirty();
				par0Hopper.markDirty();
				return true;
			}

			par1IInventory.setInventorySlotContents(par2, itemstack1);
		}

		return false;
	}

	// 落ちているアイテムを拾う
	public static boolean insertStackFromEntity(IInventory par0IInventory, EntityItem par1EntityItem) {
		boolean flag = false;

		if (par1EntityItem == null) {
			return false;
		} else {
			ItemStack itemstack = par1EntityItem.getEntityItem().copy();
			ItemStack itemstack1 = insertStack(par0IInventory, itemstack, -1);

			if (itemstack1 != null && itemstack1.stackSize != 0) {
				par1EntityItem.setEntityItemStack(itemstack1);
			} else {
				flag = true;
				par1EntityItem.setDead();
			}

			return flag;
		}
	}

	/*
	 * ホッパー、対象の双方で使うアイテム挿入処理
	 */
	public static ItemStack insertStack(IInventory par0IInventory, ItemStack par1ItemStack, int side) {
		if (par0IInventory instanceof ISidedInventory && side > -1) {
			ISidedInventory isidedinventory = (ISidedInventory) par0IInventory;
			int[] aint = isidedinventory.getAccessibleSlotsFromSide(side);

			for (int j = 0; j < aint.length && par1ItemStack != null && par1ItemStack.stackSize > 0; ++j) {
				par1ItemStack = insertItemstack(par0IInventory, par1ItemStack, aint[j], side);
			}
		} else {
			int k = par0IInventory.getSizeInventory();

			for (int l = 0; l < k && par1ItemStack != null && par1ItemStack.stackSize > 0; ++l) {
				par1ItemStack = insertItemstack(par0IInventory, par1ItemStack, l, side);
			}
		}

		if (par1ItemStack != null && par1ItemStack.stackSize == 0) {
			par1ItemStack = null;
		}

		return par1ItemStack;
	}

	// バニラのままではスタック数2以上のアイテム搬出を考慮していないため、搬出可能数を事前チェックする
	public static int canInsertSize(IInventory inv, ItemStack item, int maxCount, int side) {
		if (inv == null || item == null)
			return 0;
		int ret = Math.min(maxCount, item.stackSize);
		int check = 0;

		if (inv instanceof ISidedInventory && side > -1) {
			ISidedInventory isidedinventory = (ISidedInventory) inv;
			int[] aint = isidedinventory.getAccessibleSlotsFromSide(side);

			for (int j = 0; j < aint.length; ++j) {
				ItemStack checkItem = isidedinventory.getStackInSlot(j);
				if (!canInsertItemToInventory(inv, checkItem, j, side)) {
					continue;
				}
				if (checkItem == null) {
					check = isidedinventory.getInventoryStackLimit();
					break;
				} else if (areItemStacksEqualItem(item, checkItem)) {
					check = isidedinventory.getInventoryStackLimit() - checkItem.stackSize;
					if (check > 0)
						break;
				}
			}
		} else {
			int k = inv.getSizeInventory();

			for (int l = 0; l < k; ++l) {
				ItemStack checkItem = inv.getStackInSlot(l);
				if (!canInsertItemToInventory(inv, checkItem, l, side)) {
					continue;
				}
				if (checkItem == null) {
					check = inv.getInventoryStackLimit();
					break;
				} else if (areItemStacksEqualItem(item, checkItem)) {
					check = inv.getInventoryStackLimit() - checkItem.stackSize;
					if (check > 0)
						break;
				}
			}
		}

		ret = Math.min(ret, check);
		return ret;
	}

	/**
	 * Args: inventory, item, slot, side
	 */
	protected static boolean canInsertItemToInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2,
			int par3) {
		return !par0IInventory.isItemValidForSlot(par2, par1ItemStack) ? false
				: !(par0IInventory instanceof ISidedInventory)
						|| ((ISidedInventory) par0IInventory).canInsertItem(par2, par1ItemStack, par3);
	}

	// 吸い込み対象のインベントリ。SidedInventoryの材料スロットやホッパー系からの搬入は禁止。
	protected static boolean canExtractItemFromInventory(IInventory par0IInventory, ItemStack par1ItemStack, int par2,
			int par3) {
		if (par0IInventory instanceof ISidedInventory) {
			return ((ISidedInventory) par0IInventory).canExtractItem(par2, par1ItemStack, par3);
		} else if (par0IInventory instanceof TileEntityRHopper) {
			return false;
		} else if (par0IInventory instanceof TileEntityHopper) {
			return false;
		} else {
			return true;
		}
	}

	private static ItemStack insertItemstack(IInventory par0IInventory, ItemStack par1ItemStack, int par2, int par3) {
		ItemStack itemstack1 = par0IInventory.getStackInSlot(par2);

		if (canInsertItemToInventory(par0IInventory, par1ItemStack, par2, par3)) {
			boolean flag = false;

			if (itemstack1 == null) {
				par0IInventory.setInventorySlotContents(par2, par1ItemStack);
				par1ItemStack = null;
				flag = true;
			} else if (areItemStacksEqualItem(itemstack1, par1ItemStack)) {
				int k = par1ItemStack.getMaxStackSize() - itemstack1.stackSize;
				int l = Math.min(par1ItemStack.stackSize, k);
				par1ItemStack.stackSize -= l;
				itemstack1.stackSize += l;
				flag = l > 0;
			}

			if (flag) {
				if (par0IInventory instanceof TileEntityRHopper) {
					((TileEntityRHopper) par0IInventory).setTransferCooldown(4);
					par0IInventory.markDirty();
				}

				par0IInventory.markDirty();
			}
		}

		return par1ItemStack;
	}

	// 搬出先インベントリ
	protected IInventory getOutputInventory() {
		int i = BlockRHopper.getDirectionFromMetadata(this.getBlockMetadata());
		IInventory iinventory = getInventoryAtLocation(this.getWorldObj(), this.xCoord + Facing.offsetsXForSide[i],
				this.yCoord + Facing.offsetsYForSide[i], this.zCoord + Facing.offsetsZForSide[i]);
		return iinventory;
	}

	// 1ブロック下のインベントリ取得
	public static IInventory getInventoryAboveHopper(IHopper par0Hopper) {
		double ajust = 1.0D;
		if (par0Hopper instanceof TileEntityRHopper) {
			ajust = ((TileEntityRHopper) par0Hopper).isReversal() ? -1.0D : 1.0D;
		}
		return getInventoryAtLocation(par0Hopper.getWorldObj(), par0Hopper.getXPos(), par0Hopper.getYPos() + ajust,
				par0Hopper.getZPos());
	}

	// EntityItemの吸引範囲
	public static EntityItem getEntityAbove(World par0World, IHopper hopper, double par1, double par3, double par5) {
		double range = 1.0D;
		int height = 1;
		AxisAlignedBB AABB = null;
		if (hopper instanceof TileEntityRHopper) {
			range = ((TileEntityRHopper) hopper).getSuctionRange();
			height = MathHelper.ceiling_float_int((float) range + 1.0F);
			if (height < 1)
				height = 1;
			if (((TileEntityRHopper) hopper).isReversal()) {
				AABB = AxisAlignedBB.getBoundingBox(par1 - range, par3 - height, par5 - range, par1 + range + 1, par3,
						par5 + range + 1);
			} else {
				AABB = AxisAlignedBB.getBoundingBox(par1 - range, par3 + 1, par5 - range, par1 + range + 1,
						par3 + height + 1, par5 + range + 1);
			}
		}

		if (AABB != null) {

		}
		List list = par0World.selectEntitiesWithinAABB(EntityItem.class, AABB, IEntitySelector.selectAnything);
		return list.size() > 0 ? (EntityItem) list.get(0) : null;
	}

	/**
	 * Gets an inventory at the given location to extract items into or take
	 * items from. Can find either a tile entity
	 * or regular entity implementing IInventory.
	 */
	public static IInventory getInventoryAtLocation(World par0World, double par1, double par3, double par5) {
		IInventory iinventory = null;
		int i = MathHelper.floor_double(par1);
		int j = MathHelper.floor_double(par3);
		int k = MathHelper.floor_double(par5);
		TileEntity tileentity = par0World.getTileEntity(i, j, k);

		if (tileentity != null && tileentity instanceof IInventory) {
			iinventory = (IInventory) tileentity;

			if (iinventory instanceof TileEntityChest) {
				Block block = par0World.getBlock(i, j, k);

				if (block instanceof BlockChest) {
					iinventory = ((BlockChest) block).func_149951_m(par0World, i, j, k);
				}
			}
		}

		if (iinventory == null) {
			List list = par0World.getEntitiesWithinAABBExcludingEntity((Entity) null,
					AxisAlignedBB.getBoundingBox(par1, par3, par5, par1 + 1.0D, par3 - 2.0D, par5 + 1.0D),
					IEntitySelector.selectInventories);

			if (list != null && list.size() > 0) {
				iinventory = (IInventory) list.get(par0World.rand.nextInt(list.size()));
			}
		}

		return iinventory;
	}

	protected static boolean areItemStacksEqualItem(ItemStack stack, ItemStack target) {
		if (stack != null && target != null && stack.getItem() != null && stack.getItem() != null) {
			boolean i = stack.getItem() == target.getItem();
			boolean m = stack.getItemDamage() == target.getItemDamage();
			boolean n = !stack.hasTagCompound() && !target.hasTagCompound();
			if (stack.hasTagCompound() && target.hasTagCompound()
					&& stack.stackTagCompound.equals(target.stackTagCompound)) {
				n = true;
			}

			return i && m && n;
		}

		return stack == null && target == null;
	}

	// Inventory
	@Override
	public int getSizeInventory() {

		return this.hopperItems.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i < this.getSizeInventory()) {
			return this.hopperItems[i];
		} else
			return null;

	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.hopperItems[i] != null) {
			ItemStack itemstack;

			if (this.hopperItems[i].stackSize <= j) {
				itemstack = this.hopperItems[i].copy();
				this.hopperItems[i].stackSize = 0;
				this.hopperItems[i] = null;
				return itemstack;
			} else {
				itemstack = this.hopperItems[i].splitStack(j);

				if (this.hopperItems[i].stackSize == 0) {
					this.hopperItems[i] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.hopperItems[i] != null) {
			ItemStack itemstack = this.hopperItems[i];
			this.hopperItems[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (i < 5) {
			this.hopperItems[i] = itemstack;

			if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
				itemstack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	@Override
	public String getInventoryName() {

		return "Reversal Hopper";
	}

	@Override
	public boolean hasCustomInventoryName() {

		return false;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {

		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: entityplayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {

		return true;
	}

	/**
	 * Gets the world X position for this hopper entity.
	 */
	@Override
	public double getXPos() {
		return this.xCoord;
	}

	/**
	 * Gets the world Y position for this hopper entity.
	 */
	@Override
	public double getYPos() {
		return this.yCoord;
	}

	/**
	 * Gets the world Z position for this hopper entity.
	 */
	@Override
	public double getZPos() {
		return this.zCoord;
	}

	@Override
	public World getWorldObj() {

		return this.worldObj;
	}

	// 以下はBCパイプへアイテムを送るためのメソッドのはずだけどちゃんと動かないよく分からない何か。
	public ItemStack[] extractItem(boolean doRemove, ForgeDirection from, int maxCount) {
		TileEntityRHopper inv = this;
		ItemStack extract = null;

		for (int i = 0; i < this.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack stack = inv.getStackInSlot(i);
				int count = Math.min(maxCount, stack.stackSize);

				if (doRemove) {
					extract = inv.decrStackSize(i, count);
					break;
				}
				extract = stack.copy();

				break;
			}
		}
		if (extract != null) {
			return new ItemStack[] {
					extract
			};
		}
		return null;
	}

	// こちらはパイプから来たアイテムを受け入れるためのもの。
	public int addItem(ItemStack stack, boolean doAdd, ForgeDirection from) {

		int l = this.getSizeInventory();
		int removeItemstack = 0;
		boolean sameDir = from == ForgeDirection.getOrientation(this.getBlockMetadata());

		if (!sameDir) {
			for (int i = 0; i < 5 && stack != null && stack.stackSize > 0; i++) {
				ItemStack current = this.getStackInSlot(i);
				if (this.isItemValidForSlot(i, stack)) {
					boolean flag = false;

					if (current == null || current.stackSize <= 0) {
						this.setInventorySlotContents(i, stack.copy());
						removeItemstack = stack.stackSize;
						stack.stackSize = 0;
						stack = null;
						flag = true;
					} else if (areItemStacksEqualItem(current, stack)) {
						int k = stack.getMaxStackSize() - current.stackSize;
						int j = Math.min(stack.stackSize, k);
						stack.stackSize -= j;
						current.stackSize += j;
						removeItemstack = j;
						flag = j > 0;
					}

					if (flag) {
						this.markDirty();
					}
				}
			}
		}

		if (stack != null && stack.stackSize == 0) {
			stack = null;
			return 0;
		} else {
			return removeItemstack;
		}
	}

	/* RHopper用の設定メソッド */
	@Override
	public int getHopperSize() {
		return 5;
	}

	@Override
	public int getExtractLimitSize() {
		return 1;
	}

	@Override
	public int getSuctionRange() {
		return 1;
	}

	@Override
	public boolean isReversal() {
		return true;
	}

	// BC連携
	@Optional.Method(modid = "BuildCraft|Core")
	@Override
	public ConnectOverride overridePipeConnection(PipeType type, ForgeDirection with) {
		if (type == PipeType.ITEM) {
			return ConnectOverride.CONNECT;
		} else
			return ConnectOverride.DEFAULT;
	}
}
