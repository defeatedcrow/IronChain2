package defeatedcrow.ironchain.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.living.LivingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.IronChainLog;
import defeatedcrow.ironchain.packet.MessageGuiKey;
import defeatedcrow.ironchain.packet.PacketHandlerDC;

// anzen belt の機能実装用
public class PlayerUpdateEvent {

	private int count = 10;
	private boolean push = false;

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.entityLiving;

		if (entity != null && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack plate = player.inventory.armorInventory[2];
			ItemStack belt = player.inventory.armorInventory[1];
			boolean hasLantern = false;

			if (plate != null && plate.getItem() != null && plate.getItem() == DCsIronChain.sagyougi) {
				if (!player.onGround && player.isOnLadder()) {
					if (player.isCollidedHorizontally) {
						player.motionY = 0.32D;
					}
				}
			}

			if (plate != null && plate.getItem() != null && plate.getItem() == DCsIronChain.lifeJacket) {
				if (player.isInWater()) {
					player.motionY += 0.038D;
				}
			}

			if (belt != null && belt.getItem() != null && belt.getItem() == DCsIronChain.toolBag) {
				if (DCsIronChain.proxy.isGuiKeyDown()) {
					if (!push) {
						PacketHandlerDC.INSTANCE.sendToServer(new MessageGuiKey((byte) 1));
						push = true;
					}
				} else {
					push = false;
				}
			}

			if (!player.worldObj.isRemote && belt != null && belt.getItem() != null
					&& belt.getItem() == DCsIronChain.anzenBelt) {
				NBTTagCompound nbt = belt.getTagCompound();
				boolean flag = false;
				double pX = 0.5D;
				double pY = 0.5D;
				double pZ = 0.5D;
				int dim = 0;

				if (nbt != null) {
					pX = nbt.getInteger("DCIanzenX") + 0.5D;
					pY = nbt.getInteger("DCIanzenY") + 0.5D;
					pZ = nbt.getInteger("DCIanzenZ") + 0.5D;
					dim = nbt.getInteger("DCIanzenDim");
					flag = nbt.hasKey("DCIanzenDim");
				}

				if (player.onGround) {
					if (player.riddenByEntity == null && player.ridingEntity == null) {
						int newX = MathHelper.floor_double(player.posX);
						int newY = MathHelper.floor_double(player.posY);
						int newZ = MathHelper.floor_double(player.posZ);
						// double dx = Math.abs(player.posX - newX);
						// double dy = Math.abs(player.posY - newY);
						// double dz = Math.abs(player.posZ - newZ);
						// dx = Math.abs(dx - 0.5D);
						// dy = Math.abs(dy - 0.5D);
						// dz = Math.abs(dz - 0.5D);

						if (player.worldObj.isSideSolid(newX, newY - 1, newZ, ForgeDirection.UP)) {
							if (nbt == null)
								nbt = new NBTTagCompound();
							nbt.setInteger("DCIanzenX", newX);
							nbt.setInteger("DCIanzenY", newY);
							nbt.setInteger("DCIanzenZ", newZ);
							nbt.setInteger("DCIanzenDim", MathHelper.floor_double(player.worldObj.provider.dimensionId));
							nbt.setString("DCIanzenDimName", player.worldObj.provider.getDimensionName());
							belt.setTagCompound(nbt);
						}
					}
				}

				if (flag && !player.onGround && !player.isInWater()) {
					if (player.riddenByEntity != null || player.ridingEntity != null || player.capabilities.isFlying) {
						player.fallDistance = 0.0F;
						if (player.ridingEntity != null && !(player.ridingEntity instanceof EntityLivingBase))
							player.ridingEntity.fallDistance = 0.0F;
						flag = false;
					} else if (player.worldObj.provider.dimensionId != dim) {
						flag = false;
					}

					if (flag && player.fallDistance > DCsIronChain.harnessCheckDistance) {
						player.setPositionAndUpdate(pX, pY, pZ);
						player.fallDistance = 0.0F;
						player.worldObj.playSoundAtEntity(player, "dcironchain:chain", 1.0F, 0.7F);
					}
				}
			}
		}
	}
}
