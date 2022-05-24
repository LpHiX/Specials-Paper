package me.lphix.specials.specialItems;

import me.lphix.specials.utilities.BlockPair;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class ZiplineHook {
    private static final String chatPrefix = ChatColor.GREEN + "[Zipline] " + ChatColor.AQUA;
    public static void useEvent(PlayerInteractEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        Block block = p.rayTraceBlocks(3).getHitBlock();
        BlockPair blockPair = ZipLineTool.findZipline(block);
        if(blockPair == null){
            p.sendMessage(chatPrefix + "No Zipline was found");
            return;
        }
        //TODO: make the player ride on the zipline if it is found
    }
}
