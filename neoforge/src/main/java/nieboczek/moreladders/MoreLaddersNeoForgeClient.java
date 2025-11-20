package nieboczek.moreladders;

import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@Mod(value = MoreLadders.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = MoreLadders.MOD_ID, value = Dist.CLIENT)
public final class MoreLaddersNeoForgeClient {
    @SubscribeEvent
    private static void setupBuiltinResourcepack(AddPackFindersEvent event) {
        event.addPackFinders(MoreLadders.id("resourcepacks/3d_ladders"), PackType.CLIENT_RESOURCES, MoreLadders.RESOURCE_PACK_DISPLAY_NAME, PackSource.BUILT_IN, false, Pack.Position.TOP);
    }
}
