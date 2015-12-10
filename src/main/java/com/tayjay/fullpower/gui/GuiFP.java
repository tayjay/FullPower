package com.tayjay.fullpower.gui;

import com.tayjay.fullpower.inventory.ContainerCamoMine;
import com.tayjay.fullpower.reference.Reference;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by tayjm_000 on 2015-09-28.
 */
public abstract class GuiFP extends GuiContainer
{
    //Location of GUI Texture
    private final ResourceLocation guiTexture;
    private final IInventory inventory;

    public GuiFP(Container container, String guiTextureName, IInventory inventory)
    {
        super(container);
        //Create GUI from a passed String
        guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/" + guiTextureName + ".png");
        this.inventory = inventory;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String s = inventory.hasCustomInventoryName() ? inventory.getInventoryName() : I18n.format(inventory.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
    {
        //Bind GUI Texture to the Background Layer
        mc.getTextureManager().bindTexture(guiTexture);
        //Starting Pos on screen left, top, Starting pos of texture left, top, xSize,ySize
        this.drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);
    }

    public void onTextfieldUpdate(int id)
    {

    }

}
