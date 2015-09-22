package com.tayjay.fullpower.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by tayjm_000 on 2015-09-20.
 */
public class MessageExplode extends MessageBase<MessageExplode>
{
    private float explosionSize;

    public MessageExplode()
    {

    }

    public MessageExplode(float explosionSize)
    {
        this.explosionSize = explosionSize;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        //explosionSize = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeFloat(explosionSize);
        //ByteBufUtils
    }

    @Override
    public void handleServerSide(MessageExplode message, EntityPlayer player)
    {
        player.worldObj.createExplosion(player,player.posX,player.posY,player.posZ,message.explosionSize, true);
    }

    @Override
    public void handleClientSide(MessageExplode message, EntityPlayer player)
    {

    }
}
