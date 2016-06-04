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

public class BlockAshiba extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;

	public BlockAshiba() {
		super(Material.clay);
		this.setHardness(0.2F);
		this.setResistance(15.0F);
		this.setStepSound(Block.soundTypeMetal);
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public void setBlockBoundsForItemRender() {
		float f2 = 0.0625F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.updateCollisionBounds(par1World.getBlockMetadata(par2, par3, par4));
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.updateAnchorBounds(par1World.getBlockMetadata(par2, par3, par4));
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.updateAnchorBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void updateAnchorBounds(int par1) {
		if (par1 == 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	public void updateCollisionBounds(int par1) {
		float f = 0.0625F;
		if (par1 == 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 1.0F - f, 0.0F, 1.0F, 1.0F, 1.0F);
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
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7,
			float par8, int par9) {
		if (par7 <= 0.5F || par7 == 1.0F) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int getRenderType() {
		return DCsIronChain.modelAshiba;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return (par1 == 0 || par1 == 1) ? this.blockIcon : this.sideIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("dcironchain:ashiba_top");
		this.sideIcon = par1IconRegister.registerIcon("dcironchain:ashiba_side");
	}
}
