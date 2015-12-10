package com.tayjay.fullpower.handler;

import com.tayjay.fullpower.util.ItemUtil;
import com.tayjay.fullpower.util.RenderHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by tayjm_000 on 2015-09-19.
 */
public class TickHandler
{

    Minecraft mc = Minecraft.getMinecraft();
    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent evt)
    {
        double doubleX = mc.thePlayer.posX - 0.5;
        double doubleY = mc.thePlayer.posY + 0.1;
        double doubleZ = mc.thePlayer.posZ - 0.5;

        GL11.glPushMatrix();
        GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
        GL11.glColor3ub((byte)255,(byte)0,(byte)0);
        float mx = 9;
        float my = 70;
        float mz = 9;
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(mx + 4.4f, my, mz + 0.4f);
        GL11.glVertex3f(mx - 0.4f, my, mz - 0.4f);
        GL11.glVertex3f(mx+0.4f,my,mz-0.4f);
        GL11.glVertex3f(mx-0.4f,my,mz+0.4f);
        GL11.glVertex3f(mx-0.2f,my+0.4f,mz-0.2f);
        GL11.glVertex3f(mx+0.2f,my-0.4f,mz+0.2f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
/*
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
*/
}
