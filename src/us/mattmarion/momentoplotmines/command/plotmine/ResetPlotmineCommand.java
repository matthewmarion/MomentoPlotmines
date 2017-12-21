package us.mattmarion.momentoplotmines.command.plotmine;

import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.plotmine.PlotmineService;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

public class ResetPlotmineCommand extends MomentoCommandExecutor {

    public ResetPlotmineCommand() {
        setSubCommand("reset");
        setPermission("plotmine.user");
        setUsage("/plotmine reset");
        setPlayer(true);
        setLength(1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Profile profile = Profile.getByPlayer(player);

        Plotmine currentMine = profile.getPlotmine();
        if (profile.getPlotmine() == null) {
            MessageUtils.tell(sender, MessageUtils.NULL_PLOTMINE_MESSAGE, null, null);
            return;
        }

        PlotPlayer plotPlayer = PlotPlayer.get(player.getName());
        Location location = plotPlayer.getLocation();
        Plot plot = Plot.getPlot(location);

        Vector min = Utilities.getMinimumVectorFromLocation(currentMine.getLocation(), currentMine.getSize());
        Vector max = Utilities.getMaximumVectorFromLocation(currentMine.getLocation(), currentMine.getSize());
        Plotmine resetMine = new Plotmine(player.getUniqueId(), currentMine.getLocation(), min, max, currentMine.getMaterial(), currentMine.getSize(), currentMine.getMembers());
        PlotmineService plotmineService = new PlotmineService(resetMine, plot);
        plotmineService.build(currentMine.getMaterial());
        MessageUtils.tell(sender, MessageUtils.SUCCESS_RESET_MINE_MESSAGE, null, null);
    }
}
