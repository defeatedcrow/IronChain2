package defeatedcrow.ironchain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabIChain extends CreativeTabs {
	
	//クリエイティブタブのアイコン画像や名称の登録クラス
	public CreativeTabIChain(String type)
	{
		super(type);
	}
 
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "IronChain";
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(DCsIronChain.ironChain);
	}
	

}
