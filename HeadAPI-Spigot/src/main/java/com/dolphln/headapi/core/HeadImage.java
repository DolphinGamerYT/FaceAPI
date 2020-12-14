package com.dolphln.headapi.core;

import com.dolphln.headapi.utils.BlockChar;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class HeadImage {

    private String[] lines;
    private BufferedImage image;
    private int size;

    public HeadImage(BufferedImage image, int size) {
        this.image = image;
        this.size = size;
        Color[][] chatColors = toChatColorArray(this.image, this.size);
        lines = toImgMessage(chatColors, BlockChar.BLOCK.getChar());
    }

    public String getLine(int line) {
        if (line <= this.size && line >= 0) {
            return this.lines[line];
        } else {
            return null;
        }
    }

    public String[] getLines() {
        return lines;
    }

    public String getFormattedLines() {
        StringBuilder head = new StringBuilder();

        for (String line : lines) {
            head.append(line).append("\n");
        }
        return head.substring(0, head.length()-1);
    }

    private Color[][] toChatColorArray(BufferedImage image, int height) {
        double ratio = (double) image.getHeight() / image.getWidth();
        int width = (int) (height / ratio);
        if (width > 10) width = 10;
        BufferedImage resized = resizeImage(image, width, height);

        Color[][] chatImg = new Color[resized.getWidth()][resized.getHeight()];
        for (int x = 0; x < resized.getWidth(); x++) {
            for (int y = 0; y < resized.getHeight(); y++) {
                int rgb = resized.getRGB(x, y);
                chatImg[x][y] = new Color(rgb, true);
            }
        }
        return chatImg;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        AffineTransform af = new AffineTransform();
        af.scale(
                width / (double) originalImage.getWidth(),
                height / (double) originalImage.getHeight());

        AffineTransformOp operation = new AffineTransformOp(af, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return operation.filter(originalImage, null);
    }

    private String[] toImgMessage(Color[][] colors, char imgchar) {
        lines = new String[colors[0].length];

        for (int y = 0; y < colors[0].length; y++) {
            StringBuilder line = new StringBuilder();

            for (Color[] value : colors) {
                Color color = value[y];

                if (color != null) {
                    line.append(ChatColor.of(value[y]))
                            .append(imgchar);
                } else {
                    line.append(BlockChar.TRANSPARENT_CHAR);
                }
            }
            lines[y] = line.toString() + ChatColor.RESET;
        }

        return lines;
    }

    private String colorToHex(Color c) {
        return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    private Boolean useHTMLColors() {
        return Bukkit.getServer().getClass().getPackage().toString().contains("1.16");
    }

}
