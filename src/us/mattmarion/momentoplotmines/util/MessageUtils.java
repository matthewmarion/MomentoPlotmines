package us.mattmarion.momentoplotmines.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import us.mattmarion.momentoplotmines.configuration.ConfigManager;

import java.util.List;

public class MessageUtils {

    public static void tell(CommandSender sender, String message, String placeholder, String value) {
        if (placeholder != null && value != null) {
            message = replacePlaceholders(message, placeholder, value);
        }
        message = replaceColorCodes(message);
        sender.sendMessage(message);
    }

    public static void tellList(CommandSender sender, List<String> messages) {
        if (messages == null) {
            return;
        }
        for (String message : messages) {
            message = replaceColorCodes(message);
            sender.sendMessage(message);
        }
    }

    public static String replacePlaceholders(String message, String placeholder, String value) {
        return message = message.replace(placeholder, value);
    }

    private static String replaceColorCodes(String message) {
        return message = ChatColor.translateAlternateColorCodes('&', message);
    }

    // Util messages
    public static final String NO_PERMISSION_MESSAGE = ConfigManager.getMessagesConfig().getString("NO-PERMISSION-MESSAGE");
    public static final String ONLY_CONSOLE_MESSAGE = ConfigManager.getMessagesConfig().getString("ONLY-CONSOLE-MESSAGE");
    public static final String ONLY_PLAYER_MESSAGE = ConfigManager.getMessagesConfig().getString("ONLY-PLAYER-MESSAGE");
    public static final String PLAYER_NOT_FOUND_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER-NOT-FOUND-MESSAGE");
    public static final String FIELD_NOT_NUMERIC_MESSAGE = ConfigManager.getMessagesConfig().getString("FIELD-NOT-NUMERIC-MESSAGE");

    // Token messages
    public static final String PLAYER_TOKEN_BALANCE_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER-TOKEN_BALANCE-MESSAGE");
    public static final String PLAYER_GIVE_TOKEN_SENDER_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER-GIVE-TOKEN-SENDER-MESSAGE");
    public static final String PLAYER_GIVE_TOKEN_RECIPIENT_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER-GIVE-TOKEN-RECIPIENT-MESSAGE");
    public static final String PLAYER_SET_TOKEN_SENDER_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER-SET-TOKEN_SENDER-MESSAGE");
    public static final String PLAYER_SET_TOKEN_RECIPIENT_MESSAGE = ConfigManager.getMessagesConfig().getString("PLAYER-SET-TOKEN-RECIPIENT-MESSAGE");

    // Mine messages
    public static final List<String> HELP_MESSAGES = ConfigManager.getMessagesConfig().getStringList("HELP-MESSAGES");
    public static final String SUCCESS_CREATE_PLOTMINE_MESSAGE = ConfigManager.getMessagesConfig().getString("SUCCESS-CREATE-PLOTMINE-MESSAGE");
    public static final String INVALID_CREATE_LOCATION_PLOTMINE_MESSAGE = ConfigManager.getMessagesConfig().getString("INVALID-CREATE-LOCATION-PLOTMINE-MESSAGE");
    public static final String CONFIRM_PLOTMINE_CREATE_MESSAGE = ConfigManager.getMessagesConfig().getString("CONFIRM-PLOTMINE-CREATE-MESSAGE");
    public static final String NULL_PLOTMINE_MESSAGE = ConfigManager.getMessagesConfig().getString("NULL-PLOTMINE-MESSAGE");
    public static final String SUCCESS_TELEPORT_MESSAGE = ConfigManager.getMessagesConfig().getString("SUCCESS-TELEPORT-MESSAGE");
    public static final String CONFIRM_PLOTMINE_DELETE_MESSAGE = ConfigManager.getMessagesConfig().getString("CONFIRM-PLOTMINE-DELETE-MESSAGE");
    public static final String SUCCESS_DELETE_PLOTMINE_MESSAGE = ConfigManager.getMessagesConfig().getString("SUCCESS-DELETE-PLOTMINE-MESSAGE");
    public static final String NO_UPGRADES_AVAILABLE = ConfigManager.getMessagesConfig().getString("NO-UPGRADES-AVAILABLE-MESSAGE");
    public static final String SUCCESS_UPGRADE_MINE_MESSAGE = ConfigManager.getMessagesConfig().getString("SUCCESS-UPGRADE-MINE-MESSAGE");

}
