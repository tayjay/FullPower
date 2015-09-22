package com.tayjay.fullpower.item;

import com.tayjay.fullpower.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-03.
 */
public class ItemInstantTool extends ItemFP
{

    public ItemInstantTool()
    {
        super();
        this.setUnlocalizedName("instantTool");
        this.maxStackSize = 1;
        ModItems.register(this);
    }
    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float sidex, float sidey, float sidez)
    {

        world.getBlock(x,y,z).dropBlockAsItem(world,x,y,z,1,0);
        world.getBlock(x,y,z).addDestroyEffects(world, x,y,z,0,null);
        world.setBlockToAir(x,y,z);

        return false;
    }

    /**
     * Drops the specified block items
     */

}
