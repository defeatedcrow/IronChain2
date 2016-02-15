package defeatedcrow.ironchain.block.tileentity;

import defeatedcrow.addonforamt.fluidity.block.FluidTankFF;
import defeatedcrow.ironchain.block.tileentity.FluidDataRegistry.FluidData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileFluidSign extends TileEntity {

	protected static final String Fluid = null;
	protected String id = "None";
	protected String type = "dcs.fluidtype.none";
	protected int amount = 0;

	protected boolean f = false;
	protected boolean e = false;
	protected boolean p = false;
	protected boolean h = false;
	protected boolean g = false;

	private int lastAmount = 0;

	private int coolTime = 0;

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		id = tag.getString("FluidID");
		type = tag.getString("ModID");
		amount = tag.getInteger("Amount");

		f = tag.getBoolean("Flammable");
		e = tag.getBoolean("Explosive");
		p = tag.getBoolean("Poison");
		h = tag.getBoolean("HighTemp");
		g = tag.getBoolean("Gas");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("FluidID", id);
		tag.setString("ModID", type);
		tag.setInteger("Amount", amount);

		tag.setBoolean("Flammable", f);
		tag.setBoolean("Explosive", e);
		tag.setBoolean("Poison", p);
		tag.setBoolean("HighTemp", h);
		tag.setBoolean("Gas", g);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	/* --- getter --- */

	public String getFluidID() {
		return id;
	}

	public Fluid getFluid() {
		return FluidRegistry.getFluid(id);
	}

	public String getFluidLocalisedName() {
		if (getFluid() != null) {
			String s = getFluid().getLocalizedName(new FluidStack(getFluid(), 1000));
			return s;
		} else {
			return "None";
		}
	}

	public String getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public boolean isFlammable() {
		return f;
	}

	public boolean isExplosive() {
		return e;
	}

	public boolean isPoison() {
		return p;
	}

	public boolean isHighTemp() {
		return h;
	}

	public boolean isGas() {
		return g;
	}

	/* --- update --- */

	@Override
	public void updateEntity() {

		if (!worldObj.isRemote) {

			if (coolTime > 0) {
				coolTime--;
				return;
			}

			coolTime = 5;

			int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			int x = Direction.offsetX[meta];
			int z = Direction.offsetZ[meta];
			TileEntity tile = worldObj.getTileEntity(xCoord + x, yCoord, zCoord + z);

			if (tile != null && tile instanceof IFluidHandler) {
				IFluidHandler tank = (IFluidHandler) tile;
				FluidTankInfo[] info = tank.getTankInfo(ForgeDirection.DOWN); // 完成品を狙うため下から
				Fluid fluid = null;
				int amo = 0;
				if (info != null) {
					for (FluidTankInfo i : info) {
						if (i.fluid != null && i.fluid.getFluid() != null) {
							fluid = i.fluid.getFluid();
							amo = i.fluid.amount;
						}
					}
				}

				if (fluid == null && !id.equalsIgnoreCase("None")) {
					this.clearTile();
				} else {
					this.updateServer(fluid, amo);
				}
			} else if (!id.equalsIgnoreCase("None")) {
				this.clearTile();
			}
		}

	}

	private void clearTile() {
		id = "None";
		type = "dcs.fluidtype.none";
		amount = 0;
		f = false;
		e = false;
		p = false;
		h = false;
		g = false;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	private void updateServer(Fluid fluid, int a) {
		if (fluid == null) {
			clearTile();
			return;
		}

		String s = fluid.getName();
		if (id.equalsIgnoreCase(s) || lastAmount != a) {
			id = s;
			amount = a;
			lastAmount = a;
			h = fluid.getTemperature() > 500;
			g = fluid.isGaseous();
			if (FluidDataRegistry.instance.getData(s) != null) {
				FluidData data = FluidDataRegistry.instance.getData(s);
				type = data.getType();
				f = data.isFlammable();
				e = data.isExplosive();
				p = data.isPoison();
			} else {
				f = false;
				e = false;
				p = false;
			}

			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

	}

	public int getMetadata() {
		return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}

}
