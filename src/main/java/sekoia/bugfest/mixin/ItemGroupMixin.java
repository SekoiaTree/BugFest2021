package sekoia.bugfest.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import sekoia.bugfest.ItemGroupInventoryPlacementManager;

import java.util.*;

@Mixin(ItemGroup.class)
public class ItemGroupMixin implements ItemGroupInventoryPlacementManager {
    private final HashMap<Item, List<Item>> placementOverride = new HashMap<>();
    private final List<Item> doNotAdd = new ArrayList<>();

    @Inject(method = "appendStacks", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;appendStacks(Lnet/minecraft/item/ItemGroup;Lnet/minecraft/util/collection/DefaultedList;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void overridePlacement(DefaultedList<ItemStack> stacks, CallbackInfo ci, Iterator<Item> var2, Item item) {
        if (placementOverride.containsKey(item)) {
            for (Item i : placementOverride.get(item)) {
                i.appendStacks((ItemGroup) (Object)this, stacks);
            }
        }
    }

    @Redirect(method = "appendStacks", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;appendStacks(Lnet/minecraft/item/ItemGroup;Lnet/minecraft/util/collection/DefaultedList;)V"))
    private void doNotAddOverridden(Item item, ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (doNotAdd.contains(item)) {
            return;
        }
        item.appendStacks(group, stacks);
    }

    @Override
    public void addItemAfter(Item toAdd, Item after) {
        if (!placementOverride.containsKey(after)) {
            placementOverride.put(after, new ArrayList<>());
        }
        placementOverride.get(after).add(toAdd);
        doNotAdd.add(toAdd);
    }

    @Override
    public void addItemsAfter(Item[] toAdd, Item after) {
        if (!placementOverride.containsKey(after)) {
            placementOverride.put(after, new ArrayList<Item>());
        }
        placementOverride.get(after).addAll(Arrays.asList(toAdd));
        doNotAdd.addAll(Arrays.asList(toAdd));
    }
}
