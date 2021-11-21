import java.util.HashSet;

/*
 * Implementation by Eric Xing
 * Cache-Aside implementation of a Reader object
 */

public class CacheAside implements Reader {

	// All values contained or previously contained in the cache
	HashSet<Integer> database;

	// Cache object
	Cache cache;

	// Tracked values
	double hits;
	double misses;
	int database_reads;
	int cache_reads;
	int cache_writes;

	// Initialize the cache and relevent attributes
	public CacheAside(Cache cache) {
		this.cache = cache;
		cache_writes = database_reads = 0;
		hits = misses = 0.00;
	}

	/*
	 * Getter function for CacheWrites, as cache writes can come from both Reader
	 * and Writer
	 */
	public int getCacheWrites() {
		return cache_writes;
	}

	public void query(int value) {
		if (cache.get(value) == value) { // query
			hits++; // cache hit
		} else {
			misses++; // cache miss

			// The following are the basic mechanism of the Cache Aside cache
			database_reads++;
			cache_writes++;
			cache.add(value);
		}
		// The cache gets read no matter
		cache_reads++;
	}

	public void display() {
		// Display the basic information about the Cache Reader
		System.out.println("Hit Rate: " + (hits / (hits + misses)));
		System.out.println("Cache Reads: " + cache_reads);
		System.out.println("Database Reads: " + database_reads);
	}

}
