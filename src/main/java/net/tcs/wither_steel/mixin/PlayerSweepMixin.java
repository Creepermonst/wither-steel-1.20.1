package net.tcs.wither_steel.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.tcs.wither_steel.item.ModItems;
import net.tcs.wither_steel.particle.ModParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerSweepMixin {
	@Inject(method = "spawnSweepAttackParticles", at = @At("HEAD"), cancellable = true)
	public void injectSweepAttackParticlesMethod(CallbackInfo ci) {
		PlayerEntity player = ((PlayerEntity) (Object) this);
		if(player.getMainHandStack().getItem() == ModItems.WITHER_STEEL_SWORD) {
			ci.cancel();
			double d = -MathHelper.sin(player.getYaw() * 0.017453292F);
			double e = MathHelper.cos(player.getYaw() * 0.017453292F);
			if (player.getWorld() instanceof ServerWorld) {
				((ServerWorld) player.getWorld()).spawnParticles(ModParticles.WITHER_SWORD_SWING_PARTICLE, player.getX() + d, player.getBodyY(0.5), player.getZ() + e, 0, d, 0.0, e, 0.0);
			}
		}
	}
}
