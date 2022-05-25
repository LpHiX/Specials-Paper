package me.lphix.specials.listeners;

import me.lphix.specials.SpecialItem;
import me.lphix.specials.special_items.HookItem;
import me.lphix.specials.special_items.ZipLineTool;
import me.lphix.specials.special_items.ZiplineHook;
import me.lphix.specials.utilities.Utility;
import me.lphix.specials.inventory.SpecialInventory;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if(!e.getAction().isRightClick())return;
        if (e.getItem() == null)return;

        if (e.getItem().getType().equals(Material.SLIME_BALL)) {e.getPlayer().openInventory(new SpecialInventory().getInventory());}
        else checkSpecialItem(e);
    }
    public void checkSpecialItem(PlayerInteractEvent e){
        ItemStack item = e.getItem();
        if(!Utility.hasKey(item)) return;
        e.setCancelled(true);
        switch (SpecialItem.byKey(Utility.returnKey(item))){
            case HOOK_ONE:
                HookItem.useEvent(e);
                break;
            case ZIPLINE_HOOK:
                ZiplineHook.useEvent(e);
                break;
            case ZIPLINE_TOOL:
                ZipLineTool.useEvent(e);
                break;
            default:
                break;

        }
    }

}
