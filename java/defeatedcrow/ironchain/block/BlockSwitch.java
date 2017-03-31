package defeatedcrow.ironchain.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;

public class BlockSwitch extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon baseIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;

	public BlockSwitch() {
		super(Material.ground);
		this.setHardness(0.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeMetal);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		float f = 0.0625F;
		this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.updateAnchorBounds(par1World.getBlockMetadata(par2, par3, par4), true);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.updateAnchorBounds(par1World.getBlockMetadata(par2, par3, par4), false);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.updateAnchorBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4), false);
	}

	public void updateAnchorBounds(int par1, boolean b) {
		float f = b ? 1.5F : 1.0F;
		float f2 = b ? 0.125F : 0.25F;

		if (par1 == 0) {
			this.setBlockBounds(0.0F, 0.0F, 1.0F - f2, 1.0F, f, 1.0F);
		}

		if (par1 == 2) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, f2);
		}

		if (par1 == 3) {
			this.setBlockBounds(1.0F - f2, 0.0F, 0.0F, 1.0F, f, 1.0F);
		}

		if (par1 == 1) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f2, f, 1.0F);
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
		int m = l;

		par1World.setBlockMetadataWithNotify(par2, par3, par4, m, 3);
	}

	@Override
	public int getRenderType() {
		return DCsIronChain.modelFence;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return (par1 == 0 || par1 == 1) ? this.baseIcon : this.sideIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("dcironchain:ashiba_side");
		this.sideIcon = par1IconRegister.registerIcon("dcironchain:ashiba_side");
		this.baseIcon = par1IconRegister.registerIcon("dcironchain:fence_front");
	}
}
