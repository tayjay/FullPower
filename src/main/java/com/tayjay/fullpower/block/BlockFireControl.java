package com.tayjay.fullpower.block;

import com.tayjay.fullpower.creativetab.CreativeTabFP;
import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.tileentity.TileEntityFireControl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by tayjm_000 on 2015-11-01.
 */
public class BlockFireControl extends BlockFPTileEntity
{

    public BlockFireControl()
    {
        super();
        setBlockName("Fire Control");
        ModBlocks.register(this);
        this.setCreativeTab(CreativeTabFP.FP_TAB);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {

        return new TileEntityFireControl();
    }


}
