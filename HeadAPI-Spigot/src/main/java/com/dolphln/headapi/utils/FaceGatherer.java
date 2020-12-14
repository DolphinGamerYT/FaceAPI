package com.dolphln.headapi.utils;

import com.dolphln.headapi.HeadAPI;
import com.dolphln.headapi.core.HeadImage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class FaceGatherer {

    private HeadAPI plugin;

    private String head_url = "https://minepic.org/avatar/%size%/%identifier%";

    public FaceGatherer(HeadAPI plugin) {
        this.plugin = plugin;
    }

    public HeadImage getHeadImage(Player p) {
        BufferedImage image;

        // SkinsRestorer Support going to be added later
        /*if (plugin.getSkinGetter().isHook() && plugin.getSkinGetter().playerHasSkin(p)) {
            plugin.getSkinGetter().getPlayerSkin(p);
        }*/
        image = getPlayerImage(p);

        return new HeadImage(image, 8);
    }

    private BufferedImage getPlayerImage(Player p) {
        int size = 8;

        URL head_image;
        if (Bukkit.getServer().getOnlineMode()) {

            head_image = getUrl(Integer.toString(size), p.getUniqueId().toString());
        } else {
            head_image = getUrl(Integer.toString(size), p.getName());
        }


        // URL Formatted correctly.
        if (head_image != null) {
            try {
                //User-Agent is needed for HTTP requests
                HttpURLConnection connection = (HttpURLConnection) head_image.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                return ImageIO.read(connection.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private URL getUrl(String size, String identifier) {
        try {
            return new URL(head_url
                    .replaceAll("%size%", size)
                    .replaceAll("%identifier%", identifier)
            );
        } catch (Exception e) {
            return null;
        }
    }

}