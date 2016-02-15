package defeatedcrow.ironchain.client.gui;

import defeatedcrow.ironchain.DCsIronChain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryToolBag implements IInventory {
	private InventoryPlayer inventoryPlayer;
	private ItemStack currentItem;
	private ItemStack[] items;
	private boolean isWear;

	public InventoryToolBag(InventoryPlayer inventory, boolean wear) {
		inventoryPlayer = inventory;
		if (wear) {
			currentItem = inventoryPlayer.armorItemInSlot(1);
		} else {
			currentItem = inventoryPlayer.getCurrentItem();
		}

		// InventorySize
		items = new ItemStack[8];
		isWear = wear;
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return items[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (this.items[slot] != null) {
			ItemStack itemstack;

			if (this.items[slot].stackSize <= size) {
				itemstack = this.items[slot];
				this.items[slot] = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.items[slot].splitStack(size);

				if (this.items[slot].stackSize == 0) {
					this.items[slot] = null;
				}

				this.markDirty();
				return itemstack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.items[slot] != null) {
			ItemStack itemstack = this.items[slot];
			this.items[slot] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.items[slot] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return "Tool Bag";
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
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	/*
	 * Containerが開かれたタイミングでItemStackの持っているNBTからアイテムを読み込んでいる
	 */
	@Override
	public void openInventory() {
		if (!currentItem.hasTagCompound()) {
			currentItem.setTagCompound(new NBTTagCompound());
			currentItem.getTagCompound().setTag("Items", new NBTTagList());
		}
		NBTTagList tags = (NBTTagList) currentItem.getTagCompound().getTag("Items");

		for (int i = 0; i < tags.tagCount(); i++) {
			NBTTagCompound tagCompound = tags.getCompoundTagAt(i);
			int slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < items.length) {
				items[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
	}

	/*
	 * Containerを閉じるときに保存
	 */
	@Override
	public void closeInventory() {
		NBTTagList tagList = new NBTTagList();
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte) i);
				items[i].writeToNBT(compound);
				tagList.appendTag(compound);
			}
		}
		ItemStack result = currentItem.copy();
		NBTTagCompound nbt = currentItem.hasTagCompound() ? currentItem.getTagCompound() : new NBTTagCompound();
		nbt.setTag("Items", tagList);
		result.setTagCompound(nbt);

		// ItemStackをセットする。NBTは右クリック等のタイミングでしか保存されないため再セットで保存している。
		if (isWear) {
			inventoryPlayer.armorInventory[1] = result;
		} else {
			inventoryPlayer.mainInventory[inventoryPlayer.currentItem] = result;
		}
	}

	// 自身は入れられない
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

}
