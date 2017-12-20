package us.mattmarion.momentoplotmines.command.plotmine;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

public class AddMemberPlotmineCommand extends MomentoCommandExecutor {

    private final int costToAddMember = 1;

    public AddMemberPlotmineCommand() {
        setSubCommand("add");
        setPermission("plotmine.user");
        setUsage("/plotmine add <name>");
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
        if (playerIsAlreadyMember(plotmine, target)) {
            MessageUtils.tell(sender, MessageUtils.PLAYER_ALREADY_MEMBER_MESSAGE, null, null);
            return;
        }

        int currentTokenBalance = profile.getTokens();
        if (currentTokenBalance < 1) {
            MessageUtils.tell(sender, MessageUtils.INVALID_BALANCE_MESSAGE, null, null);
            return;
        }

        profile.removeTokens(costToAddMember);
        plotmine.addMember(target.getUniqueId().toString());
        profile.save();
        MessageUtils.tell(sender, MessageUtils.SUCCESS_ADD_MEMBER_MESSAGE, "{player}", target.getName());
    }

    private boolean playerIsAlreadyMember(Plotmine plotmine, Player player) {
        return plotmine.getMembers().contains(player.getUniqueId().toString());
    }
}
