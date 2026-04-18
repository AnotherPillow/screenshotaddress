package rocks.pillow.screenshotaddress;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;

public class Server {
    private static String getCurrentRawAddress() {
        MinecraftClient client = MinecraftClient.getInstance();
        ServerInfo server = client.getCurrentServerEntry();
        return server == null ? null : server.address;
    }

    public static String getCurrentLocation() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.world == null) return "menus";
        if (client.isInSingleplayer()) return "singleplayer";
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
