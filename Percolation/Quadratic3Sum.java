/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 ****************************************************************************
 *
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n^2 in the worst case.
 * You may assume that you can sort the integers in time proportional to n squared or better.
 *
 * */

import java.util.Arrays;

public class Quadratic3Sum {

    public void threeSum(int[] a) {
        Arrays.sort(a);
        for (int i = 0; i < a.length - 2; i++) {
            int j = i + 1;
            int k = a.length - 1;
            while (j < k) {
                if (a[i] + a[j] + a[k] == 0) {
                    System.out.println(a[i] + " " + a[j] + " " + a[k]);
                    j++;
                    k--;
                }
                else if (a[i] + a[j] + a[k] < 0) {
                    j++;
                }
                else {
                    k--;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = { 30, -40, -20, -10, 40, 0, 10, 5 };
        Quadratic3Sum q = new Quadratic3Sum();
        q.threeSum(a);
    }
}
