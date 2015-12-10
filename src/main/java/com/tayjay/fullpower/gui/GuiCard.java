package com.tayjay.fullpower.gui;

import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.inventory.ContainerEmpty;
import com.tayjay.fullpower.inventory.ContainerFP;
import com.tayjay.fullpower.item.ItemCard;
import com.tayjay.fullpower.reference.Reference;
import com.tayjay.fullpower.util.RenderHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by tayjm_000 on 2015-12-10.
 */
public class GuiCard extends GuiContainer
{
    //Location of GUI Texture
    private ResourceLocation guiTexture;
    private EntityPlayer player;
    private ItemStack itemToRender;

    public GuiCard(Container p_i1072_1_)
    {
        super(p_i1072_1_);
    }

    public GuiCard(EntityPlayer player, String guiTextureName)
    {
        super(new ContainerEmpty());
        guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/" + guiTextureName + ".png");
        xSize=256;
        ySize=256;
        this.player = player;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);
        ItemStack heldItem = player.getHeldItem();
        if(heldItem.getItem() instanceof ItemCard)
        {
            switch (heldItem.getItemDamage())
            {
                case 0:
                    itemToRender=new ItemStack(Blocks.stone);
                    break;
                case 1:
                    itemToRender=new ItemStack(Blocks.grass);
                    break;
                default:
                    itemToRender=new ItemStack(ModItems.card);
            }
        }
        else
        {
            itemToRender=new ItemStack(ModItems.card);
        }
        RenderHelper.drawItemStack(itemToRender, guiLeft / 3 - 16, guiTop / 3, "");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        //Bind GUI Texture to the Background Layer
        mc.getTextureManager().bindTexture(guiTexture);
        //Starting Pos on screen left, top, Starting pos of texture left, top, xSize,ySize
        this.drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);
    }
}
