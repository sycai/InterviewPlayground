package RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by sycai on 5/14/2017.
 * CCI 8.9 Implement an algorithm to print all valid (e.g., properly opened and closed) combinations of n pairs of
 * parentheses.
 */
public class Parens {
    public static HashSet<String> parens(int n) {
        // This is not an efficient algorithm since it produces duplicates.
        HashSet<String> res = new HashSet<>();
        if (n == 0) {
            res.add("");
        } else if (n >= 1) {
            for (String sub : parens(n-1)) {
                res.addAll(Arrays.asList("()" + sub, "(" + sub + ")", sub + "()"));
            }
        }
        return res;
    }

    // This more efficient algorithm is provided in the book
    public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        // IMPORTANT!
        // In the substring str[0..i], the number of left parentheses is large than or equal to the number of
        // right parentheses. This claim holds for all 0 <= i < str.length.
        if (leftRem < 0 || rightRem < leftRem)  return;

        if (leftRem == 0 && rightRem == 0) {
            list.add(String.copyValueOf(str));
        } else {
            str[index] = '('; // Add left and recurse
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')'; // Add right and recurse
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }

    public static ArrayList<String> generateParens(int count) {
        char[] str = new char[count* 2];
        ArrayList<String> list = new ArrayList<>();
        addParen(list, count,count,str,0);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(generateParens(4));
    }
}
