package com.github.arcoda.cashplugin.discord.listener;

import com.github.arcoda.cashplugin.CashPlugin;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlashCommand extends ListenerAdapter {
    private FileConfiguration getConfig;
    public SlashCommand(FileConfiguration config) {this.getConfig = config;}
    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        switch(event.getName()) {
            case "channel":
                channel(event);
                break;
            case "rm-channel":
                rmChannel(event);
                break;
        }
    }
    private void channel(SlashCommandEvent event) {
        List<String> newList = (List<String>) getConfig.getList("Channels");
        newList.add(event.getOption("channel").getAsGuildChannel().getId());
        getConfig.set("Channels", newList);
        CashPlugin.getInstance.saveConfig();
        event.reply("Added!");
    }
    private void rmChannel(SlashCommandEvent event) {
        List<String> newList = (List<String>) getConfig.getList("Channels");
        if(newList.remove(event.getOption("channel").getAsGuildChannel().getId())) {
            getConfig.set("Channels", newList);
            CashPlugin.getInstance.saveConfig();
            event.reply("Done!");
        } else {
            event.reply("This channel isn't in the config.");
        }
    }
}
