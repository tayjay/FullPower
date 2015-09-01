package com.tayjay.fullpower.client.gui;

import com.tayjay.fullpower.handler.ConfigHandler;
import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by Taylar on 30/08/2015.
 */
public class ModGuiConfig extends GuiConfig
{
    public ModGuiConfig(GuiScreen guiScreen)
    {
        super(guiScreen, new ConfigElement(ConfigHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, //requires world restart
                false, //reqiures minecraft restart
                GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
    }
}