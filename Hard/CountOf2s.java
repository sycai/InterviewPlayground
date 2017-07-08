package Hard;

/**
 * Created by sycai on 7/7/2017.
 * CCI 17.6 Write a method to count the number of 2s that appear in all the numbers between 0 and n (inclusive)
 *
 * EXAMPLE
 * Input: 25
 * Output: 9 (2, 12, 20, 21, 22 ,23 ,24 and 25. Note that 22 counts for two 2s.)
 */
public class CountOf2s {
    public static int count2AtDigit(int n, int d) {
        int powerOfTen = (int) Math.pow(10, d);
        int nexPowerOfTen = powerOfTen * 10;
        int currDigit = (n % nexPowerOfTen) / powerOfTen;

        if (currDigit < 2) {
            // Round down to the nearest 10^(d+1) and divide it by 10
            return (n / nexPowerOfTen * nexPowerOfTen) / 10;
        } else if (currDigit > 2) {
            // Round up to the nearest 10^(d+1) and divide it by 10
            return ((n / nexPowerOfTen + 1) * nexPowerOfTen) / 10;
        } else {
            // Round town to the nearest 10(d+1) and divide it by 10.
            // Then add the value to the right of this digit plus one.
            return ((n / nexPowerOfTen * nexPowerOfTen) / 10) + (n % powerOfTen + 1);
        }
    }

    public static int count(int n) {
        int count2 = 0;
        String num = String.valueOf(n);
        for (int i = 0; i < num.length(); i++) {
            count2 += count2AtDigit(n, i);
        }
        return count2;
    }

    public static void main(String[] args) {
        System.out.println("Value  Count2");
        System.out.println("=====  ======");
       for (int n = 200; n < 300; n++) {
           System.out.println(String.format("%02d:   %d", n, count(n)));
       }
    }
}
