package defeatedcrow.ironchain.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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
			
			if (plate != null && plate.getItem() != null)
			{
				if (!player.onGround && player.isOnLadder())
				{
					if (player.isCollidedHorizontally)
					{
						player.motionY = 0.32D;
					}
				}
			}
		}
	}

}
