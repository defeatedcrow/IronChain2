package defeatedcrow.ironchain.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockAnchorBolt extends Block{
	
	public BlockAnchorBolt ()
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
        this.setBlockBounds(0.5F - f, 0.4F, 1.0F - f2, 0.5F + f, 0.4F + f, 1.0F);
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
        this.updateAnchorBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public void updateAnchorBounds(int par1)
    {
        float f = 0.075F;
        float f2 = 0.625F;
        
        if (par1 == 0)
        {
            this.setBlockBounds(0.5F - f, 0.0F, 1.0F - f2, 0.5F + f, 0.125F, 1.0F);
        }

        if (par1 == 1)
        {
            this.setBlockBounds(0.5F - f, 0.0F, 0.0F, 0.5F + f, 0.125F, f2);
        }

        if (par1 == 2)
        {
            this.setBlockBounds(1.0F - f2, 0.0F, 0.5F - f, 1.0F, 0.125F, 0.5F + f);
        }

        if (par1 == 3)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.5F - f, f2, 0.125F, 0.5F + f);
        }
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
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
		return par1World.isSideSolid(par2 - 1, par3, par4, ForgeDirection.EAST ) ||
	               par1World.isSideSolid(par2 + 1, par3, par4, ForgeDirection.WEST ) ||
	               par1World.isSideSolid(par2, par3, par4 - 1, ForgeDirection.SOUTH) ||
	               par1World.isSideSolid(par2, par3, par4 + 1, ForgeDirection.NORTH);
    }
	
	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int j1 = par9;
        
        if ((j1 == 0 || par5 == 0) && par1World.isSideSolid(par2, par3, par4 + 1, ForgeDirection.NORTH))
        {
            j1 = 0;
        }

        if ((j1 == 0 || par5 == 1) && par1World.isSideSolid(par2, par3, par4 - 1, ForgeDirection.SOUTH))
        {
            j1 = 1;
        }

        if ((j1 == 0 || par5 == 2) && par1World.isSideSolid(par2 + 1, par3, par4, ForgeDirection.WEST))
        {
            j1 = 2;
        }

        if ((j1 == 0 || par5 == 3) && par1World.isSideSolid(par2 - 1, par3, par4, ForgeDirection.EAST))
        {
            j1 = 3;
        }
        
        return j1;
    }
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        boolean flag = false;

        if (i1 == 0 && par1World.isSideSolid(par2, par3, par4 + 1, ForgeDirection.NORTH))
        {
            flag = true;
        }

        if (i1 == 1 && par1World.isSideSolid(par2, par3, par4 - 1, ForgeDirection.SOUTH))
        {
            flag = true;
        }

        if (i1 == 2 && par1World.isSideSolid(par2 + 1, par3, par4, ForgeDirection.WEST))
        {
            flag = true;
        }

        if (i1 == 3 && par1World.isSideSolid(par2 - 1, par3, par4, ForgeDirection.EAST))
        {
            flag = true;
        }

        if (!flag)
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, i1, 0);
            par1World.setBlockToAir(par2, par3, par4);
        }

        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
    }
	
	@Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
    {
        return true;
    }
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int l = 0;
        l = this.thisChainLength(par1World, par2, par3, par4);

        if (itemstack == null)
        {
        	if (l < 0)
        	{
        		return false;
        	}
        	else if (l < 65)
        	{
        		par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsIronChain.ironChain, l));
        		par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsIronChain.anchorBolt, 1));
        		if (l > 0)
        		{
        			par1World.playSoundAtEntity(par5EntityPlayer, "dcironchain:chain", 1.0F, 0.7F);
        		}
        		else
        		{
        			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.9F);
        		}
        		
        		for(int i = 0; i < (l + 1); i++)
        		{
        			par1World.setBlockToAir(par2, (par3 - l + i), par4);
        		}
        	}
        	return false;
        }
        else if (this.canPlace(itemstack))
        {
            Block placeID = Block.getBlockFromItem(itemstack.getItem());
            if (placeID == null) return false;
            int placeMeta = itemstack.getItemDamage();
            
            if ((l < 65) && par1World.isAirBlock(par2, (par3 - l - 1), par4))
            {
            	par1World.setBlock(par2, (par3 - l - 1), par4, placeID, placeMeta, 3);
            	par1World.playSoundAtEntity(par5EntityPlayer, "dcironchain:chain", 1.0F, 0.7F);
            	if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
                {
                    par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
            	return true;
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
	
    private boolean canPlace(ItemStack itemstack) {
		
		if (itemstack.getItem() == Item.getItemFromBlock(DCsIronChain.ironChain)) {
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
		
		for(int i = 1; i < 66; i++)
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
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("dcironchain:anchorbolt");
		
	}
}