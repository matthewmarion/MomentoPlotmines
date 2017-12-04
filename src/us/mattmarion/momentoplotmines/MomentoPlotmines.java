package us.mattmarion.momentoplotmines;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import us.mattmarion.momentoplotmines.profile.ProfileListeners;

import java.util.Arrays;

public class MomentoPlotmines extends JavaPlugin {

    private static MomentoPlotmines instance;

    public final void onEnable() {
        registerListeners(new ProfileListeners());
    }

    private void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    public static MomentoPlotmines getInstance() {
        return instance;
    }
}
