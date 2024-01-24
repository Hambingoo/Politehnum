package org.hambubklim.politehnum.Item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PickaxeItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.hambubklim.politehnum.Politehnum;

public class ModItems {

    public static final Item POLITEHNUM = register("politehnum",new Item(new FabricItemSettings()));

    public static final Item POLITEHNUM_PICKAXE = register("politehnum_pickaxe",
            new PickaxeItem(ModToolMaterial.POLITEHNUM,2,2f, new FabricItemSettings()));



    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries)
    {
        entries.add(POLITEHNUM);
    }
    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Politehnum.MOD_ID, id),item);}


    public static void register()
    {
        Politehnum.LOGGER.info(("Registering items for: "+ Politehnum.MOD_ID));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SEARCH).register(ModItems::addItemsToIngredientItemGroup);
    }
}
