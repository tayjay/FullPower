package com.tayjay.fullpower.gui;

import com.sun.deploy.util.ReflectionUtil;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.inventory.ContainerCamoMine;
import com.tayjay.fullpower.network.MessageHandleGuiButtonPress;
import com.tayjay.fullpower.network.MessageHandleTextUpdate;
import com.tayjay.fullpower.network.NetworkHandler;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import com.tayjay.fullpower.util.ItemUtil;
import com.tayjay.fullpower.util.RenderHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tayjm_000 on 2015-09-28.
 */
public class GuiCamoMine extends GuiFP
{
    private final TileEntityCamoMine te;
    private GuiButton resetButton;
    private GuiTextField textbox;

    public GuiCamoMine(InventoryPlayer playerInventory, TileEntityCamoMine te)
    {
        super(new ContainerCamoMine(playerInventory, te), "camoMine", te);
        this.te = te;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        //Define Buttons
        resetButton = new GuiButton(0,guiLeft + 10, guiTop + 37,40,20,""); //(ID,XPos,YPos,width,height(20 is best),text)
        buttonList.add(resetButton);

        //Used for the TextBox
        this.textbox = new GuiTextField(this.fontRendererObj, guiLeft + 100, guiTop+ 60, 70, 12);
        //this.textbox.setTextColor(-1);
        //this.textbox.setDisabledTextColour(-1);
        //this.textbox.setEnableBackgroundDrawing(false);
        this.textbox.setMaxStringLength(40);

        if(te.getTarget()!=null)
        {
            textbox.setText(te.getTarget());
        }
        else
        {
            textbox.setText("");
        }
        //******************************/Textbox
    }

    @Override
    public void onTextfieldUpdate(int id)
    {
        if(id == 0)
        {
            textbox.setText(te.getTarget());
            te.setTarget(textbox.getText());
        }
    }

    /*TextBox Code*/
    @Override
    protected void keyTyped(char chr, int keyCode)
    {
        if(this.textbox.textboxKeyTyped(chr,keyCode))
        {
            NetworkHandler.sendToServer(new MessageHandleTextUpdate(te,0,textbox.getText()));
        }
        else
        {
            super.keyTyped(chr, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);
        this.textbox.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick)
    {
        super.drawScreen(mouseX, mouseY, partialTick);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        this.textbox.drawTextBox();
        RenderHelper.drawItemStack(ItemUtil.newItemStack(ModItems.commandScroll, 1, 0), 10,10, "TEST");
        drawItemStack(ItemUtil.newItemStack(ModItems.commandScroll,1,0),20,20,"TEST");


    }

    private void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_)
    {
        float scale = 3.85f;
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);//0.0F,0.0F,32.0F default
        GL11.glScalef(scale,scale,scale);
        this.zLevel = 200.0F;
        itemRender.zLevel = 200.0F;
        FontRenderer font = null;
        if (p_146982_1_ != null) font = p_146982_1_.getItem().getFontRenderer(p_146982_1_);
        if (font == null) font = fontRendererObj;
        itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_);
        this.zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
        GL11.glPopMatrix();
    }
    /*/TextBox code*/

    /**
     * Called when a button is pressed
     * @param button The button that has been activated.
     */
    @Override
    protected void actionPerformed(GuiButton button)
    {
        if(button.id == 0)
        {
            NetworkHandler.sendToServer(new MessageHandleGuiButtonPress(te, 0));
        }
    }

    /*
        Draw text on the GUI
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        if(te.getTimer()>=0)
        {
            this.fontRendererObj.drawString(I18n.format("gui.fullpower.camoMine.timer", te.getTimer()), 10, 25, 0xFF000000);//0xAARRGGBB
        }
        this.fontRendererObj.drawString("Target", 120, 50, 0XFF000000);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        resetButton.displayString = I18n.format("gui.fullpower.camoMine.button." + (te.getTimer() == -1 ? "restart":"reset"));

    }
}
