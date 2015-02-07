
public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();

        double[] xs = new double[T];
        final int NUMBER_CELLS = N * N;

        for (int k = 0; k < T; k++) {



            Percolation p = new Percolation(N);
            int x = 0;

            while (x < NUMBER_CELLS) {

                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);

                if (!p.isOpen(i, j)) {
                    p.open(i, j);
                    x++;

                    if (p.percolates()) {
                        xs[k] = (double) x / NUMBER_CELLS;
                        //System.out.println("xs[" + k + "] = " + xs[k]);
                        break;
                    }

                }
            }
        }

        this.mean = StdStats.mean(xs);
        this.stddev = StdStats.stddev(xs);
        this.confidenceLo = this.mean - (1.96 * this.stddev) / Math.sqrt(T);
        this.confidenceHi = this.mean + (1.96 * this.stddev) / Math.sqrt(T);
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;
    }


    public static void main(String[] args) {

        PercolationStats ps = new PercolationStats(2, 10000);

        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("confidenceLo = " + ps.confidenceLo());
        System.out.println("confidenceHi = " + ps.confidenceHi());
    }
}
