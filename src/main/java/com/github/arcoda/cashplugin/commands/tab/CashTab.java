package com.github.arcoda.cashplugin.commands.tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CashTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            ArrayList<String> baseList;
            if (sender.isOp()){
                baseList = new ArrayList<>();
                baseList.add("enable");
                baseList.add("disable");
            } else {
                baseList = new ArrayList<>();
                baseList.add("op-only-get-out");
            }
            return baseList;
        }
        return(new ArrayList<>());
    }
}
