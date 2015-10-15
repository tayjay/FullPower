package com.tayjay.fullpower.event;

import com.tayjay.fullpower.handler.TickHandler;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.List;

/**
 * Created by tayjm_000 on 2015-10-15.
 */
public class EventHandlerFP
{
    @SubscribeEvent
    public void addPigDrops(LivingDropsEvent event)
    {
        if(event.entityLiving instanceof EntityPig && event.entityLiving.getRNG().nextInt(3) == 0)
        {
            ItemStack commandScroll = new ItemStack(ModItems.commandScroll);
            event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ));
        }
    }

    /*
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        final int RADIUS = 3;
        if(event.side == Side.SERVER && event.phase == TickEvent.Phase.END)
        {
            List<Entity> entities = event.player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(event.player.posX - RADIUS, event.player.posY - RADIUS, event.player.posZ - RADIUS, event.player.posX + RADIUS, event.player.posY + RADIUS, event.player.posZ + RADIUS));
            for(Entity entity : entities)
            {
                if(entity != event.player)
                {
                    entity.setVelocity(0,1,0);
                }
            }
        }
            //LogHelper.info("Player " + event.player.getCommandSenderName() + " Ticked " + event.phase);
    }
    */
}
