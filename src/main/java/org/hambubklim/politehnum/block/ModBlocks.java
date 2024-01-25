package org.hambubklim.politehnum.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.hambubklim.politehnum.Politehnum;

public class ModBlocks {

    public static final  Block POLITEHNUM_ORE = registerBlock("politehnum_ore",new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(10.0F).sounds(BlockSoundGroup.DEEPSLATE)));


    public static final  Block DEEPSLATE_POLITEHNUM_ORE = registerBlock("deepslate_politehnum_ore",new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(10.0F).sounds(BlockSoundGroup.DEEPSLATE)));





    public static final Block NETHER_POLITEHNUM_ORE = registerBlock("nether_politehnum_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHERRACK).strength(10f)));
    public static final Block END_POLITEHNUM_ORE = registerBlock("end_politehnum_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.END_STONE).strength(10f)));





    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Politehnum.MOD_ID, name),block);
    }
    public static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM,new Identifier(Politehnum.MOD_ID,name),new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks()
    {
        Politehnum.LOGGER.info("Registering ModBlocks for "+Politehnum.MOD_ID);
    }

}
