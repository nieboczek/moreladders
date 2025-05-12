package nieboczek.moreladders;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import nieboczek.moreladders.block.ModBlocks;

public class MoreLaddersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModBlocks.BLOCKS.forEach((id, block) -> {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        });

        ModContainer container = FabricLoader.getInstance().getModContainer(MoreLadders.MOD_ID).orElseThrow(() -> new IllegalStateException("MoreLadders mod container couldn't be found"));
        ResourceManagerHelper.registerBuiltinResourcePack(MoreLadders.id("3d_ladders"), container, Component.literal("3D Ladders"), ResourcePackActivationType.NORMAL);
    }
}
