import java.util.EmptyStackException;

/**
 * Created by sycai on 1/7/2017.
 */
public class MinStack {
    private class Node {
        public int data;
        public int minSoFar;
        public Node next;

        public Node(int value, int minimum) {
            data = value;
            minSoFar = minimum;
        }
    }

    private Node head;

    public MinStack() {
        head = null;
    }

    public void push(int val) {
        int min;
        if (head == null)   min = val;
        else                min = (val < head.minSoFar ? val : head.minSoFar);
        Node newHead = new Node(val, min);
        newHead.next = head;
        head = newHead;
    }

    public int pop() throws EmptyStackException {
        if (head == null) {
            throw new EmptyStackException();
        } else {
            int data = head.data;
            head = head.next;
            return data;
        }
    }

    public int getMin() throws EmptyStackException {
        if (head == null) {
            throw new EmptyStackException();
        } else {
            return head.minSoFar;
        }
    }

    @Override
    public String toString() {
        Node ptr = head;
        StringBuilder sb = new StringBuilder();
        if (ptr != null) {
            while (ptr.next != null) {
                sb.append(ptr.data + " -> ");
                ptr = ptr.next;
            }
            sb.append(ptr.data);
        }
        return sb.toString();
    }
}
