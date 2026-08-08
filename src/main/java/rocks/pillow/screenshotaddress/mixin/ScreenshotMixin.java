package rocks.pillow.screenshotaddress.mixin;

import net.minecraft.client.Screenshot;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rocks.pillow.screenshotaddress.Server;

import java.io.File;

@Mixin(Screenshot.class)
public class ScreenshotMixin {
	@Inject(
			method = "getFile",
			at = @At("RETURN"),
			cancellable = true
	)
	private static void getScreenshotFilename(
			File directory,
			CallbackInfoReturnable<File> cir
	) {
		File originalFile = cir.getReturnValue();
		String filename = originalFile.getName();

		cir.setReturnValue(new File(directory,
				filename.replace(".png", "") + "_" + Server.getCurrentLocationSafe() + ".png"
		));
	}
}