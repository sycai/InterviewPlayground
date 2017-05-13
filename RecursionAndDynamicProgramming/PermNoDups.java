package RecursionAndDynamicProgramming;


import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by sycai on 5/13/2017.
 * CCI 8.7 Write a method to compute all permutations of a string of unique characters
 */

public class PermNoDups {
    public static ArrayList<String> getPerm(String s) {
        ArrayList<Pair> pairs = getPerm(new Pair(s, ""));
        return (ArrayList<String>) pairs.stream().map(p -> p.suffix).collect(Collectors.toList());
    }

    private static ArrayList<Pair> getPerm(Pair p) {
        ArrayList<Pair> res = new ArrayList<>();
        if (p.prefix.isEmpty()) {
            res.add(p);
        } else {
            for (int i = 0; i < p.prefix.length(); i++) {
                StringBuilder sb = new StringBuilder(p.prefix);
                Pair np = new Pair(p);
                np.suffix = np.suffix + sb.charAt(i);
                np.prefix = sb.deleteCharAt(i).toString();
                res.addAll(getPerm(np));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getPerm("abc"));
    }
}

class Pair{
    public String prefix;
    public String suffix;

    public Pair(String p, String s) {
        prefix = p;
        suffix = s;
    }

    public Pair(Pair other) {
        prefix = other.prefix;
        suffix = other.suffix;
    }
}
