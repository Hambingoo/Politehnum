package org.hambubklim.politehnum.Item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.hambubklim.politehnum.Politehnum;
import org.hambubklim.politehnum.block.ModBlocks;

public class ModItemGroups {


    public static final ItemGroup POLITEHNUM_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Politehnum.MOD_ID, "politehnum"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.politehnum"))
                    .icon(() -> new ItemStack(ModItems.POLITEHNUM)).entries((displayContext, entries) -> {
                        entries.add(ModItems.POLITEHNUM);

                        entries.add(ModBlocks.POLITEHNUM_ORE);

                    }).build());
    public static void registerItemGroup(){
        Politehnum.LOGGER.info("Regestering Item Groups for "+Politehnum.MOD_ID);
    }
}
