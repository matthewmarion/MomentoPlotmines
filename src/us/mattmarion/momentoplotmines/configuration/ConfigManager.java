package us.mattmarion.momentoplotmines.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import us.mattmarion.momentoplotmines.MomentoPlotmines;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File profilesf, messagesf;
    private static FileConfiguration profilesConfig, messagesConfig;

    public void loadConfig() {
        profilesf = new File(MomentoPlotmines.getInstance().getDataFolder(), "profiles.yml");
        createNewFile(profilesf, "profiles.yml");

        messagesf = new File(MomentoPlotmines.getInstance().getDataFolder(), "messages.yml");
        createNewFile(messagesf, "messages.yml");

        profilesConfig = new YamlConfiguration();
        loadFileIntoConfiguration(profilesf, profilesConfig);

        messagesConfig = new YamlConfiguration();
        loadFileIntoConfiguration(messagesf, messagesConfig);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getProfilesConfig() {
        return profilesConfig;
    }

    public static FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }
}
