package com.github.arcoda.cashplugin;

import com.github.arcoda.cashplugin.commands.CashCommand;
import com.github.arcoda.cashplugin.commands.tab.CashTab;
import com.github.arcoda.cashplugin.discord.BotMain;
import com.github.arcoda.cashplugin.listener.ChatListener;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public final class CashPlugin extends JavaPlugin {
public static CashPlugin getInstance;
public String getPrefix = "[§e§lCash§r] ";
public BotMain getBot;
public Boolean isBotActive;
    @Override
    public void onEnable() {
        getInstance = this;
        //Config init
        this.getCommand("cash").setExecutor(new CashCommand());
        this.getCommand("cash").setTabCompleter(new CashTab());
        getConfig().addDefault("Token", "ADD-TOKEN-HERE");
        getConfig().addDefault("Channels", new ArrayList<>());
        getConfig().options().copyDefaults(true);
        this.saveConfig();
        //
        if(!getConfig().getString("Token").equals("ADD-TOKEN-HERE")) {
            //Try&catch to handle errors from discord bot
            try {
                getBot = new BotMain(getConfig().getString("Token"));
                isBotActive = true;
            } catch (LoginException e) {
                getLogger().warning("There was an error starting the bot :(");
                isBotActive = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            getLogger().info("No bot token found in config, discord bot wont be initialized");
            isBotActive = false;
        }
        //
        registerListener(new ChatListener());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getBot.getApi.shutdown();
    }
    private void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
