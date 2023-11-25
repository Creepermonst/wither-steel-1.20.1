package net.tcs.wither_steel.client;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.particle.SweepAttackParticle;
import net.tcs.wither_steel.item.ModItems;
import net.tcs.wither_steel.particle.ModParticles;
import net.tcs.wither_steel.util.WithSteelClientUtil;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

//@Environment(EnvType.CLIENT)
public class WitherSteelClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		ParticleFactoryRegistry.getInstance().register(ModParticles.WITHER_SWORD_SWING_PARTICLE, SweepAttackParticle.Factory::new);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> WithSteelClientUtil.getChargeColor(stack), ModItems.WITHER_STEEL_SWORD);
	}
}
