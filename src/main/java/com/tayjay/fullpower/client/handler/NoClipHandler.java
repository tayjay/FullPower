package com.tayjay.fullpower.client.handler;

import com.tayjay.fullpower.network.MessageNoClip;
import com.tayjay.fullpower.network.NetworkHandler;
import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;

import java.util.Locale;

/**
 * Created by tayjm_000 on 2015-09-19.
 */
@Cancelable
public class NoClipHandler
{
    private static final Minecraft minecraft = Minecraft.getMinecraft();
    World world = minecraft.theWorld;
    private static EntityPlayer entityPlayer=null;
    private boolean firstTick = true;

    public NoClipHandler()
    {

    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(firstTick)
        {
            entityPlayer = event.player;
            firstTick = false;
        }
        //LogHelper.info("In TickEvent");
        if(this.entityPlayer!=null && this.entityPlayer.noClip)
        {
            event.player.capabilities.isFlying = true;
            event.player.addPotionEffect(new PotionEffect(16,1600,0));
        }
    }

    /*
        Hocked into the Render Event to allow NoClip players to see though walls.
     */
    @SubscribeEvent
    public void onRenderEvent(RenderBlockOverlayEvent event)
    {
        //LogHelper.info("In onRenderEvent!");
        if(entityPlayer.noClip)
            event.setCanceled(true);
    }

    public static void setNoClip(EntityPlayer player, boolean noClip)
    {
        entityPlayer = player;
        if (!player.worldObj.isRemote)
        {
            ChatHelper.send(player, "NoClip: " + player.noClip);
        }
        player.noClip = noClip;
        if (!player.noClip) // Turning NoClip off
        {
            player.capabilities.isFlying = false;
            player.removePotionEffect(16);
            //player.capabilities.setFlySpeed(3.0F);
            if (!player.capabilities.isCreativeMode)
            {
                player.capabilities.disableDamage = false;
            }
        } else // Turning No Clip on
        {
            player.capabilities.disableDamage = true;
        }
    }

}
