package defeatedcrow.ironchain.block;

import net.minecraft.block.Block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockIronChain extends Block{
	
	public BlockIronChain()
	{
		super(Material.iron);
		this.setHardness(0.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeMetal);
		float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}
	
	/* 当たり判定と表示 */
	@Override
	public void setBlockBoundsForItemRender()
    {
		float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
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
		float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public int getRenderType()
    {
        return 1;
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("dcironchain:blockchain");
		
	}
	
	/* 右クリック動作部分 */
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        
        int l = 0;
        l = this.thisChainLength(par1World, par2, par3, par4);

        if (itemstack == null)
        {
        	if (l < 1)
        	{
        		return false;
        	}
        	else if (l < 65)
        	{
        		par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsIronChain.ironChain, l));
        		par1World.playSoundAtEntity(par5EntityPlayer, "dcironchain:chain", 1.0F, 0.7F);
        		for(int i = 1; i < (l + 1); i++)
        		{
        			par1World.setBlockToAir(par2, (par3 - l + i), par4);
        		}
        		return true;
        	}
        	else
        	{
        		return false;
        	}
        }
        else if (this.canPlace(itemstack))
        {
            Item place = itemstack.getItem();
            Block placeID;
            int placeMeta = itemstack.getItemDamage();
            
            if (place instanceof ItemBlock){
            	placeID = Block.getBlockFromItem(place);
            }
            else {
            	return false;
            }
            
            if (l < 0)
            {
            	return false;
            }
            else if ((l < 65) && par1World.isAirBlock(par2, (par3 - l), par4))
            {
            	par1World.setBlock(par2, (par3 - l), par4, placeID, placeMeta, 3);
            	par1World.playSoundAtEntity(par5EntityPlayer, "dcironchain:chain", 1.0F, 0.7F);
            	if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
                {
                    par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
            	return true;
            }
            else
            {
            	return true;
            }
            
            }
        else
        {
        	Item place = itemstack.getItem();
            Block block;
            int placeMeta = itemstack.getItemDamage();
            
            if (place instanceof ItemBlock){
            	block = Block.getBlockFromItem(place);
            }
            else {
            	return false;
            }
        	
        	if (l < 0)
            {
            	return false;
            }
            else if ((l < 65) && par1World.isAirBlock(par2, (par3 - l), par4))
            {
            	if (block != null)
            	{
                	if(block.canBlockStay(par1World, par2, (par3 - l), par4))
                	{
                		BlockSand.fallInstantly = true;
                		par1World.setBlock(par2, (par3 - l), par4, block, itemstack.getItemDamage(), 3);
                		BlockSand.fallInstantly = false;
                    	par1World.playSoundAtEntity(par5EntityPlayer, "dcironchain:chain", 1.0F, 0.7F);
                    	if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
                        {
                            par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
                        }
                    	return true;
                	}
                	else
                	{
                		return true;
                	}
            	}
            	else
            	{
            		return false;
            	}
            }
            else
            {
            	return false;
            }
        	
        	
        }
    }
	
	public boolean canPlace(ItemStack itemstack) {
		
		if (itemstack.getItem() == Item.getItemFromBlock(this)) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private int thisChainLength (World world, int x, int y, int z)
	{
		int l = 0;
		boolean end = false;
		
		for(int i = 0; i < 65; i++)
		{
			if ((world.getBlock(x, (y - i), z) == DCsIronChain.ironChain) && ((y - i) > 0) && !end)
			{
				++l;
			}
			else
			{
				end = true;
			}
		}
		
		
		return l;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		ForgeDirection dir = ForgeDirection.DOWN;
		if ((par1World.getBlock(par2, par3 + 1, par4) != DCsIronChain.ironChain) && (par1World.getBlock(par2, par3 + 1, par4) != DCsIronChain.anchorBolt) && !par1World.isSideSolid(par2, par3 + 1, par4, dir))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
	
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
		ForgeDirection dir = ForgeDirection.DOWN;
		if (par1World.isAirBlock(par2, par3 + 1, par4) || par1World.getBlock(par2, par3 + 1, par4) == null) return false;
		return ((par1World.getBlock(par2, par3 + 1, par4) == DCsIronChain.ironChain) || (par1World.getBlock(par2, par3 + 1, par4) == DCsIronChain.anchorBolt) || par1World.isSideSolid(par2, par3 + 1, par4, dir));
    }
	
	@Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
    {
        return true;
    }

}
