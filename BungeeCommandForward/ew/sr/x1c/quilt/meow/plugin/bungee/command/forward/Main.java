package ew.sr.x1c.quilt.meow.plugin.bungee.command.forward;

import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    public static final String CHANNEL = "quilt:command_forward";

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().log(Level.INFO, "正在啟用 Bungee Cord 指令轉發插件 ...");

        ProxyServer server = getProxy();
        server.registerChannel(CHANNEL);
        server.getPluginManager().registerListener(this, new CommandPluginMessage());
    }

    @Override
    public void onDisable() {
        getLogger().info("正在關閉插件 ...");
    }

    public static Main getPlugin() {
        return plugin;
    }
}
