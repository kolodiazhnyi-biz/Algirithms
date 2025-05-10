/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        final Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        }
        else {
            last = null; // deque is now empty
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        }
        else {
            first = null; // deque is now empty
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        var deque = new Deque<Integer>();
        deque.addFirst(1);
        print(deque);
        deque.addFirst(2);
        print(deque);
        deque.addFirst(0);
        print(deque);
        deque.addLast(3);
        print(deque);
        System.out.println(deque.removeFirst());
        print(deque);
        System.out.println(deque.removeLast());
        print(deque);
        System.out.println(deque.removeLast());
        print(deque);
        System.out.println(deque.removeFirst());
        print(deque);
    }

    private static void print(Deque<Integer> deque) {
        for (Integer i : deque) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
