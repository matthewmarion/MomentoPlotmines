package us.mattmarion.momentoplotmines.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.plotmine.*;
import us.mattmarion.momentoplotmines.command.tokens.GiveTokenCommand;
import us.mattmarion.momentoplotmines.command.tokens.SetTokenCommand;
import us.mattmarion.momentoplotmines.command.tokens.TokensCommand;
import us.mattmarion.momentoplotmines.util.MessageUtils;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    private HashMap<String, MomentoCommandExecutor> commands = new HashMap<String, MomentoCommandExecutor>();

    public CommandHandler() {
        commands.put("tokens", new TokensCommand());
        commands.put("givetoken", new GiveTokenCommand());
        commands.put("settoken", new SetTokenCommand());
        commands.put("create", new CreatePlotmineCommand());
        commands.put("help", new HelpPlotmineCommand());
        commands.put("teleport", new TeleportPlotmineCommand());
        commands.put("tp", new TeleportPlotmineCommand());
        commands.put("delete", new DeletePlotmineCommand());
        commands.put("upgrade", new UpgradePlotmineCommand());
        commands.put("add", new AddMemberPlotmineCommand());
        commands.put("remove", new RemoveMemberPlotmineCommand());
        commands.put("reset", new ResetPlotmineCommand());
        commands.put("composition", new CompositionPlotmineCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("plotmine")) {
            if (args.length == 0) {
                if (sender.hasPermission("plotmine.admin")) {
                    MessageUtils.tellList(sender, MessageUtils.ADMIN_HELP_MESSAGES);
                    return true;
                }
                MessageUtils.tellList(sender, MessageUtils.HELP_MESSAGES);
                return true;
            }
            String name = args[0].toLowerCase();
            if (!commands.containsKey(name)) {
                return false;
            }

            final MomentoCommandExecutor command = commands.get(name);

            if (command.getPermission() != null && !sender.hasPermission(command.getPermission())) {
                MessageUtils.tell(sender, MessageUtils.NO_PERMISSION_MESSAGE, null, null);
                return false;
            }

            if (!command.isBoth()) {
                if (command.isConsole() && sender instanceof Player) {
                    MessageUtils.tell(sender, MessageUtils.ONLY_CONSOLE_MESSAGE, null, null);
                    return false;
                }

                if (command.isPlayer() && sender instanceof ConsoleCommandSender) {
                    MessageUtils.tell(sender, MessageUtils.ONLY_PLAYER_MESSAGE, null, null);
                    return false;
                }
            }

            if (command.getLength() > args.length) {
                sender.sendMessage(ChatColor.RED + command.getUsage());
                return false;
            }
            command.execute(sender, args);
        }
        return false;
    }
}
