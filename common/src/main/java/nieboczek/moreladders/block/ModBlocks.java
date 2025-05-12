package nieboczek.moreladders.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import nieboczek.moreladders.MoreLadders;
import nieboczek.moreladders.platform.Services;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModBlocks {
    public static final Map<ResourceLocation, Block> BLOCKS = new LinkedHashMap<>();

    public static Block SPRUCE_LADDER;
    public static Block BIRCH_LADDER;
    public static Block DARK_OAK_LADDER;
    public static Block JUNGLE_LADDER;
    public static Block WARPED_LADDER;
    public static Block CRIMSON_LADDER;
    public static Block IRON_LADDER;
    public static Block ACACIA_LADDER;
    public static Block CHAIN_LADDER;
    public static Block CHERRY_LADDER;
    public static Block BAMBOO_LADDER;
    public static Block MANGROVE_LADDER;
    public static Block GOLD_LADDER;
    public static Block PALE_OAK_LADDER;
    public static Block COPPER_LADDER;
    public static Block EXPOSED_COPPER_LADDER;
    public static Block WEATHERED_COPPER_LADDER;
    public static Block OXIDIZED_COPPER_LADDER;
    public static Block WAXED_COPPER_LADDER;
    public static Block WAXED_EXPOSED_COPPER_LADDER;
    public static Block WAXED_WEATHERED_COPPER_LADDER;
    public static Block WAXED_OXIDIZED_COPPER_LADDER;

    private static Block addNormal(String id, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, MoreLadders.id(id));
        Block block = Services.PLATFORM.newLadderBlock(properties.setId(key));
        BLOCKS.put(MoreLadders.id(id), block);
        return block;
    }

    private static Block addOxidizable(String id, BlockBehaviour.Properties properties, WeatheringCopper.WeatherState state) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, MoreLadders.id(id));
        Block block = new OxidizableLadder(properties.setId(key), state);
        BLOCKS.put(MoreLadders.id(id), block);
        return block;
    }

    private static BlockBehaviour.Properties ladderProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER);
    }

    public static void createBlocks() {
        SPRUCE_LADDER = addNormal("spruce_ladder", ladderProperties().sound(SoundType.LADDER));
        BIRCH_LADDER = addNormal("birch_ladder", ladderProperties().sound(SoundType.LADDER));
        DARK_OAK_LADDER = addNormal("dark_oak_ladder", ladderProperties().sound(SoundType.LADDER));
        JUNGLE_LADDER = addNormal("jungle_ladder", ladderProperties().sound(SoundType.LADDER));
        WARPED_LADDER = addNormal("warped_ladder", ladderProperties().sound(SoundType.LADDER));
        CRIMSON_LADDER = addNormal("crimson_ladder", ladderProperties().sound(SoundType.LADDER));
        IRON_LADDER = addNormal("iron_ladder", ladderProperties().sound(SoundType.METAL));
        ACACIA_LADDER = addNormal("acacia_ladder", ladderProperties().sound(SoundType.LADDER));
        CHAIN_LADDER = addNormal("chain_ladder", ladderProperties().sound(SoundType.CHAIN));
        CHERRY_LADDER = addNormal("cherry_ladder", ladderProperties().sound(SoundType.LADDER));
        BAMBOO_LADDER = addNormal("bamboo_ladder", ladderProperties().sound(SoundType.BAMBOO));
        MANGROVE_LADDER = addNormal("mangrove_ladder", ladderProperties().sound(SoundType.LADDER));
        GOLD_LADDER = addNormal("gold_ladder", ladderProperties().sound(SoundType.METAL));
        PALE_OAK_LADDER = addNormal("pale_oak_ladder", ladderProperties());
        COPPER_LADDER = addOxidizable("copper_ladder", ladderProperties().randomTicks(), WeatheringCopper.WeatherState.UNAFFECTED);
        EXPOSED_COPPER_LADDER = addOxidizable("exposed_copper_ladder", ladderProperties().randomTicks(), WeatheringCopper.WeatherState.EXPOSED);
        WEATHERED_COPPER_LADDER = addOxidizable("weathered_copper_ladder", ladderProperties().randomTicks(), WeatheringCopper.WeatherState.WEATHERED);
        OXIDIZED_COPPER_LADDER = addOxidizable("oxidized_copper_ladder", ladderProperties(), WeatheringCopper.WeatherState.OXIDIZED);
        WAXED_COPPER_LADDER = addOxidizable("waxed_copper_ladder", ladderProperties(), WeatheringCopper.WeatherState.UNAFFECTED);
        WAXED_EXPOSED_COPPER_LADDER = addOxidizable("waxed_exposed_copper_ladder", ladderProperties(), WeatheringCopper.WeatherState.EXPOSED);
        WAXED_WEATHERED_COPPER_LADDER = addOxidizable("waxed_weathered_copper_ladder", ladderProperties(), WeatheringCopper.WeatherState.WEATHERED);
        WAXED_OXIDIZED_COPPER_LADDER = addOxidizable("waxed_oxidized_copper_ladder", ladderProperties(), WeatheringCopper.WeatherState.OXIDIZED);
    }
}
