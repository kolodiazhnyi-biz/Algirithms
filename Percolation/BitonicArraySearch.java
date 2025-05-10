/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 ****************************************************************************
 *
 * Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of
 * integers followed immediately by a decreasing sequence of integers. Write a program that,
 * given a bitonic array of n distinct integer values, determines whether a given integer is in the array.
 * Standard version: Use ∼3lgn compares in the worst case.
 * Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee
 * to perform fewer than ~2lgn n compares in the worst case).
 *
 * */

public class BitonicArraySearch {

    public int search(int[] a, int key) {
        return bitonicSearch(a, key, 0, a.length - 1);
    }

    private int bitonicSearch(int[] a, int key, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == key) {
            return mid;
        }
        if (mid > 0 && a[mid] > a[mid - 1]) {
            var index = binarySearch(a, key, lo, mid - 1);
            if (index != -1) {
                return index;
            }
            else {
                return bitonicSearch(a, key, mid + 1, hi);
            }
        }
        else {
            var index = reverseBinarySearch(a, key, mid + 1, hi);
            if (index != -1) {
                return index;
            }
            else {
                return bitonicSearch(a, key, lo, mid - 1);
            }
        }
    }

    private int binarySearch(int[] a, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    private int reverseBinarySearch(int[] a, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] > key) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BitonicArraySearch b = new BitonicArraySearch();
        int[] a = { 1, 3, 5, 7, 9, 8, 6, 4, 2 };
        System.out.println(b.search(a, 6));
        System.out.println(b.search(a, 1));
        System.out.println(b.search(a, 2));
        System.out.println(b.search(a, 9));
        System.out.println(b.search(a, 3));
    }
}
