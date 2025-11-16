import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClassicIOCacheWithLimit {
    private Map<String, FileCacheEntry> cache;
    private int maxSize;

    // внутренний класс
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

    // Конструкторы
    public ClassicIOCacheWithLimit(int maxSize) {
        this.cache = new HashMap<>();
        this.maxSize = maxSize;
    }

    public ClassicIOCacheWithLimit() {
        this.maxSize = 100;
        this.cache = new HashMap<>();
        //this.cache = new ClassicIOCacheWithLimit(maxSize);
    }
    //                         Методы
    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("Нет файла " + filePath);
        }

        // путь и время файла
        String absPath = file.getAbsolutePath();
        long curModifiredTime = file.lastModified();
        // есть ли файл в кеше
        FileCacheEntry cached = cache.get(absPath);
        
        if (cached != null && isCacheValid(cached, curModifiredTime)) {
            cached.lastReadTime = System.currentTimeMillis();
            return cached.content;
        }
        else {
            return updateCache(file, absPath, curModifiredTime);
        }
    }

    //                      вспомогательные методы
    private boolean isCacheValid(FileCacheEntry CachedEntry, long curModifiredTime) {
        return CachedEntry.lastModifiredTimeRead == curModifiredTime;
    }

    private String updateCache(File file, String absolutePath, long curModifiredTime) throws IOException {
        String content = readFileContent(file);

        // Новая запись в кеше
        FileCacheEntry newEntry = new FileCacheEntry(content, System.currentTimeMillis(), curModifiredTime);
        cache.put(absolutePath, newEntry);

        // не превышает ли размер кеша
        if (cache.size() > maxSize) {
            removeOldest();
        }

        return content;
    }
    // для updateCache()
    private void removeOldest() {
        String oldest = null;
        long oldTime = Long.MAX_VALUE;

        for (var entry : cache.entrySet()) {
            if (entry.getValue().lastReadTime < oldTime) {
                oldTime = entry.getValue().lastReadTime;
                oldest = entry.getKey();
            }
        }

        if (oldest != null) {
            cache.remove(oldest);
        }
    }

    private String readFileContent(File file) throws IOException {
        StringBuilder content = new StringBuilder();

        // FileReader и BufferedReader
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        return content.toString();
    }

    //                      методы управления кешем
    public void invalidate(String filePath) {
        String file = new File(filePath).getAbsolutePath();
        cache.remove(file);
    }

    public void invalidateAll() {
        cache.clear();
    }

    public boolean isCached(String filePath) {
        String file = new File(filePath).getAbsolutePath();

        return cache.containsKey(file);
    }

    public int getCachedFilesCount() {
        return cache.size();
    }

    //                  Методы статистики
    public long getCacheSizeInMemory() {
        long total = 0;

        for (FileCacheEntry entry : cache.values()) {
            if (entry.content != null) {
                total += entry.content.length() * (long)2;
            }
        }

        return total;
    }

    public void printCacheStats() {
        System.out.println("Cache stats:\n");

        System.err.println("    Files:          " + cache.size() + "\n");
        System.err.println("    Max size:       " + maxSize + "\n");
        System.err.println("    Mem usage:      " + getCacheSizeInMemory() + "\n");

        System.err.println("Files:\n");
        for (Map.Entry<String, FileCacheEntry> entry : cache.entrySet()) {
            String path = entry.getKey();
            FileCacheEntry data = entry.getValue();
            long sizeInMem = (data.content != null ? data.content.length() * (long)2 : 0);

            System.err.println("    - " + path + "\n");
            System.err.println("        Size: " + sizeInMem + "\n");
            System.err.println("        Last read: " + data.lastReadTime + "\n");
            System.err.println("        Last modifired: " + data.lastModifiredTimeRead + "\n");
        }
    }
}