package com.github.arcoda.cashplugin.listener;

import com.github.arcoda.cashplugin.CashPlugin;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        for (Guild server : CashPlugin.getInstance.getBot.getApi.getGuildCache()) {
            for (TextChannel channel : server.getTextChannelCache()) {
                if(CashPlugin.getInstance.getConfig().getList("Channels").contains(channel.getId())) {
                    channel.sendMessage("["+event.getPlayer().getName()+"] "+event.getMessage()).queue();
                }
            }
        }
    }
}
