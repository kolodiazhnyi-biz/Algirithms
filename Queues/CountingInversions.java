/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *
 * Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j] such
 * that i<j but a[i]>a[j]. Given an array, design a linearithmic algorithm to count the
 * number of inversions.
 *
 **************************************************************************** */

public class CountingInversions {

    public int count(int[] a) {
        var aux = new int[a.length];
        return sortWithCount(a, aux, 0, a.length - 1);
    }

    private int sortWithCount(int[] a, int[] aux, int start, int end) {
        if (start >= end) {
            return 0;
        }
        var mid = start + (end - start) / 2;
        var count = sortWithCount(a, aux, start, mid);
        count += sortWithCount(a, aux, mid + 1, end);
        count += mergeWithCount(a, aux, start, mid, end);
        return count;
    }

    private int mergeWithCount(int[] a, int[] aux, int lo, int mid, int hi) {
        var count = 0;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        var i = lo;
        var j = mid + 1;
        for (var k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi) {
                a[k] = aux[i++];
            }
            else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
                count += mid + 1 - i;
            }
            else {
                a[k] = aux[i++];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        var countingInversions = new CountingInversions();

        var a = new int[] { 1 };
        System.out.println(countingInversions.count(a)); // 0

        var b = new int[] { 1, 2, 3 };
        System.out.println(countingInversions.count(b)); // 0

        var c = new int[] { 2, 1 };
        System.out.println(countingInversions.count(c)); // 1

        var d = new int[] { 3, 2, 1 };
        System.out.println(countingInversions.count(d)); // 3

        var e = new int[] { 1, 3, 2 };
        System.out.println(countingInversions.count(e)); // 1

        var f = new int[] { 3, 1, 2 };
        System.out.println(countingInversions.count(f)); // 2

        var g = new int[] { 1, 5, 4, 7, 9, 8, 2 };
        System.out.println(countingInversions.count(g)); // 7
    }
}
