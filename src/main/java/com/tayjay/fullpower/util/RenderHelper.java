package com.tayjay.fullpower.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import sun.java2d.pipe.RenderingEngine;

/**
 * Created by tayjm_000 on 2015-11-26.
 */
public class RenderHelper
{
    private static Minecraft mc;
    private static RenderItem itemRender;
    private static FontRenderer fontRendererObj;
    private static float zLevel;
    public static void init()
    {
        mc = Minecraft.getMinecraft();
        itemRender = new RenderItem();
        fontRendererObj = mc.fontRenderer;
        zLevel = itemRender.zLevel;
    }

    public static void drawItemStack(ItemStack itemStack, int x, int y, String p_146982_4_)
    {
        //todo: Dynamic Scaling, positioning, custom layer
        GL11.glPushMatrix();
        float defaultzLevel = itemRender.zLevel;
        float scale = 3f;
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);//0.0F,0.0F,32.0F default
        GL11.glScalef(scale, scale, scale);
        zLevel = 200.0F;
        itemRender.zLevel = 10.0F;
        FontRenderer font = null;
        if (itemStack != null) font = itemStack.getItem().getFontRenderer(itemStack);
        if (font == null) font = fontRendererObj;
        //itemRender.doRender(ItemUtil.newEntityItem(mc.theWorld,(int)mc.thePlayer.posX,(int)mc.thePlayer.posY,(int)mc.thePlayer.posZ,itemStack),mc.thePlayer.posX,mc.thePlayer.posX,mc.thePlayer.posX,2f,2f);
        //itemRender.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), itemStack, x, y);
        itemRender.renderItemIntoGUI(font, mc.getTextureManager(), itemStack, x, y);
        zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
        itemRender.zLevel = defaultzLevel;
        GL11.glPopMatrix();
    }
}
