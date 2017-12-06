package us.mattmarion.momentoplotmines.util;

import us.mattmarion.momentoplotmines.configuration.ConfigManager;

public class MessageUtils {
    public static final String NO_PERMISSION_MESSAGE = ConfigManager.getMessagesConfig().getString("NO_PERMISSION_MESSAGE");
    public static final String ONLY_CONSOLE_MESSAGE = ConfigManager.getMessagesConfig().getString("ONLY_CONSOLE_MESSAGE");
    public static final String ONLY_PLAYER_MESSAGE = ConfigManager.getMessagesConfig().getString("ONLY_PLAYER_MESSAGE");


}
