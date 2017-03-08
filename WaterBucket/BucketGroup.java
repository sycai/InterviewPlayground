package WaterBucket;

/**
 * Created by sycai on 2/21/2017.
 */
public class BucketGroup {
    private Bucket[] B;

    public BucketGroup(int size) {
        B = new Bucket[size];
        for (int i = 0 ; i < size; i++) {
            B[i] = new Bucket(0,0);
        }
    }

    public BucketGroup(BucketGroup bg) {
        B = new Bucket[bg.getSize()];
        for (int i = 0 ; i < bg.getSize(); i++) {
            B[i] = new Bucket(bg.get(i));
        }
    }

    public void set (int i, int c, int s) {
        B[i] = new Bucket(c,s);
    }

    public void pourTo(int i, int j) {
        B[i].pourTo(B[j]);
    }

    public Bucket get(int i) {
        return B[i];
    }

    public int getSize() {
        return B.length;
    }

    @Override
    public int hashCode() {
        int res = 1;
        for (Bucket b : B) {
            res = res * 37 + b.hashCode();
        }
        return res;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Bucket b : B) {
            res.append(b.toString() + " ");
        }
        return res.toString();
    }

    public boolean equals(BucketGroup that) {
        if (this.B.length == that.B.length) {
            for (int i = 0; i < B.length; i++) {
                if (!this.B[i].equals(that.B[i])) return false;
            }
            return true;
        }
        return false;
    }
}
