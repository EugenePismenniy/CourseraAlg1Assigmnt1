
public class PercolationStats {
    
    public PercolationStats(int N, int T) // perform T independent experiments
					  // on an N-by-N grid
    {
	if (N <= 0 || T <= 0) {
	    throw new IllegalArgumentException();
	}
    }

    public double mean() // sample mean of percolation threshold
    {
	return 0;
    }

    public double stddev() // sample standard deviation of percolation threshold
    {
	return 0;
    }

    public double confidenceLo() // low endpoint of 95% confidence interval
    {
	return 0;
    }

    public double confidenceHi() // high endpoint of 95% confidence interval
    {
	return 0;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

    }
}
