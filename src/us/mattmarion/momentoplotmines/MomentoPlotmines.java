package us.mattmarion.momentoplotmines;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import us.mattmarion.momentoplotmines.command.CommandHandler;
import us.mattmarion.momentoplotmines.configuration.ConfigManager;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.profile.ProfileListeners;
import java.util.Arrays;

public class MomentoPlotmines extends JavaPlugin {

    private static MomentoPlotmines instance;

    static {
        ConfigurationSerialization.registerClass(Plotmine.class, "Plotmine");
    }


    public void onEnable() {
        instance = this;
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
