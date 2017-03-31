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
public class RenderHashigo implements ISimpleBlockRenderingHandler {

	private IIcon baseIcon;
	private IIcon sideIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata & 3;
		this.baseIcon = block.getBlockTextureFromSide(0);
		this.sideIcon = block.getBlockTextureFromSide(2);
		float f = 16.0F;

		if (modelID == this.getRenderId()) {
			renderInvCuboid(renderer, block, 2 / f, 0 / f, 8 / f, 14 / f, 16 / f, 8 / f, this.baseIcon);
			renderInvCuboid(renderer, block, 0 / f, 0 / f, 7 / f, 2 / f, 16 / f, 9 / f, this.sideIcon);
			renderInvCuboid(renderer, block, 14 / f, 0 / f, 7 / f, 16 / f, 16 / f, 9 / f, this.sideIcon);
		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int side = world.getBlockMetadata(x, y, z) & 3;
		this.baseIcon = block.getBlockTextureFromSide(0);
		this.sideIcon = block.getBlockTextureFromSide(2);
		float f = 16.0F;

		if (modelId == this.getRenderId()) {
			if (side == 0) {
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(2 / f, 0 / f, 15 / f, 14 / f, 16 / f, 15 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.sideIcon);
				block.setBlockBounds(0 / f, 0 / f, 14 / f, 2 / f, 16 / f, 16 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.sideIcon);
				block.setBlockBounds(14 / f, 0 / f, 14 / f, 16 / f, 16 / f, 16 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

			} else if (side == 1) {
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(1 / f, 0 / f, 2 / f, 1 / f, 16 / f, 14 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.sideIcon);
				block.setBlockBounds(0 / f, 0 / f, 0 / f, 2 / f, 16 / f, 2 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.sideIcon);
				block.setBlockBounds(0 / f, 0 / f, 14 / f, 2 / f, 16 / f, 16 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

			} else if (side == 2) {
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(2 / f, 0 / f, 1 / f, 14 / f, 16 / f, 1 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.sideIcon);
				block.setBlockBounds(0 / f, 0 / f, 0 / f, 2 / f, 16 / f, 2 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.sideIcon);
				block.setBlockBounds(14 / f, 0 / f, 0 / f, 16 / f, 16 / f, 2 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

			} else if (side == 3) {
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(15 / f, 0 / f, 2 / f, 15 / f, 16 / f, 14 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.sideIcon);
				block.setBlockBounds(14 / f, 0 / f, 0 / f, 16 / f, 16 / f, 2 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.sideIcon);
				block.setBlockBounds(14 / f, 0 / f, 14 / f, 16 / f, 16 / f, 16 / f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}

			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int i) {
		return true;
	}

	@Override
	public int getRenderId() {
		return DCsIronChain.modelHashigo;
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX,
			float maxY, float maxZ, IIcon icon) {
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setRenderBoundsFromBlock(block);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}
}
