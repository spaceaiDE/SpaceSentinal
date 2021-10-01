package de.spaceai.spacesentinal.command;

import com.google.common.collect.Lists;
import de.spaceai.spacesentinal.SpaceSentinal;
import de.spaceai.spacesentinal.api.SpaceSentinalAPI;
import de.spaceai.spacesentinal.service.impl.ConfigService;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    private final SpaceSentinal spaceSentinal;

    public GamemodeCommand(SpaceSentinal spaceSentinal) {
        this.spaceSentinal = spaceSentinal;
    }

    // /gamemode <creative,survival,adventure,spectator> [player]

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player))
            return true;

        Player player = (Player) commandSender;

        ConfigService configService = SpaceSentinalAPI.getApi().getService("configService");

        if(!player.hasPermission("spacesentinal.gamemode")) {
            player.sendMessage(configService.get("message.no-permission"));
            return true;
        }

        // -- TODO: Integration of the command

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return Lists.newArrayList();
    }
}
