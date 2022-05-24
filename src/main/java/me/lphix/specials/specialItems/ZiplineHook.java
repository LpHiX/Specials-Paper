package me.lphix.specials.specialItems;

import me.lphix.specials.Specials;
import me.lphix.specials.utilities.BlockPair;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
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
        ArmorStand ziplineEntity = e.getPlayer().getWorld().spawn(e.getClickedBlock().getLocation().add(new Vector(0.5,0,0.5).toLocation(e.getPlayer().getWorld())), ArmorStand.class, b ->{
            if(p.isValid()){
                b.setGravity(false);
                b.addPassenger(p);
            }
        });
        boolean atFirstBlock = block.getLocation().equals(blockPair.block1.getLocation());
        new BukkitRunnable(){
            int i = 0;
            @Override
            public void run() {
                ziplineEntity.removePassenger(p);
                ziplineEntity.teleport(blockPair.returnMovingLocation(atFirstBlock, i/(blockPair.lengthXZ * 5)).add(0,- 3,0));
                i++;
                ziplineEntity.addPassenger(p);
                if(i > blockPair.lengthXZ * 5) this.cancel();
            }
        }.runTaskTimer(Specials.getPlugin(), 0L, 1L);
        p.sendMessage("you have been teleported");
        //TODO: make the player ride on the zipline if it is found
    }
}
