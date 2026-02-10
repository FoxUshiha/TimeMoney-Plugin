package com.foxsrv.timemoney.commands;

import com.foxsrv.timemoney.TimeFormatter;
import com.foxsrv.timemoney.TimeMoney;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeMoneyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
                             String label, String[] args) {

        Player alvo = args.length > 0 ?
                Bukkit.getPlayer(args[0]) :
                (Player) sender;

        if (alvo == null) {
            sender.sendMessage("§cUnknown Player.");
            return true;
        }

        double saldo = TimeMoney.get().getEconomy().getBalance(alvo);

        sender.sendMessage("§eTime of " + alvo.getName() + ": "
                + TimeFormatter.format((long) saldo));

        return true;
    }
}
