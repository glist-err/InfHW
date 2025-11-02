import java.util.LinkedList;
import java.util.Queue; // для структур данных в виде очереди

public class Cache<T> {
    private int n;
    public Queue<T> cache;

    public Cache(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be > 0");
        }

        this.n = n;
        this.cache = new LinkedList<>();
    }
}