package net.sillyrainy.stabby.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sillyrainy.stabby.items.CleaverItem;
import net.sillyrainy.stabby.items.CleaverOfBeheadingItem;
import net.sillyrainy.stabby.items.RapierItem;
import net.sillyrainy.stabby.items.ScytheItem;

import static net.sillyrainy.stabby.Stabby.MODID;

public interface StabbyItems {
    // Create a Deferred Register to hold Items which will all be registered under the "stabby" namespace
    DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "stabby" namespace
    DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Weapon Registers
    DeferredItem<Item> RAPIER = ITEMS.register("rapier", () -> new RapierItem(Tiers.IRON,1,-1.4f));
    DeferredItem<Item> SCYTHE = ITEMS.register("scythe", () -> new ScytheItem(Tiers.IRON,5,-2f));
    DeferredItem<Item> CLEAVER = ITEMS.register("cleaver", () -> new CleaverItem(Tiers.IRON,2,-2f));
    DeferredItem<Item> CLEAVER_OF_BEHEADING = ITEMS.register("cleaverofbeheading", () -> new CleaverOfBeheadingItem(Tiers.IRON,12,-3.5f));

    // Stabby Item Creative Tab
    DeferredHolder<CreativeModeTab, CreativeModeTab> WEAPONS_TAB = CREATIVE_MODE_TABS.register("weapons_tab", () -> CreativeModeTab.builder().title(Component.translatable("weapons-tab.stabby")).withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> RAPIER.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(RAPIER.get());
        output.accept(SCYTHE.get());
        output.accept(CLEAVER.get());
        output.accept(CLEAVER_OF_BEHEADING.get());
    }).build());

    static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        CREATIVE_MODE_TABS.register(eventBus);
    }

    // Deals 1.6x more Damage to Animals whilst holding a Cleaver
    @SubscribeEvent
    static void bonusDamage(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Animal) {
            if (event.getSource().getEntity() instanceof LivingEntity attacker) {

                ItemStack heldItem = attacker.getMainHandItem();

                if (heldItem.getItem() == CLEAVER.get()) {
                    event.setNewDamage(event.getNewDamage() * 1.6f);
                }
            }
        }
    }
}
