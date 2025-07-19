package naimv.atlaslib.anim;

import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

public class Frame {
    private final Vec3d position;
    private final Vec3d rotation;
    private final boolean finalFrame;

    public Frame(Vec3d position, Vec3d rotation, boolean finalFrame) {
        this.position = position;
        this.rotation = rotation;
        this.finalFrame = finalFrame;

    }

    public Vec3d getPosition() {
        return position;

    }

    public double getX() {
        return position.getX();

    }

    public double getY() {
        return position.getY();

    }

    public double getZ() {
        return position.getZ();

    }

    public Vec3d getRotation() {
        return rotation;

    }

    public double getPitch() {
        return rotation.getX();

    }

    public double getYaw() {
        return rotation.getY();

    }

    public double getRoll() {
        return rotation.getZ();

    }

    public boolean finalFrame() {
        return finalFrame;

    }

}
