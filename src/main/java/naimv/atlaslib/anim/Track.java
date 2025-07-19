package naimv.atlaslib.anim;

import com.google.gson.stream.JsonReader;
import naimv.atlaslib.AtlasLib;
import net.minecraft.util.math.Vec3d;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Track {
    private final float duration;
    private final Map<String, Vec3d> positionKeyframes;
    private final Map<String, Vec3d> rotationKeyframes;

    public Track(float duration, Map<String, Vec3d> positionKeyframes, Map<String, Vec3d> rotationKeyframes) {
        this.duration = duration;
        this.positionKeyframes = positionKeyframes;
        this.rotationKeyframes = rotationKeyframes;

    }

    public static Track fromInputStream(InputStream inputStream) {
        return AtlasLib.GSON.fromJson(new JsonReader(new InputStreamReader(inputStream)), Track.class);

    }

    public static Track fromString(String json) {
        return AtlasLib.GSON.fromJson(json, Track.class);

    }

    public Frame getFrame(float progress) {
        if (progress < 0 || progress > 1) throw new IllegalArgumentException("Passed parameter must be in 0 to 1 range");

        HashSet<Float> positionKeySet = new HashSet<>();
        positionKeyframes.keySet().forEach(key -> positionKeySet.add(Float.valueOf(key)));
        HashSet<Float> rotationKeySet = new HashSet<>();
        rotationKeyframes.keySet().forEach(key -> rotationKeySet.add(Float.valueOf(key)));

        TreeSet<Float> positionTreeSet = new TreeSet<>(positionKeySet);
        TreeSet<Float> rotationTreeSet = new TreeSet<>(rotationKeySet);

        float frameProgress = Math.min(duration * progress, duration);
        float positionProgress = Math.min(duration * progress, positionTreeSet.last());
        float rotationProgress = Math.min(duration * progress, rotationTreeSet.last());

        Float[] positionKeyframeKeys = {positionTreeSet.floor(positionProgress) == null ? positionTreeSet.first() : positionTreeSet.floor(positionProgress), positionTreeSet.ceiling(positionProgress) == null ? positionTreeSet.last() : positionTreeSet.ceiling(positionProgress)};
        Float[] rotationKeyframeKeys = {rotationTreeSet.floor(rotationProgress) == null ? rotationTreeSet.first() : rotationTreeSet.floor(rotationProgress), rotationTreeSet.ceiling(rotationProgress) == null ? rotationTreeSet.last() : rotationTreeSet.ceiling(rotationProgress)};

        Vec3d[] framePositionKeyframes = {positionKeyframes.get(String.valueOf(positionKeyframeKeys[0])), positionKeyframes.get(String.valueOf(positionKeyframeKeys[1]))};
        Vec3d[] frameRotationKeyframes = {rotationKeyframes.get(String.valueOf(rotationKeyframeKeys[0])), rotationKeyframes.get(String.valueOf(rotationKeyframeKeys[1]))};

        float positionKeyframeProgress = (positionProgress - positionKeyframeKeys[0]) / (positionKeyframeKeys[1] - positionKeyframeKeys[0]);
        float rotationKeyframeProgress = (rotationProgress - rotationKeyframeKeys[0]) / (rotationKeyframeKeys[1] - rotationKeyframeKeys[0]);
        if ((positionKeyframeKeys[1] - positionKeyframeKeys[0]) == 0) positionKeyframeProgress = 1;
        if ((rotationKeyframeKeys[1] - rotationKeyframeKeys[0]) == 0) rotationKeyframeProgress = 1;

        Vec3d tweenedPosition = framePositionKeyframes[0].lerp(framePositionKeyframes[1], positionKeyframeProgress); // ADD TWEENS IN THE FUTURE
        Vec3d tweenedRotation = frameRotationKeyframes[0].lerp(frameRotationKeyframes[1], rotationKeyframeProgress);

        return new Frame(tweenedPosition, tweenedRotation, frameProgress == duration);

    }

    public float getDuration() {
        return duration;

    }

}
