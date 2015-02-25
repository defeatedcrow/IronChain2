package defeatedcrow.ironchain.block;

import net.minecraftforge.common.util.ForgeDirection;

public class BlockUtil {
	
	private BlockUtil(){}
	
	public static int metaFromDir(ForgeDirection dir)
	{
		if (dir == ForgeDirection.NORTH) return 0;
		if (dir == ForgeDirection.SOUTH) return 1;
		if (dir == ForgeDirection.WEST) return 2;
		if (dir == ForgeDirection.EAST) return 3;
		else return 0;
		
	}
	
//	public static ForgeDirection getDir(int i)
//	{
//		if (i >= 0 && i < 4)
//		{
//			return ForgeDirection.getOrientation(i);
//		}
//		
//		return ForgeDirection.UNKNOWN;
//	}

}
