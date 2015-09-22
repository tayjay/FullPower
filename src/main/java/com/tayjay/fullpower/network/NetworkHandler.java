package com.tayjay.fullpower.network;

import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by tayjm_000 on 2015-09-19.
 */
public class NetworkHandler
{
    private static SimpleNetworkWrapper INSTANCE;

    public static void preInit()
    {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

        INSTANCE.registerMessage(MessageExplode.class,MessageExplode.class,0, Side.SERVER);
    }

    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player)
    {
        INSTANCE.sendTo(message, player);
    }

}
