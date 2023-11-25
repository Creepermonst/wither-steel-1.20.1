package net.tcs.wither_steel.mixin;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.tcs.wither_steel.enchant.custom.RetributionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class AttackMixin {
	@Inject(method = "applyDamage", at = @At("HEAD"))
	protected void injectDamageMethod(DamageSource source, float amount, CallbackInfo ci) {
		if (source.getAttacker() != null) {
			World world = source.getAttacker().getWorld();
			PlayerEntity victim = world.getClosestPlayer(source.getAttacker(), 5);
			if (!world.isClient()) {
				if (victim != null && victim.isPlayer()) {
					ItemStack handStack = victim.getStackInHand(Hand.MAIN_HAND);
						if (handStack.hasNbt()) {
							if (handStack.getEnchantments().toString().equals("[{id:\"wither_steel:retribution\",lvl:1s}]")) {
								NbtCompound nbtData = handStack.getNbt();
								amount = applyArmorToDamage(source, amount, victim);
								amount = applyEnchantmentsToDamage(source, amount, victim);
								nbtData.putFloat("charge", (RetributionEnchantment.getCharge(handStack) + (amount * 2)));
								if (RetributionEnchantment.getCharge(handStack) >= RetributionEnchantment.getMaxCharge())
									nbtData.putFloat("charge", RetributionEnchantment.getMaxCharge());
							}
						}
				}
			}
		}
	}
	private static float applyArmorToDamage(DamageSource source, float amount, LivingEntity entity) {
		if (!source.isTypeIn(DamageTypeTags.BYPASSES_ARMOR)) {
			entity.damageArmor(source, amount);
			amount = DamageUtil.getDamageLeft(amount, (float)entity.getArmor(), (float)entity.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS));
		}

		return amount;
	}


	private static float applyEnchantmentsToDamage(DamageSource source, float amount, LivingEntity entity) {
		if (source.isTypeIn(DamageTypeTags.BYPASSES_EFFECTS)) {
			return amount;
		} else {
			int i;
			if (entity.hasStatusEffect(StatusEffects.RESISTANCE) && !source.isTypeIn(DamageTypeTags.BYPASSES_RESISTANCE)) {
				i = (entity.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1) * 5;
				int j = 25 - i;
				float f = amount * (float)j;
				float g = amount;
				amount = Math.max(f / 25.0F, 0.0F);
				float h = g - amount;
				if (h > 0.0F && h < 3.4028235E37F) {
					if (entity instanceof ServerPlayerEntity) {
						((ServerPlayerEntity)entity).increaseStat(Stats.DAMAGE_RESISTED, Math.round(h * 10.0F));
					} else if (source.getAttacker() instanceof ServerPlayerEntity) {
						((ServerPlayerEntity)source.getAttacker()).increaseStat(Stats.DAMAGE_DEALT_RESISTED, Math.round(h * 10.0F));
					}
				}
			}

			if (amount <= 0.0F) {
				return 0.0F;
			} else if (source.isTypeIn(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
				return amount;
			} else {
				i = EnchantmentHelper.getProtectionAmount(entity.getArmorItems(), source);
				if (i > 0) {
					amount = DamageUtil.getInflictedDamage(amount, (float)i);
				}

				return amount;
			}
		}
	}
}
