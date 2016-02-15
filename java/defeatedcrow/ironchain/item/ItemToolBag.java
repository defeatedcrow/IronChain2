package defeatedcrow.ironchain.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;

public class ItemToolBag extends ItemArmor {

	public ItemToolBag(ItemArmor.ArmorMaterial material, int par3, int par4) {
		super(material, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("dcironchain:toolbag");
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {

		return "dcironchain:textures/armor/toolbag.png";
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		player.openGui(DCsIronChain.instance, DCsIronChain.guiIdToolBag, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return itemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		super.addInformation(item, player, list, par4);
		if (!item.hasTagCompound()) {
			item.setTagCompound(new NBTTagCompound());
			item.getTagCompound().setTag("Items", new NBTTagList());
		}
		NBTTagList tags = (NBTTagList) item.getTagCompound().getTag("Items");
		int count = tags.tagCount();
		String s = "Item count : " + count;
		list.add(s);
	}

	@Override
	@SideOnly(Side.CLIENT)
	// エフェクト
	public boolean hasEffect(ItemStack item) {
		if (!item.hasTagCompound()) {
			item.setTagCompound(new NBTTagCompound());
			item.getTagCompound().setTag("Items", new NBTTagList());
		}
		NBTTagList tags = (NBTTagList) item.getTagCompound().getTag("Items");
		int count = tags.tagCount();
		return count > 0;
	}

}
