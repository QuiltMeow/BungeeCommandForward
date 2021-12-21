package ew.sr.x1c.quilt.meow.plugin.bukkit.command.forward;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;

public class Main extends JavaPlugin {

    private static final String CHANNEL = "quilt:command_forward";

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().log(Level.INFO, "正在啟用 Bukkit 指令轉發插件 ...");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, CHANNEL);

        getCommand("bungeeconsole").setExecutor(new BungeeConsoleCommand());
        getCommand("bungeeplayer").setExecutor(new BungeePlayerCommand());
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "正在關閉插件 ...");
    }

    public static Main getPlugin() {
        return plugin;
    }

    public void sendCommandPluginMessage(PluginMessageRecipient sender, CommandType type, String command) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            try (DataOutputStream packet = new DataOutputStream(stream)) {
                packet.writeByte(type.ordinal());
                packet.writeUTF(command);
                sender.sendPluginMessage(this, CHANNEL, stream.toByteArray());
            }
        } catch (IOException ex) {
            getLogger().log(Level.WARNING, "發送指令轉發訊息時發生例外狀況", ex);
        }
    }
}
