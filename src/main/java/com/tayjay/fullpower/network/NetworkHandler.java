package com.tayjay.fullpower.network;

import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

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
        INSTANCE.registerMessage(MessageHandleGuiButtonPress.class,MessageHandleGuiButtonPress.class,1,Side.SERVER);
    }

    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player)
    {
        INSTANCE.sendTo(message, player);
    }

    public static void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point)
    {
        INSTANCE.sendToAllAround(message, point);
    }

    /**
     * Will send a packet to all players within 64 blocks of the XYZ position in XYZ packet
     * @param message Message being sent
     * @param world World to look in.
     */
    public static void sendToAllAround(MessageXYZ message, World world)
    {
        INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(world.provider.dimensionId,message.x,message.y,message.z,64D));
    }

    public static void sentToAll(IMessage message)
    {
        INSTANCE.sendToAll(message);
    }

    public void sendToDimension(IMessage message, int dimensionId)
    {
        INSTANCE.sendToDimension(message,dimensionId);
    }

}
