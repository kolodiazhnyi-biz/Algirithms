/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 ****************************************************************************
 *
 * Implement a queue with two stacks so that each queue operations takes a
 * constant amortized number of stack operations.
 *
 * */

import edu.princeton.cs.algs4.Stack;

public class QueueWithTwoStacks<T> {

    private Stack<T> enqueueStack;
    private Stack<T> dequeueStack;

    public QueueWithTwoStacks() {
        enqueueStack = new Stack<>();
        dequeueStack = new Stack<>();
    }

    public void enqueue(T item) {
        enqueueStack.push(item);
    }

    public T dequeue() {
        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        return dequeueStack.pop();
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(5);
        System.out.println(queue.dequeue());
    }
}
