package defeatedcrow.ironchain.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import defeatedcrow.ironchain.*;


//こちら側では、周囲に何か変化があった時のみチェックして消滅させる
public class BlockLightPart extends Block{
	
	@SideOnly(Side.CLIENT)
	private IIcon baseIcon;
	@SideOnly(Side.CLIENT)
	private IIcon glowIcon;
	
	public BlockLightPart ()
	{
		super(Material.circuits);
		this.setHardness(0.1F);
		this.setResistance(0.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setLightLevel(1.0F);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }

	@Override
	public int tickRate(World par1World)
    {
        return 10;
    }
	
	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
	
	@Override
	public boolean isAir(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		if (!par1World.isRemote)
        {
			if (!this.canBlockStay(par1World, par2, par3, par4))
	        {
	        	par1World.setBlockToAir(par2, par3, par4);
	        }
        }
		
    }
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		if (!this.canBlockStay(par1World, par2, par3, par4))
		{
			par1World.setBlockToAir(par2, par3, par4);
		}
		else if ((par3 - 2 > 0) && par1World.isAirBlock(par2, par3 - 1, par4) && par1World.getBlock(par2, par3 - 2, par4) != this)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);
			par1World.setBlock(par2, par3 - 1, par4, this, l, 2);
			par1World.setBlockToAir(par2, par3, par4);
		}
    }
	
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		return this.checkFloodLight(par1World, par2, par3, par4, l);
    }
	
	private boolean checkFloodLight (World world, int X, int Y, int Z, int meta)
	{
		boolean end = false;
		
		for(int i = 0; i < 21; i++)
		{
			Block ID = world.getBlock((X + getOffsetX(meta)), (Y + i), (Z + getOffsetZ(meta)));
			
			if (ID == DCsIronChain.floodLight && ((Y + i) < 255) && !end)
			{
				int l = world.getBlockMetadata((X + getOffsetX(meta)), (Y + i), (Z + getOffsetZ(meta)));
				if (l == meta) end = true;
			}
		}
		
		return end;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon (int par1, int par2)
	{
		return (DCsIronChain.visibleLight || DCsIronChain.debug) ? this.baseIcon : this.glowIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.baseIcon = par1IconRegister.registerIcon("dcironchain:floodlight_light");
		this.glowIcon = par1IconRegister.registerIcon("dcironchain:floodlight_air");
		
	}
	
	private int getOffsetX(int meta)
	{
		int i = MathHelper.clamp_int(0, meta, 3);
		return PlaneDir.getDir(i).getOpposite().offsetX * 3;
	}
	
	private int getOffsetZ(int meta)
	{
		int i = MathHelper.clamp_int(0, meta, 3);
		return PlaneDir.getDir(i).getOpposite().offsetZ * 3;
	}

}
