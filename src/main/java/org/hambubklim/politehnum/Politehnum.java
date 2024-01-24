package org.hambubklim.politehnum;

import net.fabricmc.api.ModInitializer;
import org.hambubklim.politehnum.Item.ModItemGroups;
import org.hambubklim.politehnum.Item.ModItems;
import org.hambubklim.politehnum.block.ModBlocks;
import org.hambubklim.politehnum.util.ModLootTableModifiers;
import org.hambubklim.politehnum.world.gen.ModOreGeneration;
import org.hambubklim.politehnum.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Politehnum implements ModInitializer {
    public static final String MOD_ID="politehnum";

    public static final Logger LOGGER = LoggerFactory.getLogger("politehnum");

    @Override
    public void onInitialize() {
        ModItems.register();
        ModItemGroups.registerItemGroup();
        ModBlocks.registerModBlocks();
        ModWorldGeneration.generateModWorldGen();
        ModLootTableModifiers.modifyLootTables();
    }
}
