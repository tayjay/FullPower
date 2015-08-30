package com.tayjay.fullpower.init;

import com.tayjay.fullpower.item.ItemDebugTool;
import com.tayjay.fullpower.item.ItemFP;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Taylar on 30/08/2015.
 */
public class ModItems
{
    public static final ItemFP debugTool = new ItemDebugTool();

    public static void init()
    {
        GameRegistry.registerItem(debugTool, debugTool.getUnlocalizedName());
    }
}
