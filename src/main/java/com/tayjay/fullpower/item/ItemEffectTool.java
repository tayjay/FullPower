package com.tayjay.fullpower.item;

import com.tayjay.fullpower.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by Taylar on 31/08/2015.
 */
public class ItemEffectTool extends ItemFP
{
    public ItemEffectTool()
    {
        super();
        this.setUnlocalizedName("effectTool");
        ModItems.register(this);

    }


    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            player.addPotionEffect(new PotionEffect(10, 200, 10));
            player.removePotionEffect(10);
        }
    }
}
