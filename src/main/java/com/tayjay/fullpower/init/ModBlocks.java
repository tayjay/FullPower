package com.tayjay.fullpower.init;

import com.tayjay.fullpower.block.BlockAreaEffect;
import com.tayjay.fullpower.block.BlockCamoMine;
import com.tayjay.fullpower.block.BlockDiamondGold;
import com.tayjay.fullpower.block.BlockFP;
import com.tayjay.fullpower.handler.ConfigHandler;
import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * Created by Taylar on 31/08/2015.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID) //Stops other people form messing with your block registry
public class ModBlocks
{
    public static BlockFP areaEffect;
    public static BlockFP diamondGold;
    public static BlockFP camoMine;

    public static void init()
    {
        areaEffect = new BlockAreaEffect();
        diamondGold = new BlockDiamondGold();
        camoMine = new BlockCamoMine();

        //GameRegistry.registerBlock(areaEffect, "areaEffect");
    }










    public static void register(BlockFP block)
    {
        String name = block.getUnwrappedUnlocalizedName(block.getUnlocalizedName());
        if(isEnabled(block))GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
    }

    public static void register(BlockFP block, Class<? extends ItemBlock> item)
    {
        String name = block.getUnwrappedUnlocalizedName(block.getUnlocalizedName());
        if (isEnabled(block))GameRegistry.registerBlock(block, item, name.substring(name.indexOf(":") + 1));
    }

    public static void registerOther(Block block)
    {
        String name = block.getUnlocalizedName().substring(block.getUnlocalizedName().indexOf(".") + 1);
        if (isEnabled(block)) GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
    }

    public static boolean isEnabled(Block block)
    {
        //return !ConfigHandler.disabledNamesList.contains(block.getUnlocalizedName());
        return true;
    }
}
