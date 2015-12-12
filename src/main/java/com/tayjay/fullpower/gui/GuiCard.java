package com.tayjay.fullpower.gui;

import codechicken.lib.render.RenderUtils;
import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.inventory.ContainerEmpty;
import com.tayjay.fullpower.inventory.ContainerFP;
import com.tayjay.fullpower.item.ItemCard;
import com.tayjay.fullpower.reference.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by tayjm_000 on 2015-12-10.
 */
public class GuiCard extends GuiContainer
{
    //Location of GUI Texture
    private ResourceLocation guiTexture;
    private EntityPlayer player;
    private ItemStack itemToRender;
    private int rotateEntity;
    private EntityLiving entityLiving;

    public GuiCard(Container p_i1072_1_)
    {
        super(p_i1072_1_);
    }

    public GuiCard(EntityPlayer player, String guiTextureName)
    {
        super(new ContainerEmpty());
        guiTexture = new ResourceLocation(Reference.MOD_ID_LOWER + ":textures/gui/" + guiTextureName + ".png");
        xSize=185;
        ySize=255;
        this.player = player;
        entityLiving = new EntityCow(player.worldObj);

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
                    itemToRender=new ItemStack(Items.diamond_chestplate);
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
        //RenderHelper.drawItemStack(heldItem, xSize / 3, ySize / 8, "");
        //RenderHelper.drawItemStack(itemToRender,(int) (xSize/3.3),(int) (ySize/9), "");

        fontRendererObj.drawString("Cow", 16, 8, 1210752);

        fontRendererObj.drawString("Set: Mob         Rarity: 1", 28, 130, 4210752);

        fontRendererObj.drawSplitString("OH GOD WHAT IS IT DOING!?!", 28, 170, xSize - 60, 4210752);


        drawEntity(entityLiving);
        rotateEntity++;rotateEntity++;

    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        rotateEntity++;rotateEntity++;




    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        //defaultEntityRenderer(guiLeft+90, guiTop+80, 40, p_73863_1_%360, 0, new EntityCow(mc.theWorld));
    }

    private void drawEntity(EntityLiving entity) {
        //int xPos = (this.width - this.menuW) / 2;
        //int yPos = (this.height - this.menuH) / 2;
        int xPos = 0;
        int yPos = 0;

        if (entity == null) {
            return;
        }

        //(float)(k + 51) - this.xSizeFloat, (float)(l + 75 - 50) - this.ySizeFloat

    }

    public void temp()
    {
        int xPos = 0;
        int yPos = 0;
        EntityLiving entity=null;
        //GL11.glPushMatrix();
        GL11.glColor3f(1F, 1F, 1F);
        GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
        GL11.glEnable(2903 /* GL_COLOR_MATERIAL */);
        GL11.glDepthMask(true);
        GL11.glPushMatrix();
        GL11.glTranslatef(xPos + 90, yPos + 98, 50F);
        float f1 = 40F;
        GL11.glScalef(-f1, f1, f1);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(135F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135F, 0.0F, 1.0F, 0.0F);
        //GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);

        RenderManager.instance.playerViewX = 180F;

        entity.renderYawOffset = entity.rotationYaw = entity.prevRotationYaw = entity.prevRotationYawHead = entity.rotationYawHead = this.rotateEntity;
        entity.rotationPitch = 0.0F;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180F;
        RenderManager.instance.playerViewX = 180F;
        int i1 = 240;
        int k1 = 240;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,
                i1 / 1.0F, k1 / 1.0F);
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D,
                0.0F, 1.0F);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
        GL11.glTranslatef(0F, 0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(2896 /* GL_LIGHTING */);
        GL11.glDisable(2929 /* GL_DEPTH_TEST */);
        //GL11.glPopMatrix();
    }

    /**
     * Default code from Minecraft's GuiInventory to render the player on the screen
     * @param x
     * @param y
     * @param scale
     * @param yaw
     * @param pitch
     * @param entity
     */
    public static void defaultEntityRenderer(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, 50.0F);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset;
        float f3 = entity.rotationYaw;
        float f4 = entity.rotationPitch;
        float f5 = entity.prevRotationYawHead;
        float f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(pitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        entity.renderYawOffset = (float)Math.atan((double)(yaw / 40.0F)) * 20.0F;
        entity.rotationYaw = (float)Math.atan((double)(yaw / 40.0F)) * 40.0F;
        entity.rotationPitch = -((float)Math.atan((double)(pitch / 40.0F))) * 20.0F;
        entity.rotationYawHead = entity.rotationYaw;
        entity.prevRotationYawHead = entity.rotationYaw;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        entity.renderYawOffset = f2;
        entity.rotationYaw = f3;
        entity.rotationPitch = f4;
        entity.prevRotationYawHead = f5;
        entity.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        //Bind GUI Texture to the Background Layer
        mc.getTextureManager().bindTexture(guiTexture);
        //Starting Pos on screen left, top, Starting pos of texture left, top, xSize,ySize
        this.drawTexturedModalRect(guiLeft,guiTop,0,0,xSize, ySize);

        //Draw an entity onto the screen
        defaultEntityRenderer(guiLeft + xSize / 2, guiTop + ySize / 3, 40, mouseX, mouseY, this.entityLiving);



    }
}
