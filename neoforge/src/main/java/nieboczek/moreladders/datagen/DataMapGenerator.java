package nieboczek.moreladders.datagen;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Oxidizable;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;
import nieboczek.moreladders.block.MLBlocks;

import java.util.concurrent.CompletableFuture;

public final class DataMapGenerator extends DataMapProvider {
    DataMapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        Builder<Oxidizable, Block> oxidizable = builder(NeoForgeDataMaps.OXIDIZABLES);
        add(oxidizable, MLBlocks.COPPER_LADDER, new Oxidizable(MLBlocks.EXPOSED_COPPER_LADDER));
        add(oxidizable, MLBlocks.EXPOSED_COPPER_LADDER, new Oxidizable(MLBlocks.WEATHERED_COPPER_LADDER));
        add(oxidizable, MLBlocks.WEATHERED_COPPER_LADDER, new Oxidizable(MLBlocks.OXIDIZED_COPPER_LADDER));

        Builder<Waxable, Block> waxable = builder(NeoForgeDataMaps.WAXABLES);
        add(waxable, MLBlocks.COPPER_LADDER, new Waxable(MLBlocks.WAXED_COPPER_LADDER));
        add(waxable, MLBlocks.EXPOSED_COPPER_LADDER, new Waxable(MLBlocks.WAXED_EXPOSED_COPPER_LADDER));
        add(waxable, MLBlocks.WEATHERED_COPPER_LADDER, new Waxable(MLBlocks.WAXED_WEATHERED_COPPER_LADDER));
        add(waxable, MLBlocks.OXIDIZED_COPPER_LADDER, new Waxable(MLBlocks.WAXED_OXIDIZED_COPPER_LADDER));
    }

    private <T> void add(Builder<T, Block> b, Block before, T value) {
        b.add(BuiltInRegistries.BLOCK.getKey(before), value, false);
    }
}
