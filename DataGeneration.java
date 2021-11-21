import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

/*
 * Implementation by Eric Xing
 * Generates weighted data according to the Poisson distribution
 */

public class DataGeneration {

	// all values
	static LinkedHashSet<Integer> universe = new LinkedHashSet<Integer>();
	// random number generation, fixed seed
	static Random random = new Random(0L);
	// Poisson constant to account for rounding error
	static double CONSTANT = 7.00;

	// factorial function for Poisson calculation with BigDecimal
	public static BigDecimal factorial(int num) {
		// Initial answer is 1
		BigDecimal res = new BigDecimal("1");

		// Multiply down
		for (int i = num; i > 1; i--) {
			res.multiply(BigDecimal.valueOf(i));
		}
		return res;
	}

	// Gets a value from the universe according to the Poisson distribution
	public static Integer getWeighted(double center) {

		// Adjust center with constant
		center *= CONSTANT;
		// MathContext for calculations
		MathContext ctx = new MathContext(100, RoundingMode.HALF_UP);

		// Initialize variables
		int sz = universe.size();
		int lambda = (int) (center * sz);
		BigDecimal L = new BigDecimal(center * sz);
		double threshold = Math.random();
		BigDecimal sum = new BigDecimal("0.00");
		int idx = 0;
		BigDecimal E = new BigDecimal(Math.E);
		Iterator<Integer> itr = universe.iterator();

		while (idx < sz - 1 && sum.doubleValue() < threshold) {
			// Calculation for Poisson distribution
			BigDecimal power = L.pow(idx);
			BigDecimal fact = factorial(idx);
			BigDecimal ePow = E.pow(lambda);
			sum = sum.add(power.divide(ePow, ctx).divide(fact, ctx), ctx);

			// advance idx and iterator
			idx++;
			itr.next();
		}

		// return the value
		return itr.next();

	}

	// Uniform distribution of probabilities (not normal or Poisson)
	public static Integer get() {

		// Get a random value
		Iterator<Integer> itr = universe.iterator();
		int threshold = (int) (random.nextDouble() * universe.size());
		int idx = 0;

		while (idx < threshold - 1) {
			itr.next();
			idx++;
		}

		// Return
		return itr.next();

	}

	public static void main(String[] args) throws IOException {

		// Write to the input data file
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("test_data_0.75.in")));

		// Produce 10,000 queries only due to expensive computations
		for (int i = 0; i < 10000; i++) {
			if (random.nextDouble() < 0.50000) {
				// Half are queries
				pw.println("q " + getWeighted(0.75));
			} else {
				// Other half are additions
				int num = random.nextInt(10000000);

				// Add something that isn't already there
				while (universe.contains(num)) {
					num = random.nextInt(10000000);
				}

				// Add it
				pw.println("a " + num);
				// Update the universe
				universe.add(num);
			}
		}

		pw.close();
		System.out.println("done");
	}
}
