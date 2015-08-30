package com.tayjay.fullpower.item;

import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by Taylar on 30/08/2015.
 */
public class ItemDebugTool extends ItemFP
{
    public ItemDebugTool() {
        super();
        this.setUnlocalizedName("debugTool");
        LogHelper.info("debugTool name: " + this.getUnlocalizedName());
        LogHelper.info("debugTool Texture: "+ this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    /*Block Looking At Coords*/
    int blockX;
    int blockY;
    int blockZ;
    int coolDown;

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if(coolDown == 0) {
            Block blockLookingAt = getBlockLookingAt(world, player);
            if (blockLookingAt != null) {
                //player.addChatComponentMessage(new ChatComponentText(blockLookingAt.getUnlocalizedName().substring(5)));
                //MinecraftServer mcServer = MinecraftServer.getServer();
                //mcServer.getConfigurationManager().sendChatMsg(new ChatComponentText(blockLookingAt.getUnlocalizedName().substring(5)));
                ChatHelper.send(player,blockLookingAt.getUnlocalizedName().substring(5));
                blockLookingAt.breakBlock(world, blockX, blockY, blockZ, blockLookingAt, 0);
            }
            coolDown();
        }
        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    public Block getBlockLookingAt(World world, EntityPlayer player)
    {
        MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
        if(mop != null)
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
    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_)
    {
        if(coolDown>0)
            --coolDown;
    }

    public void coolDown()
    {
        coolDown = 20;
    }
}
