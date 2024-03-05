package com.github.arcoda.cashplugin.listener;

import com.github.arcoda.cashplugin.CashPlugin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.Color;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        for (Guild server : CashPlugin.getInstance.getBot.getApi.getGuildCache()) {
            for (TextChannel channel : server.getTextChannelCache()) {
                if(CashPlugin.getInstance.getConfig().getList("Channels").contains(channel.getId())) {
                    channel.sendMessage("["+event.getPlayer().getName()+"] "+event.getMessage()).queue();
                }
                if(channel.getId().equals("1214314005204574239")) {
                    channel.sendMessage("["+event.getPlayer().getName()+"] "+ ChatColor.stripColor(event.getMessage())).queue();
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (Guild server : CashPlugin.getInstance.getBot.getApi.getGuildCache()) {
            for (TextChannel channel : server.getTextChannelCache()) {
                if(channel.getId().equals("1214314005204574239")) {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setDescription("**" + event.getPlayer().getName() + " joined the game**");
                    eb.setColor(Color.GREEN);
                    channel.sendMessageEmbeds(eb.build()).queue();
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        for (Guild server : CashPlugin.getInstance.getBot.getApi.getGuildCache()) {
            for (TextChannel channel : server.getTextChannelCache()) {
                if(channel.getId().equals("1214314005204574239")) {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setDescription("**" + event.getPlayer().getName() + " left the game**");
                    eb.setColor(Color.GREEN);
                    channel.sendMessageEmbeds(eb.build()).queue();
                }
            }
        }
    }
}
