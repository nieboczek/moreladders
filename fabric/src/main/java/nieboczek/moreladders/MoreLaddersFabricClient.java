package nieboczek.moreladders;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.renderer.RenderType;
import nieboczek.moreladders.block.MLBlocks;

public final class MoreLaddersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MLBlocks.BLOCKS.forEach((id, block) -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout()));

        ModContainer container = FabricLoader.getInstance().getModContainer(MoreLadders.MOD_ID).orElseThrow();
        ResourceManagerHelper.registerBuiltinResourcePack(MoreLadders.id("3d_ladders"), container, MoreLadders.RESOURCE_PACK_DISPLAY_NAME, ResourcePackActivationType.NORMAL);
    }
}
