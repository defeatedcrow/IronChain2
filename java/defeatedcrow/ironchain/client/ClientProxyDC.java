package defeatedcrow.ironchain.client;

import defeatedcrow.ironchain.*;
import defeatedcrow.ironchain.block.tileentity.*;
import defeatedcrow.ironchain.client.block.*;
import defeatedcrow.ironchain.client.model.*;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ClientProxyDC extends CommonProxyDC {
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	public int getRenderID()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerBlockHandler(new RenderFloodLight());
		RenderingRegistry.registerBlockHandler(new RenderRHopper());
		RenderingRegistry.registerBlockHandler(new RenderRHopper2());
	}
	
	public void registerTile() {
		GameRegistry.registerTileEntity(TileEntityRHopper.class, "TileEntityRHopper");
		GameRegistry.registerTileEntity(TileRHopperGold.class, "TileEntityRHopperGold");
		GameRegistry.registerTileEntity(TileRHopperBlack.class, "TileEntityRHopperBlack");
		GameRegistry.registerTileEntity(TilePositiveHopperGold.class, "TilePHopperGold");
		GameRegistry.registerTileEntity(TilePositiveHopperBlack.class, "TilePHopperBlack");
		ClientRegistry.registerTileEntity(TileFloodLight.class, "TileEntityFloodLight", new TSRendererFloodLight());
	}
}
