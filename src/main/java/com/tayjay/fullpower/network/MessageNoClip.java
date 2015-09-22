package com.tayjay.fullpower.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by tayjm_000 on 2015-09-20.
 */
public class MessageNoClip extends MessageBase<MessageNoClip>
{
    private EntityPlayerMP player;
    private boolean noClip;

    public MessageNoClip()
    {

    }

    public MessageNoClip(EntityPlayerMP player, boolean noClip)
    {
        this.player = player;
        this.noClip = noClip;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {

        //ByteBufUtils
    }

    @Override
    public void handleServerSide(MessageNoClip message, EntityPlayer player)
    {
        this.player.noClip = noClip;
    }

    @Override
    public void handleClientSide(MessageNoClip message, EntityPlayer player)
    {
        this.player.noClip = noClip;
    }
}
