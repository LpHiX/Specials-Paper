package me.lphix.specials.specialItems;

import me.lphix.specials.Specials;
import me.lphix.specials.utilities.BlockPair;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ZipLineTool {
    private static final String chatPrefix = ChatColor.GREEN + "[Zipline] " + ChatColor.AQUA;

    public static HashMap<BlockPair, BukkitTask> blockPairs = new HashMap<>();
    private static final HashMap<Player, Block> firstBlock = new HashMap<>();
    public static void useEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block block;
        try {
            block = p.rayTraceBlocks(5).getHitBlock();
        }catch (Exception ex){
            p.sendMessage(chatPrefix + "Block is out of sight or too far!");
            return;
        }
        if(!block.getType().equals(Material.OAK_FENCE)){
            p.sendMessage(chatPrefix + "Block must be Oak Fence");
            return;
        }
        if(!(findZipline(block)==null)){
            blockPairs.get(findZipline(block)).cancel();
            blockPairs.remove(findZipline(block));
            p.sendMessage(chatPrefix + "Zipline has been removed");
            return;
        }

        if(!(firstBlock.containsKey(e.getPlayer()))){
            firstBlock.put(p, block);
            p.sendMessage(chatPrefix + "First point has been selected.");
        } else{
            Block block1;
            Block block2;
            if(firstBlock.get(p).getLocation().getY() <= block.getLocation().getY()){
                block1 = firstBlock.get(p);
                block2 = block;
            } else {
                block1 = block;
                block2 = firstBlock.get(p);
            }
            visualizeBlockPair(block1, block2, p);
        }
    }
    private static void visualizeBlockPair(Block block1, Block block2, Player p){
        BlockPair blockpair = new BlockPair(block1, block2);
        BukkitScheduler scheduler = Bukkit.getScheduler();
        BukkitTask visualizeTask = scheduler.runTaskTimer(Specials.getPlugin(), () -> {
            blockpair.visualizeParticles();
        }, 0L, 5L);

        blockPairs.put(new BlockPair(block1, block2), visualizeTask);
        firstBlock.remove(p);
        p.sendMessage(chatPrefix + "Zipline has been created");
    }
    public static BlockPair findZipline(Block block){
        for (BlockPair blockPairs: blockPairs.keySet()){
            if(blockPairs.checkBlock(block)){
                return blockPairs;
            }
        }
        return null;
    }
}
