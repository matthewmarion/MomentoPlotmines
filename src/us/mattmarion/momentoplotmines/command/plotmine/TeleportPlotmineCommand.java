package us.mattmarion.momentoplotmines.command.plotmine;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

public class TeleportPlotmineCommand extends MomentoCommandExecutor {

    public TeleportPlotmineCommand() {
        setSubCommand("teleport");
        setPermission("plotmine.user");
        setUsage("/plotmine teleport (name)");
        setPlayer(true);
        setLength(1);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 1) {
            Profile profile = Profile.getByPlayer(player);
            Plotmine plotmine = profile.getPlotmine();
            if (plotmine == null) {
                MessageUtils.tell(sender, MessageUtils.NULL_PLOTMINE_MESSAGE, null, null);
                return;
            }
            if (plotmine.getLocation() == null) {
                MessageUtils.tell(sender, MessageUtils.NULL_PLOTMINE_MESSAGE, null, null);
                return;
            }
            player.teleport(plotmine.getLocation());
            MessageUtils.tell(sender, MessageUtils.SUCCESS_TELEPORT_MESSAGE, "{name}", player.getName());
            return;
        }

        if (args.length == 2) {
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null || !target.isOnline()) {
                MessageUtils.tell(sender, MessageUtils.PLAYER_NOT_FOUND_MESSAGE, null, null);
                return;
            }
            Profile profile = Profile.getByPlayer(target);
            Plotmine plotmine = profile.getPlotmine();
            if (plotmine == null) {
                MessageUtils.tell(sender, MessageUtils.NULL_PLOTMINE_MESSAGE, null, null);
                return;
            }
            if (Utilities.isAdmin(player)) {
                player.teleport(plotmine.getLocation());
                MessageUtils.tell(sender, MessageUtils.SUCCESS_TELEPORT_MESSAGE, "{name}", target.getName());
                return;
            }

            if (playerIsPlotmineOwner(plotmine, player)) {
                player.teleport(plotmine.getLocation());
                MessageUtils.tell(sender, MessageUtils.SUCCESS_TELEPORT_MESSAGE, "{name}", target.getName());
                return;
            }

            if (!plotmine.isMember(player.getUniqueId().toString())) {
                MessageUtils.tell(sender, MessageUtils.NO_PERMISSION_MESSAGE, null, null);
                return;
            }
            player.teleport(plotmine.getLocation());
            MessageUtils.tell(sender, MessageUtils.SUCCESS_TELEPORT_MESSAGE, "{name}", target.getName());
            return;
        }

    }

    private boolean playerIsPlotmineOwner(Plotmine plotmine, Player player) {
        return plotmine.getOwnerUUID().equals(player.getUniqueId());
    }

}
