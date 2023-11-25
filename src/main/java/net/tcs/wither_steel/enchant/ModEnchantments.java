package net.tcs.wither_steel.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tcs.wither_steel.enchant.custom.RetributionEnchantment;

import static net.tcs.wither_steel.WitherSteel.MOD_ID;

public class ModEnchantments {
	public static Enchantment RETRIBUTION = register("retribution",
			new RetributionEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));


	private static Enchantment register(String name, Enchantment enchantment) {
		return Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, name), enchantment);
	}

	public static void registerModEnchantments() {
		System.out.println("Registering Enchantments for " + MOD_ID);
	}
}
