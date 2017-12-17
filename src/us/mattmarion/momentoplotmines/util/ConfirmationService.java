package us.mattmarion.momentoplotmines.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ConfirmationService {

    public static boolean playerIsPending(HashMap<UUID, Integer> pendingPlayers, Player player) {
        return pendingPlayers.get(player.getUniqueId()) != null;
    }

    public static void setPending(HashMap<UUID, Integer> pendingPlayers, Player player, String message) {
        pendingPlayers.put(player.getUniqueId(), 0);
        MessageUtils.tell(player, message, null, null);
    }

    public static void removePending(HashMap<UUID, Integer> pendingPlayers, Player player) {
        pendingPlayers.remove(player.getUniqueId());
    }
}
