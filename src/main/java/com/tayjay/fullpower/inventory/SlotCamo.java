package com.tayjay.fullpower.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by tayjm_000 on 2015-10-01.
 */
public class SlotCamo extends Slot
{
    public SlotCamo(IInventory inventory, int inventoryIndex, int x, int y)
    {
        super(inventory, inventoryIndex, x, y);
    }

    public boolean isItemValid(ItemStack itemStack)
    {
        return this.inventory.isItemValidForSlot(getSlotIndex(),itemStack);
    }
}
