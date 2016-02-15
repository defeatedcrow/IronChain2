package defeatedcrow.ironchain.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLantern extends Item {

	public ItemLantern() {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setUnlocalizedName("defeatedcrow.lantern");
		this.setTextureName("dcironchain:lantern");
	}

}
