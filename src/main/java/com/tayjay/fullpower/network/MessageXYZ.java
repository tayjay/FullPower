package com.tayjay.fullpower.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-10-13.
 *
 * Abstract Packet for passing xyz coordinates.
 */
public abstract class MessageXYZ<REQ extends IMessage> extends MessageBase<REQ>
{
    protected int x,y,z;

    /**
     * Empty Constructor. Do nothing.
     */
    public MessageXYZ(){}

    /**
     * Create the packet.
     * @param x X coord
     * @param y Y coord
     * @param z Z coord
     */
    public MessageXYZ(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Get xyz coords from a Tile Entity on Creation of Packet
     * @param te Tile Entity from which to get coordinates.
     */
    public MessageXYZ(TileEntity te)
    {
        this(te.xCoord,te.yCoord,te.zCoord);
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    /**
     * Get the Tile Entity at this position
     * @param world World In.
     * @return      Tile Entity at position XYZ.
     */
    public TileEntity getTileEntity(World world)
    {
        return world.getTileEntity(x,y,z);
    }
}
