import java.util.LinkedList;

public class Cache<T> {
    private int n;
    public LinkedList<T> cache;

    public Cache(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be > 0");
        }

        this.n = n;
        this.cache = new LinkedList<>();
    }
    
    public void add(T item) {
        if (cache.size() >= n) {
            cache.removeFirst();   // удаление самого старого элемента
        }

        cache.addLast(item);  // добавление нового элемента
    }

    public boolean remove(T item) {
        return cache.remove(item);
    }

    public boolean exist(T item) {
        return cache.contains(item);
    }

    public T getFisrt() {
        return cache.isEmpty() ? null : cache.getFirst();
    }

    public T getLast() {
        return cache.isEmpty() ? null : cache.getLast();
    }

    public T getItemByIndex(int i) {
        return (i >= 0 && i < cache.size()) ? cache.get(i) : null;
    }
}