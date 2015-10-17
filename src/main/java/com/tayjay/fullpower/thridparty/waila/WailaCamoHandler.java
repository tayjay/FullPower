package com.tayjay.fullpower.thridparty.waila;

import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import com.tayjay.fullpower.tileentity.TileEntityChatBox;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by tayjm_000 on 2015-10-16.
 */
public class WailaCamoHandler implements IWailaDataProvider
{
    /*
        Each method must be called by registrar to run.
     */

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        TileEntityCamoMine te = (TileEntityCamoMine) accessor.getTileEntity();
        int side = accessor.getSide().ordinal();
        ItemStack stack = te.getCamouflage(side);
        if(stack != null && stack.getItem() instanceof ItemBlock)
        {
            return stack;
        }
        else
        {
            return new ItemStack(ModBlocks.camoMine);
        }
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        NBTTagCompound tag = accessor.getNBTData();
        String target = tag.getString("target");
        int timer = tag.getInteger("timer");
        if(!target.equals(""))
        {
            currenttip.add("Target: "+target);
        }
        if(timer == 0)
        {
            currenttip.add(EnumChatFormatting.RED + "Primed!");
        }
        else
        {
            currenttip.add(EnumChatFormatting.GREEN+"Priming  in: " + timer);
        }
        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currenttip;
    }


    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z)
    {
        TileEntityCamoMine tile = (TileEntityCamoMine)te;
        tag.setString("target",tile.getTarget());
        tag.setInteger("timer",tile.getTimer());
        return tag;
    }
}
