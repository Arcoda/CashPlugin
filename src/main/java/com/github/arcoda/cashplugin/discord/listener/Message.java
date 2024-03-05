package com.github.arcoda.cashplugin.discord.listener;

import com.github.arcoda.cashplugin.CashPlugin;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class Message extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(CashPlugin.getInstance.getConfig().getList("Channels").contains(event.getChannel().getId()) && !event.getAuthor().isBot()) {
            Bukkit.getServer().broadcastMessage("[§9"+event.getMember().getNickname() +"§r] "+event.getMessage().getContentRaw());
        }
        if(event.getChannel().getId().equals("1214314005204574239") && !event.getAuthor().isBot()) {
            Color c = event.getMember().getColor();
            Bukkit.getServer().broadcastMessage(""+ ChatColor.of(String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()))  + event.getMember().getNickname()+"§r] "+event.getMessage().getContentRaw());
        }
    }
}
