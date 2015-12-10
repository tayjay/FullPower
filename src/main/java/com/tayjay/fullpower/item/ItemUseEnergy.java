package com.tayjay.fullpower.item;

import com.tayjay.fullpower.handler.ExtendedPlayer;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.reference.Names;
import com.tayjay.fullpower.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by tayjm_000 on 2015-10-18.
 */
public class ItemUseEnergy extends ItemFP
{
    public ItemUseEnergy()
    {
        super();
        this.setUnlocalizedName(Names.Items.USE_ENERGY);
        this.maxStackSize = 1;
        ModItems.register(this);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
			/*
			Due to the length of code needed to get extended entity properties, I always find it
			handy to create a local variable named 'props' for whatever properties I need.

			Also, getExtendedProperties("name") returns the type 'IExtendedEntityProperties', so
			you need to cast it as your extended properties type for it to work.

			Old, ugly method:
			ExtendedPlayer props = (ExtendedPlayer) player.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME);

			This is using Seigneur_Necron's slick method (will be used from here on):
			 */
            ExtendedPlayer props = ExtendedPlayer.get(player);

            // Here we'll use the method we made to see if the player has enough mana to do something
            // We'll print something to the console for debugging, but I'm sure you'll find a much
            // better action to perform.
            if (props.consumeEnergy(15))
            {
                LogHelper.info("There is enough energy. Using ItemUseEnergy");

            }
            else
            {
                LogHelper.info("Player ran out of energy!");
                props.fillEnergy();
            }
            LogHelper.info("Current Energy: "+props.getCurrentEnergy());
        }


        return itemstack;
    }
}
