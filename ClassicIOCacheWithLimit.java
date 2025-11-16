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
    // Методы
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
            return cached.content;
        }
        else {
            return updateCache(file, absPath, curModifiredTime);
        }
    }

    // вспомогательные методы
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
            //
        }

        return content;
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

    // методы управления кешем
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
}