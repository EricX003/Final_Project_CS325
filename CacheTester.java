import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Implementation by Eric Xing
 * Driver style class for testing the 6 cache replacement policies under
 * Various poisson distributions
 * Similar to Driver.java
 */

public class CacheTester {

	// Cache, Reader, and Writer objects for testing
	static Cache cache;
	static Reader reader;
	static Writer writer;

	// Cache capacity
	static int CAPACITY = 500;

	// Type of cache for displaying results
	static String cache_type;

	// Display hit rate
	public static void dispAll() {
		System.out.println(cache_type + " cache");
		reader.display();
	}

	/*
	 * Return a Writer object based on an id parameter Where 0 <= id <= 1
	 */
	public static Writer getWriter(int id) {
		switch (id) {
		case 0:
			return new WriteBack(cache);
		case 1:
			return new WriteThrough(cache);
		}
		return null;
	}

	/*
	 * Return a Cache object based on id parameter Where 0 <= id <= 5
	 */
	public static Cache getCache(int id) {
		switch (id) {
		case 0:
			cache_type = "MRU";
			return new MRUCache(CAPACITY);
		case 1:
			cache_type = "LRU";
			return new LRUCache(CAPACITY);
		case 2:
			cache_type = "FIFO";
			return new FIFOCache(CAPACITY);
		case 3:
			cache_type = "LIFO";
			return new LIFOCache(CAPACITY);
		case 4:
			cache_type = "RR";
			return new RandomReplaceCache(CAPACITY);
		case 5:
			cache_type = "LFU";
			return new LFUCache(CAPACITY);
		}
		return null;
	}

	/*
	 * Return a Reader object based on an id parameter Where 0 <= id <= 1
	 */
	public static Reader getReader(int id) {
		switch (id) {
		case 0:
			return new ReadThrough(cache);
		case 1:
			return new CacheAside(cache);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {

		// test all 6 replacement policies
		for (int i = 0; i < 6; i++) {

			// Read data from the testing data file
			BufferedReader br = new BufferedReader(new FileReader(new File("test_data_0.75.in")));

			// get the right cache, reader and writer don't matter
			cache = getCache(i);
			reader = getReader(0);
			writer = getWriter(0);

			// record start time
			long start_time = System.nanoTime();

			// go through all queries
			for (int idx = 0; idx < 10000; idx++) {
				String[] line = br.readLine().split(" ");
				if (line[0].equals("q")) {
					// query
					reader.query(Integer.parseInt(line[1]));
				} else {
					// add
					writer.write(Integer.parseInt(line[1]));
				}
			}
			// record end time
			long end_time = System.nanoTime();

			// display all info for this replacement policy
			dispAll();
			System.out.println("Time elapsed: " + ((end_time - start_time) / 1000000.0));
			System.out.println();

			br.close();
		}

	}
}
