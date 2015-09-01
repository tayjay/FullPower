package com.tayjay.fullpower.item;

import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by Taylar on 30/08/2015.
 */
public class ItemDebugTool extends ItemFP
{
    public ItemDebugTool()
    {
        super();
        this.setUnlocalizedName("debugTool");
        LogHelper.info("debugTool name: " + this.getUnlocalizedName());
        LogHelper.info("debugTool Texture: " + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
        this.maxStackSize = 1;

    }

    /*Block Looking At Coords*/ int blockX;
    int blockY;
    int blockZ;
    int coolDown;

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entity)
    {

        MinecraftServer server = MinecraftServer.getServer();
        if (coolDown == 0)
        {
            ChatHelper.send(entity,entity.toString());
            if(entity instanceof EntityClientPlayerMP)
            {
                EntityClientPlayerMP player = (EntityClientPlayerMP) entity;
                ChatHelper.send("Entered onItemRightClick");
                Block blockLookingAt = getBlockLookingAt(world, player);
                Entity entityLookingAt = getEntityLookingAt(world, player);

                EntityItem droppedItem = new EntityItem(world,blockX,blockY+2,blockZ,new ItemStack(Items.diamond,1,1));
                if(!player.worldObj.isRemote) //Server Side Only
                {
                    ChatHelper.send("Should drop item");
                    world.spawnEntityInWorld(droppedItem);
                }else{
                    ChatHelper.send("Not Dropping Item.");
                }
            }else{
                ChatHelper.send(entity,"Entity not instance of EntityPlayerSP");
            }





            /*List<Entity> e = player.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(player.posX - 5, player.posY - 5, player.posZ - 5, player.posX + 5, player.posY + 5, player.posZ + 5));
            if (e.size() > 0) {

                for (int i = 0; i <= e.size() - 1; i++) {
                    Entity em = e.get(i);
                    ChatHelper.send(em.toString());
                }

            }*/


            /*
            if(entityLookingAt!=null)
            {
                ChatHelper.send(player,entityLookingAt.toString());
            } else {
                if (blockLookingAt != null) {
                    ChatHelper.send(player, blockLookingAt.toString());


                    world.setBlockToAir(blockX,blockY,blockZ);
                }
            }
            coolDown();
            */
            coolDown();
        }

        return itemStack;
    }

    private Entity getEntityLookingAt(World world, EntityPlayer player)
    {
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if (mop != null)
        {
            return mop.entityHit;
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public Block getBlockLookingAt(World world, EntityPlayer player)
    {
        MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
        if (mop != null)
        {
            int blockHitSide = mop.sideHit;
            blockX = mop.blockX;
            blockY = mop.blockY;
            blockZ = mop.blockZ;
            return world.getBlock(mop.blockX, mop.blockY, mop.blockZ); //Return the block I'm Looking at.
        }
        return null;
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if (coolDown > 0) --coolDown;

        if (entity != null && entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.capabilities.isFlying)
            {
                player.noClip = true;

            }
        }

    }

    public void coolDown()
    {
        coolDown = 20;
    }
}
