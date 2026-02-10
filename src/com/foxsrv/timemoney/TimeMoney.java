package com.foxsrv.timemoney;

import com.foxsrv.timemoney.commands.TimePayCommand;
import com.foxsrv.timemoney.commands.TimeMoneyCommand;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeMoney extends JavaPlugin {

    private static TimeMoney instance;
    private Economy economy;

    @Override
    public void onEnable() {
        instance = this;

        if (!setupEconomy()) {
            getLogger().severe("Vault missing!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getScheduler().runTaskTimer(this, new TimeTask(), 20L, 20L);

        getCommand("timepay").setExecutor(new TimePayCommand());
        getCommand("timemoney").setExecutor(new TimeMoneyCommand());

        getServer().getPluginManager().registerEvents(new PrisonListener(), this);

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderHook().register();
        }

        getLogger().info("TimeMoney started!");
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp =
            getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) return false;

        economy = rsp.getProvider();
        return economy != null;
    }

    public static TimeMoney get() {
        return instance;
    }

    public Economy getEconomy() {
        return economy;
    }
}
