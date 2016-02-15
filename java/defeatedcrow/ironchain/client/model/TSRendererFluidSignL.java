package defeatedcrow.ironchain.client.model;

import java.io.FileNotFoundException;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.block.tileentity.TileFluidSign;

public class TSRendererFluidSignL extends TileEntitySpecialRenderer {

	private static final ResourceLocation signTex = new ResourceLocation("dcironchain:textures/blocks/sign_banner.png");
	private static final ResourceLocation baseTex = new ResourceLocation("dcironchain:textures/blocks/sign_baseL.png");
	public static TSRendererFluidSignL renderer;

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
			j = -180.0F;
		if (l == 1)
			j = -90.0F;
		if (l == 2)
			j = 0.0F;
		if (l == 3)
			j = 90.0F;

		Tessellator tessellator = Tessellator.instance;

		Fluid flu = par0Tile.getFluid();
		String name = par0Tile.getFluidLocalisedName();
		String type = StatCollector.translateToLocal(par0Tile.getType());
		int amo = par0Tile.getAmount();
		String pass = null;
		String jp = DCsIronChain.JPsign ? "jp" : "us";
		if (par0Tile.isGas()) {
			pass = "dcironchain:textures/blocks/sign/gas_" + jp + ".png";
		}
		if (par0Tile.isHighTemp()) {
			pass = "dcironchain:textures/blocks/sign/temp_" + jp + ".png";
		}
		if (par0Tile.isFlammable()) {
			pass = "dcironchain:textures/blocks/sign/flam_" + jp + ".png";
		}
		if (par0Tile.isPoison()) {
			pass = "dcironchain:textures/blocks/sign/poison_" + jp + ".png";
		}
		if (par0Tile.isExplosive()) {
			pass = "dcironchain:textures/blocks/sign/exp_" + jp + ".png";
		}

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
			if (pass != null) {
				float u = 0.0F;
				float U = 1.0F;
				float v = 0.0F;
				float V = 1.0F;

				this.bindTexture(new ResourceLocation(pass));

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(1.0D, -0.875D, 0.425D, U, v);
				tessellator.addVertexWithUV(-1.0D, -0.875D, 0.425D, u, v);
				tessellator.addVertexWithUV(-1.0D, -0.375D, 0.425D, u, V);
				tessellator.addVertexWithUV(1.0D, -0.375D, 0.425D, U, V);
				tessellator.draw();

			} else {
				float u;
				float U;
				float v;
				float V;

				if (!DCsIronChain.signIcon) {
					String banner = "dcironchain:textures/blocks/sign_banner_" + jp + ".png";
					this.bindTexture(new ResourceLocation(banner));
					u = 0.0F;
					U = 1.0F;
					v = 0.0F;
					V = 1.0F;

					tessellator.startDrawingQuads();
					tessellator.setNormal(1.0F, 0.0F, 0.0F);
					tessellator.addVertexWithUV(1.0D, -0.875D, 0.425D, U, v);
					tessellator.addVertexWithUV(-1.0D, -0.875D, 0.425D, u, v);
					tessellator.addVertexWithUV(-1.0D, -0.375D, 0.425D, u, V);
					tessellator.addVertexWithUV(1.0D, -0.375D, 0.425D, U, V);
					tessellator.draw();

				} else {
					this.bindTexture(TextureMap.locationBlocksTexture);
					u = icon.getMinU();
					U = icon.getMaxU();
					v = icon.getMinV();
					V = icon.getMaxV();

					tessellator.startDrawingQuads();
					tessellator.setNormal(1.0F, 0.0F, 0.0F);
					tessellator.addVertexWithUV(-0.5D, -0.875D, 0.425D, U, v);
					tessellator.addVertexWithUV(-1.0D, -0.875D, 0.425D, u, v);
					tessellator.addVertexWithUV(-1.0D, -0.375D, 0.425D, u, V);
					tessellator.addVertexWithUV(-0.5D, -0.375D, 0.425D, U, V);
					tessellator.draw();

					tessellator.startDrawingQuads();
					tessellator.setNormal(1.0F, 0.0F, 0.0F);
					tessellator.addVertexWithUV(0.0D, -0.875D, 0.425D, U, v);
					tessellator.addVertexWithUV(-0.5D, -0.875D, 0.425D, u, v);
					tessellator.addVertexWithUV(-0.5D, -0.375D, 0.425D, u, V);
					tessellator.addVertexWithUV(0.0D, -0.375D, 0.425D, U, V);
					tessellator.draw();

					tessellator.startDrawingQuads();
					tessellator.setNormal(1.0F, 0.0F, 0.0F);
					tessellator.addVertexWithUV(0.5D, -0.875D, 0.425D, U, v);
					tessellator.addVertexWithUV(0.0D, -0.875D, 0.425D, u, v);
					tessellator.addVertexWithUV(0.0D, -0.375D, 0.425D, u, V);
					tessellator.addVertexWithUV(0.5D, -0.375D, 0.425D, U, V);
					tessellator.draw();

					tessellator.startDrawingQuads();
					tessellator.setNormal(1.0F, 0.0F, 0.0F);
					tessellator.addVertexWithUV(1.0D, -0.875D, 0.425D, U, v);
					tessellator.addVertexWithUV(0.5D, -0.875D, 0.425D, u, v);
					tessellator.addVertexWithUV(0.5D, -0.375D, 0.425D, u, V);
					tessellator.addVertexWithUV(1.0D, -0.375D, 0.425D, U, V);
					tessellator.draw();
				}

			}

