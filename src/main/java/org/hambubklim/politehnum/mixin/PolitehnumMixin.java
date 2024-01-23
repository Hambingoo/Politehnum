package org.hambubklim.politehnum.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.hambubklim.politehnum.Politehnum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
class PolitehnumMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info){
        Politehnum.LOGGER.info("This labe is printed!");
    }

}
