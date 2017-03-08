package WaterBucket;

/**
 * Created by sycai on 2/21/2017.
 */
public class Bucket {
    private final int capacity;
    private int size;

    public Bucket(int c, int s) {
        capacity = c;
        size = s;
    }

    public Bucket(Bucket b) {
        capacity = b.capacity;
        size = b.size;
    }

    public void pourTo(Bucket b) {
        while (b.size < b.capacity && this.size > 0) {
            size--;
            b.size++;
        }
    }

    public String toString() {
        return size + "/" + capacity;
    }

    @Override
    public int hashCode() {
        return capacity * 31 + size;
    }

    public boolean equals(Bucket that) {
        return (this.size == that.size) && (this.capacity == that.capacity);
    }
}
