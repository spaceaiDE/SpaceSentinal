package de.spaceai.spacesentinal.api.economy.user;

import de.spaceai.spacesentinal.api.economy.EconomyResultCallback;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IEconomyUser {

    int getBalance();

    void setBalance(int amount);

    UUID getUUID();

    void pay(IEconomyUser economyUser, int amount, EconomyResultCallback economyResultCallback);

    boolean isOnline();

    Player getPlayer();

    OfflinePlayer getOfflinePlayer();

}
