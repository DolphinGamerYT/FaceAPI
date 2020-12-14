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
        Color[][] chatColors = toChatColorArray(this.image);
        lines = toImgMessage(chatColors, BlockChar.BLOCK.getChar(), false);
    }

    public HeadImage(BufferedImage image, int size, Boolean useBold) {
        this.image = image;
        this.size = size;
        Color[][] chatColors = toChatColorArray(this.image);
        lines = toImgMessage(chatColors, BlockChar.BLOCK.getChar(), useBold);
    }

    public HeadImage(BufferedImage image, int size, BlockChar blockChar) {
        this.image = image;
        this.size = size;
        Color[][] chatColors = toChatColorArray(this.image);
        lines = toImgMessage(chatColors, blockChar.getChar(), false);
    }

    public HeadImage(BufferedImage image, int size, BlockChar blockChar, Boolean useBold) {
        this.image = image;
        this.size = size;
        Color[][] chatColors = toChatColorArray(this.image);
        lines = toImgMessage(chatColors, blockChar.getChar(), useBold);
    }

    public String getLine(int line) {
        if (line > this.size && line < 1) {
            return null;
        } else {
            return this.lines[line-1];
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

    public int getSize() {
        return this.size;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    private Color[][] toChatColorArray(BufferedImage image) {
        double ratio = (double) image.getHeight() / image.getWidth();
        int width = (int) (this.size / ratio);
        if (width > 10) width = 10;

        Color[][] chatImg = new Color[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
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

    private String[] toImgMessage(Color[][] colors, char imgchar, Boolean useBold) {
        lines = new String[colors[0].length];

        for (int y = 0; y < colors[0].length; y++) {
            StringBuilder line = new StringBuilder();

            for (Color[] value : colors) {
                Color color = value[y];

                if (color != null) {
                    if (useBold){
                        line.append(ChatColor.of(value[y]))
                                .append(ChatColor.BOLD)
                                .append(imgchar);
                    } else {
                        line.append(ChatColor.of(value[y]))
                                .append(imgchar);
                    }
                } else {
                    line.append(BlockChar.TRANSPARENT_CHAR);
                }
            }
            lines[y] = line.toString() + ChatColor.RESET;
        }

        return lines;
    }

}
