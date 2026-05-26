package net.sillyrainy.stabby.items;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.sillyrainy.stabby.index.StabbySounds;
import org.jetbrains.annotations.NotNull;

public class ScytheItem extends SwordItem {
    public ScytheItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, new Properties().attributes(createAttributes(tier, attackDamage, attackSpeed)));
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, LivingEntity attacker) {
        attacker.level().playSound(
                null,
                attacker.getX(),
                attacker.getY(),
                attacker.getZ(),
                StabbySounds.SCYTHE_SLASH.value(),
                SoundSource.PLAYERS,
                1.0f,
                1.0f
        );

        return super.hurtEnemy(stack, target, attacker);
    }
}
