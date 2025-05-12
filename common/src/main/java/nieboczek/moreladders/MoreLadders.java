package nieboczek.moreladders;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreLadders {
    public static final String MOD_ID = "moreladders";
    public static final String MOD_NAME = "MoreLadders";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {}

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
}