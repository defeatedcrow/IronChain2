package defeatedcrow.ironchain.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import defeatedcrow.ironchain.DCsIronChain;

public class MessageHandlerGuiKey implements IMessageHandler<MessageGuiKey, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageGuiKey message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		if (player != null) {
			player.openGui(DCsIronChain.instance, DCsIronChain.guiIdToolBag, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
		return null;
	}
}
