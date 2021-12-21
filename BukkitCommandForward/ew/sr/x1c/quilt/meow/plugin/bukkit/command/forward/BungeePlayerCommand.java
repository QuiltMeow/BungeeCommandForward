package ew.sr.x1c.quilt.meow.plugin.bukkit.command.forward;

import ew.sr.x1c.quilt.meow.plugin.bukkit.command.forward.util.ArgumentUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BungeePlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "該指令必須在遊戲中由玩家使用");
            return false;
        }
        if (args.length <= 0) {
            sender.sendMessage(ChatColor.RED + "請輸入有效指令");
            return false;
        }
        String command = ArgumentUtil.joinStringFrom(args, 0);

        Player player = (Player) sender;
        Main.getPlugin().sendCommandPluginMessage(player, CommandType.PLAYER, command);
        return true;
    }
}
