package com.tayjay.fullpower.command;

import com.tayjay.fullpower.util.ChatHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by tayjm_000 on 2015-09-20.
 */
public class CommandPing extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "ping";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_)
    {
        return "/ping [player]";
    }

    @Override
    public void processCommand(ICommandSender player, String[] args)
    {
        EntityPlayerMP entityPlayerMP = args.length == 0 ? getCommandSenderAsPlayer(player) : getPlayer(player, args[0]);
        ChatHelper.send(entityPlayerMP,entityPlayerMP.getDisplayName()+"'s Ping is: "+entityPlayerMP.ping+" ms");
    }
}
