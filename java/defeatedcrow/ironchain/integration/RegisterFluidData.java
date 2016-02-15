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
		FluidDataRegistry.instance.register("shothu_dc", "dcs.fluidtype.alc_drink", false, false, false);
		FluidDataRegistry.instance.register("whiskey_dc", "dcs.fluidtype.alc_drink", false, false, false);
		FluidDataRegistry.instance.register("brandy_dc", "dcs.fluidtype.alc_drink", false, false, false);
		FluidDataRegistry.instance.register("rum_dc", "dcs.fluidtype.alc_drink", false, false, false);
		FluidDataRegistry.instance.register("sake_dc", "dcs.fluidtype.alc_drink", false, false, false);
		FluidDataRegistry.instance.register("beer_dc", "dcs.fluidtype.alc_drink", false, false, false);
		FluidDataRegistry.instance.register("wine_dc", "dcs.fluidtype.alc_drink", false, false, false);
		FluidDataRegistry.instance.register("vodka_dc", "dcs.fluidtype.alc", true, false, false);
		FluidDataRegistry.instance.register("fluid_flour", "dcs.fluidtype.com", true, false, false);
		FluidDataRegistry.instance.register("oil", "dcs.fluidtype.oil_1", true, false, false);
		FluidDataRegistry.instance.register("fuel", "dcs.fluidtype.oil_1", true, false, false);
		FluidDataRegistry.instance.register("redplasma", "dcs.fluidtype.reactive", true, true, false);
		FluidDataRegistry.instance.register("ic2uumatter", "dcs.fluidtype.magic", false, false, false);
		FluidDataRegistry.instance.register("ic2pahoehoelava", "dcs.fluidtype.lava", false, false, false);
		FluidDataRegistry.instance.register("ic2biomass", "dcs.fluidtype.com", true, false, false);
		FluidDataRegistry.instance.register("ic2biogas", "dcs.fluidtype.gas", true, false, false);
		FluidDataRegistry.instance.register("ic2superheatedsteam", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("ic2steam", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("bioethanol", "dcs.fluidtype.com", true, false, false);
		FluidDataRegistry.instance.register("biomass", "dcs.fluidtype.alc", true, false, false);
		FluidDataRegistry.instance.register("glass", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("seedoil", "dcs.fluidtype.oil_v", true, false, false);
		FluidDataRegistry.instance.register("lifestream", "dcs.fluidtype.magic", false, false, true);
		FluidDataRegistry.instance.register("fluxgoo", "dcs.fluidtype.magic", false, false, true);
		FluidDataRegistry.instance.register("creosote", "dcs.fluidtype.com", true, false, false);
		FluidDataRegistry.instance.register("steam", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("drinkingwater", "dcs.fluidtype.drink", false, false, false);
		FluidDataRegistry.instance.register("mana", "dcs.fluidtype.magic", false, false, false);
		FluidDataRegistry.instance.register("iron", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("gold", "dcs.fluidtype.metal", false, false, false);
		FluidDataRegistry.instance.register("glowstone", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("coal", "dcs.fluidtype.com", true, false, false);
		FluidDataRegistry.instance.register("asid", "dcs.fluidtype.tox", false, false, true);
		FluidDataRegistry.instance.register("poison", "dcs.fluidtype.tox", false, false, true);
		FluidDataRegistry.instance.register("liquidnitrogen", "dcs.fluidtype.gas", false, false, false);
		FluidDataRegistry.instance.register("asid", "dcs.fluidtype.tox", false, false, true);
		FluidDataRegistry.instance.register("turpentine", "dcs.fluidtype.oil_2", true, false, false);
		FluidDataRegistry.instance.register("binnie.growthmedium", "dcs.fluidtype.inf", false, false, false);
		FluidDataRegistry.instance.register("binnie.bacteria", "dcs.fluidtype.inf", false, false, false);
		FluidDataRegistry.instance.register("binnie.bacteriapoly", "dcs.fluidtype.inf", false, false, false);
		FluidDataRegistry.instance.register("binnie.dne.raw", "dcs.fluidtype.inf", false, false, false);
		FluidDataRegistry.instance.register("binnie.bacteriavector", "dcs.fluidtype.inf", false, false, false);
		FluidDataRegistry.instance.register("hydrogen", "dcs.fluidtype.gas", true, false, false);
		FluidDataRegistry.instance.register("oxygen", "dcs.fluidtype.oxi", true, false, false);
		FluidDataRegistry.instance.register("chlorine", "dcs.fluidtype.gas", false, false, true);
		FluidDataRegistry.instance.register("sulfurdioxidegas", "dcs.fluidtype.tox", false, false, true);
		FluidDataRegistry.instance.register("sulfurtrioxidegas", "dcs.fluidtype.oxi", false, false, true);
		FluidDataRegistry.instance.register("sulfuricacid", "dcs.fluidtype.tox", false, false, true);
		FluidDataRegistry.instance.register("hydrogenchloride", "dcs.fluidtype.tox", false, false, true);
		FluidDataRegistry.instance.register("ethene", "dcs.fluidtype.gas", true, false, false);
		FluidDataRegistry.instance.register("tritium", "dcs.fluidtype.rad", false, false, false);
		FluidDataRegistry.instance.register("fusionfueldt", "dcs.fluidtype.rad", false, false, false);
		FluidDataRegistry.instance.register("sodium", "dcs.fluidtype.pyro_1", true, true, false);
		FluidDataRegistry.instance.register("lithium", "dcs.fluidtype.pyro_1", true, true, false);
		FluidDataRegistry.instance.register("hootch", "dcs.fluidtype.alc_drink", true, false, false);
		FluidDataRegistry.instance.register("rocket_fuel", "dcs.fluidtype.oil_2", true, false, false);
		FluidDataRegistry.instance.register("fire_water", "dcs.fluidtype.oil_2", true, false, false);
		FluidDataRegistry.instance.register("iron.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("gold.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("copper.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("tin.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("alminium.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("cobalt.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("ardite.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("bronze.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("alminiumblas.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("manyullin.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("alumite.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("obsidian.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("steel.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("glass.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("stone.molten", "dcs.fluidtype.lava", false, false, false);
		FluidDataRegistry.instance.register("emerald.molten", "dcs.fluidtype.lava", false, false, false);
		FluidDataRegistry.instance.register("nickel.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("lead.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("silver.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("platinum.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("inver.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("electrum.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("lumium.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("signalum.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("mithril.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("enderium.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("pigiron.molten", "dcs.fluidtype.sol", false, false, false);
		FluidDataRegistry.instance.register("blood.molten", "dcs.fluidtype.inf", false, false, false);
	}

}
