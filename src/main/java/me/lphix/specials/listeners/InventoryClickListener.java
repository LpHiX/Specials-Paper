package me.lphix.specials.listeners;

import me.lphix.specials.SpecialItem;
import me.lphix.specials.inventory.SpecialInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        try{
            if(e.getClickedInventory().getHolder() instanceof SpecialInventory){
                switch(e.getSlot()){
                    case 10:
                        e.getWhoClicked().getInventory().addItem(SpecialItem.HOOK_ONE.initiateSpecialItem(1));
                        e.setCancelled(true);
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
