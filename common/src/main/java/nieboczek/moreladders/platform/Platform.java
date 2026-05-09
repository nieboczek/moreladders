package nieboczek.moreladders.platform;

import java.util.ServiceLoader;

public final class Platform {
    public static final IPlatform INSTANCE = load(IPlatform.class);

    private Platform() {}

    private static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}
