package com.tayjay.fullpower.init;

import com.tayjay.fullpower.block.BlockAreaEffect;
import com.tayjay.fullpower.block.BlockFP;
import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Taylar on 31/08/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID) //Stops other people form messing with your block registry
public class ModBlocks
{
    public static final BlockFP areaEffect = new BlockAreaEffect();

    public static void init()
    {
        GameRegistry.registerBlock(areaEffect, "areaEffect");
    }
}
