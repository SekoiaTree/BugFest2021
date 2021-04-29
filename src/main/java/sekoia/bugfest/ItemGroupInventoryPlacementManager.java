package sekoia.bugfest;

import net.minecraft.item.Item;

public interface ItemGroupInventoryPlacementManager {
    void addItemAfter(Item toAdd, Item after);
    void addItemsAfter(Item[] toAdd, Item after);
}
