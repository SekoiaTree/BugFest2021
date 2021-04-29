package sekoia.bugfest;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class Bugfest implements ModInitializer {
    @Override
    public void onInitialize() {
        ((ItemGroupInventoryPlacementManager) ItemGroup.DECORATIONS).addItemAfter(Items.RESPAWN_ANCHOR, Items.BLACK_BED);
        ((ItemGroupInventoryPlacementManager) ItemGroup.BUILDING_BLOCKS).addItemsAfter(new net.minecraft.item.Item[]{Items.LAPIS_LAZULI, Items.DIAMOND_ORE, Items.REDSTONE_ORE, Items.EMERALD_ORE, Items.NETHER_QUARTZ_ORE}, Items.NETHER_GOLD_ORE);
        RegistryKey<Registry<Item>> key = RegistryKey.ofRegistry(new Identifier("item"));
        Registry.ITEM.set(833, RegistryKey.of(key, new Identifier("poisonous_potato")), new Item((new Item.Settings()).group(ItemGroup.FOOD).food(FoodComponents.POISONOUS_POTATO)), Lifecycle.stable());    }
}
