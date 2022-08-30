package com.github.arcoda.cashplugin;

import com.github.arcoda.cashplugin.commands.CashCommand;
import com.github.arcoda.cashplugin.commands.tab.CashTab;
import org.bukkit.plugin.java.JavaPlugin;

public final class CashPlugin extends JavaPlugin {
public static CashPlugin getInstance;
public String getPrefix = "[§e§lCash§r] ";
    @Override
    public void onEnable() {
        getInstance = this;
        this.getCommand("cash").setExecutor(new CashCommand());
        this.getCommand("cash").setTabCompleter(new CashTab());
    }

    @Override
    public void onDisable() {
        //HandlerList.unregisterAll(this);
    }
}
