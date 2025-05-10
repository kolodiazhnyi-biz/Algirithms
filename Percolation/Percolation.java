/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
    private final WeightedQuickUnionUF connectedUf;
    private final WeightedQuickUnionUF fullUf;
    private int numberOfOpenSites;
    private final boolean[][] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n should be greater than 0");
        size = n;
        numberOfOpenSites = 0;
        grid = new boolean[n][n];
        connectedUf = new WeightedQuickUnionUF(n * n + 2);
        fullUf = new WeightedQuickUnionUF(n * n + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            numberOfOpenSites++;
            grid[row - 1][col - 1] = true;
            connectToNeighbors(row, col);
        }
    }

    private void connectToNeighbors(int row, int col) {
        if (row == 1) {
            connectedUf.union(0, col);
            fullUf.union(0, col);
            if (size != 1 && isOpen(2, col)) {
                connectedUf.union(col, size + col);
                fullUf.union(col, size + col);
            }
        }
        if (row == size) {
            connectedUf.union(size * size + 1, (row - 1) * size + col);
            if (size != 1 && isOpen(row - 1, col)) {
                connectedUf.union((row - 1) * size + col, (row - 2) * size + col);
                fullUf.union((row - 1) * size + col, (row - 2) * size + col);
            }
        }
        if (row > 1 && row < size) {
            if (isOpen(row - 1, col)) {
                connectedUf.union((row - 1) * size + col, (row - 2) * size + col);
                fullUf.union((row - 1) * size + col, (row - 2) * size + col);
            }
            if (isOpen(row + 1, col)) {
                connectedUf.union((row - 1) * size + col, row * size + col);
                fullUf.union((row - 1) * size + col, row * size + col);
            }
        }
        if (col > 1 && isOpen(row, col - 1)) {
            connectedUf.union((row - 1) * size + col, (row - 1) * size + col - 1);
            fullUf.union((row - 1) * size + col, (row - 1) * size + col - 1);
        }
        if (col < size && isOpen(row, col + 1)) {
            connectedUf.union((row - 1) * size + col, (row - 1) * size + col + 1);
            fullUf.union((row - 1) * size + col, (row - 1) * size + col + 1);
        }
    }

    private void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row and col should be between 1 and n");
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return fullUf.find(0) == fullUf.find((row - 1) * size + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return connectedUf.find(0) == connectedUf.find(size * size + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.print("Not empty");
    }
}