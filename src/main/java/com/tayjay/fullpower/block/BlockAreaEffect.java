package com.tayjay.fullpower.block;


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
