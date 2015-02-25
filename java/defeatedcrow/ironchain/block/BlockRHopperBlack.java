package defeatedcrow.ironchain.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.block.tileentity.*;

public class BlockRHopperBlack extends BlockRHopper{
	
	private final Random rand = new Random();
	@SideOnly(Side.CLIENT)
	private IIcon baseIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;
	@SideOnly(Side.CLIENT)
	private IIcon insideIcon;
	
	public boolean isReverse = false;
	
	private final int[] MODE_NEXT = {1, 2, 3, 4, 5, 0};

	public BlockRHopperBlack(boolean flag) {
		super();
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.isReverse = flag;
	}
	
	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int j1 = ForgeDirection.OPPOSITES[par5];

        if (j1 == 0 && this.isReverse)
        {
            j1 = 1;
        }
        
        if (j1 == 1 && !this.isReverse)
        {
        	j1 = 0;
        }

        return j1;
    }
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		boolean flag = false;
		if (this.isReverse)
		{
			TileRHopperBlack tile = (TileRHopperBlack)par1World.getTileEntity(par2, par3, par4);
			
			if (tile != null && !par1World.isRemote)
			{
				if (par5EntityPlayer.isSneaking())
				{
					short mode = tile.getMode();
					mode = (short) MathHelper.clamp_int(0, mode, 5);
					int next = MODE_NEXT[mode];
					tile.setMode((byte)next);
					par5EntityPlayer.addChatMessage(new ChatComponentText("Absorption area : " + (next*2 + 1) + "x" + (next*2 + 1)));
					flag = true;
				}
				else
				{
					par5EntityPlayer.openGui(DCsIronChain.instance, DCsIronChain.instance.guiIdRHopper, par1World, par2, par3, par4);
					flag = true;
				}
			}
			else
			{
				flag = true;
			}
			return flag;
		}
		else
		{
			TilePositiveHopperBlack tile = (TilePositiveHopperBlack)par1World.getTileEntity(par2, par3, par4);
			
			if (tile != null && !par1World.isRemote)
			{
				if (tile != null && !par1World.isRemote)
				{
					if (par5EntityPlayer.isSneaking())
					{
						short mode = tile.getMode();
						mode = (short) MathHelper.clamp_int(0, mode, 5);
						int next = MODE_NEXT[mode];
						tile.setMode((byte)next);
						par5EntityPlayer.addChatMessage(new ChatComponentText("Absorption area : " + (next*2 + 1) + "x" + (next*2 + 1)));
						flag = true;
					}
					else
					{
						par5EntityPlayer.openGui(DCsIronChain.instance, DCsIronChain.instance.guiIdRHopper, par1World, par2, par3, par4);
						flag = true;
					}
				}
				else
				{
					flag = true;
				}
				return flag;
			}
			else
			{
				flag = true;
			}
			return flag;
		}
			
		
    }
	
	@Override
	public TileEntity createNewTileEntity(World par1World, int i)
    {
        return this.isReverse ? new TileRHopperBlack(): new TilePositiveHopperBlack();
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
    {
		return par1 == 0 ? this.insideIcon : (par1 == 1 ? this.baseIcon : this.sideIcon);
    }
	
	public int getRenderType()
    {
        return DCsIronChain.modelHopper2;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon("dcironchain:rhopper_black_side");
		this.baseIcon = par1IconRegister.registerIcon("dcironchain:rhopper_black_top");
        this.sideIcon = par1IconRegister.registerIcon("dcironchain:rhopper_black_side");
        this.insideIcon = par1IconRegister.registerIcon("hopper_inside");
    }
}
