package com.tayjay.fullpower.network;

import com.tayjay.fullpower.FullPower;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by tayjm_000 on 2015-09-19.
 */
public abstract class MessageBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>
{

    @Override
    public REQ onMessage(REQ message, MessageContext ctx)
    {
        if(ctx.side == Side.SERVER)
        {
            handleServerSide(message, ctx.getServerHandler().playerEntity);
        }else{
            handleClientSide(message, null);
        }
        return null;
    }


    public abstract void handleServerSide(REQ message, EntityPlayer player);

    public abstract void handleClientSide(REQ message, EntityPlayer player);

}
