package us.mattmarion.momentoplotmines.command.tokens;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

public class GiveTokenCommand extends MomentoCommandExecutor {

    public void GiveTokenCommand() {
        setSubCommand("givetoken");
        setPermission("plotmine.admin");
        setUsage("/plotmine givetoken <name> <amount>");
        setBoth();
        setLength(3);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player target = Bukkit.getPlayerExact(args[1]);
        if (Utilities.targetIsNull(target)) {
            MessageUtils.tell(sender, MessageUtils.PLAYER_NOT_FOUND_MESSAGE, null, null);
            return;
        }

        if (!NumberUtils.isNumber(args[2])) {
            MessageUtils.tell(sender, MessageUtils.FIELD_NOT_NUMERIC_MESSAGE, null, null);
            return;
        }

        Profile profile = Profile.getByPlayer(target);
        int amount = Integer.parseInt(args[2]);
        profile.addTokens(amount);
        profile.save();
        MessageUtils.tell(sender, MessageUtils.PLAYER_GIVE_TOKEN_SENDER_MESSAGE, "{amount}", args[2]);
        MessageUtils.tell(target, MessageUtils.PLAYER_GIVE_TOKEN_RECIPIENT_MESSAGE, "{amount}", args[2]);
    }
}
