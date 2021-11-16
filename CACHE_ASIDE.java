import java.util.HashSet;

/*
 * Implementation by Eric Xing
 * Cache-Aside implementation of a READER object
 */

public class CACHE_ASIDE implements READER {
	
	HashSet<Integer> database;
	CACHE cache;
	double hits;
	double misses;
	int database_reads;
	int cache_reads;
	int cache_writes;
	
	public CACHE_ASIDE(CACHE cache) {
		this.cache = cache;
		cache_writes = database_reads = 0;
		hits = misses = 0.00;
	}
	
	public void query(int value) {
		if(cache.get(value) == value) { // query
			hits++; // cache hit
		} else {
			misses++; // cache miss
			
			// The following are the basic mechanism of the Cache Aside cache
			database_reads++;
			cache_writes++;
			cache.add(value);
		}
		//The cache gets read no matter 
		cache_reads++;
	}
	
	public void display() {
		// Display the basic information about the Cache Reader
		System.out.println("Hit Rate: " + (hits / (hits + misses)));
		System.out.println("Cache_Writes: " + cache_writes);
		System.out.println("Database Reads: " + database_reads);
		System.out.println("Cache Reads: " + cache_reads);
	}
	
}
