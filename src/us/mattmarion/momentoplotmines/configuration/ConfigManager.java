package us.mattmarion.momentoplotmines.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import us.mattmarion.momentoplotmines.MomentoPlotmines;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File profilesf, messagesf, plotminef;
    private static FileConfiguration profilesConfig, messagesConfig, plotmineConfig;

    public void loadConfig() {
        profilesf = new File(MomentoPlotmines.getInstance().getDataFolder(), "profiles.yml");
        createNewFile(profilesf, "profiles.yml");

        messagesf = new File(MomentoPlotmines.getInstance().getDataFolder(), "messages.yml");
        createNewFile(messagesf, "messages.yml");

        plotminef = new File(MomentoPlotmines.getInstance().getDataFolder(), "plotmine.yml");
        createNewFile(plotminef, "plotmine.yml");

        profilesConfig = new YamlConfiguration();
        loadFileIntoConfiguration(profilesf, profilesConfig);

        messagesConfig = new YamlConfiguration();
        loadFileIntoConfiguration(messagesf, messagesConfig);

        plotmineConfig = new YamlConfiguration();
        loadFileIntoConfiguration(plotminef, plotmineConfig);
    }

    private void createNewFile(File file, String fileName) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            MomentoPlotmines.getInstance().saveResource(fileName, false);
        }
    }

    private void loadFileIntoConfiguration(File file, FileConfiguration fileConfiguration) {
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save(File file, FileConfiguration fileConfiguration) {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(FileConfiguration fileConfig, String path) {
        return (T) fileConfig.get(path);
    }

    public static File getProfilesFile() {
        return profilesf;
    }

    public static FileConfiguration getProfilesConfig() {
        return profilesConfig;
    }

    public static FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }

    public static FileConfiguration getPlotmineConfig() {
        return plotmineConfig;
    }
}
