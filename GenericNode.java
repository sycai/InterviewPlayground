import java.util.ArrayList;

/**
 * Created by sycai on 1/16/2017.
 */
public class GenericNode<T> {
    private ArrayList<T> list;

    public GenericNode (T val) {
        list = new ArrayList<T>();
        for (int i = 0; i < 3; i++) {
            list.add(val);
        }
    }

    public void print () {
        for (T e : list) {
            System.out.print(e.toString() + ' ');
        }
    }
}
