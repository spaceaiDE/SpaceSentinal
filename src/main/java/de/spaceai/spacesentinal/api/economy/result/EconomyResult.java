package de.spaceai.spacesentinal.api.economy.result;

import org.bukkit.entity.Player;

public record EconomyResult(int balanceOld, int balanceNew, EconomyResultState economyResultState) {
}
