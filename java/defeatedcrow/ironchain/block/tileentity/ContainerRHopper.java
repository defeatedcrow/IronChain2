package defeatedcrow.ironchain.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;


public class ContainerRHopper extends Container
{
    private final TileEntityRHopper InventryRHopper;

    public ContainerRHopper(InventoryPlayer par1InventoryPlayer, TileEntityRHopper RHopper)
    {
        this.InventryRHopper = RHopper;
        this.InventryRHopper.openInventory();
        byte b0 = 51;
        int i;

        for (i = 0; i < this.InventryRHopper.getSizeInventory(); ++i)
        {
            addSlotToContainer(new Slot(this.InventryRHopper, i, 44 + i * 18, 20));
        }

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, i * 18 + b0));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 58 + b0));
        }
    }

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.InventryRHopper.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < this.InventryRHopper.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.InventryRHopper.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.InventryRHopper.getSizeInventory(), false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
        this.InventryRHopper.closeInventory();
    }
}
