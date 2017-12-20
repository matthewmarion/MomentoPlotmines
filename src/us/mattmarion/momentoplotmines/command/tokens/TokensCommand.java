package us.mattmarion.momentoplotmines.command.tokens;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

public class TokensCommand extends MomentoCommandExecutor {

    public TokensCommand() {
        setSubCommand("tokens");
        setPermission("plotmine.user");
        setUsage("/plotmine tokens (username)");
        setPlayer(true);
        setLength(1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String message = MessageUtils.PLAYER_TOKEN_BALANCE_MESSAGE;
        if (args.length == 1) {
            int tokens = getTokens((Player) sender);
            MessageUtils.tell(sender, message, "{tokens}", Integer.toString(tokens));
            return;
        }
        Player player = (Player) sender;
        if (player.hasPermission("plotmine.admin")) {
            MessageUtils.tell(sender, MessageUtils.NO_PERMISSION_MESSAGE, null, null);
            return;
        }
        Player target = Bukkit.getPlayerExact(args[1]);
        if (!Utilities.playerIsFound(target)) {
            MessageUtils.tell(sender, MessageUtils.PLAYER_NOT_FOUND_MESSAGE, null, null);
            return;
        }
        int tokens = getTokens(target);
        MessageUtils.tell(sender, message, "{tokens}", Integer.toString(tokens));
    }

    private int getTokens(Player target) {
        Profile profile = Profile.getByPlayer(target);
        return profile.getTokens();
    }
}
