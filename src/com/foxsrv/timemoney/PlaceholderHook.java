package com.foxsrv.timemoney;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PlaceholderHook extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "player";
    }

    @Override
    public String getAuthor() {
        return "FoxSrv";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {

        if (p == null) return "";

        if (params.equalsIgnoreCase("moneytime")) {

            double saldo = TimeMoney.get().getEconomy().getBalance(p);
            return TimeFormatter.format((long) saldo);
        }

        return null;
    }
}
