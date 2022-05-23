package me.lphix.specials;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum SpecialItem {
    HOOK_ONE(Material.IRON_HORSE_ARMOR, "Hook V1", "hook_one"),
    ZIPLINE_HOOK(Material.TRIPWIRE_HOOK, "Zipline Hook V1", "zipline_hook"),
    ZIPLINE_TOOL(Material.STICK, "Zipline Tool V1", "zipline_tool"),
    BLAZE_ARC(Material.BLAZE_ROD, "Blaze Arc", "blaze_arc");

    public final Material material;
    public final String displayName;
    public final String metaKey;

    private static final Map<String, SpecialItem> BY_KEY = new HashMap<>();

    static {
        for (SpecialItem e: values()){
            BY_KEY.put(e.metaKey, e);
        }
    }

    SpecialItem (Material material, String displayName, String metaKey){
        this.material = material;
        this.displayName = displayName;
        this.metaKey = metaKey;
    }
    public ItemStack initiateSpecialItem(int amount){
        ItemStack newItem = new ItemStack(material, amount);
        NamespacedKey key = new NamespacedKey(Specials.getPlugin(), "special-type");
        ItemMeta itemMeta = newItem.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, metaKey);
        newItem.setItemMeta(itemMeta);
        return newItem;
    }
    public static SpecialItem byKey(String key){
        return BY_KEY.get(key);
    }
}
