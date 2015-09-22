package com.tayjay.fullpower.client.gui;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;

/**
 * Created by Taylar on 31/08/2015.
 */
@Cancelable
public class RenderTick
{
    private final Minecraft minecraft = Minecraft.getMinecraft();
    World world = minecraft.theWorld;

    @SubscribeEvent
    public void onRenderEvent(RenderBlockOverlayEvent event)
    {
        //LogHelper.info("In onRenderEvent!");
        if(minecraft.thePlayer.noClip)
            event.setCanceled(true);
    }

}
