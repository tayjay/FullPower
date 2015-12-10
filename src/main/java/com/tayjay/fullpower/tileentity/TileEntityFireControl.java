package com.tayjay.fullpower.tileentity;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

/**
 * Created by tayjm_000 on 2015-11-01.
 */
public class TileEntityFireControl extends TileEntityFP
{
    private int radius = 3;
    private boolean display = false;
    public TileEntityFireControl()
    {
        super();
    }

    /*
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        for(int i = -radius; i<radius;i++)
        {
            for(int j = -radius;i<radius;j++)
            {
                for(int k = -radius;k<radius;k++)
                {
                    if(worldObj.getBlock(i,j,k)== Blocks.fire)
                    {
                        worldObj.setBlock(i,j,k,Blocks.air);
                    }
                }
            }
        }
    }
    */

    public void showRadius()
    {
        Tessellator tes = new Tessellator();
        if(!display)
        {
            tes.addVertex(-radius, -radius, -radius);
            tes.addVertex(radius, radius, radius);
            tes.draw();
        }
        else
        {
            tes.draw();
        }
    }

    @Override
    protected void writeToPacket(ByteBuf buf)
    {

    }

    @Override
    public void readFromPacket(ByteBuf buf)
    {

    }
}
