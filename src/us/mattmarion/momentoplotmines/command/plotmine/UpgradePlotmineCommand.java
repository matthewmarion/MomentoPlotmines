package us.mattmarion.momentoplotmines.command.plotmine;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.PermissionUtils;

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
            // Tell them you dont need to upgrade
        }
    }
}
