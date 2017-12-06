package us.mattmarion.momentoplotmines.profile;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.configuration.ConfigManager;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Profile {

    private static Set<Profile> profiles = new HashSet<>();
    private UUID uuid;
    private Player player;
    private double tokens;
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
        FileConfiguration profileConfig = ConfigManager.getProfilesConfig();
        profileConfig.set(uuid + ".name", player.getName());
        profileConfig.set(uuid + ".tokens", tokens);
        // profileConfig.set(uuid + ".plotmine" + ".tier", plotmine.getTier());
        // profileConfig.set(uuid + ".plotmine" + ".composition", plotmine.getComposition());
    }

    public void load() {
        FileConfiguration profileConfig = ConfigManager.getProfilesConfig();
        tokens = profileConfig.getDouble(uuid + ".tokens");
        // TODO: Load plotmine configuration data
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public double getTokens() {
        return tokens;
    }

    public void setTokens(double tokens) {
        this.tokens = tokens;
    }

    public Plotmine getPlotmine() {
        return plotmine;
    }

    public void setPlotmine(Plotmine plotmine) {
        this.plotmine = plotmine;
    }
}
