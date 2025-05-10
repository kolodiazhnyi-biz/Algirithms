/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 ****************************************************************************
 *
 * Create a data structure that efficiently supports the stack operations
 * (push and pop) and also a return-the-maximum operation. Assume the elements
 * are real numbers so that you can compare them.
 *
 * */

import edu.princeton.cs.algs4.Stack;

public class StackWithMax {

    private Stack<Double> stack = new Stack<>();
    private Stack<Double> maxStack = new Stack<>();

    public void push(double x) {
        stack.push(x);
        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        }
    }

    public double pop() {
        double x = stack.pop();
        if (x == maxStack.peek()) {
            maxStack.pop();
        }
        return x;
    }

    public double max() {
        return maxStack.peek();
    }

    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();
        stack.push(3.0);
        System.out.println(stack.max());
        stack.push(5.0);
        System.out.println(stack.max());
        stack.push(1.0);
        System.out.println(stack.max());
        stack.push(4.0);
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.push(6.0);
        System.out.println(stack.max());
        stack.push(2.0);
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
        stack.pop();
        System.out.println(stack.max());
    }
}
