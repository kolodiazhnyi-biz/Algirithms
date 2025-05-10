/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *  Given two integer arrays of size n, design a subquadratic algorithm to determine whether one is
 *  a permutation of the other. That is, do they contain exactly the same entries but, possibly,
 *  in a different order.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Shell;

public class IfPermutation {

    private static boolean isPermutation(Integer a[], Integer b[]) {
        if (a.length != b.length) {
            return false;
        }

        Shell.sort(a);
        Shell.sort(b);

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Integer[] a = { 1, 2, 3, 4, 6, 5, 7, 9, 8 };
        Integer[] b = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        if (isPermutation(a, b)) {
            System.out.println("The arrays are permutations of each other.");
        }
        else {
            System.out.println("The arrays are not permutations of each other.");
        }

        Integer[] c = { 1, 2, 3, 4, 5 };
        Integer[] d = { 1, 2, 3, 4, 6 };

        if (isPermutation(c, d)) {
            System.out.println("The arrays are permutations of each other.");
        }
        else {
            System.out.println("The arrays are not permutations of each other.");
        }
    }
}
