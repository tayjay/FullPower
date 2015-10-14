package com.tayjay.fullpower.network;

import com.tayjay.fullpower.gui.GuiFP;
import com.tayjay.fullpower.tileentity.TileEntityFP;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by tayjm_000 on 2015-10-13.
 */
public class MessageHandleTextUpdate extends MessageXYZ<MessageHandleTextUpdate>
{
    private int id;
    private String text;

    public MessageHandleTextUpdate(){}

    public MessageHandleTextUpdate(TileEntityFP te, int id, String text)
    {
        super(te);
        this.id = id;
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        id = buf.readInt();
        text = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeInt(id);
        ByteBufUtils.writeUTF8String(buf, text);
    }

    @Override
    public void handleClientSide(MessageHandleTextUpdate message, EntityPlayer player)
    {
        handleServerSide(message,player);
        GuiScreen gui = Minecraft.getMinecraft().currentScreen;
        if(gui instanceof GuiFP)
        {
            ((GuiFP)gui).onTextfieldUpdate(message.id);
        }
    }

    @Override
    public void handleServerSide(MessageHandleTextUpdate message, EntityPlayer player)
    {
        TileEntity te = message.getTileEntity(player.worldObj);
        if(te instanceof TileEntityFP)
        {
            ((TileEntityFP)te).onGuiTextfieldUpdate(message.id,message.text);
        }
    }
}
