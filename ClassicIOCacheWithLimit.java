import java.util.Map;

public class ClassicIOCacheWithLimit {
    private Map<String, FileCacheEntry> cache;
    private int maxSize;

    public static class FileCacheEntry {
        String content;
        long lastReadTime;
        long lastModifiredTimeRead;

        public FileCacheEntry(String content, long lastReadTime, long lastModifiredTimeRead) {
            this.content = content;
            this.lastReadTime = lastReadTime;
            this.lastModifiredTimeRead = lastModifiredTimeRead;
        }
    }
}