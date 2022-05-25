package me.lphix.specials.utilities;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class TeleportUtil {

    private TeleportUtil() {
        throw new IllegalStateException("Cannot instantiate utility class");
    }

    public static void teleportRidingEntity(Entity entity, Location location) {
        // Try the bukkit method first
        if (entity.teleport(location)) {
            return;
        }
        final CraftEntity craftEntity = (CraftEntity) entity;
        final net.minecraft.world.entity.Entity nmsEntity = craftEntity.getHandle();
        nmsEntity.moveTo(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

}
