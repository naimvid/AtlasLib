package naimv.atlaslib.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;

public class AtlasMath {
    public static Vec2f interpolateVec2f(Vec2f toA, Vec2f toB, double delta) {
        return new Vec2f((float) MathHelper.lerp(delta, toA.x, toB.x), (float) MathHelper.lerp(delta, toA.y, toB.y));

    }

    public static int millisecondsToTicks(double milliseconds) {
        return (int) (milliseconds / 50);

    }

    public static double millisecondsToSeconds(double milliseconds) {
        return milliseconds / 1000;

    }

}
