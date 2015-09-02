package com.tayjay.fullpower.item;

import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.LogHelper;
import com.tayjay.fullpower.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Random;

/**
 * Created by Taylar on 02/09/2015.
 */
public class ItemCommandScroll extends ItemFP
{

    private int coolDown = 0;


    public ItemCommandScroll()
    {
        super();
        this.setUnlocalizedName("commandScroll");
        this.maxStackSize = 1;

    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {

            if(NBTHelper.getBoolean(itemStack,"isSet"))
            {
                list.remove("Right Click to examine text");
                String owner = NBTHelper.getString(itemStack, "owner");
                String command = NBTHelper.getString(itemStack, "command");

                list.add("Owner: " + NBTHelper.getString(itemStack, "owner"));
                if (NBTHelper.getString(itemStack, "owner").equals(player.getDisplayName()))
                {
                    list.add(EnumChatFormatting.GREEN + "Command:" + NBTHelper.getString(itemStack, "command"));
                } else
                {
                    list.add(EnumChatFormatting.RED + "Command: " + EnumChatFormatting.OBFUSCATED + NBTHelper.getString(itemStack, "command"));
                }
            }
            else
            {
                list.add("Right Click to examine text");
            }
            /*
            String owner = itemStack.stackTagCompound.getString("owner");
            int code = itemStack.stackTagCompound.getInteger("code");
            list.add("owner: " + owner);
            if (owner.equals(player.username)) {
                list.add(EnumChatFormatting.GREEN + "code: " + code);
            } else {
                list.add(EnumChatFormatting.RED + "code: "
                        + EnumChatFormatting.OBFUSCATED + code);
            }
            */




    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        LogHelper.info("CoolDown: "+coolDown);
        LogHelper.info("World.isRemote: "+ world.isRemote);
        LogHelper.info("entityPlayer.worldObj.isRemote: "+ entityPlayer.worldObj.isRemote);


        if (coolDown == 0)
        {
            coolDown();
            if (NBTHelper.getBoolean(itemStack,"isSet"))
            {
                LogHelper.info("Entered Function"); //DEBUG


                    ChatHelper.send(entityPlayer, "Command: " + NBTHelper.getString(itemStack, "command"));
                    --itemStack.stackSize;


            }
            else
            {
                NBTHelper.setBoolean(itemStack,"isSet",true);
                LogHelper.info("Entered Else"); // DEBUG
                ChatHelper.send(entityPlayer, EnumChatFormatting.DARK_BLUE + "The scroll reveals its true power!");
                NBTHelper.setBoolean(itemStack, "isSet", true);
                NBTHelper.setString(itemStack, "owner", entityPlayer.getDisplayName());
                NBTHelper.setString(itemStack, "command", getCommand(itemStack));
                NBTHelper.removeTag(itemStack,"commandIDTEMP");

            }
        }
        return itemStack;
    }
    /**
     * Called when a player drops the item into the world,
     * returning false from this will prevent the item from
     * being removed from the players inventory and spawning
     * in the world
     *
     * @param player The player that dropped the item
     * @param item The item stack, before the item is removed.
     */
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        return false;
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if(coolDown>0)
        {
            coolDown--;
        }
    }

    public void coolDown()
    {
        coolDown = 20;
    }

    public String getCommand(ItemStack itemStack)
    {
        Random random = new Random();

        NBTHelper.setInteger(itemStack,"commandIDTEMP",random.nextInt(5));


        switch (NBTHelper.getInt(itemStack, "commandIDTEMP"))
        {
            case 0:
                return "/toggleDownFall";
            case 1:
                return "/give Random";
            case 2:
                return "/kill Entity";
            case 3:
                return "/time set morning";
            case 4:
                return "/time set evening";
            default:
                return "ERROR";
        }
    }
}
