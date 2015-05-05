package defeatedcrow.ironchain.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class ItemAltimeter extends Item {

	public ItemAltimeter() {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setUnlocalizedName("defeatedcrow.altimeter");
		this.setTextureName("dcironchain:altimeter");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = new TextureAlitmeter("dcironchain:altimeter");

		((TextureMap) par1IconRegister).setTextureEntry("dcironchain:altimeter", (TextureAtlasSprite) this.itemIcon);

	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		super.addInformation(item, player, list, par4);
		if (player != null) {
			int pos = MathHelper.floor_double(player.posY);
			list.add("Altitude: " + EnumChatFormatting.AQUA + pos);
		}
	}

}
