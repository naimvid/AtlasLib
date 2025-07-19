package naimv.atlaslib.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import naimv.atlaslib.access.CameraAccess;
import naimv.atlaslib.mixin.access.GameRendererAccessor;
import naimv.atlaslib.manager.TrackManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Redirect(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;update(Lnet/minecraft/world/BlockView;Lnet/minecraft/entity/Entity;ZZF)V"))
    public void renderWorld(Camera instance, BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, @Local(ordinal = 0) Camera camera, @Local(ordinal = 0) Entity entity, @Local(ordinal = 0) float g) {
        MinecraftClient mixinClient = ((GameRendererAccessor) this).getClient();
        TrackManager trackManager = TrackManager.getInstance();
        if (!(trackManager.isPlayingTrack())) {
            camera.update(mixinClient.world, entity, !mixinClient.options.getPerspective().isFirstPerson(), mixinClient.options.getPerspective().isFrontView(), g);

        } else {
            ((CameraAccess) camera).atlas$update(mixinClient.world, g);

        }

    }

}
