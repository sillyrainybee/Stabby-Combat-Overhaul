package net.sillyrainy.stabby.index;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public interface StabbyDataGen {

    @SubscribeEvent
    static void dataGenerator(GatherDataEvent event) {
        DataGenerator dataGen = event.getGenerator();
        PackOutput output = dataGen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registry = event.getLookupProvider();


    }
}
