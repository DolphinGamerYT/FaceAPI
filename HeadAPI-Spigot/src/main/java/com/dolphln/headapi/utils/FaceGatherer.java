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

    /**
     * Registering an instance of the plugin.
     *
     * @param plugin The instance of our plugin.
     */
    public FaceGatherer(HeadAPI plugin) {
        this.plugin = plugin;
    }

    /**
     * Get the object HeadImage of a Player.
     *
     * @param p Player that we are going to get the HeadImage.
     * @return HeadImage object of the Player.
     */
    public HeadImage getHeadImage(Player p) {
        // SkinsRestorer Support going to be added later
        /*if (plugin.getSkinGetter().isHook() && plugin.getSkinGetter().playerHasSkin(p)) {
            plugin.getSkinGetter().getPlayerSkin(p);
        }*/
        if (plugin.getHeadCache().isPlayerInCache(p)) {
            return plugin.getHeadCache().getPlayerInCache(p);
        }
        BufferedImage image = getPlayerImage(p);
        HeadImage head = new HeadImage(image);
        plugin.getHeadCache().addPlayerInCache(p, head);

        return head;
    }

    /**
     * Get an Image of the player skin's head.
     *
     * @param p Player to get the image.
     * @return BufferedImage of the players head (8x8)
     */
    private BufferedImage getPlayerImage(Player p) {
        URL head_image;

        if (Bukkit.getServer().getOnlineMode()) {
            head_image = getUrl(p.getUniqueId().toString());
        } else {
            head_image = getUrl(p.getName());
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

    /**
     * Get a well formatted URL to fetch the player's head image.
     *
     * @param identifier Identifier (Can be UUID or Player Name)
     * @return URL object well formatted.
     */
    private URL getUrl(String identifier) {
        try {
            return new URL(head_url
                    .replaceAll("%size%", Integer.toString(Size.NORMAL.getHeadSize()))
                    .replaceAll("%identifier%", identifier)
            );
        } catch (Exception e) {
            return null;
        }
    }

}