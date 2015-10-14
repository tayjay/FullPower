package com.tayjay.fullpower.gui;

import com.tayjay.fullpower.inventory.ContainerCamoMine;
import com.tayjay.fullpower.network.MessageHandleGuiButtonPress;
import com.tayjay.fullpower.network.MessageHandleTextUpdate;
import com.tayjay.fullpower.network.NetworkHandler;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

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
        super(new ContainerCamoMine(playerInventory, te),"camoMine", te);
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
        this.textbox.mouseClicked(mouseX,mouseY,button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick)
    {
        super.drawScreen(mouseX, mouseY, partialTick);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        this.textbox.drawTextBox();

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
        this.fontRendererObj.drawString("Target",120,50,0XFF000000);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        resetButton.displayString = I18n.format("gui.fullpower.camoMine.button." + (te.getTimer() == -1 ? "restart":"reset"));
    }
}
