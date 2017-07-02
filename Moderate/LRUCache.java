package Moderate;

import java.util.HashMap;

/**
 * Created by sycai on 7/2/2017.
 * CCI 16.25 Design and build a "least recently used" cache, which evicts the least recently used item. The cache
 * should map from keys to values (allowing you to insert and retrieve a value associated with a particular key) and
 * be initialized with a max size. When it is full, it should evict the least recently used item.
 */
public class LRUCache<T> {
    private class ListNode<T> {
        int key;
        T value;
        ListNode<T> next;
        ListNode<T> prev;

        ListNode(int k, T v) {
            key = k;
            this.value = v;
            next = null;
            prev = null;
        }

        @Override
        public String toString() {
            return String.format("(%d, %s)", key, value);
        }
    }
    private final int maxCacheSize;
    private HashMap<Integer, ListNode<T>> map;
    private ListNode<T> dummy; // We implement a doubly-linked circular list.
    private int size;

    public LRUCache(int maxSize) {
        size = 0;
        maxCacheSize = maxSize;
        map = new HashMap<>();
        dummy = new ListNode<>(-1, null);
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public void insert(int key, T value) {
        if (map.containsKey(key))   {
            throw new IllegalArgumentException("Key already exists");
        }
        ListNode<T> newNode = new ListNode<>(key, value);
        map.put(key, newNode);
        if (size < maxCacheSize) {
            insertBeforeHead(newNode);
            size++;
        } else {
            removeLast();
            insertBeforeHead(newNode);
        }
    }

    public T lookUp(int key) {
        if (map.containsKey(key)) return map.get(key).value;
        else                      throw new IllegalArgumentException("Key does not exist");
    }

    public void set(int key, T value) {
        if (map.containsKey(key)) {
            ListNode<T> oldNode = map.get(key);
            oldNode.prev.next = oldNode.next;
            oldNode.next.prev = oldNode.prev;
            map.remove(key);
            size--;
        }
        insert(key, value);
    }

    private void insertBeforeHead(ListNode<T> node) {
        node.prev = dummy;
        node.next = dummy.next;
        dummy.next.prev = node;
        dummy.next = node;
    }

    private void removeLast() {
        ListNode<T> last = dummy.prev;
        dummy.prev.prev.next = dummy;
        dummy.prev = dummy.prev.prev;
        map.remove(last.key);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        for (ListNode<T> curr = dummy.next; curr != dummy; curr = curr.next) {
            res.append(curr + ", ");
        }
        res.replace(res.length()-2, res.length(), "");
        return res.append(']').toString();
    }

    public static void main(String[] args) {
        LRUCache<String> cache = new LRUCache<>(3);
        for (int i = 0; i < 5; i++) {
            cache.insert(i, EnglishInt.getEnglish(i));
            System.out.println(cache);
        }
        cache.set(2, "TwoTwo");
        System.out.println(cache);
        System.out.println(cache.lookUp(4));
    }
}
