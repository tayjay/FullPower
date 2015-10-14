package com.tayjay.fullpower.block;

import com.tayjay.fullpower.FullPower;
import com.tayjay.fullpower.GuiHandler;
import com.tayjay.fullpower.creativetab.CreativeTabFP;
import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-20.
 *
 * Most of the logic is in tileentity/TileEntityCamoMine
 */
public class BlockCamoMine extends BlockFPTileEntity
{
    public BlockCamoMine()
    {
        super();
        this.setBlockName(Names.Blocks.CAMO_MINE);
        this.setBlockTextureName(Names.Blocks.CAMO_MINE);
        //Register this block
        ModBlocks.register(this);
        this.setCreativeTab(CreativeTabFP.FP_TAB);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityCamoMine();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            if(player.isSneaking())
            {
                player.openGui(FullPower.instance, GuiHandler.GuiIDs.CAMO_MINE.ordinal(),world,x,y,z);
            }
                TileEntityCamoMine te = (TileEntityCamoMine) world.getTileEntity(x, y, z);
                if(te.getCamouflage(side) != null)
                {
                    ItemStack camoStack = te.getCamouflage(side);
                    te.setCamouflage(null,side);
                    EntityItem itemEntity = new EntityItem(world,x,y,z,camoStack);
                    LogHelper.info("StackSize="+camoStack.stackSize);
                    //Tell world this item needs to be spawned
                    world.spawnEntityInWorld(itemEntity);
                }
                else
                {
                    ItemStack playerItem = player.getCurrentEquippedItem();
                    if (playerItem != null)
                    {
                        ItemStack camoStack = playerItem.splitStack(1); //Remove 1 from stack
                        ChatHelper.send(camoStack.toString());
                        te.setCamouflage(camoStack,side);
                    }
                }


        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        TileEntityCamoMine te = (TileEntityCamoMine) world.getTileEntity(x, y, z);
        ItemStack stack = te.getCamouflage(side);
        if(stack != null && stack.getItem() instanceof ItemBlock)
        {
            Block block = ((ItemBlock)stack.getItem()).field_150939_a;
            return block.getIcon(side, stack.getItemDamage());
        }
        else
        {
            return super.getIcon(world,x,y,z,side);
        }
    }
}
