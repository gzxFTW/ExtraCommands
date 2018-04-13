package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class ShowPlayers extends ExtraCommandExecutor {
	
	public ShowPlayers(JavaPlugin plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.getConfig().getBoolean("commands.seeplayers")) {
			if (sender instanceof Player) {
				if (sender.hasPermission("extracommands.showplayers")) {
				Player player = (Player) sender;
				for (Player p : Bukkit.getOnlinePlayers()) {
					player.showPlayer(plugin, p);
				}
				player.sendMessage(ChatColor.GREEN + "Players shown!");
			}
				else 
					sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
		}
			else 
				sender.sendMessage(ChatColor.RED + "This command must be executed by a player.");
			
		}
		else
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.command-disabled-message")));
		return true;
	}
	
	
	
	
	
	
}
