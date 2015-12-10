package com.tayjay.fullpower.event;

import com.tayjay.fullpower.handler.ExtendedPlayer;
import com.tayjay.fullpower.handler.TickHandler;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.util.ItemUtil;
import com.tayjay.fullpower.util.LogHelper;
import com.tayjay.fullpower.util.RenderHelper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by tayjm_000 on 2015-10-15.
 */
public class EventHandlerFP
{
    @SubscribeEvent
    public void addPigDrops(LivingDropsEvent event)
    {
        if(event.entityLiving instanceof EntityPig && event.entityLiving.getRNG().nextInt(3) == 0)
        {
            ItemStack commandScroll = new ItemStack(ModItems.commandScroll);
            event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ));
        }
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        /*
		Be sure to check if the entity being constructed is the correct type
		for the extended properties you're about to add!
		The null check may not be necessary - I only use it to make sure
		properties are only registered once per entity
		*/
        if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
        {
            // This is how extended properties are registered using our convenient method from earlier
            LogHelper.info("Constructing EntityPlayer with ExtendedPlayer properties!");
            ExtendedPlayer.register((EntityPlayer) event.entity);
        }
        // That will call the constructor as well as cause the init() method
        // to be called automatically
    }


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void playerRenderEventPre(RenderPlayerEvent.Pre event)
    {
        /*
        //event.setCanceled(true);
        //LogHelper.info("Player " + event.entityPlayer);

        //event.entityPlayer.eyeHeight = 10.0F;
            GL11.glPushMatrix();

            GL11.glTranslatef(0.0F, 4.0F, 0.0F);

            GL11.glScalef(3.5F, 3.5F, 3.5F);

            //GL11.glPopMatrix();
        */

        //scalePlayer(false);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void playerRenderPost(RenderPlayerEvent.Post event)
    {
        //GL11.glPopMatrix();
        //scalePlayer(true);

    }

    boolean isPlayerScaled;
    public void scalePlayer(boolean post)
    {
        if(!post)
        {
            this.isPlayerScaled = true;
            GL11.glPushMatrix();

            GL11.glTranslatef(0.0F, .0F, 0.0F);

            GL11.glScalef(0.5F, 0.5F, 0.5F); //3.5F
        }
        else
        {
            if(isPlayerScaled)
            {
                GL11.glPopMatrix();
                isPlayerScaled = false;
            }
        }
    }

    /*
    float ySize;
    float eyeHeight;
    @SubscribeEvent
    public void renderTickEvent(TickEvent.RenderTickEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();

        if(mc.theWorld!=null)
        {


            if(event.phase == TickEvent.Phase.START)
            {
                this.ySize = mc.thePlayer.yOffset  - 0.7F+ mc.thePlayer.getDefaultEyeHeight();
                //this.eyeHeight = mc.thePlayer.eyeHeight-2;
                this.eyeHeight=-1.5F; //3.7F
                mc.thePlayer.lastTickPosY -= ySize;
                mc.thePlayer.prevPosY -= ySize;
                mc.thePlayer.posY -= ySize;
                mc.thePlayer.eyeHeight = mc.thePlayer.getDefaultEyeHeight();
            }
            else
            {
                mc.thePlayer.lastTickPosY += ySize;
                mc.thePlayer.prevPosY += ySize;
                mc.thePlayer.posY += ySize;
                mc.thePlayer.eyeHeight = eyeHeight;
            }

        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        event.player.width = 0.0F;
        event.player.height = -0.50F;
    }
    */

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onGameOverlayEvent(RenderGameOverlayEvent event)
    {
/*
        if (event.type == RenderGameOverlayEvent.ElementType.CHAT)
        {
            RenderHelper.drawItemStack(ItemUtil.newItemStack(Items.apple, 1, 0), 10, 10, "");
        }
*/
    }

    public void onFireTick(BlockEvent event)
    {

    }

    public void itemRenderTick(RenderGameOverlayEvent event)
    {
    }




    /*
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        final int RADIUS = 3;
        if(event.side == Side.SERVER && event.phase == TickEvent.Phase.END)
        {
            List<Entity> entities = event.player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(event.player.posX - RADIUS, event.player.posY - RADIUS, event.player.posZ - RADIUS, event.player.posX + RADIUS, event.player.posY + RADIUS, event.player.posZ + RADIUS));
            for(Entity entity : entities)
            {
                if(entity != event.player)
                {
                    entity.setVelocity(0,1,0);
                }
            }
        }
            //LogHelper.info("Player " + event.player.getCommandSenderName() + " Ticked " + event.phase);
    }
    */
}
