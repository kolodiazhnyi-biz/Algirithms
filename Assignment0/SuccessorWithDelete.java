/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 ****************************************************************************
 *
 * Successor with delete. Given a set of n integers S={0,1,...,n−1} a sequence of requests of the following form:
 * Remove x from S Find the successor of the smallest y in S such that y≥xy, is greater than or equal to, x.
 * design a data type so that all operations (except construction)  take logarithmic time or better in the worst case.
 *
 *
 *
 * */

public class SuccessorWithDelete {

    private final int[] parent;
    private final int[] max;
    private final int[] size;

    public SuccessorWithDelete(int n) {
        parent = new int[n];
        max = new int[n];
        size = new int[n];
        for (var i = 0; i < n; i++) {
            parent[i] = i;
            max[i] = i;
            size[i] = 1;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        var rootP = find(p);
        var rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
            max[rootQ] = Math.max(max[rootP], max[rootQ]);
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            max[rootP] = Math.max(max[rootP], max[rootQ]);
        }
    }

    public int successor(int x) {
        return max[find(x)];
    }

    public void remove(int x) {
        if (x == parent.length - 1) {
            return;
        }
        union(x, x + 1);
    }

    public static void main(String[] args) {
        var successorWithDelete = new SuccessorWithDelete(10);
        successorWithDelete.remove(5);
        System.out.println(successorWithDelete.successor(5));
        successorWithDelete.remove(4);
        System.out.println(successorWithDelete.successor(4));
        System.out.println(successorWithDelete.successor(5));
        successorWithDelete.remove(6);
        System.out.println(successorWithDelete.successor(6));
        successorWithDelete.remove(8);
        System.out.println(successorWithDelete.successor(8));
        successorWithDelete.remove(7);
        System.out.println(successorWithDelete.successor(5));
        successorWithDelete.remove(0);
        System.out.println(successorWithDelete.successor(0));
        successorWithDelete.remove(9);
        System.out.println(successorWithDelete.successor(9));
    }
}
