/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *
 * Intersection of two sets. Given two arrays a[] and b[], each containing n
 * distinct 2D points in the plane, design a subquadratic algorithm to count the number of points
 * that are contained both in array a[] and array b[].
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Shell;

public class IntersectionOfSets {
    static class Point implements Comparable<Point> {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point o) {
            if (x < o.x) {
                return -1;
            }
            else if (x > o.x) {
                return 1;
            }
            else if (y < o.y) {
                return -1;
            }
            else if (y > o.y) {
                return 1;
            }
            return 0;
        }
    }

    public int countIntersection(Point[] a, Point[] b) {
        Shell.sort(a);
        Shell.sort(b);

        int count = 0;
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) == 0) {
                count++;
                i++;
                j++;
            }
            else if (a[i].compareTo(b[j]) < 0) {
                i++;
            }
            else {
                j++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Point[] a = { new Point(1, 2), new Point(3, 4), new Point(5, 6) };
        Point[] b = { new Point(3, 4), new Point(7, 8), new Point(5, 6) };

        IntersectionOfSets intersectionOfSets = new IntersectionOfSets();
        int count = intersectionOfSets.countIntersection(a, b);
        System.out.println("Number of points in both sets: " + count);
    }
}
