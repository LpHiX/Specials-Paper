package me.lphix.specials.specialItems;

import me.lphix.specials.utilities.BlockPair;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class ZipLineTool {
    private static final String chatPrefix = ChatColor.GREEN + "[Zipline] " + ChatColor.AQUA;

    public static ArrayList<BlockPair> blockPairs = new ArrayList<>();
    private static HashMap<Player, Block> firstBlock = new HashMap<>();
    public static void useEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block block = p.rayTraceBlocks(3).getHitBlock();
        if(block == null){
            p.sendMessage(chatPrefix + "Block is out of sight or too far!");
            return;
        }
        if(block.getType().equals(Material.OAK_FENCE)){
            p.sendMessage(chatPrefix + "Block must be Oak Fence");
            return;
        }

        if(!(firstBlock.containsKey(e.getPlayer()))){
            p.sendMessage(chatPrefix + "First point has been selected.");
            firstBlock.put(p, block);
        } else{
            blockPairs.add(new BlockPair(firstBlock.get(p), block));
        }
    }
}
