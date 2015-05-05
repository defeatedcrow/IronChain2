package defeatedcrow.ironchain;

import static cpw.mods.fml.relauncher.Side.CLIENT;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import defeatedcrow.ironchain.*;
import defeatedcrow.ironchain.block.*;
import defeatedcrow.ironchain.event.PlayerUpdateEvent;
import defeatedcrow.ironchain.item.ItemAltimeter;
import defeatedcrow.ironchain.item.ItemAnzenArmor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.src.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(
		modid = "DCIronChain",
		name = "DCsIronChain2",
		version = "1.7.10_2.1b",
		dependencies = "required-after:Forge@[10.13.2.1291,);after:BuildCraft|Core")
public class DCsIronChain {

	@SidedProxy(
			clientSide = "defeatedcrow.ironchain.client.ClientProxyDC",
			serverSide = "defeatedcrow.ironchain.CommonProxyDC")
	public static CommonProxyDC proxy;

	@Instance("DCIronChain")
	public static DCsIronChain instance;

	public static final CreativeTabs ironchainTab = new CreativeTabIChain("dcironchain");

	public static Block ironChain;
	public static Block anchorBolt;
	public static Block hanging;
	public static Block kyatatu;
	public static Block floodLight;
	public static Block DCLightPart;
	public static Block RHopper;
	public static Block RHopperGold;
	public static Block RHopperBlack;
	public static Block hopperGold;
	public static Block hopperBlack;
	public static Block barriarCorn;

	public static Item anzenMet, sagyougi, anzenBelt, anzenBoots;

	public static Item altimeter;

	public static int guiIdRHopper = 1;
	public static int modelRHopper;
	public static int modelFLight;
	public static int modelHopper2;
	public static int modelBarriar;
	public static int modelHook;

	public static int RHGoldCoolTime = 4;
	public static float harnessCheckDistance = 5.0F;

	public static boolean getLoadIC2 = false;
	public static boolean getLoadBC = false;
	public static boolean notUseLight = false;
	public static boolean visibleLight = false;
	public static boolean bronzeRecipe = true;

	public static boolean debug = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			cfg.load();

			Property notUseLightBlock = cfg
					.get("Setting", "NotUseLightBlock", notUseLight, "Floodlight block does not put a invisible light block.");
			Property visibleLightBlock = cfg
					.get("Setting", "VisibleLightBlock", visibleLight, "Allow invisible light block be visible.");
			Property setCoolTime = cfg
					.get("Setting", "GoldenHopperCoolingTime", RHGoldCoolTime, "Set the cooling time for Golden Upward Hopper.");
			Property setHarnessDistance = cfg
					.get("Setting", "SetHarnessCheckDistance", harnessCheckDistance, "Set the fall distance of checking by the safety harness.");

			Property useBronzeRecipe = cfg
					.get("Setting", "UseBronzeRecipe", bronzeRecipe, "Enable some recipe that use the bronze ingot instead of the iron ingot.");

