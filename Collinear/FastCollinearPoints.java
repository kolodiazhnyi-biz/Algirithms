/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        validatePointsNotNull(points);
        validateNoNullPoints(points);
        validateNoSamePoint(points);

        var segmenstQueue = new Queue<LineSegment>();

        Merge.sort(points);

        for (int i = 0; i < points.length; i++) {
            var sortedPoints = new Point[points.length - 1];

            System.arraycopy(points, 0, sortedPoints, 0, i);
            if (i != points.length - 1) {
                System.arraycopy(points, i + 1, sortedPoints, i, points.length - i - 1);
            }
            Merge.sort(sortedPoints);
            MergeX.sort(sortedPoints, points[i].slopeOrder());

            var start = 0;
            while (start < sortedPoints.length - 1) {
                var count = 1;
                var end = start + 1;
                while (end < sortedPoints.length && doubleEquals(
                        points[i].slopeTo(sortedPoints[start]),
                        points[i].slopeTo(sortedPoints[end]))) {
                    end++;
                    count++;
                }
                if (count >= 3) {
                    var subArray = new Point[count + 1];
                    subArray[0] = points[i];
                    System.arraycopy(sortedPoints, start, subArray, 1, count);
                    Insertion.sort(subArray);
                    if (subArray[0].compareTo(points[i]) == 0) {
                        segmenstQueue.enqueue(
                                new LineSegment(subArray[0], subArray[subArray.length - 1]));
                    }
                }
                start = end;
            }
        }

        var size = segmenstQueue.size();
        segments = new LineSegment[size];
        for (int i = 0; i < size; i++) {
            segments[i] = segmenstQueue.dequeue();
        }
    }

    private static boolean doubleEquals(double a, double b) {
        return Double.compare(a, b) == 0;
    }

    private static void validateNoSamePoint(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private static void validateNoNullPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }
    }

    private static void validatePointsNotNull(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
    }

    public int numberOfSegments() { // the number of line segments
        return segments.length;
    }

    public LineSegment[] segments() {               // the line segments
        return segments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
