package nieboczek.moreladders;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MoreLadders {
    public static final String MOD_ID = "moreladders";
    public static final String MOD_NAME = "More Ladders";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final Component RESOURCE_PACK_DISPLAY_NAME = Component.translatable("resourcePack.moreladders.3d_ladders");

    private MoreLadders() {
    }

    public static Identifier id(String id) {
        return Identifier.fromNamespaceAndPath(MOD_ID, id);
    }
}
