package com.tayjay.fullpower.proxy;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Taylar on 29/08/2015.
 */
public class ServerProxy extends CommonProxy
{

    @Override
    public void preInit()
    {

    }

    @Override
    public void init()
    {

    }

    @Override
    public void postInit()
    {

    }

    @Override
    public EntityPlayer getClientPlayer()
    {
        //NOOP
        return null;
    }

    @Override
    public void registerKeyBindings()
    {
        // NOOP No Operation
    }
}
