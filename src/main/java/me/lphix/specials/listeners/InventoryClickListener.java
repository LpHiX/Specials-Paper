package me.lphix.specials.listeners;

import me.lphix.specials.SpecialItem;
import me.lphix.specials.inventory.SpecialInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        try{
            if(e.getClickedInventory().getHolder() instanceof SpecialInventory){
                Inventory inv = e.getWhoClicked().getInventory();
                e.setCancelled(true);
                switch(e.getSlot()){
                    case 10:
                        inv.addItem(SpecialItem.HOOK_ONE.initiateSpecialItem(1));
                        break;
                    case 11:
                        inv.addItem(SpecialItem.ZIPLINE_HOOK.initiateSpecialItem(1));
                        break;
                    case 12:
                        inv.addItem(SpecialItem.ZIPLINE_TOOL.initiateSpecialItem(1));
                        break;
                    case 13:
                        inv.addItem(SpecialItem.BLAZE_ARC.initiateSpecialItem(1));
                        break;
                    default:
                        e.setCancelled(true);
                        break;
                }
            }
        } catch (Exception ex){
            return;
        }
    }

}
