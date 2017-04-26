package BitManipulation;

/**
 * Created by sycai on 4/25/2017.
 * CCI 5.8 A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored
 * in one byte. The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows). The
 * height of the screen, of course, can be derived from the length of the array and the width. Implement a function
 * that draws a horizontal line from (x1, y) to (x2, y).
 *
 * The method signature should look something like:
 * drawLine(byte[] screen, int width, int x1, int x2, int y)
 */
public class DrawLine {

    public static void main(String[] args) {
        byte[] screen1 = new byte[10];
        byte[] screen2 = new byte[10];
        int width = 40, x1 = 0, x2 = 23, y =0;
        drawLine(screen1,width,x1,x2,y);
        drawLine2(screen2,width,x1,x2,y);
        printArray(screen1);
        printArray(screen2);
    }

    public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int bytePerLine = width / 8;
        int lineBegin = y * bytePerLine;

        int byteIdxStart = x1/8 + lineBegin;
        int byteIdxEnd = x2/8 + lineBegin;

        byte firstByte = (byte) (0xFF >> (x1 % 8));
        byte lastByte = (byte) ~(0xFF >> ((x2 + 1) % 8)); // important!

        // Special case: when x2 = 8n + 7, lastByte represents the byte after the ACTUAL last byte.
        // We know that in such case the last byte should be 11111111. We correct the off-by-one error using the
        // following lines.
        if (lastByte == 0) {
            lastByte = (byte) 0xFF;
        }

        if (byteIdxStart == byteIdxEnd) {
            screen[byteIdxStart] = (byte) (firstByte & lastByte);
        } else {
            screen[byteIdxStart] = firstByte;
            screen[byteIdxEnd] = lastByte;
            int i  = byteIdxStart + 1;
            while (i < byteIdxEnd) {
                screen[i] = (byte) 0xFF;
                i++;
            }
        }
    }

    public static void drawLine2(byte[] screen, int width, int x1, int x2, int y) {
        // This method is provided in the book
        int start_offset = x1 % 8;
        int first_full_byte = x1 / 8;
        if (start_offset != 0) {
            first_full_byte++;
        }

        int end_offset = x2 % 8;
        int last_full_byte = x2 / 8;
        if (end_offset != 7) {
            last_full_byte--;
        }

        // Set full bytes
        for (int b = first_full_byte; b <= last_full_byte; b++) {
            screen[(width / 8) * y + b] = (byte) 0xFF;
        }

        // Create masks fro start and end of line
        byte start_mask = (byte) (0xFF >> start_offset);
        byte end_mask = (byte) ~(0xFF >> (end_offset + 1));

        // Set start and end of line
        if((x1 / 8) == (x2 / 8)) { // x1 and x2 are in the same byte
            byte mask = (byte) (start_mask & end_mask);
            screen[(width / 8) * y + (x1 / 8)] |= mask;
        } else {
            if (start_offset != 0) {
                int byte_number = (width / 8) * y + first_full_byte - 1;
                screen[byte_number] |= start_mask;
            }
            if (end_offset != 7) {
                int byte_number = (width / 8) * y + last_full_byte + 1;
                screen[byte_number] |= end_mask;
            }
        }
    }

    private static void printArray(byte[] screen) {
        for (int i = 0; i < screen.length; i++) {
            System.out.print(screen[i] + "\t");
        }
        System.out.println();
    }
}
