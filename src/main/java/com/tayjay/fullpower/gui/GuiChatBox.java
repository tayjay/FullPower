package com.tayjay.fullpower.gui;

import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.inventory.ContainerChatBox;
import com.tayjay.fullpower.network.MessageHandleTextUpdate;
import com.tayjay.fullpower.network.NetworkHandler;
import com.tayjay.fullpower.tileentity.TileEntityChatBox;
import com.tayjay.fullpower.util.ItemUtil;
import com.tayjay.fullpower.util.LogHelper;
import com.tayjay.fullpower.util.RenderHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

/**
 * Created by tayjm_000 on 2015-10-15.
 */
public class GuiChatBox extends GuiFP
{
    private TileEntityChatBox te;
    private EntityPlayer player;
    private GuiTextField nameField;
    private GuiTextField messageField;
    private GuiButton setTarget;

    public GuiChatBox(EntityPlayer player,TileEntityChatBox te)
    {
        super(new ContainerChatBox(te), "chatBox", te);
        if(te!=null)
        {
            this.te = te;
            this.player = player;
        }
        else
        {
            throw new NullPointerException("No Tile Entity in GuiChatBox.");
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        //setNameButton = new GuiButton(0,guiLeft + 10, guiTop + 37,40,20,""); //(ID,XPos,YPos,width,height(20 is best),text)
        //buttonList.add(setNameButton);
        //Used for the TextBox
        this.nameField = new GuiTextField(this.fontRendererObj, guiLeft+5, guiTop+20, 100, 12);
        this.messageField = new GuiTextField(this.fontRendererObj, guiLeft+5, guiTop+48, 160,12);
        //this.textbox.setTextColor(-1);
        //this.textbox.setDisabledTextColour(-1);
        //this.textbox.setEnableBackgroundDrawing(false);
        //this.nameField.setMaxStringLength(40);
        this.nameField.setMaxStringLength(40);
        this.messageField.setMaxStringLength(70);


        if(this.te.getName()!=null)
        {
            nameField.setText(te.getName());
            messageField.setText(te.getMessage());
        }
        else
        {
            nameField.setText("");
            messageField.setText("");
        }
        //******************************/Textbox
    }

    @Override
    public void onTextfieldUpdate(int id)
    {
        //super.onTextfieldUpdate(id);
        if(id == 0)
        {
            nameField.setText(te.getName());
            te.setName(nameField.getText());
        }
        if(id==1)
        {
            messageField.setText(te.getMessage());
            te.setMessage(messageField.getText());
        }
    }

    @Override
    protected void keyTyped(char chr, int keyCode)
    {
        if(this.nameField.textboxKeyTyped(chr,keyCode))
        {
            NetworkHandler.sendToServer(new MessageHandleTextUpdate(te, 0, nameField.getText()));
        }
        else if(this.messageField.textboxKeyTyped(chr,keyCode))
        {
            NetworkHandler.sendToServer(new MessageHandleTextUpdate(te, 1, messageField.getText()));
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
        this.nameField.mouseClicked(mouseX, mouseY, button);
        this.messageField.mouseClicked(mouseX,mouseY,button);
        LogHelper.info("nameField Focused: "+nameField.isFocused()+" messageField Focused: "+messageField.isFocused());
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        this.nameField.drawTextBox();
        this.messageField.drawTextBox();

        RenderHelper.drawItemStack(new ItemStack(ModBlocks.chatBox),guiLeft/3-16,guiTop/3,"");
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        super.actionPerformed(button);
        if(button.id==0)
        {

        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        //super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        fontRendererObj.drawString("Name", 5, 10, 4210752);
        fontRendererObj.drawString("Message", 5, 36, 4210752);

    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
    }
}
