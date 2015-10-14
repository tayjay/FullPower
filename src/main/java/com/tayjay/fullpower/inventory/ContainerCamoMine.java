package com.tayjay.fullpower.inventory;

import com.tayjay.fullpower.network.MessageHandleTextUpdate;
import com.tayjay.fullpower.network.NetworkHandler;
import com.tayjay.fullpower.tileentity.TileEntityCamoMine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by tayjm_000 on 2015-09-28.
 */
public class ContainerCamoMine extends ContainerFP
{
    private TileEntityCamoMine te;
    private int lastTimer = -1;
    private String lastTarget= "";

    public ContainerCamoMine(InventoryPlayer playerInventory, TileEntityCamoMine te)
    {
        //Order of adding slots is IMPORTANT
        //Hard Coded Locations
        this.addSlotToContainer(new SlotCamo(te, 0, 80, 58)); //Bottom
        this.addSlotToContainer(new SlotCamo(te, 1, 80, 22)); //Top
        this.addSlotToContainer(new SlotCamo(te, 2, 62, 40)); //Left
        this.addSlotToContainer(new SlotCamo(te, 3, 80, 40)); //Middle
        this.addSlotToContainer(new SlotCamo(te, 4, 98, 40)); //Right
        this.addSlotToContainer(new SlotCamo(te, 5, 102, 18)); //Back

        //Player Slots start at index 6 in this example
        //Position of player slots.
        this.addPlayerSlots(playerInventory, 8, 84);
        this.te = te;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return te.isUseableByPlayer(player);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        if(lastTimer != te.getTimer())
        {
            for(ICrafting crafter : (List<ICrafting>)this.crafters)
            {
                crafter.sendProgressBarUpdate(this,0,te.getTimer());
            }
            lastTimer = te.getTimer();
        }
        if(!lastTarget.equals(te.getTarget()))
        {
            for(Object crafter : (List<ICrafting>)this.crafters)
            {
                if(crafter instanceof EntityPlayerMP)
                {
                    NetworkHandler.sendTo(new MessageHandleTextUpdate(te,0,te.getTarget()),(EntityPlayerMP)crafter);
                }
            }
            lastTarget = te.getTarget();
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
        super.updateProgressBar(id, value);
        if(id == 0)
        {
            te.setTimer(value);
        }
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            //From here, Change to match inventory size
            if (slotIndex < 6) //6 slots in inventory
            {
                if (!this.mergeItemStack(itemstack1, 6, 42, true))
                {
                    return null;
                }
            }
            else
            {
                if(itemstack1.stackSize == 1)
                {
                    for(int i = 0;i<6;i++)
                    {
                        Slot shiftedInSlot = (Slot)inventorySlots.get(i);
                        if((!shiftedInSlot.getHasStack()) && shiftedInSlot.isItemValid(itemstack1))
                        {
                            mergeItemStack(itemstack1,i,i+1,false);
                        }
                    }
                }
            }
            //Till here.

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
