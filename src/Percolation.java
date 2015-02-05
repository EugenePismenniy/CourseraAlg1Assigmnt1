

public class Percolation {

    /**
     * true - free, false - blocked
     */
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

        if (!isOpen(i, j)) {
            return false;
        }

        if(i == 1) {
            return true;
        }

        int p = xyTo1D(i, j);

        return uf.find(p) < N;

    }

    public boolean percolates() // does the system percolate?
    {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                if (isFull(i, j)) {

                    int p = getOpenSideCell(i , j);

                    System.out.println("isFull: i = " + i + "; j = " + j + "; p = " + p);

                    if (p >= 0) {
                        int q = xyTo1D(i, j);

                        //System.out.println(p);

                        if (!uf.connected(p, q)) {

                            uf.union(p, q);
                        }
                    }
                }


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

    private int getOpenSideCell(int i, int j) {

        if (i < N && isOpen(i + 1, j) && !isFull(i + 1, j)) {
            return xyTo1D(i + 1, j);
        }

        if (i > 1 && j < N && isOpen(i, j + 1) && !isFull(i, j + 1)) {
            return xyTo1D(i, j + 1);
        }

        if (i > 1 && j > 1 && isOpen(i, j - 1) && !isFull(i, j - 1)) {
            return xyTo1D(i, j - 1);
        }

        return -1;
    }

}