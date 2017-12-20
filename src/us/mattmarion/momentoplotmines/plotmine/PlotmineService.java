package us.mattmarion.momentoplotmines.plotmine;

import com.sk89q.worldedit.Vector;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class PlotmineService {

    private Plotmine plotmine;
    private double xOne;
    private double xTwo;
    private double zOne;
    private double zTwo;
    private double yOne;
    private double yTwo;

    public PlotmineService(Plotmine plotmine) {
        this.plotmine = plotmine;
        xOne = plotmine.getLocation().getX() - (plotmine.getSize() / 2);
        xTwo = plotmine.getLocation().getX() + (plotmine.getSize() / 2);
        zOne = plotmine.getLocation().getZ() - (plotmine.getSize()/2);
        zTwo = plotmine.getLocation().getZ() + (plotmine.getSize()/2);
        yOne = plotmine.getLocation().getY() - 1;
        yTwo = plotmine.getLocation().getY() -(plotmine.getSize());
    }

    public void build(Material material) {
        Cuboid plotCube = new Cuboid(plotmine.getLocation().getWorld().getName(), xOne, yOne, zOne, xTwo, yTwo, zTwo);
        replaceCubeBlock(plotCube, material);
    }

    private void replaceCubeBlock(Cuboid plotCube, Material material) {
        for (Block block : plotCube.getBlocks()) {
            block.setType(material);
        }

        Vector wallOne = new Vector(xOne, yOne, zOne);
    }
}
