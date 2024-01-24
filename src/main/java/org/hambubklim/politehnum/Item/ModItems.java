package org.hambubklim.politehnum.Item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.hambubklim.politehnum.Item.custom.Mod3x3AxeItem;
import org.hambubklim.politehnum.Item.custom.Mod3x3PickaxeItem;
import org.hambubklim.politehnum.Item.custom.Mod3x3ShovelItem;
import org.hambubklim.politehnum.Item.custom.ModArmorItem;
import org.hambubklim.politehnum.Politehnum;

public class ModItems {

    public static final Item POLITEHNUM = register("politehnum",new Item(new FabricItemSettings()));

    public static final Item POLITEHNUM_DRILL = register("politehnum_drill",
            new Mod3x3PickaxeItem(ModToolMaterial.POLITEHNUM,2,0F, new FabricItemSettings()));
    public static final Item POLITEHNUM_CHAINSAW = register("politehnum_chainsaw",
            new Mod3x3AxeItem(ModToolMaterial.POLITEHNUM,2F,0F, new FabricItemSettings()));
    public static final Item POLITEHNUM_SHOVEL = register("politehnum_shovel",
            new Mod3x3ShovelItem(ModToolMaterial.POLITEHNUM,2F,0F, new FabricItemSettings()));
    public static final Item POLITEHNUM_SWORD = register("politehnum_sword",
            new SwordItem(ModToolMaterial.POLITEHNUM,10,-2F, new FabricItemSettings()));
    public static final Item POLITEHNUM_HOE = register("politehnum_hoe",
            new HoeItem(ModToolMaterial.POLITEHNUM,0,2F, new FabricItemSettings()));
    public static final Item POLITEHNUM_HELMET = register("politehnum_helmet",
            new ModArmorItem(ModArmorMaterials.POLITEHNUM, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item POLITEHNUM_CHESTPLATE = register("politehnum_chestplate",
            new ArmorItem(ModArmorMaterials.POLITEHNUM, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item POLITEHNUM_LEGGINS = register("politehnum_leggins",
            new ArmorItem(ModArmorMaterials.POLITEHNUM, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item POLITEHNUM_BOOTS = register("politehnum_boots",
            new ArmorItem(ModArmorMaterials.POLITEHNUM, ArmorItem.Type.BOOTS, new FabricItemSettings()));



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
