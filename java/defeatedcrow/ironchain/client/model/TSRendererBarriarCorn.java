package defeatedcrow.ironchain.client.model;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.block.tileentity.TileBarriarCorn;

@SideOnly(Side.CLIENT)
public class TSRendererBarriarCorn extends TileEntitySpecialRenderer
{
	public static TSRendererBarriarCorn boxRenderer;
	
	public void renderTileEntityBottleAt(TileBarriarCorn par1Tile, double par2, double par4, double par6, float par8)
    {
        this.setRotation(par1Tile, (float)par2, (float)par4, (float)par6);
    }
	
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer)
    {
        super.func_147497_a(par1TileEntityRenderer);
        boxRenderer = this;
    }
	
	public void setRotation(TileBarriarCorn tile, float par1, float par2, float par3)
    {
		byte meta = (byte) tile.getBlockMetadata();
		
		Tessellator tessellator = Tessellator.instance;
		
		GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
        GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 0.5F, (float)par3 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
        
        GL11.glPolygonOffset(-1, -1);
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
        
        IIcon iicon = DCsIronChain.barriarCorn.getIcon(2, 0);
        float u = iicon.getMinU();
        float U = iicon.getMaxU();
        float v = iicon.getMinV();
        float V = iicon.getMaxV();
        this.bindTexture(TextureMap.locationBlocksTexture);
        
        double x = -0.225D;
        double y = 0.425D;
        double z = -0.225D;
        double X = 0.225D;
        double Y = -0.5D;
        double Z = 0.225D;
        
//        tessellator.startDrawingQuads();
//        tessellator.setNormal(1.0F, 0.0F, 0.0F);
//        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)f14, (double)f5);
//        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)f15, (double)f5);
//        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)f15, (double)f4);
//        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)f14, (double)f4);
//        tessellator.draw();
    	
    	tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(x, y, z, (double)u, (double)V);
        tessellator.addVertexWithUV(X, y, z, (double)U, (double)V);
        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)U, (double)v);
        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)u, (double)v);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(X, y, Z, (double)u, (double)V);
        tessellator.addVertexWithUV(x, y, Z, (double)U, (double)V);
        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)U, (double)v);
        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)u, (double)v);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(x, y, z, (double)U, (double)V);
        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)U, (double)v);
        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)u, (double)v);
        tessellator.addVertexWithUV(x, y, Z, (double)u, (double)V);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)u, (double)v);
        tessellator.addVertexWithUV(X, y, z, (double)u, (double)V);
        tessellator.addVertexWithUV(X, y, Z, (double)U, (double)V);
        tessellator.addVertexWithUV(0.0D, Y, 0.0D, (double)U, (double)v);
        tessellator.draw();
        
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
        GL11.glPopMatrix();
    }
	
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityBottleAt((TileBarriarCorn)par1TileEntity, par2, par4, par6, par8);
    }
}
