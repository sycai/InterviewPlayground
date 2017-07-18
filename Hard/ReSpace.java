package Hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sycai on 7/17/2017.
 * CCI 17.13 Oh, no! You have accidentally removed all spaces, punctuation, and capitalization in a lengthy document. A
 * sentence like "I reset the computer. It still didn't boot!" became "iresetthecomputeritstilldidntboot". You'll deal
 * with the punctuation and capitalization later; right now you need to re-insert the spaces. Most of the words are in
 * a dictionary but a few are not; Given a dictionary (a list of strings) and the document (a string), design an
 * algorithm to unconcatenate the document in a way that minimizes the number of unrecognized characters.
 *
 * EXAMPLE:
 * Input: jesslookedjustliketimherbrother
 * Output: (jess) looked just like (tim) her brother (7 unrecognized characters)
 */
public class ReSpace {
    public static String restore(String[] dict, String doc) {
        List<String> words = new LinkedList<>();
        int prevIdx = 0;
        for (int currIdx = 1; currIdx <= doc.length(); currIdx++) {
            String str = doc.substring(prevIdx, currIdx);
            if (exist(dict, str)) {
                words.add(str);
                prevIdx = currIdx;
            } else {
                for (int j = currIdx - 1; j >= prevIdx; j--) {
                    str = doc.substring(j, currIdx);
                    if (exist(dict, str)) {
                        words.add(String.format("(%s)", doc.substring(prevIdx, j)));
                        words.add(str);
                        prevIdx = currIdx;
                    }
                }
            }
        }

        if (prevIdx != doc.length()) {
            words.add(String.format("(%s)", doc.substring(prevIdx, doc.length())));
        }

        return words.stream().collect(Collectors.joining(" "));
    }

    private static boolean exist(String[] dict, String str) {
        return Arrays.stream(dict).anyMatch(x -> x.equals(str));
    }

    public static void main(String[] args){
        String[] dict = {"looked", "just", "like", "her", "brother"};
        String doc = "jesslookedjustliketimherbrother";
        System.out.println(restore(dict, doc));
    }
}
