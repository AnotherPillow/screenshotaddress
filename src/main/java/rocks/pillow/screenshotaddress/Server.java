package rocks.pillow.screenshotaddress;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;

public class Server {
    private static String getCurrentRawAddress() {
        Minecraft client = Minecraft.getInstance();
        ServerData server = client.getCurrentServer();
        return server == null ? null : server.ip;
    }

    public static String getCurrentLocation() {
        Minecraft client = Minecraft.getInstance();

        if (client.level == null) return "menus";
        if (client.isLocalServer()) return "singleplayer";
        return getCurrentRawAddress();
    }

    public static String getCurrentLocationSafe() {
        try {
            return getCurrentLocation().replace(":", "_");
        } catch (Exception e) {
            return "unknown";
        }
    }
}