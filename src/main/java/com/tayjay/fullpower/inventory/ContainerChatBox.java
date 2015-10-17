package com.tayjay.fullpower.inventory;

import com.tayjay.fullpower.network.MessageHandleTextUpdate;
import com.tayjay.fullpower.network.NetworkHandler;
import com.tayjay.fullpower.tileentity.TileEntityChatBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

import java.util.List;

/**
 * Created by tayjm_000 on 2015-10-15.
 */
public class ContainerChatBox extends ContainerFP
{
    private String lastName="";
    private String lastMessage="";

    private TileEntityChatBox te;
    public ContainerChatBox(TileEntityChatBox te)
    {
        this.te = te;
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        if(!lastName.equals(te.getName()))
        {
            for(Object crafter : (List<ICrafting>)this.crafters)
            {
                if(crafter instanceof EntityPlayerMP)
                {
                    NetworkHandler.sendTo(new MessageHandleTextUpdate(te,0,te.getName()),(EntityPlayerMP)crafter);
                }
            }
            lastName = te.getName();
        }
        if(!lastMessage.equals(te.getMessage()))
        {
            for(Object crafter : (List<ICrafting>)this.crafters)
            {
                if(crafter instanceof EntityPlayerMP)
                {
                    NetworkHandler.sendTo(new MessageHandleTextUpdate(te,1,te.getMessage()),(EntityPlayerMP)crafter);
                }
            }
            lastMessage = te.getMessage();
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return te.isUseableByPlayer(player);
    }


}
