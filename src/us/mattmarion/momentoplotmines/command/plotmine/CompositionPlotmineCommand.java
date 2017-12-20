package us.mattmarion.momentoplotmines.command.plotmine;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.mattmarion.momentoplotmines.command.MomentoCommandExecutor;
import us.mattmarion.momentoplotmines.plotmine.Plotmine;
import us.mattmarion.momentoplotmines.plotmine.PlotmineService;
import us.mattmarion.momentoplotmines.profile.Profile;
import us.mattmarion.momentoplotmines.util.MessageUtils;

public class CompositionPlotmineCommand extends MomentoCommandExecutor {

    private final int costToChangeComposition = 1;

    public CompositionPlotmineCommand() {
        setSubCommand("composition");
        setPermission("plotmine.user");
        setUsage("/plotmine composition <id>");
        setPlayer(true);
        setLength(2);
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Profile profile = Profile.getByPlayer(player);

        Plotmine plotmine = profile.getPlotmine();
        if (profile.getPlotmine() == null) {
            MessageUtils.tell(sender, MessageUtils.NULL_PLOTMINE_MESSAGE, null, null);
            return;
        }

        String inputComposition = args[1].toUpperCase();
        Material material = Material.getMaterial(inputComposition);
        if (material == null) {
            MessageUtils.tell(sender, MessageUtils.INVALID_MATERIAL_MESSAGE, null, null);
            return;
        }

        int currentTokenBalance = profile.getTokens();
        if (currentTokenBalance < 1) {
            MessageUtils.tell(sender, MessageUtils.INVALID_BALANCE_MESSAGE, null, null);
            return;
        }

        profile.removeTokens(costToChangeComposition);
        plotmine.setMaterial(material);
        PlotmineService plotmineService = new PlotmineService(plotmine);
        plotmineService.build(plotmine.getMaterial());
        profile.save();
        MessageUtils.tell(sender, MessageUtils.SUCCESS_COMPOSITION_CHANGE_MESSAGE, null, null);
    }
}
