package com.tayjay.fullpower.init;

import com.tayjay.fullpower.item.*;
import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Taylar on 30/08/2015.
 *
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID) //Stop other people from messing with your item registry
public class ModItems
{
    /**
     * Declare the items.
     */
    public static ItemFP debugTool;
    public static ItemFP effectTool;
    public static ItemFP commandScroll;
    public static ItemFP instantTool;
    public static ItemFP card;
    public static ItemFP useEnergy;
    public static ItemFP deckOfManyThings;

    /**
     * Initiate the Item objects
     */
    public static void init()
    {
        debugTool = new ItemDebugTool();
        effectTool = new ItemEffectTool();
        commandScroll = new ItemCommandScroll();
        instantTool = new ItemInstantTool();
        card = new ItemCard();
        useEnergy = new ItemUseEnergy();
        deckOfManyThings = new ItemDeckOfManyThings();
        /*
        GameRegistry.registerItem(debugTool, "debugTool");
        GameRegistry.registerItem(effectTool, "effectTool");
        GameRegistry.registerItem(commandScroll, "commandScroll");
        GameRegistry.registerItem(instantTool, "instantTool");
        */
    }

    /**
     * Try to register an item.
     *
     * @param item  Item to register.
     */
    public static void register(final ItemFP item)
    {
        String name = item.getUnwrappedUnlocalizedName(item.getUnlocalizedName());
        if(isEnabled(item)) GameRegistry.registerItem(item, name.substring(name.indexOf(":") + 1));
    }

    /**
     * Check is an item can be registered.
     *
     * @param item  Item to check.
     * @return      Item is NOT on blacklist.
     */
    public static boolean isEnabled(Item item)
    {
        //return !ConfigHandler.disabledNamesList.contains(item.getUnlocalizedName());
        return true;
    }
}
