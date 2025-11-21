package nieboczek.moreladders.block;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import nieboczek.moreladders.MoreLadders;

import java.util.HashMap;

public final class MLBlocks {
    public static final HashMap<ResourceLocation, Block> BLOCKS = new HashMap<>();

    public static final Block SPRUCE_LADDER = ladder("spruce_ladder", ladderProps());
    public static final Block BIRCH_LADDER = ladder("birch_ladder", ladderProps());
    public static final Block DARK_OAK_LADDER = ladder("dark_oak_ladder", ladderProps());
    public static final Block JUNGLE_LADDER = ladder("jungle_ladder", ladderProps());
    public static final Block WARPED_LADDER = ladder("warped_ladder", ladderProps());
    public static final Block CRIMSON_LADDER = ladder("crimson_ladder", ladderProps());
    public static final Block IRON_LADDER = ladder("iron_ladder", ladderProps().sound(SoundType.METAL).requiresCorrectToolForDrops());
    public static final Block ACACIA_LADDER = ladder("acacia_ladder", ladderProps());
    public static final Block CHAIN_LADDER = ladder("chain_ladder", ladderProps().sound(SoundType.CHAIN).requiresCorrectToolForDrops());
    public static final Block CHERRY_LADDER = ladder("cherry_ladder", ladderProps());
    public static final Block BAMBOO_LADDER = ladder("bamboo_ladder", ladderProps().sound(SoundType.BAMBOO));
    public static final Block MANGROVE_LADDER = ladder("mangrove_ladder", ladderProps());
    public static final Block GOLD_LADDER = ladder("gold_ladder", ladderProps().sound(SoundType.METAL).requiresCorrectToolForDrops());
    public static final Block PALE_OAK_LADDER = ladder("pale_oak_ladder", ladderProps());
    public static final Block COPPER_LADDER = oxidizableLadder("copper_ladder", ladderProps().randomTicks(), WeatheringCopper.WeatherState.UNAFFECTED);
    public static final Block EXPOSED_COPPER_LADDER = oxidizableLadder("exposed_copper_ladder", ladderProps().randomTicks(), WeatheringCopper.WeatherState.EXPOSED);
    public static final Block WEATHERED_COPPER_LADDER = oxidizableLadder("weathered_copper_ladder", ladderProps().randomTicks(), WeatheringCopper.WeatherState.WEATHERED);
    public static final Block OXIDIZED_COPPER_LADDER = oxidizableLadder("oxidized_copper_ladder", ladderProps(), WeatheringCopper.WeatherState.OXIDIZED);
    public static final Block WAXED_COPPER_LADDER = oxidizableLadder("waxed_copper_ladder", ladderProps(), WeatheringCopper.WeatherState.UNAFFECTED);
    public static final Block WAXED_EXPOSED_COPPER_LADDER = oxidizableLadder("waxed_exposed_copper_ladder", ladderProps(), WeatheringCopper.WeatherState.EXPOSED);
    public static final Block WAXED_WEATHERED_COPPER_LADDER = oxidizableLadder("waxed_weathered_copper_ladder", ladderProps(), WeatheringCopper.WeatherState.WEATHERED);
    public static final Block WAXED_OXIDIZED_COPPER_LADDER = oxidizableLadder("waxed_oxidized_copper_ladder", ladderProps(), WeatheringCopper.WeatherState.OXIDIZED);

    private static Block ladder(String id, BlockBehaviour.Properties properties) {
        final Block block = MoreLadders.PLATFORM.newLadderBlock(properties);
        BLOCKS.put(MoreLadders.id(id), block);
        return block;
    }

    private static Block oxidizableLadder(String id, BlockBehaviour.Properties properties, WeatheringCopper.WeatherState state) {
        Block block = new OxidizableLadder(properties.sound(SoundType.COPPER).requiresCorrectToolForDrops(), state);
        BLOCKS.put(MoreLadders.id(id), block);
        return block;
    }

    @SuppressWarnings("deprecation")
    private static BlockBehaviour.Properties ladderProps() {
        return BlockBehaviour.Properties.of().forceSolidOff().strength(0.4f).sound(SoundType.LADDER).noOcclusion().pushReaction(PushReaction.DESTROY);
    }
}
