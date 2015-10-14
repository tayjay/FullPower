package com.tayjay.fullpower.init;

import com.tayjay.fullpower.block.*;
import com.tayjay.fullpower.handler.ConfigHandler;
import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * Created by Taylar on 31/08/2015.
 *
 * Register blocks to be used in the mod.
 */

@GameRegistry.ObjectHolder(Reference.MOD_ID) //Stops other people form messing with your block registry
public class ModBlocks
{
    /**
     * Declare all Blocks
     */
    public static BlockFP areaEffect;
    public static BlockFP diamondGold;
    public static BlockFP camoMine;
    public static BlockFP chatBox;

    /**
     * Initiate each block object
     */
    public static void init()
    {
        areaEffect = new BlockAreaEffect();
        diamondGold = new BlockDiamondGold();
        camoMine = new BlockCamoMine();
        chatBox = new BlockChatBox();

        //GameRegistry.registerBlock(areaEffect, "areaEffect");
    }


    /**
     * Register the block.
     *
     * @param block Block to be Registered.
     */
    public static void register(BlockFP block)
    {
        String name = block.getUnwrappedUnlocalizedName(block.getUnlocalizedName());
        if(isEnabled(block))GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
    }

    /**
     * Register the block.
     *
     * @param block Block to be Registered
     * @param item
     */
    public static void register(BlockFP block, Class<? extends ItemBlock> item)
    {
        String name = block.getUnwrappedUnlocalizedName(block.getUnlocalizedName());
        if (isEnabled(block))GameRegistry.registerBlock(block, item, name.substring(name.indexOf(":") + 1));
    }

    /**
     * Register another block.
     *
     * @param block Block to be registered
     */
    public static void registerOther(Block block)
    {
        String name = block.getUnlocalizedName().substring(block.getUnlocalizedName().indexOf(".") + 1);
        if (isEnabled(block)) GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
    }

    /**
     * Look through config to find if the block param is allowed to be registered.
     *
     * @param block Block to check
     * @return      Is the block NOT on the blacklist
     */
    public static boolean isEnabled(Block block)
    {
        //return !ConfigHandler.disabledNamesList.contains(block.getUnlocalizedName());
        return true;
    }
}
