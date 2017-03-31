package defeatedcrow.ironchain.block.tileentity;

import java.util.ArrayList;
import java.util.List;

public class FluidDataRegistry {

	public final static FluidDataRegistry instance = new FluidDataRegistry();

	FluidDataRegistry() {}

	private static ArrayList<FluidData> dataList = new ArrayList<FluidData>();

	public List<FluidData> getList() {
		return dataList;
	}

	public void register(String id, String type, boolean flam, boolean exp, boolean poi) {
		if (id != null) {
			dataList.add(new FluidData(id, type, flam, exp, poi));
		}
	}

	public FluidData getData(String id) {
		if (dataList.isEmpty()) {
			return null;
		} else {
			for (FluidData data : dataList) {
				if (data.getFluidID().equalsIgnoreCase(id)) {
					return data;
				}
			}
		}
		return null;
	}

	public class FluidData {

		private final String fluidID;
		private final String fluidType;
		private final boolean isFlam;
		private final boolean isExpl;
		private final boolean isPois;

		public FluidData(String s, String type, boolean flm, boolean exp, boolean poi) {
			fluidID = s;
			fluidType = type;
			isFlam = flm;
			isExpl = exp;
			isPois = poi;
		}

		public String getFluidID() {
			return fluidID;
		}

		public String getType() {
			return fluidType;
		}

		public boolean isFlammable() {
			return isFlam;
		}

		public boolean isExplosive() {
			return isExpl;
		}

		public boolean isPoison() {
			return isPois;
		}

	}

}
