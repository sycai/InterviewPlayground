package Hard;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by sycai on 7/11/2017.
 * CCI 17.9 Design an algorithm to find the kth number such that the only prime factors are 3, 5 and 7. Note that 3, 5,
 * and 7 do not have to be factors, but it should not have any other prime factors. For example, the first several
 * multiples would be (in order) 1, 3, 5, 7, 9, 15, 21
 */
public class KthMultiple {
    public static List<Integer> firstKNum(int k) {
        Queue<Integer> Q3 = new LinkedList<>();
        Queue<Integer> Q5 = new LinkedList<>();
        Queue<Integer> Q7 = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        int min = 1;
        res.add(1);
        while (res.size() < k) {
            addToQueue(Q3, Q5, Q7, min);
            min = getMinOf(Q3, Q5, Q7);
            if (res.getLast() != min )   res.add(min);
        }
        return res;
    }

    private static void addToQueue(Queue<Integer> q3, Queue<Integer> q5, Queue<Integer> q7, int base) {
        q3.add(base * 3);
        q5.add(base * 5);
        q7.add(base * 7);
    }

    private static int getMinOf(Queue<Integer> q3, Queue<Integer> q5, Queue<Integer> q7) {
        final int min3 = q3.peek();
        final int min5 = q5.peek();
        final int min7 = q7.peek();

        int minOfAll = Math.min(min3, Math.min(min5, min7));
        if (minOfAll == min3)       return q3.poll();
        else if (minOfAll == min5)  return q5.poll();
        else                        return q7.poll();
    }

    public static void main(String[] args) {
        System.out.println(firstKNum(10));
    }
}
