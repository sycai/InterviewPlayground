/**
 * Created by sycai on 1/10/2017.
 */
public class QueueViaStack {
    private Stack stackNewest;
    private Stack stackOldest;

    public QueueViaStack() {
        stackNewest = new Stack();
        stackOldest = new Stack();
    }

    public int size() {
        return stackNewest.size() + stackOldest.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(int value) {
        stackNewest.push(value);
    }


    private void shiftStacks() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public int peek() {
        shiftStacks();
        return stackOldest.peek();
    }

    public int pop() {
        shiftStacks();
        return stackOldest.pop();
    }

    @Override
    public String toString() {
        return "stackNewest: " + stackNewest + "\nstackOldest: " + stackOldest;
    }
}
