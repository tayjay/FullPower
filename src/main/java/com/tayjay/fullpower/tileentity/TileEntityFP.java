package com.tayjay.fullpower.tileentity;

import com.tayjay.fullpower.network.DescriptionHandler;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by tayjm_000 on 2015-09-23.
 *
 * Base Tile Entity
 */
public abstract class TileEntityFP extends TileEntity
{

    @Override
    public Packet getDescriptionPacket()
    {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(xCoord);
        buf.writeInt(yCoord);
        buf.writeInt(zCoord);
        writeToPacket(buf);
        return new FMLProxyPacket(buf, DescriptionHandler.CHANNEL);
    }

    protected abstract void writeToPacket(ByteBuf buf);

    public abstract void readFromPacket(ByteBuf buf);

    public void onGuiButtonPress(int id)
    {

    }

    public void onGuiTextfieldUpdate(int id, String text){}
}
