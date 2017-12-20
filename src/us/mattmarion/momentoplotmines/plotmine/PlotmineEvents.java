package us.mattmarion.momentoplotmines.plotmine;

import com.plotsquared.bukkit.events.PlotClearEvent;
import com.plotsquared.bukkit.events.PlotDeleteEvent;
import com.plotsquared.bukkit.events.PlotEvent;
import com.plotsquared.listener.PlotListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.mattmarion.momentoplotmines.profile.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PlotmineEvents implements Listener {

    @EventHandler
    public void on(PlotClearEvent event) {
        System.out.println("Deleted plot");
        Set<UUID> owners = event.getPlot().getOwners();
        if (owners == null) {
            return;
        }
        List<UUID> plotOwner = new ArrayList<>(owners);
        UUID plotOwnerUUID = plotOwner.get(0);
        Profile profile = Profile.getByUUID(plotOwnerUUID);
        if (profile == null) {
            return;
        }
        profile.setPlotmine(null);
        profile.save();
    }
}
