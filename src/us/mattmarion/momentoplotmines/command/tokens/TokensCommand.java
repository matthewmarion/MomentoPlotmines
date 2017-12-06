package us.mattmarion.momentoplotmines.command.tokens;

import org.bukkit.command.CommandSender;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;

public class TokensCommand extends MomentoCommandExecutor {

    public TokensCommand() {
        setSubCommand("tokens");
        setPermission("plotmine.tokens");
        setUsage("/plotmine tokens (username)");
        setPlayer(true);
        setLength(1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }
}
