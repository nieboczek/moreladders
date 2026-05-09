package nieboczek.moreladders.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import nieboczek.moreladders.MoreLadders;

public final class MLBlockTags {
    public static final TagKey<Block> LADDERS = blockTag("ladders");

    private MLBlockTags() {
    }

    private static TagKey<Block> blockTag(String key) {
        return TagKey.create(Registries.BLOCK, MoreLadders.id(key));
    }
}
