package me.lphix.specials.utilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.Random;

public class BlockPair {
    Block block1;
    Block block2;

    Vector diffXZ;
    int diffY;
    float lengthXZ;
    double parameterA;
    double parameterB;
    float length;

    public BlockPair(Block block1, Block block2){
        this.block1 = block1;
        this.block2 = block2;

        Location point1 = block1.getLocation();
        Location point2 = block2.getLocation();

        this.diffXZ = new Vector(point2.getX()-point1.getX(), 0, point2.getZ()-point1.getZ());
        this.diffY = block2.getY()-block1.getY();
        this.parameterA = Math.pow(diffXZ.length(), -1.5) * (Math.pow(diffY, 0.5) + 1);
        this.parameterB = diffXZ.length() - diffY/(parameterA * diffXZ.length());
        this.length = Math.round(block2.getLocation().clone().subtract(block1.getLocation()).length());
        this.lengthXZ = (float) diffXZ.length();
    }

    public boolean checkBlock(Block block){
        if(block1.getLocation().equals(block.getLocation()) || block2.getLocation().equals(block.getLocation())) return true;
        return false;
    }

    public void visualizeParticles(){
        for (int i = 0; i < length; i++) {
            float t =  (float) i * (float)diffXZ .length()/length + new Random().nextFloat();
            block1.getWorld().spawnParticle(Particle.REDSTONE, returnMovingLocation(1,t/lengthXZ), 1, new Particle.DustOptions(Color.fromRGB(50,255,200), 1));
        }
    }
    public Location returnMovingLocation(int blockID, float parameter){
        float t = parameter * lengthXZ;
        return block1.getLocation().clone().add(diffXZ.clone().normalize().multiply(t).setY(parameterA * t * (t - parameterB))).add(new Vector(0.5,0,0.5));
    }
}
