public class Percolation {

    private boolean[][] grid; // true - free, false - blocked
    private final int N;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) // create N-by-N grid, with all sites blocked
    {
        if (N <= 0) 
            throw new IllegalArgumentException();

        this.N = N;
        this.uf = new WeightedQuickUnionUF(N * N);
        this.grid = new boolean[N][N];
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        throwExceptionIfNotValidIndex(i, j);
        set(i, j, true);
        
        int q = xyTo1D(i, j);
        
        int p = getCellSide(i > 1, i - 1, j); // top
        tryConnect(p, q);
        
        p = getCellSide(i < N, i + 1, j); // bottom
        tryConnect(p, q);
        
        p = getCellSide(j > 1, i, j - 1); // left
        tryConnect(p, q);
        
        p = getCellSide(j < N, i, j + 1); // right
        tryConnect(p, q);
    }

    public boolean isOpen(int i, int j) // is site (row i, column j) open?
    {
        throwExceptionIfNotValidIndex(i, j);
        return get(i, j);
    }

    public boolean isFull(int i, int j) // is site (row i, column j) full?
    {
        throwExceptionIfNotValidIndex(i, j);
        
        int p = xyTo1D(i, j);    
        
        for (int k = 1; k <= N; k ++) {
            if(isOpen(1, k)) {
        	int q = xyTo1D(1, k);
        	if(uf.connected(p, q)) 
        	    return true;
            }
        }
        return false;
    }

    public boolean percolates() // does the system percolate?
    {
        for (int j = 1; j <= N; j++) {            
            int p = xyTo1D(N, j);
            int q = xyTo1D(1, j);
            
            if (uf.connected(p, q)) 
        	return true;
        }
        return false;
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
        return N * (i - 1) + (j - 1);
    }
    
    private int getCellSide(boolean condition, int i, int j) {
	return condition && isOpen(i, j) ? xyTo1D(i, j) : -1;
    }
    
    private void tryConnect(int p, int q) {
	if(p >= 0  && !uf.connected(p, q)) 
	    uf.union(p, q);
    }
}