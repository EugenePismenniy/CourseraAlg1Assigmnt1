import java.util.Arrays;

public class Percolation {

    /** true - free, false - blocked */
    private boolean[][] grid;
    private final int N;

    private WeightedQuickUnionUF uf;

    public Percolation(int N) // create N-by-N grid, with all sites blocked
    {
	if (N <= 0) {
	    throw new IllegalArgumentException();
	}

	this.N = N;

	this.uf = new WeightedQuickUnionUF(N * N);
	this.grid = new boolean[N][N];

	// for (int i = 1; i <= N; i++) {
	// for (int j = 1; j <= N; j++) {
	// set(i, j, false);
	// }
	// }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
	throwExceptionIfNotValidIndex(i, j);
	set(i, j, true);
    }

    public boolean isOpen(int i, int j) // is site (row i, column j) open?
    {
	throwExceptionIfNotValidIndex(i, j);
	return get(i, j);
    }

    public boolean isFull(int i, int j) // is site (row i, column j) full?
    {
	throwExceptionIfNotValidIndex(i, j);
	int k = xyTo1D(i, j);
	return uf.find(k) == 0;
    }

    public boolean percolates() // does the system percolate?
    {

	for (int i = 1; i <= N; i++) {
	    for (int j = 1; j <= N; j++) {

	    }
	}

	return false;
    }

    private void throwExceptionIfNotValidIndex(int i, int j) {
	if (i <= 0 || j <= 0 || i > grid.length || j > grid.length)
	    throw new IndexOutOfBoundsException();
    }

    private void set(int i, int j, boolean val) {
	grid[i - 1][j - 1] = val;
    }

    private boolean get(int i, int j) {
	return grid[i - 1][j - 1];
    }

    private int xyTo1D(int i, int j) {
	return N * (i - 1) + (j - 1);
    }

    public static void main(String[] args) {

	final int N = 10;

	int[] line = new int[N * N];
	for (int i = 1; i <= N; i++) {
	    for (int j = 1; j <= N; j++) {

		int j2 = N * (i - 1) + (j - 1);

		line[j2] = j2 + 1;

	    }
	}

	System.out.println(Arrays.toString(line));

    }

}

/*
 * 
 * 2D to 1D
 * 
 * int array[width * height];
 * 
 * int SetElement(int row, int col, int value) { array[width * row + col] =
 * value; }
 */
