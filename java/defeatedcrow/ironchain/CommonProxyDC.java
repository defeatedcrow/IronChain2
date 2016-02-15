package defeatedcrow.ironchain;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.ironchain.block.tileentity.*;
import defeatedcrow.ironchain.client.gui.ContainerToolBag;
import defeatedcrow.ironchain.client.gui.GuiRHopper;
import defeatedcrow.ironchain.client.gui.GuiToolBag;
import defeatedcrow.ironchain.client.model.ModelBipedThin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxyDC implements IGuiHandler {

	public int addArmor(String armor) {
		return 0;
	}

	public int getRenderID() {
		return -1;
	}

	public ModelBipedThin getArmorModel(int slot) {
		return null;
	}

	public void registerRenderers() {
	}

	public EntityPlayer getClientPlayer() {
		return null;
	}

	public World getClientWorld() {
		return null;
	}

	public void registerTile() {
		GameRegistry.registerTileEntity(TileEntityRHopper.class, "TileEntityRHopper");
		GameRegistry.registerTileEntity(TileRHopperGold.class, "TileEntityRHopperGold");
		GameRegistry.registerTileEntity(TileRHopperBlack.class, "TileEntityRHopperBlack");
		GameRegistry.registerTileEntity(TilePositiveHopperBlack.class, "TilePHopperBlack");
		GameRegistry.registerTileEntity(TilePositiveHopperGold.class, "TilePHopperGold");
		GameRegistry.registerTileEntity(TileFloodLight.class, "TileEntityFloodLight");
		GameRegistry.registerTileEntity(TileBarriarCorn.class, "TileEntityBarriarCorn");
		GameRegistry.registerTileEntity(TileFluidSign.class, "TileEntityFluidSign.dc");
		GameRegistry.registerTileEntity(TileFluidSignL.class, "TileEntityFluidSignL.dc");
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileentity = world.getTileEntity(x, y, z);
		InventoryPlayer inventoryPlayer = player.inventory;
		if (tileentity != null && tileentity instanceof TileEntityRHopper && ID == DCsIronChain.guiIdRHopper) {
			return new ContainerRHopper(inventoryPlayer, (TileEntityRHopper) tileentity);
		} else if (ID == 2) {
			ItemStack belt = player.inventory.armorItemInSlot(1);
			ItemStack cur = player.inventory.getCurrentItem();
			boolean wear = belt != null && belt.getItem() != null && belt.getItem() == DCsIronChain.toolBag;
			boolean hold = cur != null && cur.getItem() != null && cur.getItem() == DCsIronChain.toolBag;
			if (!wear && !hold) {
				return null;
			}
			return new ContainerToolBag(inventoryPlayer, wear);
		} else {
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileentity = world.getTileEntity(x, y, z);
		InventoryPlayer inventoryPlayer = player.inventory;
		if (tileentity != null && tileentity instanceof TileEntityRHopper && ID == DCsIronChain.guiIdRHopper) {
			return new GuiRHopper(inventoryPlayer, (TileEntityRHopper) tileentity);
		} else if (ID == 2) {
			ItemStack belt = player.inventory.armorItemInSlot(1);
			ItemStack cur = player.inventory.getCurrentItem();
			boolean wear = belt != null && belt.getItem() != null && belt.getItem() == DCsIronChain.toolBag;
			boolean hold = cur != null && cur.getItem() != null && cur.getItem() == DCsIronChain.toolBag;
			if (!wear && !hold) {
				return null;
			}
			return new GuiToolBag(inventoryPlayer, wear);
		} else {
			return null;
		}
	}

	public boolean isGuiKeyDown() {
		return false;
	}

}
