package net.sillyrainy.stabby.mixin;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.sillyrainy.stabby.index.StabbyItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Vindicator.class)
public class VindicatorEntityMixin {

    @Inject(
            method = "populateDefaultEquipmentSlots",
            at = @At("TAIL")
    )
    private void stabby$giveCleaver(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        Vindicator self = (Vindicator)(Object)this;

        ItemStack itemstack = self.getItemBySlot(EquipmentSlot.HEAD);
        boolean itemOnHead = !itemstack.isEmpty() && ItemStack.matches(itemstack, Raid.getLeaderBannerInstance(self.registryAccess().lookupOrThrow(Registries.BANNER_PATTERN)));


        // Makes Vindicators have a 10% Chance to Wield a Cleaver and 30% Chance to Drop it
        if (random.nextFloat() < 0.1f) {
            ItemStack item = new ItemStack(StabbyItems.CLEAVER.get());
            self.setItemSlot(EquipmentSlot.MAINHAND, item);
            self.setDropChance(EquipmentSlot.MAINHAND, 0.3f);
        }

        if (itemOnHead) {
            ItemStack item = new ItemStack(StabbyItems.CLEAVER_OF_BEHEADING.get());
            self.setItemSlot(EquipmentSlot.MAINHAND, item);
            self.setDropChance(EquipmentSlot.MAINHAND, 0.05f);
        }
    }
}

