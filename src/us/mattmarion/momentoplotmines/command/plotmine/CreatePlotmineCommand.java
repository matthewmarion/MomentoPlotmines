package us.mattmarion.momentoplotmines.command.plotmine;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;

import java.util.*;

public class CreatePlotmineCommand extends MomentoCommandExecutor {

    public CreatePlotmineCommand() {
        setSubCommand("create");
        setPermission("plotmine.create");
        setUsage("/plotmine create");
        setPlayer(true);
        setLength(1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        List<String> members = new ArrayList<String>();
        Plotmine plotmine = new Plotmine(player.getUniqueId(), player.getLocation(), Material.EMERALD_BLOCK, 10, members);
        Profile profile = Profile.getByPlayer(player);
        profile.setPlotmine(plotmine);
        profile.save();
        MessageUtils.tell(sender, "Created plotmine", null, null);
    }
}
