package cl_esmp.erneto1307.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {

    public static String getColoredMessage(String text) {
        if (SupplementaryUtils.newestServer()) {
            Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
            Matcher match = pattern.matcher(text);

            while (match.find()) {
                String color = text.substring(match.start(),match.end());
                text = text.replace(color, ChatColor.of(color)+"");

                match = pattern.matcher(text);
            }
        }

        text = ChatColor.translateAlternateColorCodes('&', text);
        return text;
    }
}
