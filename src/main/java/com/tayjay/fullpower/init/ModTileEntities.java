package com.tayjay.fullpower.init;

import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by tayjm_000 on 2015-09-20.
 */
public class ModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityCamoMine.class, Names.TileEntities.CAMO_MINE);
    }
}
