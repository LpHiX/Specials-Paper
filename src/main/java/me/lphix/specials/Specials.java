package me.lphix.specials;

import me.lphix.specials.listeners.InventoryClickListener;
import me.lphix.specials.listeners.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Specials extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JavaPlugin getPlugin(){
        return plugin;
    }
}
