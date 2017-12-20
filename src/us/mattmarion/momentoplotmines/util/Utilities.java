package us.mattmarion.momentoplotmines.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Utilities {

    public static Location getLocationFromConfig(FileConfiguration config, String path) {
        World world = Bukkit.getWorld(config.getString(path + ".world"));
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static boolean targetIsNull(Player target) {
        return target == null || !target.isOnline();
    }

    public static Vector getMinimumVectorFromLocation(org.bukkit.Location location, int size) {
        return new Vector(location.getX() - (size / 2), location.getY() - 1, location.getZ() - (size / 2));
    }

    public static Vector getMaximumVectorFromLocation(org.bukkit.Location location, int size) {
        return new Vector(location.getX() + (size / 2), location.getY() - size, location.getZ() + (size / 2));
    }
}
