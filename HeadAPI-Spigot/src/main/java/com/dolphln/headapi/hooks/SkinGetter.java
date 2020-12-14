package com.dolphln.headapi.hooks;

import com.dolphln.headapi.HeadAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import skinsrestorer.bukkit.SkinsRestorer;
import skinsrestorer.bukkit.SkinsRestorerBukkitAPI;

public class SkinGetter {

    private HeadAPI plugin;
    private Boolean isHook;

    private SkinsRestorer skinsRestorer;
    private SkinsRestorerBukkitAPI skinsRestorerBukkitAPI;

    public SkinGetter(HeadAPI plugin) {
        this.plugin = plugin;
        this.isHook = setup();
    }

    private Boolean setup() {
        if (Bukkit.getPluginManager().getPlugin("SkinsRestorer") != null && Bukkit.getPluginManager().getPlugin("SkinsRestorer").isEnabled()) {
            //Connecting to the main SkinsRestorer API
            this.skinsRestorer = JavaPlugin.getPlugin(SkinsRestorer.class);

            // Connecting to Bukkit API for applying the skin
            this.skinsRestorerBukkitAPI = skinsRestorer.getSkinsRestorerBukkitAPI();
            return true;
        }
        return false;
    }

    public void getPlayerSkin(Player p) {
        Object skin = this.skinsRestorerBukkitAPI.getSkinData(this.skinsRestorerBukkitAPI.getSkinName(p.getName()));
        System.out.println(skin);
    }
}
