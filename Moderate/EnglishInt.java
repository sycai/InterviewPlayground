package Moderate;

/**
 * Created by sycai on 6/17/2017.
 * CCI 16.8 Given any integer, print an English phrase that describes the integer (e.g., "One Thousand, Two Hundred
 * Thirty Four").
 */

public class EnglishInt {
    public static String getEnglish(int n) {
        StringBuilder sb = new StringBuilder();
        if (n == 0)                 return "Zero";
        if (n < 0)                  sb.append("Minus ");

        int abs = Math.abs(n);
        String million = helper(abs / 1000000).trim();
        if (!million.isEmpty())     sb.append(million).append(" Million, ");

        String thousand = helper((abs % 1000000) / 1000).trim();
        if (!thousand.isEmpty())    sb.append(thousand).append(" Thousand, ");

        String remain = helper(abs % 1000).trim();
        if (!remain.isEmpty())      sb.append(remain);

        String res = sb.toString().trim();
        if (res.endsWith(","))  res = res.substring(0, res.length() - 1);
        return res;
    }

    private static String helper(int n) {
        String[] zeroToNineteen = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                                "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        StringBuilder sb = new StringBuilder();

        int hundred = n / 100;
        if (hundred != 0) sb.append(zeroToNineteen[hundred]).append(" Hundred");

        int lastTwoDigit = n % 100;
        if (lastTwoDigit < 20) {
            sb.append(" " + zeroToNineteen[lastTwoDigit]);
        }
        else {
            sb.append(" " + tens[lastTwoDigit/10]).append(" " + zeroToNineteen[lastTwoDigit%10]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getEnglish(1000014));
    }
}
