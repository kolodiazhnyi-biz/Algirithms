/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 ****************************************************************************
 *
 * Social network connectivity. Given a social network containing
 * members and a log file containing m timestamps at which times pairs of members formed friendships,
 * design an algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log
 * file is sorted by timestamp and that friendship is an equivalence relation.
 * The running time of your algorithm should be m*log(n) or better
 * and use extra space proportional to n
 *
 * */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class SocialNetworkConnectivity {
    public static void main(String[] args) {
        var log = new int[][] {
                { 0, 1, 12301 },
                { 1, 2, 12305 },
                { 2, 3, 12303 },
                { 3, 4, 12304 },
                { 0, 5, 12305 },
                { 4, 5, 12306 },
                { 5, 6, 12307 },
                { 7, 9, 12308 },
                { 6, 7, 12309 },
                { 7, 8, 12310 },
                { 8, 9, 12311 },
                { 0, 9, 12312 }
        };
        System.out.println(earliestTime(10, log));
    }

    private static int earliestTime(int n, int[][] log) {
        var uf = new WeightedQuickUnionUF(n);
        for (var i = 0; i < log.length; i++) {
            var p = log[i][0];
            var q = log[i][1];
            var time = log[i][2];
            uf.union(p, q);
            if (uf.count() == 1) {
                return time;
            }
        }
        return -1;
    }
}