			float u = 0.0F;
			float U = 1.0F;
			float v = 0.0F;
			float V = 1.0F;

			this.bindTexture(baseTex);

			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(1.0D, -0.875D, 0.43D, U, v);
			tessellator.addVertexWithUV(-1.0D, -0.875D, 0.43D, u, v);
			tessellator.addVertexWithUV(-1.0D, 0.875D, 0.43D, u, V);
			tessellator.addVertexWithUV(1.0D, 0.875D, 0.43D, U, V);
			tessellator.draw();

			this.bindTexture(new ResourceLocation("dcironchain:textures/blocks/anchorbolt.png"));

			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(1.0D, -0.375D, 0.425D, U, v);
			tessellator.addVertexWithUV(-1.0D, -0.375D, 0.425D, u, v);
			tessellator.addVertexWithUV(-1.0D, -0.355D, 0.425D, u, V);
			tessellator.addVertexWithUV(1.0D, -0.355D, 0.425D, U, V);
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(1.0D, -0.02D, 0.425D, U, v);
			tessellator.addVertexWithUV(-1.0D, -0.02D, 0.425D, u, v);
			tessellator.addVertexWithUV(-1.0D, -0.0D, 0.425D, u, V);
			tessellator.addVertexWithUV(1.0D, -0.0D, 0.425D, U, V);
			tessellator.draw();

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();

		}

		// font
		String tank = StatCollector.translateToLocal("dcs.fluidtype.signinfo");
		this.drawFluidString("Name:", par1, par2, par3, j, -0.65F, 0.5F, 0.375F);
		this.drawFluidString("Type:", par1, par2, par3, j, -0.65F, 0.25F, 0.375F);
		this.drawFluidString("Vol:", par1, par2, par3, j, -0.65F, 0.0F, 0.375F);
		this.drawFluidString(name, par1, par2, par3, j, 0.25F, 0.5F, 0.375F);
		this.drawFluidString(type, par1, par2, par3, j, 0.25F, 0.25F, 0.375F);
		this.drawFluidString(amo + " mB", par1, par2, par3, j, 0.25F, 0.0F, 0.375F);
		this.drawFluidString(tank, par1, par2, par3, j, 0.0F, 0.825F, 0.375F);

	}

	private void drawFluidString(String s, float x, float y, float z, float r, float fx, float fy, float fz) {

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(x + 0.5F, y + fy, z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(r, 0.0F, 1.0F, 0.0F);

		FontRenderer fontrenderer = this.func_147498_b();
		float f1 = 0.016F;
		float f2 = 0.02F;
		if (s.length() > 16)
			f1 = 0.013F;

		GL11.glTranslatef(fx, 0.0F, fz);
		GL11.glScalef(f1, f2, f1);
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
