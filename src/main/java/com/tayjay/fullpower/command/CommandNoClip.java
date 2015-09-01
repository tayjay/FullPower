package com.tayjay.fullpower.command;

import com.tayjay.fullpower.util.ChatHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

/**
 * Created by Taylar on 01/09/2015.
 */
public class CommandNoClip extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "noclip";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_)
    {
        return "/noclip";
    }

    @Override
    public void processCommand(ICommandSender player, String[] p_71515_2_)
    {
        Minecraft mc = Minecraft.getMinecraft();

        mc.thePlayer.noClip = !mc.thePlayer.noClip;
        ChatHelper.send("NoClip: " + mc.thePlayer.noClip);
    }
}
