package net.tcs.wither_steel.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tcs.wither_steel.WitherSteel;

public class ModParticles {
	public static final DefaultParticleType WITHER_SWORD_SWING_PARTICLE = FabricParticleTypes.simple();

	public static void registerParticles() {
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(WitherSteel.MOD_ID, "wither_sword_swing"),
				WITHER_SWORD_SWING_PARTICLE);
	}
}
