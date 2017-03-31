package defeatedcrow.ironchain;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.ironchain.block.BlockAnchorBolt;
import defeatedcrow.ironchain.block.BlockAshiba;
import defeatedcrow.ironchain.block.BlockAshibaStairs;
import defeatedcrow.ironchain.block.BlockBarriarCorn;
import defeatedcrow.ironchain.block.BlockFloodLight;
import defeatedcrow.ironchain.block.BlockGuardFence;
import defeatedcrow.ironchain.block.BlockHashigo;
import defeatedcrow.ironchain.block.BlockIronChain;
import defeatedcrow.ironchain.block.BlockKyatatu;
import defeatedcrow.ironchain.block.BlockLightPart;
import defeatedcrow.ironchain.block.BlockRHopper;
import defeatedcrow.ironchain.block.BlockRHopperBlack;
import defeatedcrow.ironchain.block.BlockRHopperGold;
import defeatedcrow.ironchain.block.BlockSignL;
import defeatedcrow.ironchain.block.BlockSignM;
import defeatedcrow.ironchain.block.BlockSignS;
import defeatedcrow.ironchain.block.ItemKyatatu;
import defeatedcrow.ironchain.block.ItemSignM;
import defeatedcrow.ironchain.event.PlayerUpdateEvent;
import defeatedcrow.ironchain.event.ToolSupplyEvent;
import defeatedcrow.ironchain.integration.RegisterFluidData;
import defeatedcrow.ironchain.item.ItemAltimeter;
import defeatedcrow.ironchain.item.ItemAnzenArmor;
import defeatedcrow.ironchain.item.ItemLifejacket;
import defeatedcrow.ironchain.item.ItemToolBag;
import defeatedcrow.ironchain.packet.PacketHandlerDC;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Mod(modid = "DCIronChain", name = "DCsIronChain2", version = "1.7.10_2.3e", dependencies = "required-after:Forge@[10.13.2.1291,);after:BuildCraft|Core")
public class DCsIronChain {

	@SidedProxy(clientSide = "defeatedcrow.ironchain.client.ClientProxyDC", serverSide = "defeatedcrow.ironchain.CommonProxyDC")
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

	public static Block fluidSignS;
	public static Block fluidSignL;
	public static Block dengerSign;

	public static Block ashibaBlock;
	public static Block ashibaStair;
	public static Block hashigo;
	public static Block guardFence;

	public static Item anzenMet, sagyougi, anzenBelt, anzenBoots;
	public static Item lifeJacket;
	public static Item toolBag;

	public static Item altimeter;

	public static int guiIdRHopper = 1;
	public static int guiIdToolBag = 2;

	public static int toolGuiKey = Keyboard.KEY_X;

	public static int modelRHopper;
	public static int modelFLight;
	public static int modelHopper2;
	public static int modelBarriar;
	public static int modelHook;
	public static int modelSignS;
	public static int modelSignM;
	public static int modelSignL;
	public static int modelChain;
	public static int modelAshiba;
	public static int modelAshibaStair;
	public static int modelHashigo;
	public static int modelFence;

	public static int RHGoldCoolTime = 4;
	public static float harnessCheckDistance = 5.0F;

	public static boolean getLoadIC2 = false;
	public static boolean getLoadBC = false;
	public static boolean notUseLight = false;
	public static boolean visibleLight = false;
	public static boolean bronzeRecipe = true;
	public static boolean JPsign = false;
	public static boolean signIcon = false;
	public static boolean autoTool = true;

	public static boolean debug = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			cfg.load();

			Property notUseLightBlock = cfg.get("Setting", "NotUseLightBlock", notUseLight,
					"Floodlight block does not put a invisible light block.");
			Property visibleLightBlock = cfg.get("Setting", "VisibleLightBlock", visibleLight,
					"Allow invisible light block be visible.");
			Property setCoolTime = cfg.get("Setting", "GoldenHopperCoolingTime", RHGoldCoolTime,
					"Set the cooling time for Golden Upward Hopper.");
			Property setHarnessDistance = cfg.get("Setting", "SetHarnessCheckDistance", harnessCheckDistance,
					"Set the fall distance of checking by the safety harness.");

			Property useBronzeRecipe = cfg.get("Setting", "UseBronzeRecipe", bronzeRecipe,
					"Enable some recipe that use the bronze ingot instead of the iron ingot.");

			Property useJapaneseFluidSign = cfg.get("Setting", "UseJapaneseFluidSign", JPsign,
					"Use Japanese to Fluid Sign. If you want to use English, please set false.");

			Property useFluidIconOnSign = cfg.get("Setting", "UseIconOnFluidSign", JPsign,
					"Use Fluid Icon instead of banner texture in the fluid signboard.");

			Property guiBagKey = cfg.get("Setting", "ToolBagGuiKey", toolGuiKey,
					"Set Key Number for Opening ToolBag Gui.");

			Property toolSup = cfg.get("Setting", "AutoToolSupply", autoTool,
					"Enable supplying item from the tool bag, when using tool is broken.");

			notUseLight = notUseLightBlock.getBoolean(notUseLight);
			visibleLight = visibleLightBlock.getBoolean(visibleLight);
			RHGoldCoolTime = setCoolTime.getInt();
			bronzeRecipe = useBronzeRecipe.getBoolean(true);
			harnessCheckDistance = (float) setHarnessDistance.getDouble();
			JPsign = useJapaneseFluidSign.getBoolean(true);
			signIcon = useFluidIconOnSign.getBoolean(true);
			toolGuiKey = guiBagKey.getInt();
			autoTool = toolSup.getBoolean();

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

