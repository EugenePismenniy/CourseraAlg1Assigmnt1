
public class Percolation {

    private boolean[][] grid; // false - blocked, true - open
    private final int N;
    private WeightedQuickUnionUF uf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException();

        this.N = N;
        this.uf = new WeightedQuickUnionUF(N * N + 2); // + 2 virtual nodes
        this.grid = new boolean[N][N];
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        throwExceptionIfNotValidIndex(i, j);
        set(i, j, true);

        int p;
        int q = xyTo1D(i, j);

        if (i == 1) {
            uf.union(q, 0); // union with top virtual node
        } else {
            p = getCellSide(i > 1, i - 1, j); // top
            tryConnect(p, q);
        }

        if (i == N) {
            uf.union(q, N * N + 1); // union with bottom virtual node
        } else {
            p = getCellSide(i < N, i + 1, j); // bottom
            tryConnect(p, q);
        }

        p = getCellSide(j > 1, i, j - 1); // left
        tryConnect(p, q);

        p = getCellSide(j < N, i, j + 1); // right
        tryConnect(p, q);
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        throwExceptionIfNotValidIndex(i, j);
        return get(i, j);
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        throwExceptionIfNotValidIndex(i, j);

        if (isOpen(i, j)) {
            int p = xyTo1D(i, j);
            return uf.connected(p, 0);
        } else {
            return false;
        }
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(N * N + 1, 0);
    }

    private void throwExceptionIfNotValidIndex(int i, int j) {
        if (i <= 0 || j <= 0 || i > N || j > N)
            throw new IndexOutOfBoundsException();
    }

    private void set(int i, int j, boolean val) {
        grid[i - 1][j - 1] = val;
    }

    private boolean get(int i, int j) {
        return grid[i - 1][j - 1];
    }

    private int xyTo1D(int i, int j) {
        return N * (i - 1) + j;
    }

    private int getCellSide(boolean condition, int i, int j) {

        if (condition && isOpen(i, j)) {
            return xyTo1D(i, j);
        }

        return -1;
    }

    private void tryConnect(int p, int q) {
        if (p >= 0 && !uf.connected(p, q)) {
            uf.union(p, q);
        }
    }

    public static void main(String[] args) {

    }
}