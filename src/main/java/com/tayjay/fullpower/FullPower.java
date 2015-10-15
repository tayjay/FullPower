package com.tayjay.fullpower;

import com.tayjay.fullpower.client.gui.RenderTick;
import com.tayjay.fullpower.client.handler.KeyInputEventHandler;
import com.tayjay.fullpower.client.handler.KeyInputHandler;
import com.tayjay.fullpower.client.handler.NoClipHandler;
import com.tayjay.fullpower.command.CommandChat;
import com.tayjay.fullpower.command.CommandNoClip;
import com.tayjay.fullpower.command.CommandPing;
import com.tayjay.fullpower.event.EventHandlerFP;
import com.tayjay.fullpower.handler.ConfigHandler;
import com.tayjay.fullpower.handler.TickHandler;
import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.init.ModTileEntities;
import com.tayjay.fullpower.init.Recipies;
import com.tayjay.fullpower.network.DescriptionHandler;
import com.tayjay.fullpower.network.NetworkHandler;
import com.tayjay.fullpower.proxy.CommonProxy;
import com.tayjay.fullpower.proxy.IProxy;
import com.tayjay.fullpower.reference.Reference;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import com.tayjay.fullpower.tileentity.TileEntityFP;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;


/**
 * Created by Taylar on 29/08/2015.
 *
 * Main Class for the mod.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class FullPower
{

    /**
     * Make a mod Instance
     */
    @Mod.Instance
    public static FullPower instance;

    /**
     * Create the sided proxy.
     */
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    /**
     * Server starting event.
     * Register server components.
     *
     * @param event
     */
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandChat());
        event.registerServerCommand(new CommandNoClip());
        event.registerServerCommand(new CommandPing());

    }

    /**
     * Pre-Initialization Event
     * Load Items,Blocks, TileEntities, Networking
     *
     * @param event
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());

        //proxy.registerKeyBindings();
        proxy.preInit();
        ModItems.init();
        ModBlocks.init();
        ModTileEntities.init();

        MinecraftForge.EVENT_BUS.register(new EventHandlerFP());
        FMLCommonHandler.instance().bus().register(new EventHandlerFP());

        NetworkHandler.preInit();
        DescriptionHandler.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        FMLInterModComms.sendMessage(Reference.MOD_ID, "camoMineBlackList",new ItemStack(Blocks.stone));
        LogHelper.info("Pre-Initialization Complete!");
    }

    /**
     * Initialization Event
     * Register Event Handlers, Recipes.
     *
     * @param event
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        Recipies.init();
        MinecraftForge.EVENT_BUS.register(new NoClipHandler()); // Handle Inside Block
        FMLCommonHandler.instance().bus().register(new NoClipHandler());
        FMLCommonHandler.instance().bus().register(new TickHandler());
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());

        LogHelper.info("Initialization Complete!");
    }

    /**
     * Post-Initialization
     * Load after everything else.
     *
     * @param event
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

        LogHelper.info("Post-Initialization Complete!");
    }

    /**
     * Receive Inter-Mod Communications. Must be in the @Mod class.
     * <br>
     * Called between Init and Post-Init events.
     * @param event
     */
    @Mod.EventHandler
    public void onIMCMessages(FMLInterModComms.IMCEvent event)
    {
        LogHelper.info("Receiving IMC");
        for(FMLInterModComms.IMCMessage message : event.getMessages())
        {
            if(message.key.equalsIgnoreCase("camoMineBlackList"))
            {
                if(message.isItemStackMessage())
                {
                    ItemStack blacklistedStack = message.getItemStackValue();
                    if (blacklistedStack.getItem() != null)
                    {
                        TileEntityCamoMine.camoflaugeBlackList.add(blacklistedStack);
                        LogHelper.info(String.format("Mod %s added %s to the blacklisted as camoflauge for the Camo Mine", message.getSender(), blacklistedStack.toString())); //Only for debugging, not recommended
                    }
                    else
                    {
                        throw new IllegalStateException(String.format("ItemStack tried to be used in registry by the mod %s has a null item.", message.getSender()));
                    }
                }
                else
                {
                    LogHelper.warn(String.format("Mod %s sent a non-ItemStack message, where an ItemStack was expected.",message.getSender()));
                }
            }
            else
            {
                LogHelper.warn(String.format("Mod %s used an invalid IMC key: %s",message.getSender(),message.key));
            }

        }
    }
}
