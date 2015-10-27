import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class Main {
    public static void main(String[] args) {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        String name = "cache";
        Configuration<Integer, Integer> config = new MutableConfiguration<Integer, Integer>();
        Cache<Integer, Integer> cache = cacheManager.createCache(name, config);

        MyEntryListenerFactory listenerFactory = new MyEntryListenerFactory();
        CacheEntryListenerConfiguration<Integer, Integer> listenerConfig = new MutableCacheEntryListenerConfiguration<Integer, Integer>(listenerFactory, null, true, true);
        cache.registerCacheEntryListener(listenerConfig);

        cache.put(1, 1);
    }
}
