package us.mattmarion.momentoplotmines.plotmine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

public class Plotmine implements ConfigurationSerializable {

    private UUID owner;
    private Location location;
    private Material material;
    private int size;

    private Set<UUID> members;

    public Plotmine(UUID owner, Location location, Material material, int size, Set<UUID> members) {
        this.owner = owner;
        this.location = location;
        this.material = material;
        this.size = size;
        this.members = members;
    }

    public Plotmine(Map<String, Object> map) {
        owner = (UUID) map.get("owner");
        location = (Location) map.get("location");
        material = (Material) map.get("material");
        size = (Integer) map.get("size");
        members = (Set<UUID>) map.get("members");
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<UUID> getMembers() {
        return members;
    }

    public void setMembers(Set<UUID> members) {
        this.members = members;
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("owner", owner.toString());
        map.put("location", location.serialize());
        map.put("material", material.toString());
        map.put("size", size);
        map.put("members", members.toArray());
        return map;
    }
}
