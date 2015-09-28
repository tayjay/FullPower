package com.tayjay.fullpower.network;

import com.tayjay.fullpower.FullPower;
import com.tayjay.fullpower.reference.Reference;
import com.tayjay.fullpower.tileentity.TileEntityFP;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by tayjm_000 on 2015-09-23.
 */
@ChannelHandler.Sharable
public class DescriptionHandler extends SimpleChannelInboundHandler<FMLProxyPacket>
{
    public static final String CHANNEL = Reference.MOD_ID+" Description";

    static {
        NetworkRegistry.INSTANCE.newChannel(CHANNEL, new DescriptionHandler());
    }

    public static void init()
    {
        //Does nothing here. Used to catch an exception
    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception
    {
        ByteBuf buf = msg.payload();
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        TileEntity te = FullPower.proxy.getClientPlayer().worldObj.getTileEntity(x,y,z);
        if(te instanceof TileEntityFP)
        {
            ((TileEntityFP)te).readFromPacket(buf);
        }
    }
}
