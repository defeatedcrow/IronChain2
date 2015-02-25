package defeatedcrow.ironchain.client.block;


import org.lwjgl.opengl.GL11;

import defeatedcrow.ironchain.*;
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

@SideOnly(Side.CLIENT)
public class RenderFloodLight implements ISimpleBlockRenderingHandler{
	
	private IIcon baseIcon;
	private IIcon glowIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
		int meta = metadata;
		this.baseIcon = DCsIronChain.floodLight.getBlockTextureFromSide(0);
		this.glowIcon = DCsIronChain.floodLight.getBlockTextureFromSide(1);
		
		if (modelID == this.getRenderId())
		{
			//base
			renderInvCuboid(renderer, block,  4.0F/16.0F, 4.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  7.0F/16.0F, 1.0F/16.0F, 7.0F/16.0F,  9.0F/16.0F,  4.0F/16.0F,  9.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  5.0F/16.0F, 0.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F,  2.0F/16.0F, 11.0F/16.0F,  this.baseIcon);
			//glow
			renderInvCuboid(renderer, block,  2.0F/16.0F, 2.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 14.0F/16.0F, 12.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  2.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F, 14.0F/16.0F, 14.0F/16.0F, 16.0F/16.0F,  this.baseIcon);
			renderInvCuboid(renderer, block,  3.0F/16.0F, 3.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 13.0F/16.0F, 16.0F/16.0F,  this.glowIcon);
		}
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		
		int meta = world.getBlockMetadata(x, y, z);
		this.baseIcon = DCsIronChain.floodLight.getBlockTextureFromSide(0);
		this.glowIcon = DCsIronChain.floodLight.getBlockTextureFromSide(1);
		
		if (modelId == this.getRenderId())
		{
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
		
		return DCsIronChain.modelFLight;
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
