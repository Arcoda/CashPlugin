package com.github.arcoda.cashplugin.discord.listener;

import com.github.arcoda.cashplugin.CashPlugin;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class Message extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(CashPlugin.getInstance.getConfig().getList("Channels").contains(event.getChannel().getId()) && !event.getAuthor().isBot()) {
            Bukkit.getServer().broadcastMessage("[§9"+event.getAuthor().getAsTag()+"§r] "+event.getMessage().getContentRaw());
        }
    }
}
