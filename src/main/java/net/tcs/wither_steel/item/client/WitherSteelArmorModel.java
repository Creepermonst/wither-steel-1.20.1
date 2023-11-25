package net.tcs.wither_steel.item.client;

import net.minecraft.util.Identifier;
import net.tcs.wither_steel.item.custom.WitherSteelArmorItem;
import software.bernie.geckolib.model.GeoModel;

import static net.tcs.wither_steel.WitherSteel.MOD_ID;

public class WitherSteelArmorModel extends GeoModel<WitherSteelArmorItem> {
	@Override
	public Identifier getModelResource(WitherSteelArmorItem animatable) {
		return new Identifier(MOD_ID, "geo/wither_steel_armor.geo.json");
	}

	@Override
	public Identifier getTextureResource(WitherSteelArmorItem animatable) {
		return new Identifier(MOD_ID, "textures/armor/wither_steel_armor.png");
	}

	@Override
	public Identifier getAnimationResource(WitherSteelArmorItem animatable) {
		return new Identifier(MOD_ID, "animations/wither_steel_armor.animation.json");
	}
}
