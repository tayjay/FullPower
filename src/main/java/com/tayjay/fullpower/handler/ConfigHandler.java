package com.tayjay.fullpower.handler;

import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Taylar on 29/08/2015.
 */
public class ConfigHandler
{
    public static Configuration configuration;
    public static boolean testValue = false;

    public static void init(File configFile)
    {
        if(configuration == null) {
            //Create the configuration object from the given configuration file.
            configuration = new Configuration(configFile);
            loadConfig();
        }
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            //resync configs
            loadConfig();
        }
    }

    private static void loadConfig()
    {
        testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "This is an example config value.");

        if(configuration.hasChanged())
        {
            configuration.save();
        }
    }


}


