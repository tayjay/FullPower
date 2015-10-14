package com.tayjay.fullpower.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-20.
 *
 * The base for Blocks that need to use a Tile Entity.
 */
public abstract class BlockFPTileEntity extends BlockContainerFP
{
    /**
     * Constructor with parameters.
     * @param material  The material the block should be made of.
     */
    public BlockFPTileEntity(Material material)
    {
        super(material);
    }

    /**
     * Empty Constructor.
     * Defaults material to rock.
     */
    public BlockFPTileEntity()
    {
        this(Material.rock);
    }

}
