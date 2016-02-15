package defeatedcrow.ironchain.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiToolBag extends GuiContainer {
	private static final ResourceLocation texture = new ResourceLocation("dcironchain:textures/gui/toolbaggui.png");

	public GuiToolBag(InventoryPlayer inventoryPlayer, boolean wear) {
		super(new ContainerToolBag(inventoryPlayer, wear));
	}

	/*
	 * ChestとかInventoryとか文字を描画する
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int z) {
		String s = I18n.format("Tool Bag Inventory", new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj
				.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	/*
	 * 背景の描画
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
