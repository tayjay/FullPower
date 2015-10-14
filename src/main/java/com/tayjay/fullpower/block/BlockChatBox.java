package com.tayjay.fullpower.block;

import com.tayjay.fullpower.creativetab.CreativeTabFP;
import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.util.ChatHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by tayjm_000 on 2015-10-11.
 */
public class BlockChatBox extends BlockFP
{
    private String msg = "Signal!";

    public BlockChatBox()
    {
        super();
        this.setBlockName(Names.Blocks.CHAT_BOX);
        this.setBlockTextureName(Names.Blocks.CHAT_BOX);
        //Register this block
        ModBlocks.register(this);
        this.setCreativeTab(CreativeTabFP.FP_TAB);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neightbour)
    {
        boolean flag = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);
        int l = world.getBlockMetadata(x, y, z);
        boolean flag1 = (l & 8) != 0;

        if (flag && !flag1)
        {
            world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
            world.setBlockMetadataWithNotify(x, y, z, l | 8, 4);
            ChatHelper.send(msg);
        }
        else if (!flag && flag1)
        {
            world.setBlockMetadataWithNotify(x, y, z, l & -9, 4);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random p_149674_5_)
    {

    }
}
