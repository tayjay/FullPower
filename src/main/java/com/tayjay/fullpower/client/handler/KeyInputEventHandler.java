package com.tayjay.fullpower.client.handler;

import com.tayjay.fullpower.client.settings.Keybindings;
import com.tayjay.fullpower.reference.Key;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;


/**
 * Created by Taylar on 01/09/2015.
 */
public class KeyInputEventHandler
{
    /*
    private static Key getPressedKeyBinding()
    {
        if (Keybindings.charge.isPressed())
        {
            return Key.CHARGE;
        } else if (Keybindings.release.isPressed())
        {
            return Key.RELEASE;
        }

        return Key.UNKNOWN;
    }
    private Keybindings getPressedKey(){
        for(Keybindings key : Keybindings.values())
        {
            if(key.isPressed()) return key;
        }
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
                    break;
            }
        }
    }
    */
}
