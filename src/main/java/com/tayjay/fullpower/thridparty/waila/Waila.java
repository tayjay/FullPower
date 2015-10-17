package com.tayjay.fullpower.thridparty.waila;

import com.tayjay.fullpower.block.BlockCamoMine;
import mcp.mobius.waila.api.IWailaRegistrar;

/**
 * Created by tayjm_000 on 2015-10-16.
 */
public class Waila
{
    public static void onWailaCall(IWailaRegistrar registrar)
    {
        registrar.registerStackProvider(new WailaCamoHandler(), BlockCamoMine.class);
        /*Tooltip*/
        registrar.registerBodyProvider(new WailaCamoHandler(), BlockCamoMine.class);
        /*Handle Relog and sync*/
        registrar.registerNBTProvider(new WailaCamoHandler(), BlockCamoMine.class);
    }
}
