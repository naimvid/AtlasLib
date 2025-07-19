package naimv.atlaslib.client;

import naimv.atlaslib.manager.TrackManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class AtlasLibClient implements ClientModInitializer {

    public static MinecraftClient CLIENT;

    @Override
    public void onInitializeClient() {
        CLIENT = MinecraftClient.getInstance();
        new TrackManager();

    }

}
