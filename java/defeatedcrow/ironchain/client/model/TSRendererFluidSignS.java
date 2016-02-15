package defeatedcrow.ironchain.client.model;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import defeatedcrow.ironchain.block.tileentity.TileFloodLight;
import defeatedcrow.ironchain.block.tileentity.TileFluidSign;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class TSRendererFluidSignS extends TileEntitySpecialRenderer {

	private static final ResourceLocation lightTex = new ResourceLocation("dcironchain:textures/blocks/sign_base.png");
	public static TSRendererFluidSignS renderer;

	public void renderTileEntitySignAt(TileFluidSign par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		renderer = this;
	}

	public void setRotation(TileFluidSign par0Tile, float par1, float par2, float par3) {
		byte l = (byte) par0Tile.getBlockMetadata();
		float j = 0;
		if (l == 0)
			j = 180.0F;
		if (l == 1)
			j = -90.0F;
		if (l == 2)
			j = 0.0F;
		if (l == 3)
			j = 90.0F;

		Tessellator tessellator = Tessellator.instance;

		Fluid flu = par0Tile.getFluid();
		String name = par0Tile.getFluidLocalisedName();
		int amo = par0Tile.getAmount();
		if (flu != null) {
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
			GL11.glTranslatef(par1 + 0.5F, par2 + 0.5F, par3 + 0.5F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);

			IIcon icon = flu.getIcon();
			if (icon != null) {
				float u = icon.getMinU();
				float U = icon.getMaxU();
				float v = icon.getMinV();
				float V = icon.getMaxV();

				int col = flu.getColor();
				if (col != 0xFFFFFF) {
					float r = (col & 0xFF0000) * 1.2F / 0xFF0000;
					float g = (col & 0x00FF00) * 1.2F / 0x00FF00;
					float b = (col & 0x0000FF) * 1.2F / 0x0000FF;
					GL11.glColor4f(r, g, b, 1.0F);
				}

				this.bindTexture(TextureMap.locationBlocksTexture);
				double fz = (l & 2) == 0 ? 0.0D : -2.0D;

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(0.125D, -0.35D, 0.425D, u, V);
				tessellator.addVertexWithUV(-0.125D, -0.35D, 0.425D, U, V);
				tessellator.addVertexWithUV(-0.125D, -0.1D, 0.425D, U, v);
				tessellator.addVertexWithUV(0.125D, -0.1D, 0.425D, u, v);
				tessellator.draw();
			}

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();

		}

		// font
		this.drawFluidString(name, par1, par2, par3, j, 0.0F, 0.675F, 0.375F);
		this.drawFluidString(amo + " mB", par1, par2, par3, j, 0.0F, 0.5F, 0.375F);

	}

	private void drawFluidString(String s, float x, float y, float z, float r, float fx, float fy, float fz) {

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(x + 0.5F, y + fy, z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(r, 0.0F, 1.0F, 0.0F);

		FontRenderer fontrenderer = this.func_147498_b();
		float f1 = 0.67F;
		float f2 = 0.025F * f1;

		GL11.glTranslatef(fx, 0.0F, fz);
		GL11.glScalef(f2, f2, f2);
		GL11.glNormal3f(0.0F, 0.0F, -1.0F * f2);
		GL11.glDepthMask(false);
		byte b0 = 0;

		fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 5, b0);

		GL11.glDepthMask(true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySignAt((TileFluidSign) par1TileEntity, par2, par4, par6, par8);
	}

}
