package com.tayjay.fullpower.item;

import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.LogHelper;
import com.tayjay.fullpower.util.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

import java.util.List;
import java.util.Random;

/**
 * Created by Taylar on 02/09/2015.
 * Storing all item data in NBTTags, keep different stacks from sharing the same info(Owner, Command)
 */
public class ItemCommandScroll extends ItemFP
{

    private int coolDown = 0;

    public ItemCommandScroll()
    {
        super();
        this.setUnlocalizedName(Names.Items.COMMAND_SCROLL);
        this.maxStackSize = 1;
        ModItems.register(this);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {

        if (NBTHelper.getBoolean(itemStack, "isSet"))
        {
            list.remove("Right Click to examine text");
            //String owner = NBTHelper.getString(itemStack, "owner");
            //String command = NBTHelper.getString(itemStack, "command");

            list.add("Owner: " + NBTHelper.getString(itemStack, "owner"));
            if (NBTHelper.getString(itemStack, "owner").equals(player.getDisplayName()))
            {
                list.add(EnumChatFormatting.GREEN + "Command:" + NBTHelper.getString(itemStack, "command"));
            } else
            {
                list.add(EnumChatFormatting.RED + "Command: " + EnumChatFormatting.OBFUSCATED + NBTHelper.getString(itemStack, "command"));
            }
        } else
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
        LogHelper.info("CoolDown: " + coolDown);
        LogHelper.info("World.isRemote: " + world.isRemote);
        LogHelper.info("entityPlayer.worldObj.isRemote: " + entityPlayer.worldObj.isRemote);

        coolDown();

        if(entityPlayer.isSneaking() && entityPlayer.capabilities.isCreativeMode) // Change command in creative (MOVE THIS!)
        {
            NBTHelper.setInteger(itemStack, "commandID", NBTHelper.getInt(itemStack, "commandID")+1);
            if(NBTHelper.getInt(itemStack, "commandID")>4)
                NBTHelper.setInteger(itemStack, "commandID",0);
        }
        else
        {
            if (!world.isRemote)
            {

                if (NBTHelper.getBoolean(itemStack, "isSet"))
                {
                    LogHelper.info("Entered Function"); //DEBUG

                    if (NBTHelper.getString(itemStack, "owner") == entityPlayer.getDisplayName())
                    {
                        runCommand(itemStack, world, entityPlayer);
                        ChatHelper.send(entityPlayer, "Command: " + NBTHelper.getString(itemStack, "command"));
                        --itemStack.stackSize;
                    } else
                    {
                        ChatHelper.send(entityPlayer, "You do not understand the symbols on the page.");
                    }

                } else
                {

                    NBTHelper.setBoolean(itemStack, "isSet", true);
                    LogHelper.info("Entered Else"); // DEBUG
                    ChatHelper.send(entityPlayer, EnumChatFormatting.BLUE + "The scroll reveals its true power!");
                    NBTHelper.setBoolean(itemStack, "isSet", true);
                    NBTHelper.setString(itemStack, "owner", entityPlayer.getDisplayName());
                    NBTHelper.setString(itemStack, "command", getCommand(itemStack));


                }
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
     * @param item   The item stack, before the item is removed.
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
        if (coolDown > 0)
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

        NBTHelper.setInteger(itemStack, "commandID", random.nextInt(5));


        switch (NBTHelper.getInt(itemStack, "commandID"))
        {
            case 0:
                return "/toggleDownFall";
            case 1:
                return "/give Random";
            case 2:
                return "/kill Entity";
            case 3:
                return "/time set day";
            case 4:
                return "/time set night";
            default:
                return "ERROR";
        }
    }

    public void runCommand(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (itemStack != null || NBTHelper.getBoolean(itemStack, "isSet"))
        {
            switch (NBTHelper.getInt(itemStack, "commandID"))
            {
                case 0: // toggleDownFall
                    toggleDownfall();
                    break;
                case 1: // give Random
                    giveItem(entityPlayer);
                    break;
                case 2: // kill Entity
                    //EntityLivingBase entity = (EntityLivingBase) getEntityLookingAt(world, entityPlayer);

                    break;
                case 3: // time set day
                    setTime(world, entityPlayer, "day");
                    break;
                case 4: // time set night
                    setTime(world, entityPlayer, "night");
                    break;
                default:
                    LogHelper.error("Error running command in ItemCommandScroll");
                    LogHelper.error("Case index out of bounds:" + NBTHelper.getInt(itemStack, "commandID"));
                    break;
            }
        }
    }

    protected void toggleDownfall()
    {
        WorldInfo worldinfo = MinecraftServer.getServer().worldServers[0].getWorldInfo();
        worldinfo.setRaining(!worldinfo.isRaining());
    }

    private void giveItem(EntityPlayer entityPlayer)
    {
        EntityItem entityitem = entityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(Items.diamond, 10), false);
        entityitem.delayBeforeCanPickup = 0;
        entityitem.func_145797_a(entityPlayer.getDisplayName());
    }

    private Entity getEntityLookingAt(World world, EntityPlayer player)
    {
        ChatHelper.send(player,"Getting entity looking at.");
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if (mop != null && mop.entityHit instanceof EntityLivingBase)
        {
            return mop.entityHit;
        }
        return null;
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer entityPlayer, EntityLivingBase entity)
    {
        if(itemStack != null)
        {
            onItemRightClick(itemStack,entity.worldObj,entityPlayer);
            if (NBTHelper.getString(itemStack,"command")!=null&&NBTHelper.getString(itemStack, "command")=="/kill Entity")
            {
                if (entity != null && !entity.worldObj.isRemote)
                {
                    ChatHelper.send(entityPlayer, "Attempting to damage entity: " + entity.toString());
                    entity.attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE); // MAKE A NEW DAMAGE SOURCE
                    //entity.setHealth(0.0F);
                    //entity.setDead();
                }
            }
        }

        return true;
    }

    private void setTime(World world, EntityPlayer entityPlayer, String timeStr)
    {
        long time;
        if (timeStr == "day")
        {
            time = 1000;
        } else if (timeStr == "night")
        {
            time = 13000;
        } else
        {
            time = 0;
            LogHelper.error("Improper setTime call in ItemCommandScroll");
        }
        for (int j = 0; j < MinecraftServer.getServer().worldServers.length; ++j)
        {
            MinecraftServer.getServer().worldServers[j].setWorldTime(time);
        }
    }
}
