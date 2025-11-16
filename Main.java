
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1
        ClassicIOCacheWithLimit cache = new ClassicIOCacheWithLimit(2);

        System.err.println("1--------------------------------------------------------------------------------------------------");
        String a1 = cache.readFile("test1.txt");
        System.err.println("    test1.txt is read");
        if (cache.isCached("test1.txt")) {
            System.err.println("    tets1.txt is cached");
        }

        String a2 = cache.readFile("test2.txt");
        System.err.println("    test2.txt is read");
        if (cache.isCached("test2.txt")) {
            System.err.println("    test2.txt is cached");
        }

        cache.printCacheStats();

        System.err.println("2--------------------------------------------------------------------------------------------------");
        cache.readFile("test1.txt");
        System.err.println("    test1.txt is read");
        cache.printCacheStats();

        System.err.println("3--------------------------------------------------------------------------------------------------");
        cache.readFile("test3.txt");    // один файл должен убраться
        System.err.println("    test3.txt is read");
        if (cache.isCached("test3.txt")) {
            System.err.println("    test3.txt is cached");
        }
        cache.printCacheStats();

        System.err.println("4--------------------------------------------------------------------------------------------------");
        long start = System.nanoTime(); // наносекунды, потому что в мс буквально 0
        cache.readFile("test1.txt");
        long end = System.nanoTime();
        System.err.println("Cache time test1.txt:   " + (end-start) + " ns");

        cache.invalidateAll();  // чтоб точно читали с диска
        start = System.nanoTime();
        cache.readFile("test1.txt");
        end = System.nanoTime();
        System.err.println("Disc time test1.txt:    " + (end-start) + " ns");

        System.err.println("5--------------------------------------------------------------------------------------------------");
        cache.invalidateAll();
        cache.printCacheStats();
    }
}