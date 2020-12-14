package com.dolphln.headapi.utils;

import com.dolphln.headapi.HeadAPI;
import com.dolphln.headapi.core.HeadImage;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;

public class FaceGatherer {

    private HeadAPI plugin;

    private String head_url = "https://minepic.org/avatar/%size%/%identifier%";

    public FaceGatherer(HeadAPI plugin) {
        this.plugin = plugin;
    }

    public HeadImage getHeadImage(Player p) {
        BufferedImage image = getPlayerImage(p);

        return new HeadImage(image, 8);
    }

    private BufferedImage getPlayerImage(Player player) {
        int size = 8;
        URL head_image = getUrl(Integer.toString(size), player.getUniqueId().toString());

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

    private URL getUrl(String size, String uuid) {
        try {
            return new URL(head_url
                    .replaceAll("%size%", size)
                    .replaceAll("%identifier", uuid)
            );
        } catch (Exception e) {
            return null;
        }
    }



}