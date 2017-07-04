package Hard;

/**
 * Created by sycai on 7/4/2017.
 * CCI 17.1 Write a function that adds two numbers. You should not use + or any arithmetic operators.
 * (Arithmetic operators are: +, - , *, / and %)
 */
public class AddWithoutPlus {
    public static int add(int a, int b) {
        if (b == 0) return a;
        int partialSum = a ^ b;
        int carry = (a & b) << 1;
        return add(partialSum, carry);
    }

    public static void main(String[] args) {
        System.out.println(AddWithoutPlus.add(0,0));
    }
}
