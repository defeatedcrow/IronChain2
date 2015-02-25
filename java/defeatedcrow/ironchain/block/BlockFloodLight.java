package defeatedcrow.ironchain.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import defeatedcrow.ironchain.*;
import defeatedcrow.ironchain.block.tileentity.TileFloodLight;

public class BlockFloodLight extends BlockContainer{
	
	@SideOnly(Side.CLIENT)
	private IIcon baseIcon;
	@SideOnly(Side.CLIENT)
	private IIcon glowIcon;
	
	public BlockFloodLight ()
	{
		super(Material.glass);
		this.setHardness(0.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setLightLevel(1.0F);
		this.setTickRandomly(true);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
	
	@Override
	public int getRenderType()
	{
		return DCsIronChain.modelFLight;
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
	
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	

	public int tickRate(World par1World)
    {
        return 5;
    }
	
	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
		//初期metaは0
		int h = this.setLight(par1World, par2, par3, par4, 0);
		if (h > 0 && h < 20)
    	{
    		par1World.setBlock((par2 + getOffsetX(0)), (par3 - h), (par4 + getOffsetZ(0)), DCsIronChain.DCLightPart, 0, 2);
    	}
		
		return 0;
    }
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        int[] next = {2, 3, 1, 0};

        if (itemstack == null)
        {
        	this.eraseLight(par1World, par2, par3, par4, meta);
        	par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F); //for debug
        	
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, next[meta], 3);
    		int h = this.setLight(par1World, par2, par3, par4, next[meta]);
    		if (h > 0 && h < 20)
        	{
        		par1World.setBlock((par2 + getOffsetX(next[meta])), (par3 - h), (par4 + getOffsetZ(next[meta])), DCsIronChain.DCLightPart, next[meta], 2);
        	}
        	return true;
        }
        else
        {
        	return false;
        }
    }
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
        	int meta = par1World.getBlockMetadata(par2, par3, par4);
        	int h = this.setLight(par1World, par2, par3, par4, meta);
        	if (h > 0 && h < 20)
        	{
        		par1World.setBlock((par2 + getOffsetX(meta)), (par3 - h), (par4 + getOffsetZ(meta)), DCsIronChain.DCLightPart, meta, 2);
        		if (DCsIronChain.debug) par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.click", 0.3F, 0.5F); //for debug
        	}
        	par1World.scheduleBlockUpdate(par2, par3, par4, this, 5);
        }   
    }
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		super.onNeighborBlockChange(par1World, par2, par3, par4, this);
		if (!par1World.isRemote)
        {
        	int meta = par1World.getBlockMetadata(par2, par3, par4);
        	int h = this.setLight(par1World, par2, par3, par4, meta);
        	if (h > 0 && h < 20)
        	{
        		par1World.setBlock((par2 + getOffsetX(meta)), (par3 - h), (par4 + getOffsetZ(meta)), DCsIronChain.DCLightPart, meta, 2);
        		if (DCsIronChain.debug) par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.click", 0.3F, 0.5F); //for debug
        	}
        	par1World.markBlockForUpdate(par2, par3, par4);
        }
    }
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
		this.eraseLight(par1World, par2, par3, par4, par6);
    }
	
	private int setLight (World world, int X, int Y, int Z, int meta)
	{
		int height = -1;
		boolean end = false;
		boolean success = false;
		
		for(int i = 0; i < 21; i++)
		{
			if (world.isAirBlock((X + getOffsetX(meta)), (Y - i), (Z + getOffsetZ(meta))) && (Y - i) > 0 && !end)
			{
				height++;
			}
			else
			{
				end = true;
			}
		}
		
		if (end && (height > 0) && (height < 20))
		{
			Block ID = world.getBlock((X + getOffsetX(meta)), (Y- height - 1), (Z + getOffsetZ(meta)));
			if (ID == DCsIronChain.DCLightPart || DCsIronChain.notUseLight)
			{
				height = -1;
			}
		}
		
		return height;
	}
	
	private void eraseLight (World world, int X, int Y, int Z, int meta)
	{
		for(int i = 0; i < 21; i++)
		{
			Block ID = world.getBlock((X + getOffsetX(meta)), (Y - i), (Z + getOffsetZ(meta)));
			if (ID == DCsIronChain.DCLightPart)
			{
				world.setBlockToAir((X + getOffsetX(meta)), (Y - i), (Z + getOffsetZ(meta)));
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon (int par1, int par2)
	{
		return par1 == 0 ? this.baseIcon : this.glowIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.glowIcon = par1IconRegister.registerIcon("dcironchain:floodlight_glow");
		this.baseIcon = par1IconRegister.registerIcon("dcironchain:anchorbolt");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileFloodLight();
	}
	
	private int getOffsetX(int meta)
	{
		int i = MathHelper.clamp_int(0, meta, 3);
		return PlaneDir.getDir(i).offsetX * 3;
	}
	
	private int getOffsetZ(int meta)
	{
		int i = MathHelper.clamp_int(0, meta, 3);
		return PlaneDir.getDir(i).offsetZ * 3;
	}

}