			notUseLight = notUseLightBlock.getBoolean(notUseLight);
			visibleLight = visibleLightBlock.getBoolean(visibleLight);
			RHGoldCoolTime = setCoolTime.getInt();
			bronzeRecipe = useBronzeRecipe.getBoolean(true);
			harnessCheckDistance = (float) setHarnessDistance.getDouble();

		} catch (Exception e) {
			IronChainLog.logger.error("Error Message", e);

		} finally {
			cfg.save();
		}

		ironChain = (new BlockIronChain()).setBlockName("defeatedcrow.ironChain").setCreativeTab(ironchainTab);

		anchorBolt = (new BlockAnchorBolt()).setBlockName("defeatedcrow.anchorBolt").setCreativeTab(ironchainTab);

		kyatatu = (new BlockKyatatu()).setBlockName("defeatedcrow.kyatatu").setCreativeTab(ironchainTab);

		floodLight = (new BlockFloodLight()).setBlockName("defeatedcrow.floodlight").setCreativeTab(ironchainTab);

		DCLightPart = (new BlockLightPart()).setBlockName("defeatedcrow.lightBlock");

		barriarCorn = (new BlockBarriarCorn()).setBlockName("defeatedcrow.barriarCorn").setCreativeTab(ironchainTab);

		RHopper = (new BlockRHopper()).setBlockName("defeatedcrow.reversalHopper").setCreativeTab(ironchainTab);

		RHopperGold = (new BlockRHopperGold(true)).setBlockName("defeatedcrow.reversalHopper_gold")
				.setCreativeTab(ironchainTab);

		hopperGold = (new BlockRHopperGold(false)).setBlockName("defeatedcrow.positiveHopper_gold")
				.setCreativeTab(ironchainTab);

		RHopperBlack = (new BlockRHopperBlack(true)).setBlockName("defeatedcrow.reversalHopper_black")
				.setCreativeTab(ironchainTab);

		hopperBlack = (new BlockRHopperBlack(false)).setBlockName("defeatedcrow.positiveHopper_black")
				.setCreativeTab(ironchainTab);

		anzenMet = (new ItemAnzenArmor(ItemArmor.ArmorMaterial.CHAIN, DCsIronChain.proxy.addArmor("anzenarmor"), 0))
				.setUnlocalizedName("defeatedcrow.anzenMet").setCreativeTab(ironchainTab)
				.setTextureName("dcironchain:anzen_met");

		sagyougi = (new ItemAnzenArmor(ItemArmor.ArmorMaterial.CHAIN, DCsIronChain.proxy.addArmor("anzenarmor"), 1))
				.setUnlocalizedName("defeatedcrow.sagyougi").setCreativeTab(ironchainTab)
				.setTextureName("dcironchain:sagyougi");

		anzenBelt = (new ItemAnzenArmor(ItemArmor.ArmorMaterial.CHAIN, DCsIronChain.proxy.addArmor("anzenarmor"), 2))
				.setUnlocalizedName("defeatedcrow.anzenBelt").setCreativeTab(ironchainTab)
				.setTextureName("dcironchain:anzen_belt");

		anzenBoots = (new ItemAnzenArmor(ItemArmor.ArmorMaterial.CHAIN, DCsIronChain.proxy.addArmor("anzenarmor"), 3))
				.setUnlocalizedName("defeatedcrow.anzenBoots").setCreativeTab(ironchainTab)
				.setTextureName("dcironchain:anzen_boots");

		altimeter = (new ItemAltimeter()).setCreativeTab(ironchainTab);

		GameRegistry.registerBlock(ironChain, "ironChain");
		GameRegistry.registerBlock(anchorBolt, "anchorBolt");
		GameRegistry.registerBlock(kyatatu, ItemKyatatu.class, "kyatatu");
		GameRegistry.registerBlock(floodLight, "floodLight");
		GameRegistry.registerBlock(DCLightPart, "DCLightPart");
		GameRegistry.registerBlock(barriarCorn, "barriarCorn");
		GameRegistry.registerBlock(RHopper, "reversalHopper");
		GameRegistry.registerBlock(RHopperGold, "upwardHopper_gold");
		GameRegistry.registerBlock(RHopperBlack, "reversalHopper_black");
		GameRegistry.registerBlock(hopperGold, "positiveHopper_gold");
		GameRegistry.registerBlock(hopperBlack, "positiveHopper_black");

		GameRegistry.registerItem(anzenMet, "anzen_met");
		GameRegistry.registerItem(sagyougi, "sagyougi");
		GameRegistry.registerItem(anzenBelt, "anzen_belt");
		GameRegistry.registerItem(anzenBoots, "anzen_boots");
		GameRegistry.registerItem(altimeter, "altimeter");

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Registering new render
		this.modelRHopper = proxy.getRenderID();
		this.modelFLight = proxy.getRenderID();
		this.modelHopper2 = proxy.getRenderID();
		this.modelBarriar = proxy.getRenderID();
		proxy.registerRenderers();

		// Registering new recipe
		(new RecipeRegister()).addRecipe();

		// registering new gui, entity
		proxy.registerTile();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		// イベント
		MinecraftForge.EVENT_BUS.register(new PlayerUpdateEvent());

		// 以下は導入のチェックのみ
		if (Loader.isModLoaded("IC2")) {
			try {
				this.getLoadIC2 = true;
			} catch (Exception e) {
				System.out.println("failed to check IC2");
				e.printStackTrace(System.err);
			}
		}

		if (Loader.isModLoaded("BuildCraft|Core")) {
			try {
				this.getLoadBC = true;
			} catch (Exception e) {
				System.out.println("failed to check BC");
				e.printStackTrace(System.err);
			}
		}
	}

}
