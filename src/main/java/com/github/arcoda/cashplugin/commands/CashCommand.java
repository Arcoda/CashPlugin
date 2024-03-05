//DM me cheesecake if someone is actually reading my source code
package com.github.arcoda.cashplugin.commands;

import com.github.arcoda.cashplugin.CashPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import com.github.arcoda.cashplugin.library.FindFile;
import java.io.File;

public class CashCommand implements CommandExecutor {
    private CashPlugin cashPlugin = CashPlugin.getInstance;
    private PluginManager manager = cashPlugin.getServer().getPluginManager();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            if (!sender.isOp()) {
                send(sender,"next time try with op");
                return true;
            }
            else if (args[0].equals("disable")) {
                if (manager.isPluginEnabled(args[1])) {
                    manager.disablePlugin(manager.getPlugin(args[1]));
                    send(sender, "Plugin disabled.");
                }
                else {
                    send(sender, "Plugin not found or already disabled.");
                }
            }
            else if (args[0].equals("enable")) {
                if (!manager.isPluginEnabled(args[1])) {
                    Plugin plugin = manager.getPlugin(args[1]);
                    //Try to get plugin object if already loaded, else tries to find the .jar
                    if (plugin == null) {
                        File pluginFile = FindFile.FindFile(args[1], new File("plugins"), false);
                        if (pluginFile != null) {
                            try {
                                plugin = manager.loadPlugin(pluginFile);

                            } catch (InvalidPluginException e) {
                                send(sender, "A file was found, but it isn't a plugin?");
                                return true;

                            } catch (InvalidDescriptionException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            send(sender, "Plugin not found.");
                            return true;
                        }
                    }
                    manager.enablePlugin(plugin);
                    send(sender, "Plugin enabled.");
                } else {
                    send(sender, "Plugin already enabled.");
                }
            }
        }
        else {
            send(sender, "Invalid command length.");
        }
        return true;
    }
    private void send(CommandSender sender, String text) {
        sender.sendMessage(cashPlugin.getPrefix+text);
    }
}