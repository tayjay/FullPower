package com.tayjay.fullpower.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

/**
 * Created by Taylar on 30/08/2015.
 */
public class ChatHelper
{

    public static void send(String msg) //Send to world
    {
        MinecraftServer mcServer = MinecraftServer.getServer();
        mcServer.getConfigurationManager().sendChatMsg(new ChatComponentText(msg));
    }

    public static void send(EntityPlayer player, String msg) //Send to player
    {
        player.addChatComponentMessage(new ChatComponentText(msg));
    }
}
