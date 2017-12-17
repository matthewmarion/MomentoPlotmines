package us.mattmarion.momentoplotmines.command.plotmine;

import com.intellectualcrafters.plot.PS;
import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotArea;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.intellectualcrafters.plot.object.worlds.PlotAreaManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.ConfirmationService;
import us.mattmarion.momentoplotmines.util.MessageUtils;

import java.util.*;

public class CreatePlotmineCommand extends MomentoCommandExecutor {

    private HashMap<UUID, Integer> playersPendingPlotmineCreation = new HashMap<>();

    public CreatePlotmineCommand() {
        setSubCommand("create");
        setPermission("plotmine.create");
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
        if (playerHasExistingPlot(profile)) {
            if (!ConfirmationService.playerIsPending(playersPendingPlotmineCreation, player)) {
                ConfirmationService.setPending(playersPendingPlotmineCreation, player, MessageUtils.CONFIRM_PLOTMINE_CREATE_MESSAGE);
                return;
            }
            ConfirmationService.removePending(playersPendingPlotmineCreation, player);
        }

        List<String> members = new ArrayList<String>();
        Plotmine plotmine = new Plotmine(player.getUniqueId(), player.getLocation(), Material.EMERALD_BLOCK, 10, members);
        profile.setPlotmine(plotmine);
        profile.save();
        MessageUtils.tell(sender, MessageUtils.SUCCESS_CREATE_PLOTMINE_MESSAGE, null, null);
    }

    private boolean playerHasExistingPlot(Profile profile) {
        return profile.getPlotmine() != null;
    }

    private boolean playerIsPlotOwner(Player player, Plot plot) {
        return plot.getOwners().contains(player.getUniqueId());
    }
}
