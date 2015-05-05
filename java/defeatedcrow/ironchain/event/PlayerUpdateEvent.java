package defeatedcrow.ironchain.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.ironchain.DCsIronChain;
import defeatedcrow.ironchain.IronChainLog;

public class PlayerUpdateEvent {
	
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		
		if (entity != null && entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack plate = player.inventory.armorInventory[2];
			ItemStack belt = player.inventory.armorInventory[1];
			
			if (plate != null && plate.getItem() != null && plate.getItem() == DCsIronChain.sagyougi)
			{
				if (!player.onGround && player.isOnLadder())
				{
					if (player.isCollidedHorizontally)
					{
						player.motionY = 0.32D;
					}
				}
			}
			
			if (!player.worldObj.isRemote && belt != null && belt.getItem() != null && belt.getItem() == DCsIronChain.anzenBelt)
			{
				NBTTagCompound nbt = belt.getTagCompound();
				boolean flag = false;
				double pX = 0.5D;
				double pY = 0.5D;
				double pZ = 0.5D;
				int dim = 0;
				
				if (nbt != null)
				{
					pX = nbt.getInteger("DCIanzenX") + 0.5D;
					pY = nbt.getInteger("DCIanzenY") + 0.5D;
					pZ = nbt.getInteger("DCIanzenZ") + 0.5D;
					dim = nbt.getInteger("DCIanzenDim");
					flag = nbt.hasKey("DCIanzenDim");
				}
				
				if (player.onGround)
				{
					if (player.riddenByEntity == null && player.ridingEntity == null)
					{
						if (nbt == null) nbt = new NBTTagCompound();
						nbt.setInteger("DCIanzenX", MathHelper.floor_double(player.posX));
						nbt.setInteger("DCIanzenY", MathHelper.floor_double(player.posY));
						nbt.setInteger("DCIanzenZ", MathHelper.floor_double(player.posZ));
						nbt.setInteger("DCIanzenDim", MathHelper.floor_double(player.worldObj.provider.dimensionId));
						nbt.setString("DCIanzenDimName", player.worldObj.provider.getDimensionName());
						belt.setTagCompound(nbt);
					}
				}
				
				if (flag && !player.onGround && !player.isInWater())
				{
					if (player.riddenByEntity != null || player.ridingEntity != null
							|| player.capabilities.isFlying)
					{
						player.fallDistance = 0.0F;
						flag = false;
					}
					else if (player.worldObj.provider.dimensionId != dim)
					{ 
						flag = false;
					}
					
					if (flag && player.fallDistance > DCsIronChain.harnessCheckDistance)
					{
						player.setPositionAndUpdate(pX, pY, pZ);
						player.fallDistance = 0.0F;
						player.worldObj.playSoundAtEntity(player, "dcironchain:chain", 1.0F, 0.7F);
					}
				}
			}
		}
	}

}
