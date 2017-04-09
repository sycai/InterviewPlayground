package BitManipulation;

/**
 * Created by sycai on 4/9/2017.
 * CCI 5.2 Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print the binary
 * representation. If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR".
 */
public class BinaryToString {
    public static void binaryToString(double num) {
        StringBuilder sb = new StringBuilder(".");
        while (sb.length() <= 33 && num != 0) {
            num *= 2;
            if (num >= 1) {
                sb.append(1);
                num -= 1;
            } else {
                sb.append(0);
            }
        }

        if (num == 0)   System.out.println(sb.toString());
        else            System.out.println("ERROR");
    }

    public static void main(String[] args) {
        binaryToString(0.25);
    }
}
