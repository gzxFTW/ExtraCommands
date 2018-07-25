package net.twilightdevelopment.plugin.extracommands;

import net.md_5.bungee.api.ChatColor;
import net.twilightdevelopment.plugin.extracommands.placeholder.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class ShowPlayers extends ExtraCommandExecutor {

  public ShowPlayers(JavaPlugin plugin) {
    super(plugin, "showplayers");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (plugin.getConfig().getBoolean("commands.showplayers")) {
      if (sender instanceof Player) {
        if (sender.hasPermission("extracommands.showplayers")) {
          Player player = (Player) sender;
          for (Player p : Bukkit.getOnlinePlayers()) {
            player.showPlayer(plugin, p);
          }
          sender.sendMessage(
              ChatColor.translateAlternateColorCodes(
                  '&',
                  PlaceholderUtil.applyPlaceholders(
                      plugin.getConfig().getString("messages.showplayers-complete"),
                      Collections.emptyMap())));
        } else
          sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
      } else sender.sendMessage(ChatColor.RED + "This command must be executed by a player.");

    } else
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.command-disabled-message")));
    return true;
  }

  @Override
  protected List<String> parseTabComplete(CommandSender sender, String[] args) {
    return super.parseTabComplete(sender, args);
  }
}
