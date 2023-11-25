package net.tcs.wither_steel.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.tcs.wither_steel.item.ModItems;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import static net.tcs.wither_steel.WitherSteel.LOGGER;
import static net.tcs.wither_steel.WitherSteel.MOD_ID;

public class ModBlocks {

	public static final Block WITHER_STEEL_BLOCK = registerBlock("wither_steel_block",
			new Block(QuiltBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).mapColor(MapColor.BLUE)));

	private static Block registerBlock(String name, Block block){
		registerBlockItem(name,block);
		return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
	}

	private static Item registerBlockItem(String name, Block block) {
		Item item = Registry.register(Registries.ITEM, new Identifier(MOD_ID, name),
				new BlockItem(block, new FabricItemSettings()));
		return item;
	}
	private static void addItemsToBuildingItemGroup(FabricItemGroupEntries entries) {
		entries.addItem(WITHER_STEEL_BLOCK);
	}

	public static void registerModBlocks(){
		LOGGER.info("Registering Blocks For Mod" + MOD_ID);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModBlocks::addItemsToBuildingItemGroup);
	}
}
