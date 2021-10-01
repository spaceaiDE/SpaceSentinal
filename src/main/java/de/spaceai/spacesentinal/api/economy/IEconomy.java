package de.spaceai.spacesentinal.api.economy;

import de.spaceai.spacesentinal.api.economy.user.IEconomyUser;
import de.spaceai.spacesentinal.service.economy.EconomyUser;

import java.util.List;
import java.util.UUID;

public interface IEconomy {

    List<IEconomyUser> getEconomyUsers();

    void loadUser(UUID uuid);

    void removeUser(UUID uuid);

    boolean existUser(UUID uuid);

    EconomyUser getUser(UUID uuid);

    EconomyUser getUser(String name);

}
