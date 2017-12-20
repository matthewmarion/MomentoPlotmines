package us.mattmarion.momentoplotmines.command.plotmine;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.plotmine.PlotmineService;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.PermissionUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

public class UpgradePlotmineCommand extends MomentoCommandExecutor {

    public UpgradePlotmineCommand() {
        setSubCommand("upgrade");
        setPermission("plotmine.user");
        setUsage("/plotmine upgrade");
        setPlayer(true);
        setLength(1);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Profile profile = Profile.getByPlayer(player);

        if (profile.getPlotmine() == null) {
            MessageUtils.tell(sender, MessageUtils.NULL_PLOTMINE_MESSAGE, null, null);
            return;
        }

        Material material = PermissionUtils.getTierComposition(player);
        int size = PermissionUtils.getTierSize(player);
        Plotmine currentMine = profile.getPlotmine();
        if (currentMine.getMaterial() == material && currentMine.getSize() == size) {
            MessageUtils.tell(sender, MessageUtils.NO_UPGRADES_AVAILABLE, null, null);
            return;
        }
        if (currentMine.getSize() > size || currentMine.getMaterial() != material) {
            PlotmineService currentMineService = new PlotmineService(currentMine);
            currentMineService.build(Material.GRASS);
        }
        Vector min = Utilities.getMinimumVectorFromLocation(currentMine.getLocation(), size);
        Vector max = Utilities.getMaximumVectorFromLocation(currentMine.getLocation(), size);
        Plotmine upgradedMine = new Plotmine(player.getUniqueId(), currentMine.getLocation(), min, max, material, size, currentMine.getMembers());
        PlotmineService plotmineService = new PlotmineService(upgradedMine);
        plotmineService.build(material);
        profile.setPlotmine(upgradedMine);
        profile.save();
        MessageUtils.tell(sender, MessageUtils.SUCCESS_UPGRADE_MINE_MESSAGE, null, null);
    }
}
