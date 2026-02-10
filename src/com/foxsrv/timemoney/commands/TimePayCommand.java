package com.foxsrv.timemoney.commands;

import com.foxsrv.timemoney.TimeFormatter;
import com.foxsrv.timemoney.TimeMoney;
import com.foxsrv.timemoney.util.TimeParser;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimePayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        if (args.length < 2) {
            p.sendMessage("/timepay <nick> <time>");
            return true;
        }

        Player alvo = Bukkit.getPlayer(args[0]);

        if (alvo == null) {
            p.sendMessage("§eUnknown player.");
            return true;
        }

        long segundos = TimeParser.parse(args[1]);

        if (segundos <= 0) {
            p.sendMessage("§eInvalid time format.");
            return true;
        }

        var eco = TimeMoney.get().getEconomy();

        if (eco.getBalance(p) < segundos) {
            p.sendMessage("§cNot enough of time.");
            return true;
        }

        eco.withdrawPlayer(p, segundos);
        eco.depositPlayer(alvo, segundos);

        p.sendMessage("§eYou sent "
                + TimeFormatter.format(segundos)
                + " §eto " + alvo.getName());

        return true;
    }
}
