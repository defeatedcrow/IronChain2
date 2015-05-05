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
public class RenderBarriarCorn implements ISimpleBlockRenderingHandler {
	
	private IIcon baseIcon;
	private IIcon rodIcon;
	private IIcon sideIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
		int meta = metadata;
		this.baseIcon = block.getBlockTextureFromSide(0);
		this.rodIcon = block.getBlockTextureFromSide(1);
		
		if (modelID == this.getRenderId())
		{
			//bottom
			renderInvCuboid(renderer, block,  2.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 1.0F/16.0F, 14.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  5.0F/16.0F, 1.0F/16.0F, 5.0F/16.0F,  11.0F/16.0F,  5.0F/16.0F,  11.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  6.0F/16.0F, 5.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F,  9.0F/16.0F, 10.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  7.0F/16.0F, 9.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F,  12.0F/16.0F, 9.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  7.5F/16.0F, 12.0F/16.0F, 7.5F/16.0F, 8.5F/16.0F,  14.0F/16.0F, 8.5F/16.0F,  this.baseIcon);
		}
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		
		int meta = world.getBlockMetadata(x, y, z);
		this.baseIcon = block.getBlockTextureFromSide(0);
		this.rodIcon = block.getBlockTextureFromSide(1);
		this.sideIcon = block.getBlockTextureFromSide(0);
		
		if (modelId == this.getRenderId())
		{
			//base
			renderer.setOverrideBlockTexture(this.baseIcon);
			block.setBlockBounds(2.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 1.0F/16.0F, 14.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			boolean north = world.getBlock(x, y, z-1) == block;
			boolean south = world.getBlock(x, y, z+1) == block;
			boolean west = world.getBlock(x-1, y, z) == block;
			boolean east = world.getBlock(x+1, y, z) == block;
			boolean flag = false;
			
			if (south)
			{
				renderer.setOverrideBlockTexture(this.rodIcon);
				block.setBlockBounds(7.5F/16.0F, 13.0F/16.0F, 7.0F/16.0F, 8.5F/16.0F, 14.0F/16.0F, 16.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				flag = true;
			}
			if (north)
			{
				renderer.setOverrideBlockTexture(this.rodIcon);
				block.setBlockBounds(7.5F/16.0F, 13.0F/16.0F, 0.0F/16.0F, 8.5F/16.0F, 14.0F/16.0F, 7.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				flag = true;
			}
			if (east)
			{
				renderer.setOverrideBlockTexture(this.rodIcon);
				block.setBlockBounds(9.0F/16.0F, 13.0F/16.0F, 7.5F/16.0F, 16.0F/16.0F, 14.0F/16.0F, 8.5F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				flag = true;
			}
			if (west)
			{
				renderer.setOverrideBlockTexture(this.rodIcon);
				block.setBlockBounds(0.0F/16.0F, 13.0F/16.0F, 7.5F/16.0F, 7.0F/16.0F, 14.0F/16.0F, 8.5F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				flag = true;
			}
			if (flag)
			{
				renderer.setOverrideBlockTexture(this.rodIcon);
				block.setBlockBounds(7.0F/16.0F, 13.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 14.0F/16.0F, 9.0F/16.0F);
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
		
		return DCsIronChain.modelBarriar;
	}
	
	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, IIcon icon)
	{
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
