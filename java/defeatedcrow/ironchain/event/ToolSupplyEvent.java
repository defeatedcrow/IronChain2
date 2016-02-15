package defeatedcrow.ironchain.event;

import java.util.ArrayList;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.IronChainLog;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class ToolSupplyEvent {

	@SubscribeEvent
	public void onDestroy(PlayerDestroyItemEvent event) {
		EntityPlayer player = event.entityPlayer;
		World world = player.worldObj;
		ItemStack belt = player.inventory.armorItemInSlot(1);
		if (player != null && !world.isRemote && DCsIronChain.autoTool) {
			if (belt != null && belt.getItem() != null && belt.getItem() == DCsIronChain.toolBag) {
				// tagチェック
				NBTTagCompound nbt = null;
				if (!belt.hasTagCompound()) {
					nbt = new NBTTagCompound();
					nbt.setTag("Items", new NBTTagList());
					belt.setTagCompound(nbt);
				} else {
					nbt = belt.getTagCompound();
				}
				NBTTagList tags = (NBTTagList) belt.getTagCompound().getTag("Items");

				// Bag内のItemリスト
				ArrayList<NumberedItem> itemList = new ArrayList<NumberedItem>();
				boolean fin = false;
				ItemStack ret = null;

				for (int i = 0; i < tags.tagCount(); i++) {
					NBTTagCompound tagCompound = tags.getCompoundTagAt(i);
					int slot = tagCompound.getByte("Slot");
					if (slot >= 0 && slot < 8) {
						ItemStack get = ItemStack.loadItemStackFromNBT(tagCompound);
						if (get != null && get.getItem() != null) {
							if (!fin && get.getItem().isItemTool(get)) {
								ret = get;
								fin = true;
							} else {
								itemList.add(new NumberedItem(get, slot));
							}
						}
					}
				}

				if (!itemList.isEmpty()) {
					NBTTagList tagList = new NBTTagList();
					for (NumberedItem item : itemList) {
						NBTTagCompound compound = new NBTTagCompound();
						compound.setByte("Slot", (byte) item.getSlot());
						item.getItem().writeToNBT(compound);
						tagList.appendTag(compound);
					}
					nbt.setTag("Items", tagList);
					belt.setTagCompound(nbt);

					player.inventory.armorInventory[1] = belt;
				} else {
					NBTTagList tagList = new NBTTagList();
					nbt.setTag("Items", tagList);
					belt.setTagCompound(nbt);

					player.inventory.armorInventory[1] = belt;
				}

				if (ret != null) {
					EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ, ret);
					world.spawnEntityInWorld(drop);
				}
			}
		}
	}

	public class NumberedItem {
		private final ItemStack item;
		private final int slot;

		public NumberedItem(ItemStack i, int s) {
			item = i;
			slot = s;
		}

		public ItemStack getItem() {
			return item;
		}

		public int getSlot() {
			return slot;
		}
	}

}
