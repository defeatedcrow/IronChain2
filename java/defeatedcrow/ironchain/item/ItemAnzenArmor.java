package defeatedcrow.ironchain.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemAnzenArmor extends ItemArmor{
	
	private static final String[] textype = new String[] {"anzen_met","sagyougi","anzen_belt","anzen_boots"};
	
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
		
		return slot == 2 ? "dcironchain:textures/armor/sagyougi_2.png" : "dcironchain:textures/armor/sagyougi.png";
	}
	
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        return DCsIronChain.proxy.getArmorModel(armorSlot);
    }
	
	/* 以下は安全ベルトのみの機能 */
//	@Override
//	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
//	{
//		if (itemstack == null || itemstack.getItem() != DCsIronChain.anzenBelt) return itemstack;
//		
//		int meta = itemstack.getItemDamage();
//		NBTTagCompound nbt = itemstack.getTagCompound();
//		
//		if (!world.isRemote && entityplayer instanceof EntityPlayerMP)
//		{
//			EntityPlayerMP thisPlayer = (EntityPlayerMP) entityplayer;
//			
//			int posX = MathHelper.floor_double(thisPlayer.posX);
//			int posY = MathHelper.floor_double(thisPlayer.posY);
//			int posZ = MathHelper.floor_double(thisPlayer.posZ);
//			int dim = world.provider.dimensionId;
//			String dimName = world.provider.getDimensionName();
//			
//			if (nbt == null)
//			{
//				NBTTagCompound nbt1 = new NBTTagCompound();
//				nbt1.setInteger("DCIanzenX", posX);
//				nbt1.setInteger("DCIanzenY", posY);
//				nbt1.setInteger("DCIanzenZ", posZ);
//				nbt1.setInteger("DCIanzenDim", dim);
//				nbt1.setString("DCIanzenDimName", dimName);
//				itemstack.setTagCompound(nbt1);
//			}
//			else
//			{
//				nbt.setInteger("DCIanzenX", posX);
//				nbt.setInteger("DCIanzenY", posY);
//				nbt.setInteger("DCIanzenZ", posZ);
//				nbt.setInteger("DCIanzenDim", dim);
//				nbt.setString("DCIanzenDimName", dimName);
//			}
//			
//			world.playSoundAtEntity(thisPlayer, "random.pop", 0.4F, 1.8F);
//			thisPlayer.addChatMessage(new ChatComponentText("Registered pos : " + posX + ", " + posY + ", " + posZ));
//		}
//		return itemstack;
//	}
	
	@SideOnly(Side.CLIENT)
    //マウスオーバー時の表示情報
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(stack, player, list, par4);
		if (stack != null && stack.getItem() == DCsIronChain.anzenBelt)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			String s = "None";
			if (nbt != null)
			{
				int X = nbt.getInteger("DCIanzenX");
				int Y = nbt.getInteger("DCIanzenY");
				int Z = nbt.getInteger("DCIanzenZ");
				
				String D = "Unknown";
				if (nbt.hasKey("DCIanzenDimName"))
				{
					D = nbt.getString("DCIanzenDimName");
				}
				
				s = X + ", " + Y + ", " + Z + " / " + D;
			}
			list.add(new String("Registered pos : " + s));
		}
	}

}
