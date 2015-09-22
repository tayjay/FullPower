package com.tayjay.fullpower.block;

import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-20.
 */
public class BlockDiamondGold extends BlockFP
{
    public BlockDiamondGold()
    {
        super();
        this.setBlockName(Names.Blocks.DIAMOND_GOLD);
        this.setBlockTextureName(Names.Blocks.DIAMOND_GOLD);
        ModBlocks.register(this);
    }



    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if(!world.isRemote)
            world.createExplosion(player,(double)x,(double)y,(double)z,30,true);
        return false;
    }
}
