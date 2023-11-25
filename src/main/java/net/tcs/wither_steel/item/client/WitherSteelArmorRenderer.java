package net.tcs.wither_steel.item.client;

import net.tcs.wither_steel.item.custom.WitherSteelArmorItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class WitherSteelArmorRenderer extends GeoArmorRenderer<WitherSteelArmorItem> {
	public WitherSteelArmorRenderer(GeoModel<WitherSteelArmorItem> model) {
		super(new WitherSteelArmorModel());
	}
}
