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
import defeatedcrow.ironchain.block.BlockGuardFence;

@SideOnly(Side.CLIENT)
public class RenderFence implements ISimpleBlockRenderingHandler {

	private IIcon baseIcon;
	private IIcon sideIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata & 3;
		this.baseIcon = block.getBlockTextureFromSide(0);
		this.sideIcon = block.getBlockTextureFromSide(2);
		float f = 16.0F;

		if (modelID == this.getRenderId()) {
			renderInvCuboid(renderer, block, 2F, 0F, 8F, 14F, 16F, 8F, this.baseIcon);
			renderInvCuboid(renderer, block, 0F, 0F, 7F, 1F, 16F, 9F, this.sideIcon);
			renderInvCuboid(renderer, block, 15F, 0F, 7F, 16F, 16F, 9F, this.sideIcon);
			renderInvCuboid(renderer, block, 0F, 16F, 7F, 16F, 17F, 9F, this.sideIcon);
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
			boolean upper = world.isAirBlock(x, y + 1, z);
			boolean under = world.isAirBlock(x, y - 1, z);
			if (side == 0) {
				renderStandard(renderer, block, x, y, z, 2, 0, 15, 14, 16, 15, baseIcon);

				if (upper) {
					renderStandard(renderer, block, x, y, z, 0, 16, 14, 16, 17, 16, sideIcon);
				}

				if (under) {
					renderStandard(renderer, block, x, y, z, 0, -1, 14, 16, 0, 16, sideIcon);
				}

				renderStandard(renderer, block, x, y, z, 0, 0, 14, 1, 16, 16, sideIcon);
				renderStandard(renderer, block, x, y, z, 15, 0, 14, 16, 16, 16, sideIcon);

			} else if (side == 1) {
				renderStandard(renderer, block, x, y, z, 1, 0, 2, 1, 16, 14, baseIcon);
				if (upper) {
					renderStandard(renderer, block, x, y, z, 0, 16, 0, 2, 17, 16, sideIcon);
				}

				if (under) {
					renderStandard(renderer, block, x, y, z, 0, -1, 0, 2, 0, 16, sideIcon);
				}

				renderStandard(renderer, block, x, y, z, 0, 0, 0, 2, 16, 1, sideIcon);
				renderStandard(renderer, block, x, y, z, 0, 0, 15, 2, 16, 16, sideIcon);

			} else if (side == 2) {
				renderStandard(renderer, block, x, y, z, 2, 0, 1, 14, 16, 1, baseIcon);
				if (upper) {
					renderStandard(renderer, block, x, y, z, 0, 16, 0, 16, 17, 2, sideIcon);
				}

				if (under) {
					renderStandard(renderer, block, x, y, z, 0, -1, 0, 16, 0, 2, sideIcon);
				}

				renderStandard(renderer, block, x, y, z, 0, 0, 0, 1, 16, 2, sideIcon);
				renderStandard(renderer, block, x, y, z, 15, 0, 0, 16, 16, 2, sideIcon);

			} else if (side == 3) {
				renderStandard(renderer, block, x, y, z, 15, 0, 2, 15, 16, 14, baseIcon);
				if (upper) {
					renderStandard(renderer, block, x, y, z, 14, 16, 0, 16, 17, 16, sideIcon);
				}

				if (under) {
					renderStandard(renderer, block, x, y, z, 14, -1, 0, 16, 0, 16, sideIcon);
				}

				renderStandard(renderer, block, x, y, z, 14, 0, 0, 16, 16, 1, sideIcon);
				renderStandard(renderer, block, x, y, z, 14, 0, 15, 16, 16, 16, sideIcon);
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
		return DCsIronChain.modelFence;
	}

	private void renderStandard(RenderBlocks renderer, Block block, int x, int y, int z, int mX, int mY, int mZ,
			int xX, int xY, int xZ, IIcon tex) {
		renderer.setOverrideBlockTexture(tex);
		block.setBlockBounds(mX / 16F, mY / 16F, mZ / 16F, xX / 16F, xY / 16F, xZ / 16F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX,
			float maxY, float maxZ, IIcon icon) {
		minX /= 16.0F;
		minY /= 16.0F;
		minZ /= 16.0F;
		maxX /= 16.0F;
		maxY /= 16.0F;
		maxZ /= 16.0F;
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
