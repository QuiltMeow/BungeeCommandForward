package ew.sr.x1c.quilt.meow.plugin.bungee.command.forward;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.event.EventHandler;

public class CommandPluginMessage implements Listener {

    @EventHandler
    public void onPluginMessageReceive(PluginMessageEvent event) {
        if (!event.getTag().equalsIgnoreCase(Main.CHANNEL)) {
            return;
        }
        ByteArrayDataInput packet = ByteStreams.newDataInput(event.getData());

        ProxyServer server = ProxyServer.getInstance();
        try {
            CommandType type = CommandType.values()[packet.readByte()];
            String command = packet.readUTF();

            PluginManager pluginManager = server.getPluginManager();
            switch (type) {
                case CONSOLE: {
                    pluginManager.dispatchCommand(server.getConsole(), command);
                    break;
                }
                case PLAYER: {
                    Connection receiver = event.getReceiver();
                    if (!(receiver instanceof ProxiedPlayer)) {
                        return;
                    }
                    pluginManager.dispatchCommand((ProxiedPlayer) receiver, command);
                    break;
                }
            }
        } catch (Exception ex) {
            server.getLogger().log(Level.WARNING, "處理指令轉發時發生例外狀況", ex);
        }
    }
}
