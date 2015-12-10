package com.tayjay.fullpower.item;

import com.tayjay.fullpower.creativetab.CreativeTabFP;
import com.tayjay.fullpower.init.ModBlocks;
import com.tayjay.fullpower.init.ModItems;
import com.tayjay.fullpower.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Taylar on 30/08/2015.
 */
public class ItemFP extends Item
{
    public ItemFP()
    {
        super();
        this.setCreativeTab(CreativeTabFP.FP_TAB);

    }


    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    public String getUnwrappedUnlocalizedName(String unlocalizedName)
    {

        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
