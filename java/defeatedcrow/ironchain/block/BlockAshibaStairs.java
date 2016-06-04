package defeatedcrow.ironchain.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
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

public class BlockAshibaStairs extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;

	public BlockAshibaStairs() {
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
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
		int m = world.getBlockMetadata(x, y, z) & 3;
		float f = 0.0625F;
		if (m == 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
			this.setBlockBounds(0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		} else if (m == 1) {
			this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
			this.setBlockBounds(0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		} else if (m == 2) {
			this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F);
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
			this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		} else {
			this.setBlockBounds(0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		}

		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void updateAnchorBounds(int par1) {
		float f2 = 0.0625F;
		float f1 = 0.9375F;

		if (par1 == 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f2, 1.0F);
		} else {
			this.setBlockBounds(0.0F, f1, 0.0F, 1.0F, 1.0F, 1.0F);
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
		return DCsIronChain.modelAshibaStair;
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
