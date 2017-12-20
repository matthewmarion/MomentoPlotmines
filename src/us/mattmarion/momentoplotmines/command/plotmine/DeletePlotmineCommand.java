package us.mattmarion.momentoplotmines.command.plotmine;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.plotmine.PlotmineService;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.ConfirmationService;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

import java.util.HashMap;
import java.util.UUID;

public class DeletePlotmineCommand extends MomentoCommandExecutor {

    private HashMap<UUID, Integer> playersPendingPlotmineDeletion = new HashMap<>();

    public DeletePlotmineCommand() {
        setSubCommand("delete");
        setPermission("plotmine.user");
        setUsage("/plotmine delete (name)");
        setPlayer(true);
        setLength(1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player senderPlayer = (Player) sender;
        if (args.length == 2) {
            if (!senderPlayer.hasPermission("plotmine.admin")) {
                MessageUtils.tell(sender, MessageUtils.NO_PERMISSION_MESSAGE, null, null);
                return;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            deletePlotmine(sender, target);
        } else if (args.length == 1) {
            Player player = (Player) sender;
            deletePlotmine(sender, player);
        }

    }

    private void deletePlotmine(CommandSender sender, Player target) {
        if (Utilities.targetIsNull(target)) {
            MessageUtils.tell(sender, MessageUtils.PLAYER_NOT_FOUND_MESSAGE, null, null);
            return;
        }
        Profile profile = Profile.getByPlayer(target);
        if (profile.getPlotmine() == null) {
            MessageUtils.tell(sender, MessageUtils.NULL_PLOTMINE_MESSAGE, null, null);
            return;
        }
        if (!ConfirmationService.playerIsPending(playersPendingPlotmineDeletion, (Player) sender)) {
            ConfirmationService.setPending(playersPendingPlotmineDeletion, (Player) sender, MessageUtils.CONFIRM_PLOTMINE_DELETE_MESSAGE);
            return;
        }
        ConfirmationService.removePending(playersPendingPlotmineDeletion, (Player) sender);
        Plotmine oldMine = profile.getPlotmine();
        PlotmineService oldMineService = new PlotmineService(oldMine);
        oldMineService.build(Material.GRASS);
        profile.setPlotmine(null);
        profile.save();
        MessageUtils.tell(sender, MessageUtils.SUCCESS_DELETE_PLOTMINE_MESSAGE, null, null);
        return;
    }
}
