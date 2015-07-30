package defeatedcrow.ironchain.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.block.tileentity.TileFluidSign;
import defeatedcrow.ironchain.block.tileentity.TileFluidSignL;

public class BlockSignL extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon itemIcon;

	public BlockSignL() {
		super(Material.cloth);
		this.setHardness(0.2F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeCloth);
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public void setBlockBoundsForItemRender() {
		float f2 = 0.0625F;
		this.setBlockBounds(0.0F, 0.125F, 0.0F, 1.0F, 0.875F, f2);
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
		int i = par1 & 3;

		if (i == 0) {
			this.setBlockBounds(0.0F, 0.125F, f, 1.0F, 0.875F, 1.0F);
		}

		if (i == 2) {
			this.setBlockBounds(0.0F, 0.125F, 0.0F, 1.0F, 0.875F, f2);
		}

		if (i == 3) {
			this.setBlockBounds(f, 0.125F, 0.0F, 1.0F, 0.875F, 1.0F);
		}

		if (i == 1) {
			this.setBlockBounds(0.0F, 0.125F, 0.0F, f2, 0.875F, 1.0F);
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
		// switch (l) {
		// case 0:
		// m = 0;
		// break;
		// case 1:
		// m = 2;
		// break;
		// case 2:
		// m = 3;
		// break;
		// case 3:
		// m = 1;
		// break;
		// }

		par1World.setBlockMetadataWithNotify(par2, par3, par4, m, 3);
	}

	@Override
	public int getRenderType() {
		return DCsIronChain.modelSignL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? this.itemIcon : this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("dcironchain:sign_base");
		this.itemIcon = par1IconRegister.registerIcon("dcironchain:signitem_l");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileFluidSignL();
	}
}
