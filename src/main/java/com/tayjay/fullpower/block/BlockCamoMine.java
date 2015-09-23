package com.tayjay.fullpower.block;

import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-09-20.
 */
public class BlockCamoMine extends BlockFPTileEntity
{
    public BlockCamoMine()
    {
        super();
        this.setBlockName(Names.Blocks.CAMO_MINE);
        this.setBlockTextureName(Names.Blocks.CAMO_MINE);
        ModBlocks.register(this);
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if(!world.isRemote)
        {
            TileEntityCamoMine te = (TileEntityCamoMine) world.getTileEntity(x, y, z);
            te.setCamouflage(player.getCurrentEquippedItem());
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        TileEntityCamoMine te = (TileEntityCamoMine) world.getTileEntity(x, y, z);
        ItemStack stack = te.getCamouflage();
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
