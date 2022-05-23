package me.lphix.specials.inventory;

import me.lphix.specials.SpecialItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SpecialInventory implements InventoryHolder {
    private Inventory inv;

    public SpecialInventory(){
        inv = Bukkit.createInventory(this, 54);
        init();
    }

    private void init() {
        for (int i = 0; i < 54; i++) {
            inv.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1));
        }
        inv.setItem(10, SpecialItem.HOOK_ONE.initiateSpecialItem(1));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }
}
