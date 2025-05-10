import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
public class PercolationStats {

    private static final double THRESHOLD = 1.96;
    private final double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials should be greater than 0");
        }
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                percolation.open(row, col);
            }
            thresholds[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - THRESHOLD * stddev() / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + THRESHOLD * stddev() / Math.sqrt(thresholds.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide n and trials");
        }
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        System.out.println("Mean = " + stats.mean());
        System.out.println("Standard deviation = " + stats.stddev());
        System.out.println(
                "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi()
                        + "]");
    }

}
