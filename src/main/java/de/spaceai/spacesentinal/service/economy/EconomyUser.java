package de.spaceai.spacesentinal.service.economy;

import de.spaceai.spacesentinal.api.economy.EconomyResultCallback;
import de.spaceai.spacesentinal.api.economy.result.EconomyResult;
import de.spaceai.spacesentinal.api.economy.result.EconomyResultState;
import de.spaceai.spacesentinal.api.economy.user.IEconomyUser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EconomyUser implements IEconomyUser {

    private int balance;
    private UUID uuid;

    public EconomyUser(int balance, UUID uuid) {
        this.balance = balance;
        this.uuid = uuid;
    }

    @Override
    public int getBalance() {
        return this.balance;
    }

    @Override
    public void setBalance(int amount) {
        this.balance = amount;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public void pay(IEconomyUser economyUser, int amount, EconomyResultCallback economyResultCallback) {
        if((this.balance - amount) < 0)
            economyResultCallback.accept(new EconomyResult(this.balance, this.balance, EconomyResultState.FAILED));
        else {
            economyResultCallback.accept(new EconomyResult(this.balance, this.balance-amount, EconomyResultState.SUCCESS));
            this.balance -= amount;
            economyUser.setBalance(economyUser.getBalance()+amount);
        }
    }

    @Override
    public boolean isOnline() {
        return (getOfflinePlayer() instanceof Player);
    }

    @Override
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.uuid);
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
}
