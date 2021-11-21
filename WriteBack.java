import java.util.HashSet;
import java.util.LinkedHashSet;

/*
 *Implementation by Eric Xing
 *Write-Back writing scheme
 */

public class WriteBack implements Writer {

	/*
	 * all values that have been passed through or are currently contained in the
	 * cache
	 */
	HashSet<Integer> database = new HashSet<Integer>();

	// Cache
	Cache cache;

	// Recorded params
	int database_writes;
	int cache_writes;

	// Time data
	static final long THRESHOLD = 1000;
	long last;
	// Data that have not been flushed
	LinkedHashSet<Integer> waiting;

	// Getter for Cache writes
	public int getCacheWrites() {
		return cache_writes;
	}

	// Constructor, initialize everything
	public WriteBack(Cache cache) {
		this.cache = cache;
		database_writes = 0;
		cache_writes = 0;
		last = System.currentTimeMillis();
		waiting = new LinkedHashSet<Integer>();
	}

	public void write(int value) {

		if (System.currentTimeMillis() - last > THRESHOLD) {
			/*
			 * Time to flush the data from the waiting HashSet to the database
			 */

			for (Integer val : waiting) {
				database.add(val);
				database_writes++;
			}
			// Update time
			waiting = new LinkedHashSet<Integer>();
			last = System.currentTimeMillis();
		} else {
			// Otherwise, just flush to cache
			cache.add(value);
			cache_writes++;
		}
	}

	// Display Database Writes
	public void display() {
		System.out.println("Database Writes: " + database_writes);
	}
}
