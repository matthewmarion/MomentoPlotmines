package us.mattmarion.momentoplotmines.profile;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.configuration.ConfigManager;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.util.Utilities;

import java.util.*;

public class Profile {

    private static Set<Profile> profiles = new HashSet<>();
    private UUID uuid;
    private Player player;
    private int tokens;
    private Plotmine plotmine;

    public Profile(UUID uuid, Player player) {
        this.uuid = uuid;
        this.player = player;
        profiles.add(this);
    }

    public Profile(Player player) {
        this(player.getUniqueId(), player);
    }

    public static Profile getByUUID(UUID uuid) {
        for (Profile profile : profiles) {
            if (profile.getUuid().equals(uuid)) {
                return profile;
            }
        }
        return null;
    }

    public static Profile getByPlayer(Player player) {
        return getByUUID(player.getUniqueId());
    }

    public static Set<Profile> getProfiles() {
        return profiles;
    }


    public void save() {
        FileConfiguration profilesConfig = ConfigManager.getProfilesConfig();
        profilesConfig.set(uuid + ".name", player.getName());
        profilesConfig.set(uuid + ".tokens", tokens);
        profilesConfig.set(uuid + ".plotmine", plotmine.serialize());
        ConfigManager.save(ConfigManager.getProfilesFile(), profilesConfig);
    }

    public void load() {
        FileConfiguration profilesConfig = ConfigManager.getProfilesConfig();
        tokens = profilesConfig.getInt(uuid + ".tokens");
        if (profilesConfig.get(uuid + ".plotmine") == null) {
            return;
        }
        plotmine = Plotmine.load(profilesConfig, uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public int getTokens() {
        return tokens;
    }

    public void addTokens(int amount) {
        tokens += amount;
    }

    public void removeTokens(int amount) {
        tokens -= amount;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public Plotmine getPlotmine() {
        return plotmine;
    }

    public void setPlotmine(Plotmine plotmine) {
        this.plotmine = plotmine;
    }
}
