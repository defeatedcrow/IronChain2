package defeatedcrow.ironchain.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;

public class BlockSignM extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon[] markIcon;

	private String[] mark = { "hijouguti", "bio", "electric", "radiation" };

	public BlockSignM() {
		super(Material.cloth);
		this.setHardness(0.2F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeCloth);
	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 3;
	}

	@Override
	public void setBlockBoundsForItemRender() {
		float f2 = 0.0625F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f2);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.updateAnchorBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void updateAnchorBounds(int par1) {
		float f = 1.0F - 0.0625F;
		float f2 = 0.0625F;
		int i = par1 & 12;

		if (i == 0) {
			this.setBlockBounds(0.0F, 0.0F, f, 1.0F, 1.0F, 1.0F);
		}

		if (i == 4) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f2);
		}

		if (i == 8) {
			this.setBlockBounds(f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		if (i == 12) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f2, 1.0F, 1.0F);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int m = par6ItemStack.getItemDamage();
		int j1 = m & 12;
		int i = m & 3;

		if (l == 0) {
			j1 = 0;
		}

		if (l == 2) {
			j1 = 4;
		}

		if (l == 3) {
			j1 = 8;
		}

		if (l == 1) {
			j1 = 12;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, i | j1, 3);
	}

	@Override
	public int getRenderType() {
		return DCsIronChain.modelSignM;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2 & 3;
		return par1 == 0 ? this.blockIcon : this.markIcon[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("dcironchain:sign_base");
		this.markIcon = new IIcon[this.mark.length];
		for (int i = 0; i < this.mark.length; i++) {
			markIcon[i] = par1IconRegister.registerIcon("dcironchain:sign/" + mark[i]);
		}

	}
}
