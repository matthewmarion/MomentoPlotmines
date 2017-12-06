package us.mattmarion.momentoplotmines;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import us.mattmarion.momentoplotmines.command.CommandHandler;
import us.mattmarion.momentoplotmines.configuration.ConfigManager;
import us.mattmarion.momentoplotmines.profile.ProfileListeners;
import java.io.File;
import java.util.Arrays;

public class MomentoPlotmines extends JavaPlugin {

    private static MomentoPlotmines instance;

    public final void onEnable() {
        ConfigManager configManager = new ConfigManager();
        configManager.loadConfig();
        registerListeners(new ProfileListeners());
        registerCommands();
    }

    private void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        getCommand("plotmine").setExecutor(new CommandHandler());
    }

    public static MomentoPlotmines getInstance() {
        return instance;
    }
}
