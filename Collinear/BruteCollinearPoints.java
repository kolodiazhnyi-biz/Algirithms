/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private static final double DOUBLE_EPSILON = 1.0e-6;
    private int numSegments = 0;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        validatePointsNotNull(points);
        validateNoNullPoints(points);
        validateNoSamePoint(points);

        var segmenstQueue = new Queue<LineSegment>();

        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int m = k + 1; m < points.length; m++) {
                        if (doubleEquals(points[i].slopeTo(points[j]), points[i].slopeTo(points[k]))
                                && doubleEquals(points[i].slopeTo(points[m]),
                                                points[i].slopeTo(points[k]))) {
                            var poinstInSegment = new Point[] {
                                    points[i], points[j], points[k], points[m]
                            };
                            segmenstQueue.enqueue(new LineSegment(findMin(poinstInSegment),
                                                                  findMax(poinstInSegment)));
                            numSegments++;
                        }
                    }
                }
            }
        }
        segmentsArrayFromQueue(segmenstQueue);
    }

    private void segmentsArrayFromQueue(Queue<LineSegment> segmenstQueue) {
        segments = new LineSegment[numSegments];
        for (int i = 0; i < numSegments; i++) {
            segments[i] = segmenstQueue.dequeue();
        }
    }

    private Point findMin(Point[] points) {
        var min = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(min) < 0) {
                min = points[i];
            }
        }
        return min;
    }

    private Point findMax(Point[] points) {
        var max = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(max) > 0) {
                max = points[i];
            }
        }
        return max;
    }


    private static boolean doubleEquals(double a, double b) {
        if (a == Double.POSITIVE_INFINITY && b == Double.POSITIVE_INFINITY) {
            return true;
        }
        return Math.abs(a - b) < DOUBLE_EPSILON;
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
        return numSegments;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
