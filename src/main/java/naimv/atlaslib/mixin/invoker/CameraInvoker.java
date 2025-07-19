package naimv.atlaslib.mixin.invoker;

import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Camera.class)
public interface CameraInvoker {
    @Invoker("setPos")
    void setCameraPos(double x, double y, double z);

}
