package com.tayjay.fullpower.client.settings;

import com.tayjay.fullpower.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * Created by Taylar on 01/09/2015.
 */
public enum Keybindings
{
    EXPLODE(Names.Keys.EXPLODE, Keyboard.KEY_G),
    EXPLODE_BIG(Names.Keys.EXPLODE_BIG, Keyboard.KEY_H);

    private final KeyBinding keyBinding;

    Keybindings(String keyName, int defaultKeyCode)
    {
        keyBinding = new KeyBinding(keyName,defaultKeyCode, Names.Keys.CATEGORY);
    }

    public KeyBinding getKeybind()
    {
        return keyBinding;
    }

    public boolean isPressed()
    {
        return keyBinding.isPressed();
    }
}
