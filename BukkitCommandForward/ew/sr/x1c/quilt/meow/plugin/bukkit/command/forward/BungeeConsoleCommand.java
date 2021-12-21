package ew.sr.x1c.quilt.meow.plugin.bukkit.command.forward;

import ew.sr.x1c.quilt.meow.plugin.bukkit.command.forward.util.ArgumentUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BungeeConsoleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "權限不足 無法使用該指令");
            return true;
        }
        if (args.length <= 0) {
            sender.sendMessage(ChatColor.RED + "請輸入有效指令");
            return false;
        }
        String command = ArgumentUtil.joinStringFrom(args, 0);

        Server server = Bukkit.getServer();
        Main.getPlugin().sendCommandPluginMessage(server, CommandType.CONSOLE, command);
        return true;
    }
}
