package us.mattmarion.momentoplotmines.plotmine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;
import us.mattmarion.momentoplotmines.util.Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@SerializableAs("Plotmine")
public class Plotmine implements ConfigurationSerializable {

    private UUID ownerUUID;
    private Location location;
    private Vector minimumPoint;
    private Vector maximumPoint;
    private Material material;
    private int size;
    private List<String> members;

    public Plotmine(UUID ownerUUID, Location location, Vector minimumPoint, Vector maximumPoint, Material material, int size, List<String> members) {
        this.ownerUUID = ownerUUID;
        this.location = location;
        this.minimumPoint = minimumPoint;
        this.maximumPoint = maximumPoint;
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

    public Vector getMinimumPoint() {
        return minimumPoint;
    }

    public void setMinimumPoint(Vector minimumPoint) {
        this.minimumPoint = minimumPoint;
    }

    public Vector getMaximumPoint() {
        return maximumPoint;
    }

    public void setMaximumPoint(Vector maximumPoint) {
        this.maximumPoint = maximumPoint;
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

    public void addMember(String uuid) {
        this.members.add(uuid);
    }

    public void removeMember(String uuid) {
        this.members.remove(uuid);
    }

    public boolean isMember(String uuid) {
        return members.contains(uuid);
    }

    public boolean containsLocation(com.intellectualcrafters.plot.object.Location location) {
        return location.getX() >= this.minimumPoint.getX() - 1 && location.getX() <= this.maximumPoint.getX() && location.getZ() >= this.minimumPoint.getZ() - 1 && location.getZ() <= this.maximumPoint.getZ();
    }

    public static Plotmine load(FileConfiguration config, UUID uuid) {
        String plotminePath = uuid + ".plotmine";
        UUID ownerUUID = UUID.fromString(config.getString(plotminePath + ".ownerUUID"));
        Location location = Utilities.getLocationFromConfig(config,plotminePath + ".location");
        Vector minimumPoint = config.getVector(plotminePath + ".minimumPoint");
        Vector maximumPoint = config.getVector(plotminePath + ".maximumPoint");
        Material material = Material.valueOf(config.getString(plotminePath + ".material"));
        int size = config.getInt(plotminePath + ".size");
        List<String> members = config.getStringList(plotminePath + ".members");
        return new Plotmine(ownerUUID, location, minimumPoint, maximumPoint, material, size, members);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("ownerUUID", ownerUUID.toString());
        map.put("location", location.serialize());
        map.put("minimumPoint", minimumPoint);
        map.put("maximumPoint", maximumPoint);
        map.put("material", material.toString());
        map.put("size", size);
        map.put("members", members.toArray());
        return map;
    }
}
