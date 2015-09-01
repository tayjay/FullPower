package com.tayjay.fullpower.command;

import com.tayjay.fullpower.util.ChatHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

/**
 * Created by Taylar on 31/08/2015.
 * <p/>
 * THIS CLASS HAS BEEN HARD CODED.
 * REMOVE THIS AT SOME POINT.
 */
public class CommandChat extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "chat";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return "/chat <String>";
    }


    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        String msg = "";
        for (int i = 0; i < args.length; i++)
            msg += args[i] + " ";
        ChatHelper.send(msg);
    }
}
