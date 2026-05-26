package net.sillyrainy.stabby.items;

import net.minecraft.world.item.*;

public class CleaverItem extends AxeItem {
    public CleaverItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)));
    }
}
