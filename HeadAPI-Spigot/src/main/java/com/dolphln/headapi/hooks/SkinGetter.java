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

        //Object skinData = this.skinsRestorer.getSkinStorage().getSkinData(this.skinsRestorer.getSkinStorage().getPlayerSkin(p.getName()), true);
        //System.out.println(this.skinsRestorer.getSkinStorage().getPlayerSkin(p.getName()).toString());
        System.out.println(this.skinsRestorerBukkitAPI.getSkinData(p.getUniqueId().toString()).toString());

    }

    public Boolean playerHasSkin(Player p) {
        return this.skinsRestorerBukkitAPI.hasSkin(p.getName());
    }

    public Boolean isHook() {
        return isHook;
    }
}
