package de.spaceai.spacesentinal.command;

import com.google.common.collect.Lists;
import de.spaceai.spacesentinal.SpaceSentinal;
import de.spaceai.spacesentinal.api.SpaceSentinalAPI;
import de.spaceai.spacesentinal.service.economy.EconomyUser;
import de.spaceai.spacesentinal.service.impl.ConfigService;
import de.spaceai.spacesentinal.service.impl.EconomyService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class MoneyCommand implements CommandExecutor, TabCompleter {

    private final SpaceSentinal spaceSentinal;

    public MoneyCommand(SpaceSentinal spaceSentinal) {
        this.spaceSentinal = spaceSentinal;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player))
            return true;

        Player player = (Player) commandSender;

        EconomyService economyService = SpaceSentinalAPI.getApi().getService("economyService");
        ConfigService configService = SpaceSentinalAPI.getApi().getService("configService");

        if(economyService.getEconomy() == null) {
            player.sendMessage(configService.get("error.no-economy"));
            return true;
        }

        if(!economyService.getEconomy().existUser(player.getUniqueId()))
            economyService.getEconomy().loadUser(player.getUniqueId());

        EconomyUser economyUser = economyService.getEconomy().getUser(player.getUniqueId());

        if(args.length == 0) {
            player.sendMessage(configService.get("message.economy", economyUser.getBalance()));
        } else if(args.length == 3) {// /money <set/add/remove> <player> <amount>
            if(!player.hasPermission("spacesentinal.money")) {
                player.sendMessage(configService.get("message.no-permission"));
                return true;
            }
            if(args[0].equalsIgnoreCase("set")) {
                if(!economyService.getEconomy().existUser(Bukkit.getOfflinePlayer(args[1]).getUniqueId())) {
                    player.sendMessage(configService.get("error.user-not-found"));
                    return true;
                }
                EconomyUser target = economyService.getEconomy().getUser(Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                if(!isInt(args[2])) {
                    player.sendMessage(configService.get("error.invalid-amount"));
                    return true;
                }
                int i = Integer.parseInt(args[2]);
                if(i < 0) {
                    player.sendMessage(configService.get("error.invalid-amount"));
                    return true;
                }
                target.setBalance(i);
                player.sendMessage(configService.get("message.money-set", target.getOfflinePlayer().getName(),
                        target.getBalance()));
            } else {
                player.sendMessage("§7Nutze bitte §c/money set <Player> <Amount>");
            }
        } else {
            player.sendMessage("§7Nutze bitte §c/money");
        }
        return true;
    }

    private boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 1) {
            return Lists.newArrayList("set");
        } else if(args.length == 2) {
            return SpaceSentinalAPI.getApi().<EconomyService>getService("economyService")
                    .getEconomy().getEconomyUsers().stream().map(economyUser -> economyUser.getOfflinePlayer().getName())
                    .collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
