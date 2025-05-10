/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;
    }

    // resize the array
    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int index = StdRandom.uniformInt(size);
        Item item = items[index];
        items[index] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int index = StdRandom.uniformInt(size);
        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private final int[] indices = new int[size];
            private int current = 0;

            {
                initIndeces(indices);
                StdRandom.shuffle(indices);
            }

            public boolean hasNext() {
                return current < size;
            }

            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more items");
                }
                return items[indices[current++]];
            }
        };
    }


    private void initIndeces(int[] indices) {
        for (int i = 0; i < size; i++) {
            indices[i] = i;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Sample: " + queue.sample());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Size: " + queue.size());
        for (int item : queue) {
            System.out.println("Item: " + item);
        }
    }

}