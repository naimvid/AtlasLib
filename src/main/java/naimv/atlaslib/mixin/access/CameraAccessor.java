package naimv.atlaslib.mixin.access;

import net.minecraft.client.render.Camera;
import net.minecraft.world.BlockView;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Camera.class)
public interface CameraAccessor {
    @Accessor
    Quaternionf getRotation();

    @Accessor
    Vector3f getHORIZONTAL();

    @Accessor
    Vector3f getVERTICAL();

    @Accessor
    Vector3f getDIAGONAL();

    @Accessor
    Vector3f getHorizontalPlane();

    @Accessor
    Vector3f getVerticalPlane();

    @Accessor
    Vector3f getDiagonalPlane();

    @Accessor("ready")
    void setReady(boolean ready);

    @Accessor("area")
    void setArea(BlockView area);

    @Accessor("thirdPerson")
    void setThirdPerson(boolean thirdPerson);

    @Accessor("lastTickDelta")
    void setLastTickDelta(float lastTickDelta);

    @Accessor("pitch")
    void setPitch(float pitch);

    @Accessor("yaw")
    void setYaw(float yaw);

}
