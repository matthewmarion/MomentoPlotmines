package us.mattmarion.momentoplotmines.command.plotmine;

import org.bukkit.command.CommandSender;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.util.MessageUtils;

public class HelpPlotmineCommand extends MomentoCommandExecutor {

    public HelpPlotmineCommand() {
        setSubCommand("help");
        setPermission("plotmine.user");
        setUsage("/plotmine help");
        setBoth();
        setLength(1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("plotmine.admin")) {
            MessageUtils.tellList(sender, MessageUtils.ADMIN_HELP_MESSAGES);
            return;
        }
        MessageUtils.tellList(sender, MessageUtils.HELP_MESSAGES);
    }
}
