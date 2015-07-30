package defeatedcrow.ironchain.integration;

import defeatedcrow.ironchain.block.tileentity.FluidDataRegistry;

public class RegisterFluidData {

	private RegisterFluidData() {
	}

	public static void register() {
		FluidDataRegistry.instance.register("water", "dcs.fluidtype.water", false, false, false);
		FluidDataRegistry.instance.register("lava", "dcs.fluidtype.lava", false, false, false);
		FluidDataRegistry.instance.register("vegitable_oil", "dcs.fluidtype.oil_v", true, false, false);
		FluidDataRegistry.instance.register("camellia_oil", "dcs.fluidtype.oil_v", true, false, false);
		FluidDataRegistry.instance.register("vodka_dc", "dcs.fluidtype.alc", true, false, false);
		FluidDataRegistry.instance.register("fluid_flour", "dcs.fluidtype.flammable", true, false, false);
		FluidDataRegistry.instance.register("oil", "dcs.fluidtype.oil_1", true, false, false);
		FluidDataRegistry.instance.register("fuel", "dcs.fluidtype.oil_1", true, false, false);
		FluidDataRegistry.instance.register("redplasma", "dcs.fluidtype.reactive", true, true, false);
		FluidDataRegistry.instance.register("ic2uumatter", "dcs.fluidtype.magic", false, false, false);
		FluidDataRegistry.instance.register("ic2pahoehoelava", "dcs.fluidtype.lava", false, false, false);
		FluidDataRegistry.instance.register("ic2biomass", "dcs.fluidtype.flammable", true, false, false);
		FluidDataRegistry.instance.register("ic2biogas", "dcs.fluidtype.gas", true, false, false);
		FluidDataRegistry.instance.register("ic2superheatedsteam", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("ic2steam", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("bioethanol", "dcs.fluidtype.flammable", true, false, false);
		FluidDataRegistry.instance.register("biomass", "dcs.fluidtype.alc", true, false, false);
		FluidDataRegistry.instance.register("glass", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("seedoil", "dcs.fluidtype.oil_v", true, false, false);
		FluidDataRegistry.instance.register("lifestream", "dcs.fluidtype.magic", false, false, true);
		FluidDataRegistry.instance.register("fluxgoo", "dcs.fluidtype.magic", false, false, true);
		FluidDataRegistry.instance.register("creosote", "dcs.fluidtype.flammable", true, false, false);
		FluidDataRegistry.instance.register("steam", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("drinkingwater", "dcs.fluidtype.drink", false, false, false);
		FluidDataRegistry.instance.register("mana", "dcs.fluidtype.magic", false, false, false);
		FluidDataRegistry.instance.register("iron", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("gold", "dcs.fluidtype.metal", false, false, false);
		FluidDataRegistry.instance.register("glowstone", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("coal", "dcs.fluidtype.flammable", true, false, false);
	}

}
