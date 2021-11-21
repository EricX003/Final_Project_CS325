import java.util.HashSet;

/*
 * Implementation by Eric Xing
 * Read-Through cache reading scheme implementation
 */

public class ReadThrough implements Reader {

	/*
	 * all values that have been passed through or are currently contained in the
	 * cache
	 */
	HashSet<Integer> database;
	// Cache
	Cache cache;

	// Recorded parameters
	double hits;
	double misses;
	int database_reads;
	int cache_reads;
	int cache_writes;

	// Initialize
	public ReadThrough(Cache cache) {
		this.cache = cache;
		cache_writes = database_reads = 0;
		hits = misses = 0.00;
	}

	// Getter for CacheWrites
	public int getCacheWrites() {
		return cache_writes;
	}

	// Basic mechanism of the ReadThrough cache
	public void query(int value) {
		if (cache.get(value) == value) {
			// Contained, Hit
			hits++;
		} else {
			// Miss, add the value to the cache
			misses++;
			// Read and write to database and cache
			database_reads++;
			cache_writes++;
			cache.add(value);
		}
		cache_reads++;
	}

	// Display all recorded oarams
	public void display() {
		System.out.println("Hit Rate: " + (hits / (hits + misses)));
		System.out.println("Cache Reads: " + cache_reads);
		System.out.println("Database Reads: " + database_reads);
	}

}
