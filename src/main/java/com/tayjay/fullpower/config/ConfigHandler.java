package com.tayjay.fullpower.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Taylar on 29/08/2015.
 */
public class ConfigHandler
{
    public static void init(File configFile)
    {
        //Create the configuration object from the given configuration file.
        Configuration configuration = new Configuration(configFile);

        //Read in properties
        //                                      (Category, name parameter, default value, description)
        boolean configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "This is an example config value").getBoolean(true);

        try
        {
            //Load the configuration file
            configuration.load();
        }
        catch(Exception e)
        {
            //Log the exception
        }
        finally
        {
            //Save the configuration file
            configuration.save();
        }


    }
}
