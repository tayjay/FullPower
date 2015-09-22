package com.tayjay.fullpower.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-20.
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

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }

    public void onBlockAdded(World world, int x, int y, int z){
        super.onBlockAdded(world, x, y, z);
    }

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
