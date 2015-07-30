package defeatedcrow.ironchain.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemSignM extends ItemBlock {

	@SideOnly(Side.CLIENT)
	private IIcon iconItemType[];

	private String[] name = { "hijouguti", "bio", "electric", "radiation" };

	public ItemSignM(Block block) {
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int j = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, this.name.length);
		return super.getUnlocalizedName() + "_" + name[j];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < this.name.length; i++) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = (par1 & 3);
		return this.iconItemType[j];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.iconItemType = new IIcon[this.name.length];

		for (int i = 0; i < this.name.length; ++i) {
			this.iconItemType[i] = par1IconRegister.registerIcon("dcironchain:sign/" + name[i]);
		}
	}

}
