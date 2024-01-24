package org.hambubklim.politehnum.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import org.hambubklim.politehnum.Item.ModItems;

public class ModLootTableModifiers {
    private static final Identifier END_CITY_TREASURE_ID=
            new Identifier("minecraft","chests/end_city_treasure");
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
                if(END_CITY_TREASURE_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(0.25f)) // Drops 100% of the time
                            .with(ItemEntry.builder(ModItems.POLITEHNUM))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

                    tableBuilder.pool(poolBuilder.build());
                }
        });
    }
}
