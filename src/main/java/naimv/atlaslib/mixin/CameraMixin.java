package naimv.atlaslib.mixin;

import naimv.atlaslib.access.CameraAccess;
import naimv.atlaslib.mixin.access.CameraAccessor;
import naimv.atlaslib.anim.Frame;
import naimv.atlaslib.manager.TrackManager;
import naimv.atlaslib.mixin.invoker.CameraInvoker;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Camera.class)
public class CameraMixin implements CameraAccess {
    @Override
    public void atlas$update(BlockView area, float tickDelta) {
        CameraAccessor cameraAccessor = (CameraAccessor) this;
        CameraInvoker cameraInvoker = (CameraInvoker) this;
        TrackManager trackManager = TrackManager.getInstance();
        trackManager.update();
        Frame frame = trackManager.getCurrentFrame();
        Frame previousFrame = trackManager.getPreviousFrame();
        Vec3d trackOrigin = trackManager.getTrackOrigin();
        Vec3d translatedFramePosition = frame.getPosition().add(trackOrigin);
        Vec3d translatedPreviousFramePosition = previousFrame.getPosition().add(trackOrigin);
        cameraAccessor.setReady(true);
        cameraAccessor.setArea(area);
        cameraAccessor.setThirdPerson(false);
        cameraAccessor.setLastTickDelta(tickDelta);
        cameraInvoker.setCameraPos(tickDelta == 1 ? translatedFramePosition.getX() : MathHelper.lerp(tickDelta, translatedPreviousFramePosition.getX(), translatedFramePosition.getX()), tickDelta == 1 ? translatedFramePosition.getY() : MathHelper.lerp(tickDelta, translatedPreviousFramePosition.getY(), translatedFramePosition.getY()), tickDelta == 1 ? translatedFramePosition.getZ() : MathHelper.lerp(tickDelta, translatedPreviousFramePosition.getZ(), translatedFramePosition.getZ()));
        this.atlas$setCameraRotation((float) MathHelper.lerp(tickDelta, previousFrame.getRoll(), frame.getRoll()), (float) MathHelper.lerp(tickDelta, previousFrame.getYaw(), frame.getYaw()), (float) MathHelper.lerp(tickDelta, previousFrame.getPitch(), frame.getPitch()));

    }

    @Override
    public void atlas$setCameraRotation(float roll, float yaw, float pitch) {
        CameraAccessor cameraAccessor = (CameraAccessor) this;
        cameraAccessor.setPitch(pitch);
        cameraAccessor.setYaw(yaw);
        cameraAccessor.getRotation().rotationYXZ((float)Math.PI - yaw * ((float)Math.PI / 180F), -pitch * ((float)Math.PI / 180F), roll * ((float)Math.PI / 180F));
        cameraAccessor.getHORIZONTAL().rotate(cameraAccessor.getRotation(), cameraAccessor.getHorizontalPlane());
        cameraAccessor.getVERTICAL().rotate(cameraAccessor.getRotation(), cameraAccessor.getVerticalPlane());
        cameraAccessor.getDIAGONAL().rotate(cameraAccessor.getRotation(), cameraAccessor.getDiagonalPlane());

    }

}
