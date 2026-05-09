package nieboczek.moreladders;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public final class MoreLaddersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModContainer container = FabricLoader.getInstance().getModContainer(MoreLadders.MOD_ID).orElseThrow();
        ResourceLoader.registerBuiltinPack(MoreLadders.id("3d_ladders"), container, MoreLadders.RESOURCE_PACK_DISPLAY_NAME, PackActivationType.NORMAL);
    }
}
