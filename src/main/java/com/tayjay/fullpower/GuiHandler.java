package com.tayjay.fullpower;

import com.tayjay.fullpower.gui.GuiCamoMine;
import com.tayjay.fullpower.gui.GuiCard;
import com.tayjay.fullpower.gui.GuiChatBox;
import com.tayjay.fullpower.inventory.ContainerCamoMine;
import com.tayjay.fullpower.inventory.ContainerChatBox;
import com.tayjay.fullpower.inventory.ContainerEmpty;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import com.tayjay.fullpower.tileentity.TileEntityChatBox;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-28.
 */
public class GuiHandler implements IGuiHandler
{
    public enum GuiIDs{
        CAMO_MINE,
        CHAT_BOX,
        CARD
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case CAMO_MINE:
                return new ContainerCamoMine(player.inventory, (TileEntityCamoMine)world.getTileEntity(x,y,z));
            case CHAT_BOX:
                return new ContainerChatBox((TileEntityChatBox) world.getTileEntity(x,y,z));
            case CARD:
                return new ContainerEmpty();
        }
        throw new IllegalArgumentException("No Container with id "+ ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case CAMO_MINE:
                return new GuiCamoMine(player.inventory, (TileEntityCamoMine)world.getTileEntity(x,y,z));
            case CHAT_BOX:
                return new GuiChatBox(player, (TileEntityChatBox)world.getTileEntity(x,y,z));
            case CARD:
                return new GuiCard(player,"card");
        }
        throw new IllegalArgumentException("No GUI with id "+ ID);
    }
}
