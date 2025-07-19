package naimv.atlaslib.util;

import naimv.atlaslib.client.AtlasLibClient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.FileNotFoundException;

public class AtlasResource {
    private static final ResourceManager RESOURCE_MANAGER = AtlasLibClient.CLIENT.getResourceManager();

    public static Resource getResource(Identifier identifier) throws FileNotFoundException {
        return RESOURCE_MANAGER.getResourceOrThrow(identifier);

    }

}
