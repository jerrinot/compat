import javax.cache.configuration.FactoryBuilder;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import java.io.Serializable;

public final class MyEntryListenerFactory extends FactoryBuilder.SingletonFactory<CacheEntryCreatedListener<Integer, Integer>> {
    private static final MyListener INSTANCE = new MyListener();

    public MyEntryListenerFactory() {
        super(INSTANCE);
    }

    private static class MyListener implements CacheEntryCreatedListener<Integer, Integer>, Serializable {
        public void onCreated(Iterable<CacheEntryEvent<? extends Integer, ? extends Integer>> cacheEntryEvents) throws CacheEntryListenerException {
            onEvent(cacheEntryEvents);
        }

        private void onEvent(Iterable<CacheEntryEvent<? extends Integer, ? extends Integer>> cacheEntryEvents) {
            for (CacheEntryEvent<? extends Integer, ? extends Integer> event : cacheEntryEvents) {
                System.out.println(event);
            }
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof MyListener;
        }

        @Override
        public int hashCode() {
            return MyListener.class.hashCode();
        }
    }
}
