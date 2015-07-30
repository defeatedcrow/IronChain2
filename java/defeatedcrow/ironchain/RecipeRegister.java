package defeatedcrow.ironchain;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegister {

	public void addRecipe() {

		if (OreDictionary.getOres("ingotIron") == null || OreDictionary.getOres("ingotIron").isEmpty()) {
			OreDictionary.registerOre("ingotIron", Items.iron_ingot);
		}

		if (OreDictionary.getOres("ingotGold") == null || OreDictionary.getOres("ingotGold").isEmpty()) {
			OreDictionary.registerOre("ingotGold", Items.gold_ingot);
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.ironChain, 16), new Object[] {
				"Z",
				"Z",
				"Z",
				Character.valueOf('Z'),
				"ingotIron" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.anchorBolt, 1), new Object[] {
				"ZX",
				Character.valueOf('Z'),
				"ingotIron",
				Character.valueOf('X'),
				DCsIronChain.ironChain }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.kyatatu, 1, 0), new Object[] {
				"XYX",
				"XYX",
				"XYX",
				Character.valueOf('X'),
				"ingotIron",
				Character.valueOf('Y'),
				Blocks.ladder }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.floodLight, 1), new Object[] {
				"XXX",
				"ZYZ",
				" X ",
				Character.valueOf('X'),
				"ingotIron",
				Character.valueOf('Y'),
				Blocks.glowstone,
				Character.valueOf('Z'),
				Blocks.iron_bars }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.barriarCorn, 8), new Object[] {
				"YXZ",
				"XXX",
				Character.valueOf('X'),
				"ingotIron",
				Character.valueOf('Y'),
				"dyeRed",
				Character.valueOf('Z'),
				"dyeWhite" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.anzenMet, 1), new Object[] {
				"XYX",
				"ZWZ",
				Character.valueOf('X'),
				"dyeYellow",
				Character.valueOf('Y'),
				Items.leather_helmet,
				Character.valueOf('Z'),
				Items.string,
				Character.valueOf('W'),
				Blocks.wool }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.sagyougi, 1), new Object[] {
				"X X",
				"XXX",
				"YYY",
				Character.valueOf('X'),
				new ItemStack(Blocks.wool, 1, 9),
				Character.valueOf('Y'),
				Items.leather }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.anzenBelt, 1), new Object[] {
				"XXX",
				"  Y",
				Character.valueOf('X'),
				new ItemStack(Items.leather, 1, 0),
				Character.valueOf('Y'),
				DCsIronChain.ironChain }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.anzenBoots, 1), new Object[] {
				"XZX",
				"Y Y",
				Character.valueOf('X'),
				Items.leather,
				Character.valueOf('Y'),
				"ingotIron",
				Character.valueOf('Z'),
				"dyeBlack" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.RHopper, 1), new Object[] {
				" X ",
				"XZX",
				"XYX",
				Character.valueOf('X'),
				"ingotIron",
				Character.valueOf('Y'),
				Blocks.redstone_torch,
				Character.valueOf('Z'),
				Blocks.dropper }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.RHopperGold, 1), new Object[] {
				" X ",
				"XZX",
				" X ",
				Character.valueOf('X'),
				"ingotGold",
				Character.valueOf('Z'),
				DCsIronChain.RHopper, }));

		GameRegistry.addRecipe(new ItemStack(DCsIronChain.RHopperBlack, 1), new Object[] {
				" X ",
				"XZX",
				" X ",
				Character.valueOf('X'),
				Blocks.obsidian,
				Character.valueOf('Z'),
				DCsIronChain.RHopper, });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.RHopperBlack, 1), new Object[] {
				"X",
				Character.valueOf('X'),
				new ItemStack(DCsIronChain.hopperBlack, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.RHopperGold, 1), new Object[] {
				"X",
				Character.valueOf('X'),
				new ItemStack(DCsIronChain.hopperGold, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.hopperBlack, 1), new Object[] {
				"X",
				Character.valueOf('X'),
				new ItemStack(DCsIronChain.RHopperBlack, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.hopperGold, 1), new Object[] {
				"X",
				Character.valueOf('X'),
				new ItemStack(DCsIronChain.RHopperGold, 1) }));

		// use bronze recipe

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.ironChain, 16), new Object[] {
				"X",
				"X",
				"X",
				Character.valueOf('X'),
				"ingotBronze" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.anchorBolt, 1), new Object[] {
				"XY",
				Character.valueOf('X'),
				"ingotBronze",
				Character.valueOf('Y'),
				DCsIronChain.ironChain }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.kyatatu, 1, 0), new Object[] {
				"XYX",
				"XYX",
				"XYX",
				Character.valueOf('X'),
				"ingotBronze",
				Character.valueOf('Y'),
				Blocks.ladder }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.floodLight, 1), new Object[] {
				"XXX",
				"ZYZ",
				" X ",
				Character.valueOf('X'),
				"ingotBronze",
				Character.valueOf('Y'),
				Blocks.glowstone,
				Character.valueOf('Z'),
				Blocks.fence }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.barriarCorn, 8), new Object[] {
				"YXZ",
				"XXX",
				Character.valueOf('X'),
				"ingotBronze",
				Character.valueOf('Y'),
				"dyeRed",
				Character.valueOf('Z'),
				"dyeWhite" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.RHopper, 1), new Object[] {
				" X ",
				"XZX",
				"XYX",
				Character.valueOf('X'),
				"ingotBronze",
				Character.valueOf('Y'),
				Blocks.redstone_torch,
				Character.valueOf('Z'),
				Blocks.dropper }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.fluidSignS, 1), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('X'),
				Blocks.glass_pane,
				Character.valueOf('Y'),
				Items.sign }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.dengerSign, 1), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('X'),
				Items.paper,
				Character.valueOf('Y'),
				Items.sign }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.fluidSignL, 1), new Object[] {
				"X",
				Character.valueOf('X'),
				DCsIronChain.fluidSignS }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.fluidSignS, 1), new Object[] {
				"X",
				Character.valueOf('X'),
				DCsIronChain.fluidSignL }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.altimeter, 1), new Object[] {
				" Y ",
				"XZX",
				" W ",
				Character.valueOf('X'),
				"ingotIron",
				Character.valueOf('Y'),
				Blocks.glass,
				Character.valueOf('Z'),
				Items.compass,
				Character.valueOf('W'),
				Items.redstone }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsIronChain.altimeter, 1), new Object[] {
				" Y ",
				"XZX",
				" W ",
				Character.valueOf('X'),
				"ingotBronze",
				Character.valueOf('Y'),
				Blocks.glass,
				Character.valueOf('Z'),
				Items.compass,
				Character.valueOf('W'),
				Items.redstone }));

		if (DCsIronChain.bronzeRecipe) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.iron_bars, 16), new Object[] {
					"XXX",
					"XXX",
					Character.valueOf('X'),
					"ingotBronze" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.bucket, 1), new Object[] {
					"X X",
					" X ",
					Character.valueOf('X'),
					"ingotBronze" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.hopper, 1), new Object[] {
					"X X",
					"XYX",
					" X ",
					Character.valueOf('X'),
					"ingotBronze",
					Character.valueOf('Y'),
					Blocks.chest }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.piston, 1), new Object[] {
					"YYY",
					"ZXZ",
					"ZWZ",
					Character.valueOf('X'),
					"ingotBronze",
					Character.valueOf('Y'),
					"plankWood",
					Character.valueOf('W'),
					Items.redstone,
					Character.valueOf('Z'),
					Blocks.cobblestone }));

		}
	}

}
