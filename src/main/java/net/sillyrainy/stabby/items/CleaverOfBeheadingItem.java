package net.sillyrainy.stabby.items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class CleaverOfBeheadingItem extends SwordItem {
    public CleaverOfBeheadingItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)));
    }
}
