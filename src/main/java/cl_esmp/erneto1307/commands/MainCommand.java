package cl_esmp.erneto1307.commands;

import cl_esmp.erneto1307.ClansLiteExpansionSMP;
import cl_esmp.erneto1307.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    public ClansLiteExpansionSMP plugin;
    public MainCommand(ClansLiteExpansionSMP plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(MessageUtils.
                    getColoredMessage("#ea3630Only players can execute this command"));

            return true;
        }

        // Argument 1 = /utilssmp about OR /ssmp about
        if (args.length >= 1){
            if (args[0].equalsIgnoreCase("about")){
                sender.sendMessage(MessageUtils.getColoredMessage(plugin.prefix + "#e1e1e1Running development v" + plugin.version +
                        "\n#e1e1e1Development and designed by erneto1307 ❤"));
            } else if (args[0].equalsIgnoreCase("reload")){
                subCommandReload(sender);
            } else {
                knowledgeData(sender);
            }
        } else {
            // Knowledge message
            knowledgeData(sender);
        }
        return true;
    }

    // Knowledge message
    public void knowledgeData(CommandSender sender){
        sender.sendMessage(MessageUtils.getColoredMessage("&f"));
        sender.sendMessage(MessageUtils.getColoredMessage(plugin.prefix + "#e1e1e1Knowledge Commands &fv" + plugin.version));
        sender.sendMessage(MessageUtils.getColoredMessage("\n#a8a1a1 ⏵ /cl-exsmp #e1e1e1about &8- &7About information of the plugin"));
        sender.sendMessage(MessageUtils.getColoredMessage("#a8a1a1 ⏵ /cl-exsmp #e1e1e1reload &8- &7Reload the configuration of the plugin"));
        sender.sendMessage(MessageUtils.getColoredMessage("&f"));
    }

    public void subCommandReload(CommandSender sender){
        if (!(sender.hasPermission("cl-expanesionsmp.reload"))){
            sender.sendMessage(MessageUtils.getColoredMessage("&f"));
            sender.sendMessage(MessageUtils.getColoredMessage(plugin.prefix + "#e1e1e1Only administrators can do this."));
            sender.sendMessage(MessageUtils.getColoredMessage("&f"));
            return;
        }
        sender.sendMessage(MessageUtils.getColoredMessage("&f"));
        sender.sendMessage(MessageUtils.getColoredMessage("#f5e300Reloading modified configurations. Saving..."));
        sender.sendMessage(MessageUtils.getColoredMessage("#90f600Plugin has been reloaded successfully."));
        sender.sendMessage(MessageUtils.getColoredMessage("&f"));
    }

}
