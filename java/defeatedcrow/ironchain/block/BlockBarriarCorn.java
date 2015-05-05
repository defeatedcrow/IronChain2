package defeatedcrow.ironchain.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.block.tileentity.TileBarriarCorn;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBarriarCorn extends BlockContainer {
	
	@SideOnly(Side.CLIENT)
	private IIcon rodIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;
	
	public BlockBarriarCorn ()
	{
		super(Material.ground);
		this.setHardness(0.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeMetal);
	}
	
	public void setBlockBoundsForItemRender()
    {
        float f = 0.075F;
        float f2 = 0.625F;
        this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, f2, 1.0F - f);
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.5F, 1.0F);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
		if (entity == null || !(entity instanceof EntityPlayer))
		{
			super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		}
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }
	
	public int getRenderType()
    {
        return DCsIronChain.modelBarriar;
    }
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon (int par1, int par2)
	{
		return par1 == 0 ? this.blockIcon : (par1 == 1 ? this.rodIcon : this.sideIcon);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("dcironchain:barriarcorn");
		this.rodIcon = par1IconRegister.registerIcon("dcironchain:barriarcorn_rod");
		this.sideIcon = par1IconRegister.registerIcon("dcironchain:barriarcorn_side");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileBarriarCorn();
	}

}
