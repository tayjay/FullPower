package com.tayjay.fullpower.client.handler;

import com.tayjay.fullpower.client.settings.Keybindings;
import com.tayjay.fullpower.network.MessageExplode;
import com.tayjay.fullpower.network.NetworkHandler;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

/**
 * Created by tayjm_000 on 2015-09-19.
 */
public class KeyInputHandler
{
    private Keybindings getPressedKey(){
        for(Keybindings key : Keybindings.values())
        {
            if(key.isPressed()) return key;
        }
        return null;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        Keybindings key = getPressedKey();
        if(key != null)
        {
            switch (key)
            {
                case EXPLODE:
                    LogHelper.info("Boom!");
                    NetworkHandler.sendToServer(new MessageExplode(3));
                    break;
                case EXPLODE_BIG:
                    LogHelper.info("Big Boom!");
                    NetworkHandler.sendToServer(new MessageExplode(10));
                    break;
            }
        }
    }
}
