package naimv.atlaslib.manager;

import naimv.atlaslib.anim.Frame;
import naimv.atlaslib.anim.Track;
import naimv.atlaslib.util.AtlasMath;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class TrackManager {
    private static TrackManager instance;

    private Track currentTrack;
    private final ArrayList<Track> tracks;

    private long startTime;

    private Frame previousFrame;
    private Frame currentFrame;

    private Vec3d trackOrigin;

    public TrackManager() {
        instance = this;
        tracks = new ArrayList<>();
        trackOrigin = Vec3d.ZERO;

    }

    public static TrackManager getInstance() {
        return instance;

    }

    public void update() {
        Frame frame = currentTrack.getFrame((float) Math.min((AtlasMath.millisecondsToSeconds(System.currentTimeMillis() - startTime) / currentTrack.getDuration()), 1)); // FIX NUMBER GOING OVER 1
        previousFrame = currentFrame == null ? frame : currentFrame;
        currentFrame = frame;
        if (frame.finalFrame()) {
            currentTrack = null;

        }

    }

    public void reset() {
        startTime = 0;
        currentFrame = null;
        previousFrame = null;
        trackOrigin = Vec3d.ZERO;

    }

    public boolean isPlayingTrack() {
        return currentTrack != null;

    }

    public void playTrack(Track track) {
        startTime = System.currentTimeMillis();
        currentTrack = track;

    }

    public void playTrack(int index) {
        playTrack(tracks.get(index));

    }

    public Track getCurrentTrack() {
        return currentTrack;

    }

    public Track getTrack(int index) {
        return tracks.get(index);

    }

    public void addTrack(Track track) {
        tracks.add(track);

    }

    public void setTrackOrigin(Vec3d trackOrigin) {
        this.trackOrigin = trackOrigin;

    }

    public Vec3d getTrackOrigin() {
        return trackOrigin;

    }

    public Frame getPreviousFrame() {
        return previousFrame;

    }

    public Frame getCurrentFrame() {
        return currentFrame;

    }

}
