package defeatedcrow.ironchain.client.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;

@SideOnly(Side.CLIENT)
public class RenderChain implements ISimpleBlockRenderingHandler {

	private IIcon baseIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		boolean upper = world.getBlock(x, y + 1, z) == DCsIronChain.ironChain;
		boolean under = world.getBlock(x, y - 1, z) == DCsIronChain.ironChain;
		this.baseIcon = block.getBlockTextureFromSide(0);
		if (upper) {
			this.baseIcon = under ? block.getIcon(1, 0) : block.getIcon(0, 1);
		} else {
			this.baseIcon = under ? block.getIcon(0, 2) : block.getIcon(0, 0);
		}
		float f = 16.0F;

		if (modelId == this.getRenderId()) {
			renderer.setOverrideBlockTexture(this.baseIcon);
			block.setBlockBounds(0.0F / f, 0.0F / f, 0.0F / f, 16.0F / f, 16.0F / f, 16.0F / f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderCrossedSquares(block, x, y, z);

			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {

		return false;
	}

	@Override
	public int getRenderId() {

		return DCsIronChain.modelChain;
	}
}
