package defeatedcrow.ironchain.client;

import org.lwjgl.input.Keyboard;

import defeatedcrow.ironchain.*;
import defeatedcrow.ironchain.block.tileentity.*;
import defeatedcrow.ironchain.client.block.*;
import defeatedcrow.ironchain.client.model.*;
import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxyDC extends CommonProxyDC {

	private static final ModelBipedThin metModel = new ModelBipedThin(0);
	private static final ModelBipedThin bodyModel = new ModelBipedThin(1);
	private static final ModelBipedThin bootsModel = new ModelBipedThin(3);

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

	@Override
	public int getRenderID() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerBlockHandler(new RenderFloodLight());
		RenderingRegistry.registerBlockHandler(new RenderRHopper());
		RenderingRegistry.registerBlockHandler(new RenderRHopper2());
		RenderingRegistry.registerBlockHandler(new RenderBarriarCorn());
		RenderingRegistry.registerBlockHandler(new RenderSignM());
		RenderingRegistry.registerBlockHandler(new RenderSignS());
		RenderingRegistry.registerBlockHandler(new RenderSignL());
		RenderingRegistry.registerBlockHandler(new RenderChain());
		RenderingRegistry.registerBlockHandler(new RenderAshiba());
		RenderingRegistry.registerBlockHandler(new RenderAshibaStairs());
		RenderingRegistry.registerBlockHandler(new RenderHashigo());
		RenderingRegistry.registerBlockHandler(new RenderFence());
	}

	@Override
	public void registerTile() {
		GameRegistry.registerTileEntity(TileEntityRHopper.class, "TileEntityRHopper");
		GameRegistry.registerTileEntity(TileRHopperGold.class, "TileEntityRHopperGold");
		GameRegistry.registerTileEntity(TileRHopperBlack.class, "TileEntityRHopperBlack");
		GameRegistry.registerTileEntity(TilePositiveHopperGold.class, "TilePHopperGold");
		GameRegistry.registerTileEntity(TilePositiveHopperBlack.class, "TilePHopperBlack");
		ClientRegistry.registerTileEntity(TileFloodLight.class, "TileEntityFloodLight", new TSRendererFloodLight());
		ClientRegistry.registerTileEntity(TileBarriarCorn.class, "TileEntityBarriarCorn", new TSRendererBarriarCorn());
		ClientRegistry.registerTileEntity(TileFluidSign.class, "TileFluidSign.dc", new TSRendererFluidSignS());
		ClientRegistry.registerTileEntity(TileFluidSignL.class, "TileFluidSignL.dc", new TSRendererFluidSignL());
	}

	@Override
	public ModelBipedThin getArmorModel(int slot) {
		switch (slot) {
		case 0:
			return metModel;
		case 1:
			return bodyModel;
		case 3:
			return bootsModel;
		default:
			return null;
		}
	}

	@Override
	public boolean isGuiKeyDown() {
		return Keyboard.isKeyDown(DCsIronChain.toolGuiKey);
	}

}
