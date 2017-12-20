package us.mattmarion.momentoplotmines.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.configuration.ConfigManager;

public class PermissionUtils {

    public static Material getTierComposition(Player player) {
        for (int i = 3; i > 0; i--) {
            String permission = "plotmine.tier" + i;
            System.out.println("checking for: " + permission);
            if (player.hasPermission("plotmine.tier" + i)) {
                System.out.println("Stopping because found on: " + i);
                return Material.getMaterial(ConfigManager.getPlotmineConfig().getString("Tier" + i + ".composition"));
            }
        }
        System.out.println("found nothing");
        return Material.getMaterial(ConfigManager.getPlotmineConfig().getString("Tier1" + ".composition"));
    }

    public static int getTierSize(Player player) {
        for (int i = 3; i > 0; i--) {
            if (player.hasPermission("plotmine.tier" + i)) {
                return ConfigManager.getPlotmineConfig().getInt("Tier" + i + ".size");
            }
        }
        return ConfigManager.getPlotmineConfig().getInt("Tier1" + ".size");
    }
}