		dengerSign = (new BlockSignM()).setBlockName("defeatedcrow.dengerSign").setCreativeTab(ironchainTab);

		fluidSignS = (new BlockSignS()).setBlockName("defeatedcrow.fluidSign_S").setCreativeTab(ironchainTab);

		fluidSignL = (new BlockSignL()).setBlockName("defeatedcrow.fluidSign_L").setCreativeTab(ironchainTab);

		RHopper = (new BlockRHopper()).setBlockName("defeatedcrow.reversalHopper").setCreativeTab(ironchainTab);

		RHopperGold = (new BlockRHopperGold(true)).setBlockName("defeatedcrow.reversalHopper_gold")
				.setCreativeTab(ironchainTab);

		hopperGold = (new BlockRHopperGold(false)).setBlockName("defeatedcrow.positiveHopper_gold")
				.setCreativeTab(ironchainTab);

		RHopperBlack = (new BlockRHopperBlack(true)).setBlockName("defeatedcrow.reversalHopper_black")
				.setCreativeTab(ironchainTab);

		hopperBlack = (new BlockRHopperBlack(false)).setBlockName("defeatedcrow.positiveHopper_black")
				.setCreativeTab(ironchainTab);

		ashibaBlock = (new BlockAshiba()).setBlockName("defeatedcrow.build_ashiba").setCreativeTab(ironchainTab);

		ashibaStair = (new BlockAshibaStairs()).setBlockName("defeatedcrow.stairs_ashiba").setCreativeTab(ironchainTab);

		hashigo = (new BlockHashigo()).setBlockName("defeatedcrow.build_hashigo").setCreativeTab(ironchainTab);

		guardFence = (new BlockGuardFence()).setBlockName("defeatedcrow.build_fence").setCreativeTab(ironchainTab);

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

		lifeJacket = (new ItemLifejacket(ItemArmor.ArmorMaterial.CHAIN, DCsIronChain.proxy.addArmor("lifejacket"), 1))
				.setUnlocalizedName("defeatedcrow.lifeJacket").setCreativeTab(ironchainTab)
				.setTextureName("dcironchain:lifeJacket");

		toolBag = (new ItemToolBag(ItemArmor.ArmorMaterial.CHAIN, DCsIronChain.proxy.addArmor("lifejacket"), 2))
				.setUnlocalizedName("defeatedcrow.toolBag").setCreativeTab(ironchainTab)
				.setTextureName("dcironchain:toolBag");

		altimeter = (new ItemAltimeter()).setCreativeTab(ironchainTab);

		GameRegistry.registerBlock(ironChain, "ironChain");
		GameRegistry.registerBlock(anchorBolt, "anchorBolt");
		GameRegistry.registerBlock(kyatatu, ItemKyatatu.class, "kyatatu");
		GameRegistry.registerBlock(floodLight, "floodLight");
		GameRegistry.registerBlock(DCLightPart, "DCLightPart");
		GameRegistry.registerBlock(barriarCorn, "barriarCorn");
		GameRegistry.registerBlock(dengerSign, ItemSignM.class, "dengerSign");
		GameRegistry.registerBlock(fluidSignS, "fluidSign_S");
		GameRegistry.registerBlock(fluidSignL, "fluidSign_L");
		GameRegistry.registerBlock(RHopper, "reversalHopper");
		GameRegistry.registerBlock(RHopperGold, "upwardHopper_gold");
		GameRegistry.registerBlock(RHopperBlack, "reversalHopper_black");
		GameRegistry.registerBlock(hopperGold, "positiveHopper_gold");
		GameRegistry.registerBlock(hopperBlack, "positiveHopper_black");
		GameRegistry.registerBlock(ashibaBlock, "build_ashiba");
		GameRegistry.registerBlock(ashibaStair, "stairs_ashiba");
		GameRegistry.registerBlock(hashigo, "build_hashigo");
		GameRegistry.registerBlock(guardFence, "build_fence");

		GameRegistry.registerItem(anzenMet, "anzen_met");
		GameRegistry.registerItem(sagyougi, "sagyougi");
		GameRegistry.registerItem(anzenBelt, "anzen_belt");
		GameRegistry.registerItem(anzenBoots, "anzen_boots");
		GameRegistry.registerItem(altimeter, "altimeter");
		GameRegistry.registerItem(lifeJacket, "lifeJacket");
		GameRegistry.registerItem(toolBag, "toolBag");

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Registering new render
		this.modelRHopper = proxy.getRenderID();
		this.modelFLight = proxy.getRenderID();
		this.modelHopper2 = proxy.getRenderID();
		this.modelBarriar = proxy.getRenderID();
		this.modelSignS = proxy.getRenderID();
		this.modelSignM = proxy.getRenderID();
		this.modelSignL = proxy.getRenderID();
		this.modelChain = proxy.getRenderID();
		this.modelAshiba = proxy.getRenderID();
		this.modelAshibaStair = proxy.getRenderID();
		this.modelHashigo = proxy.getRenderID();
		this.modelFence = proxy.getRenderID();
		proxy.registerRenderers();

		// Registering new recipe
		(new RecipeRegister()).addRecipe();

		// registering new gui, entity
		proxy.registerTile();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		// イベント
		MinecraftForge.EVENT_BUS.register(new PlayerUpdateEvent());
		MinecraftForge.EVENT_BUS.register(new ToolSupplyEvent());

		// packet
		PacketHandlerDC.init();

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

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		// fluid data
		RegisterFluidData.register();
	}

}
