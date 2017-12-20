package us.mattmarion.momentoplotmines.command.tokens;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;
import us.mattmarion.momentoplotmines.util.Utilities;

public class SetTokenCommand extends MomentoCommandExecutor {

    public void GiveCreditCommand() {
        setSubCommand("settoken");
        setPermission("plotmine.admin");
        setUsage("/plotmine settoken <name> <amount>");
        setBoth();
        setLength(3);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player target = Bukkit.getPlayer(args[1]);
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
        profile.setTokens(amount);
        profile.save();
        MessageUtils.tell(sender, MessageUtils.PLAYER_SET_TOKEN_SENDER_MESSAGE, "{amount}", Integer.toString(amount));
        MessageUtils.tell(target, MessageUtils.PLAYER_SET_TOKEN_RECIPIENT_MESSAGE, "{amount}", Integer.toString(amount));
    }
}
