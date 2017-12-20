package us.mattmarion.momentoplotmines.plotmine;

import com.intellectualcrafters.plot.object.Plot;
import com.sk89q.worldedit.Vector;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.*;

public class PlotmineService {

    private Plotmine plotmine;
    private Plot playerPlot;
    public List<com.intellectualcrafters.plot.object.Location> plotMineCorners = new ArrayList<>();
    private double xOne;
    private double xTwo;
    private double zOne;
    private double zTwo;
    private double yOne;
    private double yTwo;

    public PlotmineService(Plotmine plotmine, Plot playerPlot) {
        this.plotmine = plotmine;
        this.playerPlot = playerPlot;
        xOne = plotmine.getLocation().getX() - (plotmine.getSize() / 2);
        xTwo = plotmine.getLocation().getX() + (plotmine.getSize() / 2);
        zOne = plotmine.getLocation().getZ() - (plotmine.getSize() / 2);
        zTwo = plotmine.getLocation().getZ() + (plotmine.getSize() / 2);
        yOne = plotmine.getLocation().getY() - 1;
        yTwo = plotmine.getLocation().getY() -(plotmine.getSize());
        plotMineCorners.add(new com.intellectualcrafters.plot.object.Location(plotmine.getLocation().getWorld().getName(), (int) xOne, (int) yOne, (int) zOne));
        plotMineCorners.add(new com.intellectualcrafters.plot.object.Location(plotmine.getLocation().getWorld().getName(), (int) xTwo, (int) yOne, (int) zTwo));
        plotMineCorners.add(new com.intellectualcrafters.plot.object.Location(plotmine.getLocation().getWorld().getName(), (int) xOne, (int) yOne, (int) zTwo));
        plotMineCorners.add(new com.intellectualcrafters.plot.object.Location(plotmine.getLocation().getWorld().getName(), (int) xTwo, (int) yOne, (int) zOne));
    }

    public PlotmineService(Plotmine plotmine) {
        this.plotmine = plotmine;
        xOne = plotmine.getLocation().getX() - (plotmine.getSize() / 2);
        xTwo = plotmine.getLocation().getX() + (plotmine.getSize() / 2);
        zOne = plotmine.getLocation().getZ() - (plotmine.getSize() / 2);
        zTwo = plotmine.getLocation().getZ() + (plotmine.getSize() / 2);
        yOne = plotmine.getLocation().getY() - 1;
        yTwo = plotmine.getLocation().getY() -(plotmine.getSize());
    }

    public void build(Material material) {
        if (playerPlot != null) {
            if (!canPlacePlotHere(playerPlot)) {
                return;
            }
        }
        // Creating a plotmine
        if (playerPlot != null) {
            Cuboid borderCube = new Cuboid(plotmine.getLocation().getWorld().getName(), xOne - 1, yOne, zOne - 1, xTwo + 1, yTwo - 1, zTwo + 1);
            replaceCubeBlock(borderCube, Material.BEDROCK);
            Cuboid plotCube = new Cuboid(plotmine.getLocation().getWorld().getName(), xOne, yOne, zOne, xTwo, yTwo, zTwo);
            replaceCubeBlock(plotCube, material);
            return;
        }
        //When deleting a plotmine
        Cuboid plotCube = new Cuboid(plotmine.getLocation().getWorld().getName(), xOne - 1, yOne, zOne - 1, xTwo + 1, yTwo - 1, zTwo + 1);
        replaceCubeBlock(plotCube, material);
    }

    private void replaceCubeBlock(Cuboid plotCube, Material material) {
        for (Block block : plotCube.getBlocks()) {
            block.setType(material);
        }
    }

    public boolean canPlacePlotHere(Plot playerPlot) {
        for (com.intellectualcrafters.plot.object.Location corner : plotMineCorners) {
            Plot cornerPlot = Plot.getPlot(corner);
            if (cornerPlot == null || !playerIsPlotOwner(plotmine.getOwnerUUID(), cornerPlot)) {
                return false;
            }
        }
        return true;
    }

    private boolean playerIsPlotOwner(UUID uuid, Plot playerPlot) {
        if (playerPlot.getOwners().contains(uuid)) {
            return true;
        }
        return false;
    }
}
