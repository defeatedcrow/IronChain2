package defeatedcrow.ironchain.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemLifejacket extends ItemArmor {

	public ItemLifejacket(ItemArmor.ArmorMaterial material, int par3, int par4) {
		super(material, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("dcironchain:lifejacket");
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {

		return "dcironchain:textures/armor/lifejacket.png";
	}

}
