package com.tayjay.fullpower.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by tayjm_000 on 2015-11-28.
 */
public class ClientUtil
{
    public static Tessellator tes()
    {
        return Tessellator.instance;
    }

    public static Minecraft mc()
    {
        return Minecraft.getMinecraft();
    }

    public static boolean  isMac()
    {
        return Minecraft.isRunningOnMac;
    }

    public static boolean isMultiplayer()
    {
        return mc().isIntegratedServerRunning();
    }
}
