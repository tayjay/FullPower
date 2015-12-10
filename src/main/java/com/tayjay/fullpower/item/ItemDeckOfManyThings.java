package com.tayjay.fullpower.item;

import codechicken.lib.math.MathHelper;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.util.ArrayHelper;
import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by tayjm_000 on 2015-11-05.
 */
public class ItemDeckOfManyThings extends ItemFP
{
    private final long TIMEOUT = 20;
    public ItemDeckOfManyThings()
    {
        super();
        this.setUnlocalizedName(Names.Items.DECK_OF_MANY_THINGS);
        this.maxStackSize = 1;
        setHasSubtypes(false);
        setMaxDamage(15);
        ModItems.register(this);
    }


    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_)
    {
        super.addInformation(itemStack, player, list, p_77624_4_);
        if(!NBTHelper.hasTag(itemStack,"owner"))
        {
            list.add("Right Click to bind deck.");
        }
        else
        {
            list.remove("Right Click to bind deck.");
            list.add(1, "Owner: " + NBTHelper.getString(itemStack, "owner"));
            list.add(2, "Cards: " + (itemStack.getMaxDamage() - itemStack.getItemDamage() + 1) + "/" + (itemStack.getMaxDamage() + 1));
            list.add(3, "Current Timeout: "+(NBTHelper.getLong(itemStack,"timeout")==0 ? TIMEOUT : player.worldObj.getTotalWorldTime()-NBTHelper.getLong(itemStack,"timeout"))+"/"+TIMEOUT);
        }
    }

    /*
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        //if(!world.isRemote)
        {
            itemStack.damageItem(1, player);
        }

        return true;
    }
    */


    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        super.onUpdate(itemStack, world, entity, p_77663_4_, p_77663_5_);


        /* If the deck is on timeout */
        if(NBTHelper.getLong(itemStack, "timeout")!=0)
        {
            /* Using world time to count time when player is offline. */
            long timeoutDifference = world.getTotalWorldTime()-NBTHelper.getLong(itemStack,"timeout");

            /* Handle the way Minecraft handles it's time value reaching the long limit */
            if( timeoutDifference > TIMEOUT || timeoutDifference < -(TIMEOUT))
            {
                NBTHelper.setLong(itemStack,"timeout",0);
            }

        }


    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if(!world.isRemote)
        {

            if (NBTHelper.hasTag(itemStack, "owner") && NBTHelper.hasTag(itemStack, "timeout"))
            {
            /* If the timeout is 0, draw a new card. */
                if (NBTHelper.getLong(itemStack, "timeout") == 0)
                {
                    drawCard(itemStack, world, player);
                    itemStack.damageItem(1, player);
                    NBTHelper.setLong(itemStack, "timeout", world.getTotalWorldTime());
                } else
                {
                    ChatHelper.send(player, "Timeout time: " + NBTHelper.getLong(itemStack, "timeout"));
                    ChatHelper.send(player, "World time: " + world.getTotalWorldTime());
                }
            } else
            {
            /* Initialize NBT values */
                NBTHelper.setLong(itemStack, "timeout", 0);
                NBTHelper.setString(itemStack, "owner", player.getDisplayName());
                makeDeck(itemStack);

            }
        }
        return super.onItemRightClick(itemStack, world, player);
    }

    private void makeDeck(ItemStack itemStack)
    {
        String cards = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]";
        NBTHelper.setString(itemStack,"cards",cards);
    }

    private void drawCard(ItemStack itemStack,World world, EntityPlayer player)
    {
        ChatHelper.send(player,NBTHelper.getString(itemStack,"cards"));
        Random rand = new Random();
        int[] cards = ArrayHelper.stringToIntArray(NBTHelper.getString(itemStack,"cards"));
        int skip = rand.nextInt(cards.length);
        String card = cards[skip]+"";

        int[] newCards = new int[cards.length-1];
        int index = 0;
        for(int i = 0;i<cards.length;i++)
        {
            if(i==skip)
            {
                continue;
            }
            else
            {
                newCards[index] = cards[i];
                index++;
            }
        }
        NBTHelper.setString(itemStack, "cards", ArrayHelper.arrayToString(newCards));
        ChatHelper.send(player,card+"; "+NBTHelper.getString(itemStack,"cards"));
    }
}
