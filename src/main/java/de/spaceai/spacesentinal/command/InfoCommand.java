package de.spaceai.spacesentinal.command;

import de.spaceai.spacesentinal.SpaceSentinal;
import de.spaceai.spacesentinal.api.SpaceSentinalAPI;
import de.spaceai.spacesentinal.service.impl.ConfigService;
import de.spaceai.spacesentinal.service.impl.EconomyService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand implements CommandExecutor {

    private final SpaceSentinal spaceSentinal;

    public InfoCommand(SpaceSentinal spaceSentinal) {
        this.spaceSentinal = spaceSentinal;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player))
            return true;

        Player player = (Player) commandSender;

        EconomyService economyService = SpaceSentinalAPI.getApi().getService("economyService");
        ConfigService configService = SpaceSentinalAPI.getApi().getService("configService");

        if(!player.hasPermission("spacesentinal.info")) {
            player.sendMessage(configService.get("message.no-permission"));
            return true;
        }

        player.sendMessage(new String[]{
                "§7§m          §c SpaceSentinal §7§m          ",
                " ",
                "§7Entwickelt von: §cspaceaiDE",
                "§7Integriertes Geldsystem: §c" + ((economyService.getEconomy() == null) ? "§4Nicht integriert" :
                        economyService.getEconomy().getClass().getName()),
                " ",
                "§7§m          §c SpaceSentinal §7§m          "
        });

        return false;
    }

}
