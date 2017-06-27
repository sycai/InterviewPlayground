package Moderate;

/**
 * Created by sycai on 6/26/2017.
 * CCI 16.18 You are given two strings, pattern and value. The pattern string consists of just the letters a and b,
 * describing a pattern within a string. For example, the string catcatgocatgo matches the pattern aabab (where cat is
 * a and go is b). It also matches patterns like a, ab, and b. Write a method to determine if value matches pattern.
 */
public class PatternMatching {
    public static boolean matches(String pattern, String value) {
        return matches(pattern, value, "", "");
    }

    // Here I am making the assumption that empty string is not a pattern
    private static boolean matches(String remainPattern, String remainValue, String a, String b) {
        if (remainValue.isEmpty() && remainPattern.isEmpty())   return true;
        if (!remainValue.isEmpty() && remainPattern.isEmpty())  return false;
        if (remainValue.isEmpty() && !remainPattern.isEmpty())  return false;

        char thisPattern = remainPattern.charAt(0);
        if (thisPattern == 'a') {
            if (!a.isEmpty())   {
                if (remainValue.startsWith(a))
                    return matches(remainPattern.substring(1), remainValue.substring(a.length()), a, b);
            } else {
                for (int i = 1; i <= remainValue.length(); i++) {
                    boolean result = matches(remainPattern.substring(1),
                            remainValue.substring(i),
                            remainValue.substring(0,i),
                            b);
                    if (result == true) return true;
                }
            }
        }

        if (thisPattern == 'b') {
            if (!b.isEmpty())   {
                if (remainValue.startsWith(b))
                    return matches(remainPattern.substring(1), remainValue.substring(a.length()), a, b);
            } else {
                for (int i = 1; i <= remainValue.length(); i++) {
                    boolean result = matches(remainPattern.substring(1),
                            remainValue.substring(i),
                            a,
                            remainValue.substring(0,i));
                    if (result == true) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String pattern = "aba";
        String value = "b";
        System.out.println(matches(pattern, value));
    }
}
