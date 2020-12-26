package com.dolphln.headapi.core;

import com.dolphln.headapi.utils.BlockChar;
import com.dolphln.headapi.utils.Size;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HeadImage {

    private BufferedImage image;
    private Color[][] colors;

    public HeadImage(BufferedImage image) {
        this.image = image;
        process();
    }

    private void process() {
        this.colors = generateColors();
    }

    public String getLine(int line, BlockChar c, Size size, Boolean useBold) {
        return generateLine(line, size.getCharSize(), c, useBold);
    }

    public String[] getLines(BlockChar c, Size size, Boolean useBold) {
        ArrayList<String> lines = new ArrayList<>();

        for (int i = 0; i < size.getHeadSize(); i++) {
            lines.add(getLine(i, c, size, useBold));
        }

        return lines.toArray(lines.toArray(new String[lines.size()]));
    }

    public String getFormattedLines(BlockChar c, Size size, Boolean useBold) {
        StringBuilder head = new StringBuilder();

        for (String line : getLines(c, size, useBold)) {
            head.append(line).append("\n");
        }
        return head.substring(0, head.length()-1);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    private String generateLine(int line, int space , BlockChar c, Boolean useBold) {
        int realLine = line/space;
        StringBuilder formatedLine = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int x = 0; x < space; x++) {
                formatedLine
                        .append(ChatColor.of(this.colors[i][realLine]))
                        .append((useBold) ? ChatColor.BOLD : "")
                        .append(c.getChar());
            }
        }
        return formatedLine.toString();
    }

    private Color[][] generateColors() {
        Color[][] colors = new Color[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                colors[x][y] = new Color(image.getRGB(x, y), true);
            }
        }
        return colors;
    }

}
