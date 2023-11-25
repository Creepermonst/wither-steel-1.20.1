package net.tcs.wither_steel.item.custom;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tcs.wither_steel.item.ModArmorMaterials;
import net.tcs.wither_steel.item.client.WitherSteelArmorModel;
import net.tcs.wither_steel.item.client.WitherSteelArmorRenderer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class WitherSteelArmorItem extends ArmorItem implements GeoItem {
	private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

	public WitherSteelArmorItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
		super(material, slot, settings);
	}


	public void createRenderer(Consumer<Object> consumer) {
		consumer.accept(new RenderProvider() {
			private WitherSteelArmorRenderer renderer;

			@Override
			public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
																		EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
				if (this.renderer == null)
					this.renderer = new WitherSteelArmorRenderer(new WitherSteelArmorModel());

				this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

				return this.renderer;
			}
		});
	}

	@Override
	public Supplier<Object> getRenderProvider() {
		return this.renderProvider;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController(this, "controller", 0, this::predicate));
	}

	private PlayState predicate(AnimationState animationState) {
		animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
		return PlayState.CONTINUE;
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient()) {
			if(entity instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)entity;

				if(hasFullSuitOfArmorOn(player)) {
					if(hasCorrectArmorOn(ModArmorMaterials.WITHER_STEEL, player)) {
						player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 10, 0, true, false));
						if (player.isOnFire()) {
							player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10, 0, true, false));
						}
					}
				}
			}
		}

		super.inventoryTick(stack, world, entity, slot, selected);
	}

	private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
		ItemStack boots = player.getInventory().getArmorStack(0);
		ItemStack leggings = player.getInventory().getArmorStack(1);
		ItemStack breastplate = player.getInventory().getArmorStack(2);
		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !helmet.isEmpty() && !breastplate.isEmpty()
				&& !leggings.isEmpty() && !boots.isEmpty();
	}

	private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
		ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
		ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
		ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
		ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

		return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
				leggings.getMaterial() == material && boots.getMaterial() == material;
	}
}
