package com.tayjay.fullpower.command;

import com.tayjay.fullpower.client.handler.NoClipHandler;
import com.tayjay.fullpower.util.ChatHelper;
import com.tayjay.fullpower.util.LogHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ForgeInternalHandler;

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
    @SideOnly(Side.CLIENT)
    public void processCommand(ICommandSender player, String[] args)
    {
        Minecraft mc = Minecraft.getMinecraft();
        //NoClipHandler noClipHandler = new NoClipHandler();

        EntityPlayerMP entityPlayerMP = args.length == 0 ? getCommandSenderAsPlayer(player) : getPlayer(player, args[0]);
        EntityPlayer entityPlayer = mc.theWorld.getPlayerEntityByName(entityPlayerMP.getCommandSenderName());

            if(!entityPlayer.worldObj.isRemote)
            {
                ChatHelper.send("THE WORLD IS NOT REMOTE");
                NoClipHandler.setNoClip(entityPlayer,entityPlayer.noClip);

            }else{
                ChatHelper.send("THE WORLD IS REMOTE");

                NoClipHandler.setNoClip(entityPlayer,!entityPlayer.noClip);
                NoClipHandler.setNoClip(entityPlayerMP,!entityPlayerMP.noClip);
            }
        ChatHelper.send("NoClip: " + entityPlayerMP.noClip);

        //mc.theWorld.getPlayerEntityByName(player.getCommandSenderName()).noClip = true;
        //NoClipHandler.setNoClip(entityPlayer,!entityPlayer.noClip);


    }

    @SideOnly(Side.SERVER)
    private void doSideServer()
    {
        Minecraft.getMinecraft().thePlayer.noClip = true;
    }
    @SideOnly(Side.CLIENT)
    private void doSideClient()
    {
        FMLClientHandler.instance().getClientPlayerEntity().noClip = true;
    }
}
