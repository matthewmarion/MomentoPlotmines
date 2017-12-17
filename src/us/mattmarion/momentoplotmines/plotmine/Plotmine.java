package us.mattmarion.momentoplotmines.plotmine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.configuration.serialization.SerializableAs;
import us.mattmarion.momentoplotmines.util.Utilities;

import java.lang.reflect.Field;
import java.util.*;

@SerializableAs("Plotmine")
public class Plotmine implements ConfigurationSerializable {

    private UUID ownerUUID;
    private Location location;
    private Material material;
    private int size;
    private List<String> members;

    public Plotmine(UUID ownerUUID, Location location, Material material, int size, List<String> members) {
        this.ownerUUID = ownerUUID;
        this.location = location;
        this.material = material;
        this.size = size;
        this.members = members;
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public static Plotmine load(FileConfiguration config, UUID uuid) {
        String plotminePath = uuid + ".plotmine";
        UUID ownerUUID = UUID.fromString(config.getString(plotminePath + ".ownerUUID"));
        Location location = Utilities.getLocationFromConfig(config,plotminePath + ".location");
        Material material = Material.valueOf(config.getString(plotminePath + ".material"));
        int size = config.getInt(plotminePath + ".size");
        List<String> members = config.getStringList(plotminePath + ".members");
        return new Plotmine(ownerUUID, location, material, size, members);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("ownerUUID", ownerUUID.toString());
        map.put("location", location.serialize());
        map.put("material", material.toString());
        map.put("size", size);
        map.put("members", members.toArray());
        return map;
    }
}
