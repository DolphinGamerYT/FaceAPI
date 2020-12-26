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

    private HashMap<UUID, HeadImage> headCache;
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

    public Boolean isPlayerInCache(Player p) {
        return headCache.containsKey(p.getUniqueId());
    }

    public HeadImage getPlayerInCache(Player p) {
        return headCache.get(p.getUniqueId());
    }

    public void addPlayerInCache(Player p, HeadImage head) {
        headCache.put(p.getUniqueId(), head);
    }

}

