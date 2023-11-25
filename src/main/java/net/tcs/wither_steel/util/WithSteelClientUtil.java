package net.tcs.wither_steel.util;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.tcs.wither_steel.enchant.custom.RetributionEnchantment;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class WithSteelClientUtil {

	public static int getChargeColor(ItemStack stack) {
		float damageBonus = RetributionEnchantment.getCharge(stack);
		if (damageBonus > 0) {
			float other = 1 - damageBonus / RetributionEnchantment.getMaxCharge();
			return new Color(29, 164, 222, Math.round(127f * other)).getRGB();

		}
		else
			return -1;
	}
}
