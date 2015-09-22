package com.tayjay.fullpower.block;

import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-20.
 */
public class BlockCamoMine extends BlockFPTileEntity
{
    public BlockCamoMine()
    {
        super();
        this.setBlockName(Names.Blocks.CAMO_MINE);
        this.setBlockTextureName(Names.Blocks.CAMO_MINE);
        ModBlocks.register(this);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityCamoMine();
    }
}
