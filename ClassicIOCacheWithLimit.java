import java.awt.image.CropImageFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sun.jdi.AbsentInformationException;

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
}