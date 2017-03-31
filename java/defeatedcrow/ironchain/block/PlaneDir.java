package defeatedcrow.ironchain.block;

import net.minecraftforge.common.util.ForgeDirection;

//縦方向のない、平面4方角のみのDirectionEnum
//ForgeDirectionの改変
public enum PlaneDir {

	/** -Z */
	NORTH(0, -1),

	/** +Z */
	SOUTH(0, 1),

	/** -X */
	WEST(-1, 0),

	/** +X */
	EAST(1, 0),

	UNKNOWN(0, 0);

	public final int offsetX;
	public final int offsetZ;
	public final int flag;

	public static final PlaneDir[] DIRECTIONS = { NORTH, SOUTH, WEST, EAST };
	public static final ForgeDirection[] FORGE_DIRECTIONS = {
			ForgeDirection.NORTH,
			ForgeDirection.SOUTH,
			ForgeDirection.WEST,
			ForgeDirection.EAST };
	public static final int[] OPPOSITES = { 1, 0, 3, 2 };

	private PlaneDir(int x, int z) {
		offsetX = x;
		offsetZ = z;
		flag = 1 << ordinal();
	}

	public static PlaneDir getDir(int id) {
		if (id >= 0 && id < DIRECTIONS.length) {
			return DIRECTIONS[id];
		}

		return UNKNOWN;
	}

	public static ForgeDirection getForgeDir(int id) {
		if (id >= 0 && id < DIRECTIONS.length) {
			return FORGE_DIRECTIONS[id];
		}

		return ForgeDirection.UNKNOWN;
	}

	public PlaneDir getOpposite() {
		return getDir(OPPOSITES[ordinal()]);
	}

	public static PlaneDir getPlayerDir(int i) {
		int r = i & 3;
		return NORTH;
	}

}
