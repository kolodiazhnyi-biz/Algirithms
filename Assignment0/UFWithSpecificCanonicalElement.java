/*
 * Name:              Ada Lovelace
 * Coursera User ID:  123456
 * Last modified:     October 16, 1842
 * <p>
 * Question 2
 * Union-find with specific canonical element. Add a method find(i) to the union-find data type so
 * that
 * returns the largest element in the connected component containing i. The operations, union(),
 * connected() and find() should all take logarithmic time or better.
 * For example, if one of the connected components is {1,2,6,9} find() method should return 9
 * for each of the four elements in the connected components.
 */

public class UFWithSpecificCanonicalElement {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i
    private int count;      // number of components
    private int[] max;      // max[i] = largest element in subtree rooted at i

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each element is in its own set.
     *
     * @param n the number of elements
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UFWithSpecificCanonicalElement(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        max = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            max[i] = i;
        }
    }

    /**
     * Returns the number of sets.
     *
     * @return the number of sets (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the canonical element of the set containing element {@code p}.
     *
     * @param p an element
     * @return the canonical element of the set containing {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    public int findMax(int p) {
        return max[find(p)];
    }

    /**
     * Returns true if the two elements are in the same set.
     *
     * @param p one element
     * @param q the other element
     * @return {@code true} if {@code p} and {@code q} are in the same set;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     * @deprecated Replace with two calls to {@link #find(int)}.
     */
    @Deprecated
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Merges the set containing element {@code p} with the set
     * containing element {@code q}.
     *
     * @param p one element
     * @param q the other element
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
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
        count--;
    }

    public static void main(String[] args) {
        var uf = new UFWithSpecificCanonicalElement(10);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(3, 4);
        uf.union(0, 4);
        uf.union(5, 6);
        uf.union(7, 9);
        uf.union(6, 7);
        uf.union(7, 8);
        uf.union(8, 9);
        System.out.println(uf.findMax(0));
        System.out.println(uf.findMax(1));
        System.out.println(uf.findMax(2));
        System.out.println(uf.findMax(3));
        System.out.println(uf.findMax(4));
        System.out.println(uf.findMax(5));
        System.out.println(uf.findMax(6));
        System.out.println(uf.findMax(7));
        System.out.println(uf.findMax(8));
        System.out.println(uf.findMax(9));
    }
}
