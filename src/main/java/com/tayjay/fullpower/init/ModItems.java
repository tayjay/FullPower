package com.tayjay.fullpower.init;

import com.tayjay.fullpower.item.*;
import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Taylar on 30/08/2015.
 *
 * UNLOCALIZE THIS!
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID) //Stop other people from messing with your item registry
public class ModItems
{
    public static final ItemFP debugTool = new ItemDebugTool();
    public static final ItemFP effectTool = new ItemEffectTool();
    public static final ItemFP commandScroll = new ItemCommandScroll();
    public static final ItemFP instantTool = new ItemInstantTool();

    public static void init()
    {
        GameRegistry.registerItem(debugTool, "debugTool");
        GameRegistry.registerItem(effectTool, "effectTool");
        GameRegistry.registerItem(commandScroll, "commandScroll");
        GameRegistry.registerItem(instantTool, "instantTool");
    }
}
