package me.lphix.specials.specialItems;

import me.lphix.specials.Specials;
import org.bukkit.Color;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class HookItem {

    public static void useEvent(PlayerInteractEvent e) {
        try{
            Player p = e.getPlayer();
            Block block = e.getPlayer().rayTraceBlocks(100, FluidCollisionMode.NEVER).getHitBlock();
            new BukkitRunnable(){
                int pulls = 0;
                @Override
                public void run() {
                    Location loc = p.getLocation();
                    for (int i = 0; i < 30; i++) {
                        Vector parametric = block.getLocation().toVector().subtract(loc.toVector());
                        p.getWorld().spawnParticle(Particle.REDSTONE, loc.toVector().add(parametric.multiply(((float)i+ new Random().nextFloat())/30)).toLocation(p.getWorld()), 1,
                                new Particle.DustOptions(Color.fromRGB(50,255,200), 1));
                    }
                    pulls++;
                    if(pulls < 15) {
                        p.setVelocity(p.getVelocity().add(block.getLocation().toVector().subtract(loc.toVector()).normalize().multiply(0.2)));
                    }
                    if(pulls > 50){
                        this.cancel();
                    }
                }
            }.runTaskTimer(Specials.getPlugin(), 0L, 1L);
        } catch (Exception ignored){
        }
    }
}
