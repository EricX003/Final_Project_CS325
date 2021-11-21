import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Implementation by Eric Xing
 * Driver style class for testing the 6 cache replacement policies under
 */

public class Driver {

	// tested objects
	static Cache cache;
	static Reader reader;
	static Writer writer;

	// Capacity of the cache
	static int CAPACITY = 100000;

	// For displaying
	static String cache_type;
	static String reader_type;
	static String writer_type;

	// Displays all relevent information about the tested cache parameters
	public static void dispAll() {
		System.out.println(cache_type + " cache, " + reader_type + " reader, " + writer_type + " writer");
		reader.display();
		writer.display();
		System.out.println("Cache writes: " + (reader.getCacheWrites() + writer.getCacheWrites()));
		System.out.println();

	}

	/*
	 * Return a Writer object based on an id parameter Where 0 <= id <= 1
	 */
	public static Writer getWriter(int id) {
		switch (id) {
		case 0:
			writer_type = "Write Back";
			return new WriteBack(cache);
		case 1:
			writer_type = "Write Through";
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
			reader_type = "Read Through";
			return new ReadThrough(cache);
		case 1:
			reader_type = "Cache Aside";
			return new CacheAside(cache);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {

		int cnt = 1;

		// test all 24 enumerations

		// writing schemes
		for (int k = 0; k < 2; k++) {
			// reading schemes
			for (int j = 0; j < 2; j++) {
				// cache replacement policies
				for (int i = 0; i < 6; i++) {

					// read the data from the generated file
					BufferedReader br = new BufferedReader(new FileReader(new File("test_data.in")));

					System.out.println(cnt++);

					// initialize the objects being tested
					cache = getCache(i);
					reader = getReader(j);
					writer = getWriter(k);

					// record start time
					long start_time = System.nanoTime();

					// test all 1,000,000
					for (int idx = 0; idx < 1000000; idx++) {
						String[] line = br.readLine().split(" ");
						if (line[0].equals("q")) {
							// query
							reader.query(Integer.parseInt(line[1]));
						} else {
							// add
							writer.write(Integer.parseInt(line[1]));
						}
					}

					// record the end time
					long end_time = System.nanoTime();

					// display all of the information
					dispAll();
					System.out.println("Time elapsed: " + ((end_time - start_time) / 1000000.0));

					br.close();
				}
			}
		}

	}
}
