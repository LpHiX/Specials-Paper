package me.lphix.specials.utilities;

import me.lphix.specials.SpecialItem;
import me.lphix.specials.Specials;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.Objects;

public class Utility {
    public static boolean itemCheck(ItemStack item, SpecialItem sItem){
        ItemMeta itemMeta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(Specials.getPlugin(), "special-type");
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if(!container.has(key, PersistentDataType.STRING)){
            return false;
        }
        String keyValue = container.get(key, PersistentDataType.STRING);
        if (!Objects.equals(keyValue, sItem.metaKey)){
            return false;
        }
        return true;
    }
    public static boolean hasKey(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(Specials.getPlugin(), "special-type");
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if(!container.has(key, PersistentDataType.STRING)){
            return false;
        } return true;
    }
    @Nullable
    public static String returnKey(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(Specials.getPlugin(), "special-type");
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if(!container.has(key, PersistentDataType.STRING)){
            System.out.println("[Specials] Item does not have key");
            return null;
        }
        return container.get(key, PersistentDataType.STRING);
    }
}
