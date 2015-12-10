package com.tayjay.fullpower.tileentity;

import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by tayjm_000 on 2015-10-15.
 */
public class TileEntityChatBox extends TileEntityFP implements IInventory
{

    private String name;
    private String message;

    public EntityPlayer getOwner()
    {
        return owner;
    }

    public void setOwner(EntityPlayer owner)
    {
        this.owner = owner;
    }

    private EntityPlayer owner;

    public TileEntityChatBox()
    {
        super();
        name = "nullChatBoxName";
        message = "nullChatBoxMessage";
        owner = null;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }


    public void onGuiTextfieldUpdate(int id, String text)
    {
        if(id==0)
        {
            name = text;
            markDirty();
        }
        else if(id==1)
        {
            message = text;
            markDirty();
        }
        else
        {
            LogHelper.error("Invalid TextField id in TileEntityChatBox: " + id);
        }
    }

    public void onGuiButtonPress(int id)
    {
    }

    @Override
    protected void writeToPacket(ByteBuf buf)
    {
    }

    @Override
    public void readFromPacket(ByteBuf buf)
    {

    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        name = tag.getString("name");
        message = tag.getString("message");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setString("name",name);
        tag.setString("message",message);
    }



    public void sendMessage()
    {
        ChatHelper.send("<"+name+"> "+message);
    }


    @Override
    public int getSizeInventory()
    {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_)
    {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
    {

    }

    @Override
    public String getInventoryName()
    {
        return ModBlocks.chatBox.getUnlocalizedName()+".name";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return false;
    }

}
