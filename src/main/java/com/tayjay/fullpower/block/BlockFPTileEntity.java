package com.tayjay.fullpower.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-20.
 */
public abstract class BlockFPTileEntity extends BlockContainerFP
{
    public BlockFPTileEntity(Material material)
    {
        super(material);
    }

    public BlockFPTileEntity()
    {
        this(Material.rock);
    }

}
