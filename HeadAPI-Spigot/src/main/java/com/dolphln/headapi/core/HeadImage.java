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

    /**
     * Create an image head.
     *
     * @param image BufferedImage to get colors from.
     */
    public HeadImage(BufferedImage image) {
        this.image = image;
        this.colors = generateColors();
    }

    /**
     * Get a specific line.
     *
     * @param line Integer of line you want to get.
     * @param c Character you want to get the line of.
     * @param size Size you want to get for the line.
     * @param useBold Use Minecraft Color Code for Bold.
     * @return String of the line.
     */
    public String getLine(int line, BlockChar c, Size size, Boolean useBold) {
        return generateLine(line, size.getCharSize(), c, useBold);
    }

    /**
     * Get all the lines of the head.
     *
     * @param c Character you want to get the lines of.
     * @param size Size you want to get the lines.
     * @param useBold Use Minecraft Color Code for Bold.
     * @return Array of Strings of the head.
     */
    public String[] getLines(BlockChar c, Size size, Boolean useBold) {
        ArrayList<String> lines = new ArrayList<>();

        for (int i = 0; i < size.getHeadSize(); i++) {
            lines.add(getLine(i, c, size, useBold));
        }

        return lines.toArray(lines.toArray(new String[lines.size()]));
    }

    /**
     * Get a single String of the whole head with line intents.
     *
     * @param c Character you want to get the head of.
     * @param size Size you want to get the head.
     * @param useBold Use Minecraft Color Code for Bold.
     * @return Single String of the whole head
     */
    public String getFormattedLines(BlockChar c, Size size, Boolean useBold) {
        StringBuilder head = new StringBuilder();

        for (String line : getLines(c, size, useBold)) {
            head.append(line).append("\n");
        }
        return head.substring(0, head.length()-1);
    }

    /**
     * Get the original image.
     *
     * @return BufferedImage of the head.
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Generate a line of the head.
     *
     * @param line Integer of the line you want to get.
     * @param c Character you want to get the head of.
     * @param useBold Use Minecraft Color Code for Bold.
     * @return String of the desired line.
     */
    private String generateLine(int line, int space, BlockChar c, Boolean useBold) {
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

    /**
     * Make initial generation of the head colors.
     * @return Bidimensional Array with the colors.
     */
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
