package defeatedcrow.ironchain.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAnzenArmor extends ItemArmor{
	
	private static final String[] textype = new String[] {"anzen_met","sagyougi","sagyougi","anzen_boots"};
	
	public ItemAnzenArmor (ItemArmor.ArmorMaterial material, int par3, int par4) {
		super(material, par3, par4);
	}
	
	@Override
	public void onCreated(ItemStack par1Itemstack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onCreated(par1Itemstack, par2World, par3EntityPlayer);
		if(par1Itemstack.getItem() == DCsIronChain.anzenMet)
		{
			par1Itemstack.addEnchantment(Enchantment.blastProtection, 1);
		}
		if(par1Itemstack.getItem() == DCsIronChain.anzenBoots)
		{
			par1Itemstack.addEnchantment(Enchantment.featherFalling, 1);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("dcironchain:" + this.textype[this.armorType]);
	}
	
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		
		return "dcironchain:textures/armor/sagyougi.png";
	}

}
