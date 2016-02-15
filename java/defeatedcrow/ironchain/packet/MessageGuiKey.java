package defeatedcrow.ironchain.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageGuiKey implements IMessage {

	public byte data;

	public MessageGuiKey() {
	}

	public MessageGuiKey(byte par1) {
		this.data = par1;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.data = buf.readByte();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(this.data);
	}
}
