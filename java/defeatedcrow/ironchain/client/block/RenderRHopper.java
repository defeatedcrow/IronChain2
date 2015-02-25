package defeatedcrow.ironchain.client.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;

@SideOnly(Side.CLIENT)
public class RenderRHopper implements ISimpleBlockRenderingHandler{
	
	private IIcon baseIcon;
	private IIcon insideIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
		int meta = metadata;
		int side = meta & 7;
		this.baseIcon = block.getBlockTextureFromSide(0);
		this.insideIcon = block.getBlockTextureFromSide(1);
		
		if (modelID == this.getRenderId())
		{
			//bottom
			renderInvCuboid(renderer, block,  0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 5.0F/16.0F, 16.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  14.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F,  16.0F/16.0F,  5.0F/16.0F,  16.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  2.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 14.0F/16.0F,  5.0F/16.0F, 1.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  2.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 14.0F/16.0F,  5.0F/16.0F, 16.0F/16.0F,  this.baseIcon);
			//middle
			renderInvCuboid(renderer, block,  4.0F/16.0F, 6.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F,  12.0F/16.0F, 12.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  0.0F/16.0F, 5.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F,  6.0F/16.0F, 16.0F/16.0F,  this.baseIcon);
			//inside
			renderInvCuboid(renderer, block,  2.0F/16.0F, 4.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F,  5.0F/16.0F, 14.0F/16.0F,  this.insideIcon);
			//top
			renderInvCuboid(renderer, block,  6.0F/16.0F, 12.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 16.0F/16.0F, 10.0F/16.0F,  this.baseIcon);
		}
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		
		int meta = world.getBlockMetadata(x, y, z);
		int side = meta & 7;
		this.baseIcon = block.getBlockTextureFromSide(0);
		this.insideIcon = block.getBlockTextureFromSide(1);
		
		if (modelId == this.getRenderId())
		{
			//base
			renderer.setOverrideBlockTexture(this.baseIcon);
			block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 5.0F/16.0F, 16.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			renderer.setOverrideBlockTexture(this.baseIcon);
			block.setBlockBounds(14.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F,  16.0F/16.0F,  5.0F/16.0F,  16.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			renderer.setOverrideBlockTexture(this.baseIcon);
			block.setBlockBounds(2.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 14.0F/16.0F,  5.0F/16.0F, 2.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			renderer.setOverrideBlockTexture(this.baseIcon);
			block.setBlockBounds(2.0F/16.0F, 0.0F/16.0F, 14.0F/16.0F, 14.0F/16.0F,  5.0F/16.0F, 16.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			//middle
			renderer.setOverrideBlockTexture(this.baseIcon);
			block.setBlockBounds(4.0F/16.0F, 6.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F,  12.0F/16.0F, 12.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			renderer.setOverrideBlockTexture(this.baseIcon);
			block.setBlockBounds(0.0F/16.0F, 5.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F,  6.0F/16.0F, 16.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			//inside
			renderer.setOverrideBlockTexture(this.insideIcon);
			block.setBlockBounds(2.0F/16.0F, 4.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F,  5.0F/16.0F, 14.0F/16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			
			//top
			if (side == 0)//up +Y
			{
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(6.0F/16.0F, 12.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 16.0F/16.0F, 10.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (side == 1)//down -Y
			{
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(6.0F/16.0F, 12.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 16.0F/16.0F, 10.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (side == 2)//south -Z
			{
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(6.0F/16.0F, 8.0F/16.0F, 0.0F/16.0F, 10.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (side == 3)//north +Z
			{
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(6.0F/16.0F, 8.0F/16.0F, 12.0F/16.0F, 10.0F/16.0F, 12.0F/16.0F, 16.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (side == 4)//west -X
			{
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(0.0F/16.0F, 8.0F/16.0F, 6.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 10.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else if (side == 5)//east +X
			{
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(12.0F/16.0F, 8.0F/16.0F, 6.0F/16.0F, 16.0F/16.0F, 12.0F/16.0F, 10.0F/16.0F);
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
		
		return DCsIronChain.modelRHopper;
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
