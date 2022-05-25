package me.lphix.specials.special_items;

import me.lphix.specials.Specials;
import me.lphix.specials.utilities.BlockPair;
import me.lphix.specials.utilities.TeleportUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ZiplineHook {
    private static final Component prefix = Component.text("[Zipline]", NamedTextColor.GREEN);

    public static void useEvent(PlayerInteractEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        Block block = p.rayTraceBlocks(5).getHitBlock();
        BlockPair blockPair = ZipLineTool.findZipline(block);
        if (blockPair == null) {
            final Component msg = prefix.append(Component.text("No Zipline was found", NamedTextColor.AQUA));
            p.sendMessage(msg);
            return;
        }
        ArmorStand ziplineEntity = e.getPlayer().getWorld().spawn(e.getClickedBlock().getLocation().add(new Vector(0.5, 0, 0.5).toLocation(e.getPlayer().getWorld())), ArmorStand.class, b -> {
            if (p.isValid()) {
                b.setGravity(false);
                b.addPassenger(p);
            }
        });
        boolean atFirstBlock = block.getLocation().equals(blockPair.block1.getLocation());
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                final Location location = blockPair.returnMovingLocation(atFirstBlock, i / (blockPair.lengthXZ * 5)).add(0, -3, 0);
                TeleportUtil.teleportRidingEntity(ziplineEntity, location);
                i++;
                if (i > blockPair.lengthXZ * 5) this.cancel();
            }
        }.runTaskTimer(Specials.getPlugin(), 0L, 1L);
        p.sendMessage("you have been teleported");
        //TODO: make the player ride on the zipline if it is found
    }
}
