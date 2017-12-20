package us.mattmarion.momentoplotmines.plotmine;

import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;

import java.util.UUID;

public class PlotmineEvents implements Listener {

    @EventHandler
    public void on(BlockBreakEvent event) {
        System.out.println("breaking block....");
        Block block = event.getBlock();
        Player player = event.getPlayer();
        PlotPlayer plotPlayer = PlotPlayer.get(player.getName());
        Location blockLocation = new Location(block.getWorld().getName(), block.getX(), block.getY(), block.getZ());
        Plot plot = Plot.getPlot(blockLocation);
        UUID[] owners = plot.getOwners().toArray(new UUID[plot.getOwners().size()]);
        Profile profile = Profile.getByUUID(owners[0]);
        if (profile == null) {
            return;
        }

        Plotmine plotmine = profile.getPlotmine();
        if (profile.getPlotmine() == null) {
            return;
        }
        if (playerIsPlotmineineMember(player, plotmine) || playerIsPlotmineOwner(player, plotmine)) {
            return;
        }

        if (!plotmine.containsLocation(blockLocation)) {
            return;
        }
        MessageUtils.tell(player, MessageUtils.ERROR_ON_PLOTMINE_USAGE_MESSAGE, null, null);
        event.setCancelled(true);
    }

    @EventHandler
    public void on(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        PlotPlayer plotPlayer = PlotPlayer.get(player.getName());
        Location blockLocation = new Location(block.getWorld().getName(), block.getX(), block.getY(), block.getZ());
        Plot plot = Plot.getPlot(blockLocation);
        UUID[] owners = plot.getOwners().toArray(new UUID[plot.getOwners().size()]);
        Profile profile = Profile.getByUUID(owners[0]);
        if (profile == null) {
            return;
        }
        Plotmine plotmine = profile.getPlotmine();
        if (profile.getPlotmine() == null) {
            return;
        }
        if (playerIsPlotmineineMember(player, plotmine) || playerIsPlotmineOwner(player, plotmine)) {
            return;
        }

        if (!plotmine.containsLocation(blockLocation)) {
            return;
        }

        MessageUtils.tell(player, MessageUtils.ERROR_ON_PLOTMINE_USAGE_MESSAGE, null, null);
        event.setCancelled(true);
    }


    /*@EventHandler
    public void on(PlotClearEvent event) {
        System.out.println("Deleted plot");
        Set<UUID> owners = event.getPlot().getOwners();
        if (owners == null) {
            return;
        }
        List<UUID> plotOwner = new ArrayList<>(owners);
        UUID plotOwnerUUID = plotOwner.get(0);
        Profile profile = Profile.getByUUID(plotOwnerUUID);
        if (profile == null) {
            return;
        }
        profile.setPlotmine(null);
        profile.save();
    }*/

    private boolean playerIsPlotOwner(Player player, Plot plot) {
        return plot.getOwners().contains(player.getUniqueId());
    }

    private boolean playerIsPlotmineineMember(Player player, Plotmine plotmine) {
        return plotmine.getMembers().contains(player.getUniqueId().toString());
    }

    private boolean playerIsPlotmineOwner(Player player, Plotmine plotmine) {
        return player.getUniqueId().equals(plotmine.getOwnerUUID());
    }
}
