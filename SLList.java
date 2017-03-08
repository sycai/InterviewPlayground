/**
 * Created by sycai on 12/31/2016.
 */
public class SLList {
    private class Node {
        public int data;
        public Node next;

        public Node() {
            data = 0;
            next = null;
        }

        public Node(int x) {
            data = x;
            next = null;
        }
    }

    private Node head;

    public SLList() {
        head = null;
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x);
        } else {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
        }
    }

    public void partition(int pivot) {
        if (head == null)   return;

        push(0);
        Node p1 = head, p2 = head;
        while(p2.next != null) {
            if (p2.next.data < pivot) {
                switchNode(p1,p2);
                p1 = p1.next;
            }
            p2 = p2.next;
        }
        head = head.next;
    }

    private void switchNode(Node n1, Node n2) {
        if (n1 == n2)   {
            return;
        } else if (n1.next == n2) {
            Node temp = delete(n2);
            add(n1, temp);
        } else {
            Node temp = delete(n2);
            add(n1, temp);
            temp = delete(n1.next);
            add(n2, temp);
        }
    }
    private Node delete(Node n) {
        Node res = n.next;
        n.next = n.next.next;
        return res;
    }

    private void add(Node prev, Node n) {
        n.next = prev.next;
        prev.next = n;
    }

    public String toString() {
        Node ptr = head;
        String res = "";
        if (ptr != null) {
            while (ptr.next != null) {
                res += ptr.data + " -> ";
                ptr = ptr.next;
            }
            res += ptr.data;
        }
        return res;
    }
}
