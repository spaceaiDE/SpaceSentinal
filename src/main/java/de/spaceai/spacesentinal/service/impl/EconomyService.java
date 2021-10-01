package de.spaceai.spacesentinal.service.impl;

import de.spaceai.spacesentinal.api.economy.IEconomy;
import de.spaceai.spacesentinal.service.Service;

public class EconomyService extends Service {

    private IEconomy economy = null;

    public EconomyService(String serviceName) {
        super(serviceName);
    }

    public void registerEconomy(IEconomy economy) {
        this.economy = economy;
    }

    public IEconomy getEconomy() {
        return economy;
    }
}
