package nieboczek.moreladders;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public final class MoreLadders {
    public static final String MOD_ID = "moreladders";
    public static final String MOD_NAME = "MoreLadders";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final PlatformHelper PLATFORM = load(PlatformHelper.class);
    public static final Component RESOURCE_PACK_DISPLAY_NAME = Component.translatable("resourcePack.moreladders.3d_ladders");

    // TODO: implement pale oak backport support?
    public static boolean shouldShowPaleOakLadder() {
        return false;
    }

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}
