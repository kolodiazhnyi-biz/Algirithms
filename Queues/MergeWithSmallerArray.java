/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *
 * Merging with smaller auxiliary array. Suppose that the subarray a[0]-a[n−1] is sorted and
 * the subarray a[n] a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0]t to
 * a[2∗n−1] is sorted using an auxiliary array of length n (instead of 2n)?
 *
 **************************************************************************** */

public class MergeWithSmallerArray {

    public void merge(int[] a) {
        var aux = new int[a.length / 2];

        for (int i = 0; i < aux.length; i++) {
            aux[i] = a[i];
        }
        int i = 0, left = 0, right = a.length / 2;
        while (left < right) {
            if (right >= a.length || aux[i] < a[right]) {
                a[left++] = aux[i++];
            }
            else {
                a[left++] = a[right++];
            }
        }
    }

    public static void main(String[] args) {
        var mergeWithSmallerArray = new MergeWithSmallerArray();

        var a = new int[] { };
        mergeWithSmallerArray.merge(a);
        printArray(a);

        var b = new int[] { 1, 2, 3, 4, 5, 6 };
        mergeWithSmallerArray.merge(b);
        printArray(b);

        var c = new int[] { 2, 4, 6, 1, 3, 5 };
        mergeWithSmallerArray.merge(c);
        printArray(c);

        var d = new int[] { 1, 2, 6, 3, 4, 5 };
        mergeWithSmallerArray.merge(d);
        printArray(d);

        var e = new int[] { 4, 5, 6, 1, 2, 3 };
        mergeWithSmallerArray.merge(e);
        printArray(e);
    }

    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
