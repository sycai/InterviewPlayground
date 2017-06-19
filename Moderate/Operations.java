package Moderate;

/**
 * Created by sycai on 6/18/2017.
 * CCI 16.9 Write methods to implement the multiply, subtract, and divide operations for integers. The result of all of
 * these are integers. Use only the add operator.
 */
public class Operations {
    public static int minus(int a, int b) {
        return a + negate(b);
    }

    public static int times(int a, int b) {
        int res = 0;
        boolean flip = false;
        if (b < 0) {
            flip = true;
            b = negate(b);
        }
        for (int i = 1; i <= b; i++) {
            res += a;
        }

        if (flip)   res = negate(res);
        return res;
    }

    public static int divide(int a, int b) {
        int res = 0;
        boolean flip = false;

        if (a < 0) {
            flip = !flip;
            a = negate(a);
        }

        if (b < 0) {
            flip = !flip;
            b = negate(b);
        }

        while (a > 0) {
            a = minus(a, b);
            res++;
        }

        if (flip)   res = negate(res);
        return res;
    }

    private static int negate(int n) {
        return ~n + 1;
    }

    public static void main(String[] args) {
       System.out.println(Operations.minus(5,7));
       System.out.println(Operations.times(5,-7));
       System.out.println(Operations.divide(-4,-2));
    }
}
