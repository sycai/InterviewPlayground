package Hard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sycai on 7/15/2017.
 * CCI 17.11 You have a large text file containing words. Given any two words, find the shortest distance (in terms of
 * number of words) between them in the file. If the operation will be repeated many times for the same file (but
 * different pairs of words), can you optimize your solution
 */
public class WordDistance {
    private HashMap<String, ArrayList<Integer>> wordToPos;

    public WordDistance(String article) {
        String[] words = article.split("\\s+");
        wordToPos = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String curr = words[i];
            if (!wordToPos.containsKey(curr)) {
                wordToPos.put(curr, new ArrayList<>());
            }
            wordToPos.get(curr).add(i);
        }
    }

    public int minDist(String w1, String w2) {
        if (!wordToPos.containsKey(w1)) {
            throw new IllegalArgumentException(w1 + " does not exist.");
        }

        if (!wordToPos.containsKey(w2)) {
            throw new IllegalArgumentException(w2 + " does not exist.");
        }

        ArrayList<Integer> list1 = wordToPos.get(w1);
        ArrayList<Integer> list2 = wordToPos.get(w2);

        int minDist = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < list1.size() && j < list2.size(); /* Empty*/ ) {
            int currDist = Math.abs(list1.get(i) - list2.get(j));
            minDist = Math.min(minDist,currDist);

            if (list1.get(i) < list2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return minDist;
    }

    public static void main(String[] args) {
        String text = "A B C A B B A D A C B";
        WordDistance wd = new WordDistance(text);
        System.out.println(wd.minDist("D", "B"));
    }
}
