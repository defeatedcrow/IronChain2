package defeatedcrow.ironchain.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandlerDC {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("DCsIronChain");

	public static void init() {
		INSTANCE.registerMessage(MessageHandlerGuiKey.class, MessageGuiKey.class, 0, Side.SERVER);
	}
}
