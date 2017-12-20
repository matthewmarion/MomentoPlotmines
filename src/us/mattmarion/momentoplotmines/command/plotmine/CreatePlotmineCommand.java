package us.mattmarion.momentoplotmines.command.plotmine;

import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.plotmine.PlotmineService;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.ConfirmationService;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.PermissionUtils;
import org.bukkit.util.Vector;
import us.mattmarion.momentoplotmines.util.Utilities;

import java.util.*;

public class CreatePlotmineCommand extends MomentoCommandExecutor {

    private HashMap<UUID, Integer> playersPendingPlotmineCreation = new HashMap<>();

    public CreatePlotmineCommand() {
        setSubCommand("create");
        setPermission("plotmine.user");
        setUsage("/plotmine create");
        setPlayer(true);
        setLength(1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        PlotPlayer plotPlayer = PlotPlayer.get(player.getName());
        Location location = plotPlayer.getLocation();
        Plot plot = Plot.getPlot(location);
        if (plot == null || (!playerIsPlotOwner(player, plot))) {
            MessageUtils.tell(sender, MessageUtils.INVALID_CREATE_LOCATION_PLOTMINE_MESSAGE, null, null);
            return;
        }

        Profile profile = Profile.getByPlayer(player);
        if (profile.getPlotmine() != null) {
            if (!ConfirmationService.playerIsPending(playersPendingPlotmineCreation, player)) {
                ConfirmationService.setPending(playersPendingPlotmineCreation, player, MessageUtils.CONFIRM_PLOTMINE_CREATE_MESSAGE);
                return;
            }
            ConfirmationService.removePending(playersPendingPlotmineCreation, player);
            Plotmine oldMine = profile.getPlotmine();
            PlotmineService oldMineService = new PlotmineService(oldMine);
            oldMineService.build(Material.GRASS);

        }
        List<String> members = new ArrayList<String>();
        Material composition = PermissionUtils.getTierComposition(player);
        int size = PermissionUtils.getTierSize(player);
        Vector minimumPoint = Utilities.getMinimumVectorFromLocation(player.getLocation(), size);
        Vector maximumPoint = Utilities.getMaximumVectorFromLocation(player.getLocation(), size);
        Plotmine plotmine = new Plotmine(player.getUniqueId(), player.getLocation(), minimumPoint, maximumPoint, composition, size, members);
        profile.setPlotmine(plotmine);
        profile.save();
        PlotmineService plotmineService = new PlotmineService(plotmine);
        plotmineService.build(plotmine.getMaterial());
        MessageUtils.tell(sender, MessageUtils.SUCCESS_CREATE_PLOTMINE_MESSAGE, null, null);
    }

    private boolean playerIsPlotOwner(Player player, Plot plot) {
        return plot.getOwners().contains(player.getUniqueId());
    }
}
