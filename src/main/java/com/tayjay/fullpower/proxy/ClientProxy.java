package com.tayjay.fullpower.proxy;

import com.tayjay.fullpower.client.handler.KeyInputHandler;
import com.tayjay.fullpower.client.settings.Keybindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * Created by Taylar on 29/08/2015.
 */
public class ClientProxy extends CommonProxy
{

    @Override
    public void preInit()
    {
        registerKeyBindings();
    }

    @Override
    public void registerKeyBindings()
    {
        //ClientRegistry.registerKeyBinding(Keybindings.charge);
        //ClientRegistry.registerKeyBinding(Keybindings.release);
        //ClientRegistry.registerKeyBinding(Keybindings.EXPLODE.getKeybind());
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
        for(Keybindings key : Keybindings.values())
        {
            ClientRegistry.registerKeyBinding(key.getKeybind());
        }
    }

    @Override
    public  void init()
    {

    }

    @Override
    public void postInit()
    {

    }
}
