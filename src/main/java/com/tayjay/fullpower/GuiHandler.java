package com.tayjay.fullpower;

import com.tayjay.fullpower.gui.GuiCamoMine;
import com.tayjay.fullpower.inventory.ContainerCamoMine;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-28.
 */
public class GuiHandler implements IGuiHandler
{
    public enum GuiIDs{
        CAMO_MINE;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case CAMO_MINE:
                return new ContainerCamoMine(player.inventory, (TileEntityCamoMine)world.getTileEntity(x,y,z));
        }
        throw new IllegalArgumentException("No GUI with id "+ ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case CAMO_MINE:
                return new GuiCamoMine(player.inventory, (TileEntityCamoMine)world.getTileEntity(x,y,z));
        }
        throw new IllegalArgumentException("No GUI with id "+ ID);
    }
}
