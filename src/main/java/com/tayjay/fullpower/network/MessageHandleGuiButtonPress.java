package com.tayjay.fullpower.network;

import com.tayjay.fullpower.tileentity.TileEntityFP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by tayjm_000 on 2015-10-11.
 */
public class MessageHandleGuiButtonPress extends MessageXYZ<MessageHandleGuiButtonPress>
{
    private int id;

    public MessageHandleGuiButtonPress()
    {

    }

    public MessageHandleGuiButtonPress(TileEntityFP te, int id)
    {
        super(te);
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeInt(id);
    }

    @Override
    public void handleClientSide(MessageHandleGuiButtonPress message, EntityPlayer player)
    {

    }

    @Override
    public void handleServerSide(MessageHandleGuiButtonPress message, EntityPlayer player)
    {
        //TileEntity te = player.worldObj.getTileEntity(message.x,message.y,message.z);
        TileEntity te = message.getTileEntity(player.worldObj);
        if(te instanceof TileEntityFP)
        {
            ((TileEntityFP) te).onGuiButtonPress(message.id);
        }
    }
}
