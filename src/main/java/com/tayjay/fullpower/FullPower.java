package com.tayjay.fullpower;

import com.tayjay.fullpower.handler.ConfigHandler;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.proxy.IProxy;
import com.tayjay.fullpower.reference.Reference;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.sql.Ref;


/**
 * Created by Taylar on 29/08/2015.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION,guiFactory = Reference.GUI_FACTORY_CLASS)
public class FullPower
{

    @Mod.Instance
    public static FullPower instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        ModItems.init();
        LogHelper.info("Pre-Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

        LogHelper.info("Post-Initialization Complete!");
    }
}
