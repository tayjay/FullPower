package com.tayjay.fullpower.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-11-26.
 */
public class ItemUtil
{

    public static ItemStack newItemStack(Item item,int quantity, int meta)
    {
        return new ItemStack(item,quantity,meta);
    }



    /**
     * Create  a new ItemStack with the passed NBTCompound
     * @param item          Item to make stack from.
     * @param tagCompound   NBTCompound to apply.
     * @return              ItemStack with NBTCompound applied.
     */
    public static ItemStack newItemStackWithNBT(Item item, NBTTagCompound tagCompound)
    {
        ItemStack stack = new ItemStack(item,1,0);
        stack.setTagCompound(tagCompound);
        return stack;
    }

    /* Integer NBT */

    /**
     * Create a new ItemStack with an NBT integer tag.
     * @param item  Item to make stack from, quantity 1, metadata 0
     * @param key   Key name of tag
     * @param value Integer value of the tag
     * @return  ItemStack with NBT Integer tag.
     */
    public static ItemStack newItemStackWithNBT(Item item, String key, int value)
    {
        return newItemStackWithNBT(item, 1, 0, key, value);
    }

    /**
     * Create a new ItemStack with an NBT integer tag.
     * @param item      Item to make stack from, metadata 0
     * @param quantity  Quantity of items in stack
     * @param key       Key name of tag
     * @param value     Integer value of the tag
     * @return  ItemStack with NBT Integer tag.
     */
    public static ItemStack newItemStakWithNBT(Item item, int quantity, String key, int value)
    {
        return newItemStackWithNBT(item,quantity,0,key,value);
    }

    /**
     * Create a new ItemStack with an NBT integer tag.
     * @param item      Item to make stack from
     * @param quantity  Quantity of items in stack
     * @param meta      Metadata of the stack
     * @param key       Key name of tag
     * @param value     Integer value of the tag
     * @return  ItemStack with NBT Integer tag.
     */
    public static ItemStack newItemStackWithNBT(Item item, int quantity, int meta, String key, int value)
    {
        ItemStack stack = new ItemStack(item,quantity,meta);
        NBTHelper.setInteger(stack, key, value);
        return stack;
    }

    /* String NBT */

    /**
     * Create a new ItemStack with an NBT String tag.
     * @param item      Item to make stack from
     * @param key       Key name of tag
     * @param value     String value of the tag
     * @return  ItemStack with NBT String tag.
     */
    public static ItemStack newItemStackWithNBT(Item item, String key, String value)
    {
        return newItemStackWithNBT(item, 1, 0, key, value);
    }

    /**
     * Create a new ItemStack with an NBT String tag.
     * @param item      Item to make stack from
     * @param quantity  Quantity of items in stack
     * @param key       Key name of tag
     * @param value     String value of the tag
     * @return  ItemStack with NBT String tag.
     */
    public static ItemStack newItemStakWithNBT(Item item, int quantity, String key, String value)
    {
        return newItemStackWithNBT(item,quantity,0,key,value);
    }

    /**
     * Create a new ItemStack with an NBT String tag.
     * @param item      Item to make stack from
     * @param quantity  Quantity of items in stack
     * @param meta      Metadata of the stack
     * @param key       Key name of tag
     * @param value     String value of the tag
     * @return  ItemStack with NBT String tag.
     */
    public static ItemStack newItemStackWithNBT(Item item, int quantity, int meta, String key, String value)
    {
        ItemStack stack = new ItemStack(item,quantity,meta);
        NBTHelper.setString(stack,key,value);
        return stack;
    }

    public static EntityItem newEntityItem(World world, int x, int y, int z, ItemStack itemStack)
    {
        return new EntityItem(world,x,y,z,itemStack);
    }

    public static void setLore(ItemStack stack, String... lore)
    {
        NBTTagCompound displayTag = NBTHelper.getTagCompound(stack, "display");
        NBTTagList list = new NBTTagList();
        for(String s : lore)
            list.appendTag(new NBTTagString(s));
        displayTag.setTag("Lore", list);
        NBTHelper.setTagCompound(stack, "display", displayTag);
    }


}
