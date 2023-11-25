package net.tcs.wither_steel.enchant.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tcs.wither_steel.item.ModItems;

public class RetributionEnchantment extends Enchantment {


	public RetributionEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
		super(weight, type, slotTypes);
	}

	@Override
	public boolean isTreasure() {
		return true;
	}

	@Override
	public boolean isAvailableForEnchantedBookOffer() {
		return false;
	}

	@Override
	public boolean isAvailableForRandomSelection() {
		return false;
	}

	public static float getCharge(ItemStack stack) {
		if (stack.hasNbt())
			return stack.getNbt().getInt("charge");
		else
			return 0;
	}

	public static float getMaxCharge() {
		return 48;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() == ModItems.WITHER_STEEL_SWORD;
	}
}
