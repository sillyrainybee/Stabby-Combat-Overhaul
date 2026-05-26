package net.sillyrainy.stabby.index;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.sillyrainy.stabby.Stabby.MODID;

public interface StabbySounds {
    DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);

    Holder<SoundEvent> SCYTHE_SLASH = SOUND_EVENTS.register(
            "scythe_slash",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.parse("stabby:weapons/scythe_slash")
            ));

    static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
