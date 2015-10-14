package com.tayjay.fullpower.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-20.
 *
 * Create the Tile Entity for a block.
 */
public class BlockContainerFP extends BlockFP implements ITileEntityProvider
{

    public BlockContainerFP(final Material material) {
        super(material);
        this.isBlockContainer = true;
    }

    public BlockContainerFP() {
        super(Material.rock);
        this.isBlockContainer = true;
    }

    /**
     * Returns a new Tile Entity.
     * <p>
     * Overridden in child classes.
     *
     * @param world     World of Block
     * @param metaData  Block's metadata
     * @return          TileEntity
     */
    @Override
    public TileEntity createNewTileEntity(World world, int metaData) {
        return null;
    }

    /**
     * Called when the block is added/placed.
     *
     * @param world The block's world.
     * @param x     X location
     * @param y     Y location
     * @param z     Z location
     */
    public void onBlockAdded(World world, int x, int y, int z){
        super.onBlockAdded(world, x, y, z);
    }

    /**
     * Called when the block is broken
     * Remove the tile entity associated with block.
     *
     * @param world Block's world
     * @param x     X location
     * @param y     Y location
     * @param z     Z location
     * @param block The block
     * @param meta  Block's Metadata
     */
    public void breakBlock(World world, int x, int y, int z, Block block, int meta){
        super.breakBlock(world, x, y, z, block, meta);
        world.removeTileEntity(x, y, z);
    }

    public boolean onBlockEventReceived(World world, int x, int y, int z, int p_149696_5_, int p_149696_6_){
        super.onBlockEventReceived(world, x, y, z, p_149696_5_, p_149696_6_);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }
}
