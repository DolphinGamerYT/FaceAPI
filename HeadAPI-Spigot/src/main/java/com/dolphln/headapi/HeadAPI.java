package com.dolphln.headapi;

import com.dolphln.headapi.hooks.SkinGetter;
import com.dolphln.headapi.utils.FaceGatherer;
import com.dolphln.headapi.utils.HeadCache;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeadAPI extends JavaPlugin implements Listener {

    private FaceGatherer faceGatherer;
    private SkinGetter skinGetter;
    private HeadCache headCache;

    private HeadAPI instance;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.faceGatherer = new FaceGatherer(this);
        this.skinGetter = new SkinGetter(this);
        this.headCache = new HeadCache(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        faceGatherer.getHeadImage(e.getPlayer(), 12).getFormattedLines();
    }

    public Boolean useHTMLColors() {
        return Bukkit.getServer().getClass().getPackage().toString().contains("1.16");
    }

    public FaceGatherer getFaceGatherer() {
        return faceGatherer;
    }

    public SkinGetter getSkinGetter() {
        return skinGetter;
    }

    public HeadCache getHeadCache() {
        return headCache;
    }

    public HeadAPI getInstance() {
        return instance;
    }
}
