package com.tayjay.fullpower.init;

import com.tayjay.fullpower.item.ItemDebugTool;
import com.tayjay.fullpower.item.ItemEffectTool;
import com.tayjay.fullpower.item.ItemFP;
import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Taylar on 30/08/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID) //Stop other people from messing with your item registry
public class ModItems
{
    public static final ItemFP debugTool = new ItemDebugTool();
    public static final ItemFP effectTool = new ItemEffectTool();

    public static void init()
    {
        GameRegistry.registerItem(debugTool, "debugTool");
        GameRegistry.registerItem(effectTool, "effectTool");
    }
}
