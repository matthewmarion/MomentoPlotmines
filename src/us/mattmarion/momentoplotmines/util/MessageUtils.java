package us.mattmarion.momentoplotmines.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import us.mattmarion.momentoplotmines.configuration.ConfigManager;

public class MessageUtils {

    public static void tell(CommandSender sender, String message, String placeholder, String value) {
        if (placeholder != null && value != null) {
            message = replacePlaceholders(message, placeholder, value);
        }
        message = replaceColorCodes(message);
        sender.sendMessage(message);
    }

    public static String replacePlaceholders(String message, String placeholder, String value) {
        return message = message.replace(placeholder, value);
    }

    private static String replaceColorCodes(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', message);
    }

    public static final String NO_PERMISSION_MESSAGE = ConfigManager.getMessagesConfig().getString("NO_PERMISSION_MESSAGE");
    public static final String ONLY_CONSOLE_MESSAGE = ConfigManager.getMessagesConfig().getString("ONLY_CONSOLE_MESSAGE");
    public static final String ONLY_PLAYER_MESSAGE = ConfigManager.getMessagesConfig().getString("ONLY_PLAYER_MESSAGE");
    public static final String PLAYER_NOT_FOUND_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER_NOT_FOUND_MESSAGE");
    public static final String FIELD_NOT_NUMERIC_MESSAGE = ConfigManager.getMessagesConfig().getString("FIELD_NOT_NUMERIC_MESSAGE");

    public static final String PLAYER_TOKEN_BALANCE_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER_TOKEN_BALANCE_MESSAGE");
    public static final String PLAYER_GIVE_TOKEN_SENDER_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER_GIVE_TOKEN_SENDER_MESSAGE");
    public static final String PLAYER_GIVE_TOKEN_RECIPIENT_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER_GIVE_TOKEN_RECIPIENT_MESSAGE");
    public static final String PLAYER_SET_TOKEN_SENDER_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER_SET_TOKEN_SENDER_MESSAGE");
    public static final String PLAYER_SET_TOKEN_RECIPIENT_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER_SET_TOKEN_RECIPIENT_MESSAGE");

}
