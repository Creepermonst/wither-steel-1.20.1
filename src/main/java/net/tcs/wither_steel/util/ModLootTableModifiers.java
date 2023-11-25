package net.tcs.wither_steel.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.tcs.wither_steel.enchant.ModEnchantments;
import net.tcs.wither_steel.item.ModItems;

public class ModLootTableModifiers {
	public static final Identifier FORTRESS_ID =
			new Identifier("minecraft", "chests/nether_bridge");


	public static void modifyLootTables(){
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if(FORTRESS_ID.equals(id)){
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(RandomChanceLootCondition.builder(0.14f))
						.with(ItemEntry.builder(ModItems.WITHER_STEEL_UPGRADE_SMITHING_TEMPLATE)
						.apply((LootFunction.Builder) SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build()));

				tableBuilder.pool(poolBuilder.build());
			}
		});
	}
}
