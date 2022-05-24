package me.lphix.specials.specialItems;

import me.lphix.specials.utilities.BlockPair;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class ZiplineHook {
    private static final String chatPrefix = ChatColor.GREEN + "[Zipline] " + ChatColor.AQUA;
    public static void useEvent(PlayerInteractEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        Block block = p.rayTraceBlocks(5).getHitBlock();
        BlockPair blockPair = ZipLineTool.findZipline(block);
        if(blockPair == null){
            p.sendMessage(chatPrefix + "No Zipline was found");
            return;
        }
        e.getPlayer().getWorld().spawn(e.getClickedBlock().getLocation().add(new Vector(0.5,0,0.5).toLocation(e.getPlayer().getWorld())), ArmorStand.class, b ->{
            if(p.isValid()){
                b.addPassenger(p);
            }
        });
        //TODO: make the player ride on the zipline if it is found
    }
}
