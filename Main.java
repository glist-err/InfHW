public class Main {
    public static void main(String[] args) {
        // Integer
        System.err.println("Integer");

        Cache<Integer> intCache = new Cache<>(3);   // size - 3
        System.out.println("Cache - " + intCache);

        intCache.add(10);
        intCache.add(20);
        intCache.add(30);

        System.err.println("First - " + intCache.getFisrt());
        System.err.println("Second - " + intCache.getItemByIndex(1));
        System.err.println("Last - " + intCache.getLast());

        intCache.add(40);   // Переполнение
        System.err.println("After add(40) - " + intCache.getFisrt());

        System.err.println("Remove 20 - " + intCache.remove(20));

        System.err.println("30 exists - " + intCache.exist(30));

        System.out.println("Final Cache - " + intCache);
    }
}