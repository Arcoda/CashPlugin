package com.github.arcoda.cashplugin.commands.tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import com.github.arcoda.cashplugin.CashPlugin;

import java.util.ArrayList;
import java.util.List;

public class CashTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> baseList = new ArrayList<>();
        if (args.length == 1) {
            if (sender.isOp()){
                baseList = new ArrayList<>();
                baseList.add("enable");
                baseList.add("disable");
            }
        } else if(args.length == 2 && args[0].equals("disable")) {
            if (sender.isOp()){
                for(Plugin p : CashPlugin.getInstance.getServer().getPluginManager().getPlugins()) {
                    if(p.isEnabled()) baseList.add(p.getName());
                }
            }
        } else if(args.length == 2 && args[0].equals("enable")) {
            if (sender.isOp()){
                for(Plugin p : CashPlugin.getInstance.getServer().getPluginManager().getPlugins()) {
                    if(!p.isEnabled()) baseList.add(p.getName());
                }
            }
        }
        return baseList;
    }
}
