package defeatedcrow.ironchain.integration;

import java.util.ArrayList;

import defeatedcrow.ironchain.*;
import buildcraft.api.core.Position;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class IntegrationBC {
	
	public static ArrayList<ForgeDirection> getPipeConected (World world, int X, int Y, int Z, ForgeDirection from)
	{
		ArrayList<ForgeDirection> possiblePipe = new ArrayList<ForgeDirection>();
		
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
		      if ((from == ForgeDirection.UNKNOWN) || (from == dir.getOpposite()))
		      {
		    	  {
		    		  Position pos = new Position(X, Y, Z, dir);
		    		  pos.moveForwards(1.0D);
		    		  
		    		  TileEntity pipeTile = world.getTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
		    		  
		    		  if (pipeTile instanceof IPipeTile)
		    		  {
		    			  IPipeTile pipe = (IPipeTile)pipeTile;
		    			  if ((from != ForgeDirection.UNKNOWN) && ((pipeTile instanceof IPipeConnection))) 
		    			  {
		    		            if (((IPipeConnection)pipeTile).overridePipeConnection(IPipeTile.PipeType.ITEM, dir) != IPipeConnection.ConnectOverride.DISCONNECT)
		    		            possiblePipe.add(dir);
		    		      }
		    			  else if ((pipe.getPipeType() == IPipeTile.PipeType.ITEM) && (pipe.isPipeConnected(dir.getOpposite())))
		    		            possiblePipe.add(dir);
		    		  }
		    	  }
		      }
		}
			
		return possiblePipe;
	}
	
	public static boolean dropIntoPipe (TileEntity tile, ArrayList<ForgeDirection> pipes, ItemStack itemstack)
	{
		if (itemstack != null && itemstack.stackSize > 0 && pipes.size() > 0)
		{
			int choice = tile.getWorldObj().rand.nextInt(pipes.size());
			Position itemPos = new Position(tile.xCoord, tile.yCoord, tile.zCoord, pipes.get(choice));

		    itemPos.x += 0.5D;
		    itemPos.y += 0.25D;
		    itemPos.z += 0.5D;
		    itemPos.moveForwards(0.5D);
		    
		    Position pipePos = new Position(tile.xCoord, tile.yCoord, tile.zCoord, pipes.get(choice));
		    pipePos.moveForwards(1.0D);
		    
			IPipeTile pipe = (IPipeTile)tile.getWorldObj().getTileEntity((int)pipePos.x, (int)pipePos.y, (int)pipePos.z);
			ItemStack drop = itemstack.copy();
			
			if (pipe == null) return false;
			if (pipe.injectItem(drop, true, pipePos.orientation.getOpposite()) > 0) {
			      return true;
			    }
			    pipes.remove(choice);
			    
			return false;
		}
		else
		{
			return false;
		}
	}

}
