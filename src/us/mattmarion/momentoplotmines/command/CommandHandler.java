package us.mattmarion.momentoplotmines.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.plotmine.CreatePlotmineCommand;
import us.mattmarion.momentoplotmines.command.tokens.GiveCreditCommand;
import us.mattmarion.momentoplotmines.command.tokens.SetCreditCommand;
import us.mattmarion.momentoplotmines.command.tokens.TokensCommand;
import us.mattmarion.momentoplotmines.util.MessageUtils;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    private HashMap<String, MomentoCommandExecutor> commands = new HashMap<String, MomentoCommandExecutor>();

    public CommandHandler() {
        commands.put("tokens", new TokensCommand());
        commands.put("givecredit", new GiveCreditCommand());
        commands.put("setcredit", new SetCreditCommand());
        commands.put("create", new CreatePlotmineCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("plotmine")) {
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
