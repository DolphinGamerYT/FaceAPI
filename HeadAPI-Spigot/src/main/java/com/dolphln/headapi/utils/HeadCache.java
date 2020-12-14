package com.dolphln.headapi.utils;

import com.dolphln.headapi.HeadAPI;
import com.dolphln.headapi.core.HeadImage;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class HeadCache {

    private HeadAPI plugin;

    private HashMap<PlayerHeadData, HeadImage> headCache;
    private BukkitTask deleteTask;

    public HeadCache(HeadAPI plugin) {
        this.plugin = plugin;

        this.headCache = new HashMap<>();
        this.deleteTask = new BukkitRunnable() {
            @Override
            public void run() {
                headCache.clear();
            }
        }.runTaskTimerAsynchronously(plugin, 0L, 2400L);
    }

    public Boolean isPlayerInCache(Player p, int size) {
        return headCache.containsKey(new PlayerHeadData(p.getUniqueId(), size));
    }

    public HeadImage getPlayerInCache(Player p, int size) {
        PlayerHeadData player = new PlayerHeadData(p.getUniqueId(), size);

        if (headCache.containsKey(player)) {
            return headCache.get(player);
        }
        return null;
    }

    public void addPlayerInCache(Player p, HeadImage head) {
        headCache.put(new PlayerHeadData(p.getUniqueId(), head.getSize()), head);
    }

    private static class PlayerHeadData {

        private final UUID uuid;
        private final int size;

        private PlayerHeadData(UUID uuid, int size) {
            this.uuid = uuid;
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public UUID getUUID() {
            return uuid;
        }
    }

}

