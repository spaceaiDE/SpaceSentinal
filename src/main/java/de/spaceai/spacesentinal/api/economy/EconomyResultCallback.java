package de.spaceai.spacesentinal.api.economy;

import de.spaceai.spacesentinal.api.economy.result.EconomyResult;

public interface EconomyResultCallback {

    void accept(EconomyResult economyResult);

}
