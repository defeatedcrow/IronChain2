package defeatedcrow.ironchain.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemKyatatu extends ItemBlock{
	
	public ItemKyatatu(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

}
