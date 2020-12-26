package com.dolphln.headapi.hooks;

import com.dolphln.headapi.HeadAPI;
import com.dolphln.headapi.core.HeadImage;
import com.dolphln.headapi.utils.BlockChar;
import com.dolphln.headapi.utils.Size;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class HeadPlaceholders extends PlaceholderExpansion {

    private HeadAPI plugin;

    /**
     * Since we register the expansion inside our own plugin, we
     * can simply use this method here to get an instance of our
     * plugin.
     *
     * @param plugin
     *        The instance of our plugin.
     */
    public HeadPlaceholders(HeadAPI plugin) {
        this.plugin = plugin;
    }

    /**
     * Because this is an internal class,
     * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
     * PlaceholderAPI is reloaded
     *
     * @return true to persist through reloads
     */
    @Override
    public boolean persist(){
        return true;
    }

    /**
     * Because this is a internal class, this check is not needed
     * and we can simply return {@code true}
     *
     * @return Always true since it's an internal class.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * <br>For convienience do we return the author from the plugin.yml
     *
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest
     * method to obtain a value if a placeholder starts with our
     * identifier.
     * <br>The identifier has to be lowercase and can't contain _ or %
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "headapi";
    }

    /**
     * This is the version of the expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * For convienience do we return the version from the plugin.yml
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    /**
     * This is the Placeholders Parser, were the placeholders are splitted and analized.
     *
     *
     * @param player Player to apply the Placeholder
     * @param identifier Placeholder used
     * @return Parsed placeholder
     */

    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        // Format: %headapi_<size>_[line]%

        String[] placeholder = identifier.split("_");

        if (placeholder.length >= 1) {
            int size;
            try {
                size = Integer.parseInt(placeholder[0]);
            } catch (Exception ignored) {
                return null;
            }

            if (size < 1 || size > 88) {
                return null;
            }

            HeadImage head = plugin.getFaceGatherer().getHeadImage(player);

            if (placeholder.length == 1) {
                return head.getFormattedLines(BlockChar.BLOCK, Size.getSize(size), false);
            } else if (placeholder.length == 2) {
                int line;
                try {
                    line = Integer.parseInt(placeholder[1]);
                } catch (Exception ignored) {
                    return null;
                }

                return head.getLine(line, BlockChar.BLOCK, Size.getSize(size), false);
            }
        }

        return null;
    }
}
