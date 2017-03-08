import java.util.EmptyStackException;

/**
 * Created by sycai on 1/10/2017.
 */
public class Stack {
    private class Node {
        int val;
        Node next;

        public Node(int v) {
            val = v;
            next = null;
        }
    }

    private Node head;
    private int count;

    public Stack() {
        head = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void push(int v) {
        Node newNode = new Node(v);
        newNode.next = head;
        head = newNode;
        count++;
    }

    public int peek() throws EmptyStackException {
        if (isEmpty())   throw new EmptyStackException();
        else                return head.val;
    }

    public int pop() throws EmptyStackException {
        if (isEmpty())   throw new EmptyStackException();
        else {
            int res = head.val;
            head = head.next;
            count--;
            return res;
        }
    }

    @Override
    public String toString() {
        Node ptr = head;
        StringBuilder sb = new StringBuilder();
        if (ptr != null) {
            while (ptr.next != null) {
                sb.append(ptr.val + " -> ");
                ptr = ptr.next;
            }
            sb.append(ptr.val);
        }
        return sb.toString();
    }
}
