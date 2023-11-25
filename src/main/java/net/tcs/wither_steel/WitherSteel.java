package net.tcs.wither_steel;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tcs.wither_steel.block.ModBlocks.registerModBlocks;
import static net.tcs.wither_steel.enchant.ModEnchantments.registerModEnchantments;
import static net.tcs.wither_steel.item.ModItems.registerModItems;
import static net.tcs.wither_steel.particle.ModParticles.registerParticles;

public class WitherSteel implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER =	 LoggerFactory.getLogger("Wither Steel");
	public static final String MOD_ID = "wither_steel";

	@Override
	public void onInitialize(ModContainer mod) {
		registerModItems();
		registerParticles();
		registerModEnchantments();
		registerModBlocks();

	}
}
