package defeatedcrow.ironchain.item;

import defeatedcrow.ironchain.IronChainLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.MathHelper;

public class TextureAlitmeter extends TextureAtlasSprite {

	private double field_94239_h;
	private double field_94240_i;

	protected TextureAlitmeter(String string) {
		super(string);
	}

	@Override
	public void updateAnimation() {
		if (!this.framesTextureData.isEmpty()) {
			Minecraft minecraft = Minecraft.getMinecraft();
			double d0 = 0.0D;

			if (minecraft.theWorld != null && minecraft.thePlayer != null) {
				// float f = minecraft.theWorld.getCelestialAngle(1.0F);
				double pos = minecraft.thePlayer.posY;
				float f = (float) (pos / 128.0F);
				d0 = f;
			}

			double d1;

			for (d1 = d0 - this.field_94239_h; d1 < -0.5D; ++d1) {
				;
			}

			while (d1 >= 0.5D) {
				--d1;
			}

			if (d1 < -1.0D) {
				d1 = -1.0D;
			}

			if (d1 > 1.0D) {
				d1 = 1.0D;
			}

			this.field_94240_i += d1 * 0.1D;
			this.field_94240_i *= 0.8D;
			this.field_94239_h += this.field_94240_i;
			int i;

			for (i = (int) ((this.field_94239_h + 1.0D) * this.framesTextureData.size())
					% this.framesTextureData.size(); i < 0; i = (i + this.framesTextureData.size())
					% this.framesTextureData.size()) {
				;
			}

			if (i != this.frameCounter) {
				this.frameCounter = i;
				TextureUtil.uploadTextureMipmap((int[][]) this.framesTextureData.get(this.frameCounter), this.width,
						this.height, this.originX, this.originY, false, false);
			}
		}
	}

}
