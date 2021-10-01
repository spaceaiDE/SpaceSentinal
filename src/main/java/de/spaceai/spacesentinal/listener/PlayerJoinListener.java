package de.spaceai.spacesentinal.listener;

import de.spaceai.spacesentinal.SpaceSentinal;
import de.spaceai.spacesentinal.api.SpaceSentinalAPI;
import de.spaceai.spacesentinal.service.impl.EconomyService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final SpaceSentinal spaceSentinal;

    public PlayerJoinListener(SpaceSentinal spaceSentinal) {
        this.spaceSentinal = spaceSentinal;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent playerJoinEvent) {

        Player player = playerJoinEvent.getPlayer();

        EconomyService economyService = SpaceSentinalAPI.getApi().getService("economyService");

        if(economyService.getEconomy() != null && !economyService.getEconomy().existUser(player.getUniqueId())) {
            economyService.getEconomy().loadUser(player.getUniqueId());
        }

    }

}
