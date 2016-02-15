package defeatedcrow.ironchain.client.gui;

import defeatedcrow.ironchain.DCsIronChain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerToolBag extends Container {
	private InventoryToolBag inventory;

	public ContainerToolBag(InventoryPlayer inventoryPlayer, boolean wear) {
		inventory = new InventoryToolBag(inventoryPlayer, wear);
		inventory.openInventory();
		int b0 = 43;
		int i = 8;

		for (int k = 0; k < i; ++k) {
			this.addSlotToContainer(new Slot(inventory, k, 17 + k * 18, b0));
		}

		for (int j = 0; j < 3; ++j) {
			for (int k = 0; k < 9; ++k) {
				this.addSlotToContainer(new SlotToolBag(inventoryPlayer, k + j * 9 + 9, 8 + k * 18, j * 18 + 41 + b0));
			}
		}

		for (int j = 0; j < 9; ++j) {
			this.addSlotToContainer(new SlotToolBag(inventoryPlayer, j, 8 + j * 18, 58 + 41 + b0));
		}

	}

	/*
	 * Containerが開いてられるか
	 */
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int stackSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(stackSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (stackSlot < this.inventory.getSizeInventory()) {
				if (!this
						.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
					return null;
				}
			}
			// シフトクリック時に、このアイテムだったら動かさない。
			else if (slot.getStack() != null && slot.getStack().getItem() == DCsIronChain.toolBag) {
				return null;
			} else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
				return null;
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	/*
	 * Containerを閉じるときに呼ばれる
	 */
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.inventory.closeInventory();
	}
}
