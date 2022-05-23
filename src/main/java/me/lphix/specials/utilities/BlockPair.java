package me.lphix.specials.utilities;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockPair {
    Block block1;
    Block block2;

    public BlockPair(Block block1, Block block2){
        this.block1 = block1;
        this.block2 = block2;
    }

    public boolean checkBlock(Block block){
        if(block1 == block || block2 == block) return true;
        return false;
    }
}
