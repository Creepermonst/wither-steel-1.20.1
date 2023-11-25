package net.tcs.wither_steel.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tcs.wither_steel.WitherSteel;
import net.tcs.wither_steel.item.custom.WitherSteelArmorItem;
import net.tcs.wither_steel.item.custom.WitherSteelSwordItem;
import net.tcs.wither_steel.item.custom.WitherSteelTemplateItem;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import static net.tcs.wither_steel.WitherSteel.MOD_ID;

public class ModItems {

	public static final Item WITHER_STEEL_UPGRADE_SMITHING_TEMPLATE = registerItem("wither_steel_upgrade_smithing_template", WitherSteelTemplateItem.createWitherSteelUpgrade());

	public static final Item WITHER_STEEL_SWORD = registerItem("wither_steel_sword", new WitherSteelSwordItem(
			ToolMaterials.NETHERITE, 4, -2.6f, new QuiltItemSettings().maxCount(1).fireproof()));

	public static final Item WITHER_STEEL_HELMET = registerItem("wither_steel_helmet",
			new WitherSteelArmorItem(ModArmorMaterials.WITHER_STEEL, ArmorItem.ArmorSlot.HELMET,
					new QuiltItemSettings().maxCount(1).fireproof()));

	public static final Item WITHER_STEEL_CHESTPLATE = registerItem("wither_steel_chestplate",
			new WitherSteelArmorItem(ModArmorMaterials.WITHER_STEEL, ArmorItem.ArmorSlot.CHESTPLATE,
					new QuiltItemSettings().maxCount(1).fireproof()));

	public static final Item WITHER_STEEL_LEGGINGS = registerItem("wither_steel_leggings",
			new WitherSteelArmorItem(ModArmorMaterials.WITHER_STEEL, ArmorItem.ArmorSlot.LEGGINGS,
					new QuiltItemSettings().maxCount(1).fireproof()));

	public static final Item WITHER_STEEL_BOOTS = registerItem("wither_steel_boots",
			new WitherSteelArmorItem(ModArmorMaterials.WITHER_STEEL, ArmorItem.ArmorSlot.BOOTS,
					new QuiltItemSettings().maxCount(1).fireproof()));

	public static final Item WITHER_STEEL = registerItem("wither_steel", new Item(
			new QuiltItemSettings().fireproof()));

	public static final Item WITHER_DUST = registerItem("wither_dust", new Item(
			new QuiltItemSettings()));

	private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
		entries.addItem(WITHER_DUST);
		entries.addItem(WITHER_STEEL);
		entries.addItem(WITHER_STEEL_UPGRADE_SMITHING_TEMPLATE);
	}
	private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
		entries.addItem(WITHER_STEEL_HELMET);
		entries.addItem(WITHER_STEEL_CHESTPLATE);
		entries.addItem(WITHER_STEEL_LEGGINGS);
		entries.addItem(WITHER_STEEL_BOOTS);
		entries.addItem(WITHER_STEEL_SWORD);
	}

	private static Item registerItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
	}

	public static void registerModItems(){
		WitherSteel.LOGGER.info("Registering Items For" + MOD_ID);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
	}

}
