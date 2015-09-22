package com.tayjay.fullpower.block;


import com.tayjay.fullpower.init.ModBlocks;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Taylar on 31/08/2015.
 * <p/>
 * NEED TO MAKE A TILE ENTITY FOR THIS TO WORK
 */
public class BlockAreaEffect extends BlockFP
{

    public BlockAreaEffect()
    {
        super();
        this.setBlockName("areaEffect");
        this.setBlockTextureName("areaEffect");
        ModBlocks.register(this);
    }


    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float xpos, float ypos, float zpos)
    {

        if (world.isAirBlock(x, y + 1, z)) world.setBlock(x, y + 1, z, ModBlocks.areaEffect);

        EntityLightningBolt lightning = new EntityLightningBolt(world, x, y, z);
        world.spawnEntityInWorld(lightning);
        return true; // True = Cant place on block, False = Can place on block

    }
    /* PUT THIS IN A TILE ENTITY
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float xpos, float ypos, float zpos)
    {
        EntityItem droppedItem = new EntityItem(world,x,y+2,z,new ItemStack(ModItems.debugTool));
        world.spawnEntityInWorld(droppedItem);
        return false;
    }
    */
}
