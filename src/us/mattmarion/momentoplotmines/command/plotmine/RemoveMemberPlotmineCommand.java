package us.mattmarion.momentoplotmines.command.plotmine;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

public class RemoveMemberPlotmineCommand extends MomentoCommandExecutor {

    public RemoveMemberPlotmineCommand() {
        setSubCommand("remove");
        setPermission("plotmine.user");
        setUsage("/plotmine remove <name>");
        setPlayer(true);
        setLength(2);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Profile profile = Profile.getByPlayer(player);
        if (profile.getPlotmine() == null) {
            MessageUtils.tell(sender, MessageUtils.NULL_PLOTMINE_MESSAGE, null, null);
            return;
        }

        Player target = Bukkit.getPlayerExact(args[1]);
        if (Utilities.targetIsNull(target)) {
            MessageUtils.tell(sender, MessageUtils.PLAYER_NOT_FOUND_MESSAGE, null, null);
            return;
        }

        Plotmine plotmine = profile.getPlotmine();
        if (playerIsNotMember(plotmine, target)) {
            MessageUtils.tell(sender, MessageUtils.PLAYER_NOT_MEMBER_MESSAGE, null, null);
            return;
        }

        plotmine.removeMember(target.getUniqueId().toString());
        profile.save();
        MessageUtils.tell(sender, MessageUtils.SUCCESS_REMOVE_MEMBER_MESSAGE, "{player}", target.getName());
    }

    private boolean playerIsNotMember(Plotmine plotmine, Player player) {
        return !plotmine.getMembers().contains(player.getUniqueId().toString());
    }
}
