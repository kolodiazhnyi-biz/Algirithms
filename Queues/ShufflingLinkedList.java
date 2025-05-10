/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *
 * Shuffling a linked list. Given a singly-linked list containing n items, rearrange the items
 * uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra
 *  memory and run in time proportional to n*log(n) in the worst case.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class ShufflingLinkedList {

    public static class Node {
        private Node next;
        private Object data;
    }

    public static Node shuffle(Node head) {
        var length = 0;
        var pointer = head;
        while (pointer != null) {
            length++;
            pointer = pointer.next;
        }
        return shuffle(head, length);
    }

    public static Node shuffle(Node head, int length) {
        if (length == 1) {
            return head;
        }
        var mid = head;
        var k = 0;
        while (k < length / 2 - 1) {
            mid = mid.next;
            k++;
        }
        var rh = mid.next;
        mid.next = null;

        var left = shuffle(head, length / 2);
        var right = shuffle(rh, length - length / 2);
        return merge(left, right);
    }

    private static Node merge(Node left, Node right) {
        Node result;

        if (StdRandom.uniformInt(2) > 0) {
            result = left;
            left = left.next;
        }
        else {
            result = right;
            right = right.next;
        }

        Node pointer = result;
        while (left != null || right != null) {
            if (left == null) {
                pointer.next = right;
                right = right.next;
            }
            else if (right == null) {
                pointer.next = left;
                left = left.next;
            }
            else if (StdRandom.uniformInt(2) > 0) {
                pointer.next = left;
                left = left.next;
            }
            else {
                pointer.next = right;
                right = right.next;
            }
            pointer = pointer.next;

        }

        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            var head = new Node();
            head.data = 0;
            var pointer = head;
            for (int j = 1; j < 10; j++) {
                pointer.next = new Node();
                pointer.next.data = j;
                pointer = pointer.next;
            }
            printList(shuffle(head));
        }

    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }
}
