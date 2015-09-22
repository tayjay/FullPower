package com.tayjay.fullpower.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

/**
 * Created by tayjm_000 on 2015-09-19.
 */
public class TickHandler
{
    //Called whenever the player is updated or ticked.
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.player.noClip)
        {
            event.player.capabilities.isFlying=true;
        }
    }

    //Called when the client ticks.
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {

    }

    //Called when the server ticks. Usually 20 ticks a second.
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {

    }

    //Called when a new frame is displayed (See fps)
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {

    }

    //Called when the world ticks
    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {

    }
}
