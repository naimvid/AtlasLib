package naimv.atlaslib;

import com.google.gson.Gson;
import naimv.atlaslib.manager.TrackManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtlasLib implements ModInitializer {
	public static final String MOD_ID = "atlaslib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final TrackManager TRACK_MANAGER = new TrackManager();
	public static final Gson GSON = new Gson();

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized Successfully!");

	}

}