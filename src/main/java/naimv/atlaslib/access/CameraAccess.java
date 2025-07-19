package naimv.atlaslib.access;

import net.minecraft.world.BlockView;

public interface CameraAccess {
    void atlas$update(BlockView area, float tickDelta);
    void atlas$setCameraRotation(float roll, float yaw, float pitch);

}
