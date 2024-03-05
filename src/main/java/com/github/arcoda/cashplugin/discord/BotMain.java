package com.github.arcoda.cashplugin.discord;

import com.github.arcoda.cashplugin.CashPlugin;
import com.github.arcoda.cashplugin.discord.listener.Message;
import com.github.arcoda.cashplugin.discord.listener.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class BotMain {
    private String token;
    public JDA getApi;
    public BotMain(String token) throws LoginException, InterruptedException {
        this.token = token;
        getApi = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MESSAGES).build();
        getApi.awaitReady();
        for (Guild server : getApi.getGuildCache()) {
            server.updateCommands().addCommands(
                    new CommandData("channel", "Register what channel should be used to communicate with minecraft")
                            .addOption(OptionType.CHANNEL, "channel", "The channel you wish to register", true),
                    new CommandData("rm-channel", "Unregister a channel from communicating with minecraft")
                            .addOption(OptionType.CHANNEL, "channel", "The channel you wish to unregister", true)
            ).queue();
        }
        getApi.getPresence().setActivity(Activity.playing("CashCraft"));
        getApi.addEventListener(new SlashCommand(CashPlugin.getInstance.getConfig()));
        getApi.addEventListener(new Message());
    }
}
