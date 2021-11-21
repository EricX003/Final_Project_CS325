import java.util.HashSet;

/*
 * Implementation by Eric Xing
 * Write Through cache writing scheme
 */

public class WriteThrough implements Writer {

	// All data that has passed through the cache
	HashSet<Integer> database = new HashSet<Integer>();
	// Cache object
	Cache cache;

	// Tracked parameters
	int database_writes;
	int cache_writes;

	// Getter method for Cache Writes
	public int getCacheWrites() {
		return cache_writes;
	}

	// Constructor
	public WriteThrough(Cache cache) {
		// Initialize
		this.cache = cache;
		database_writes = 0;
		cache_writes = 0;
	}

	public void write(int value) {
		// Write data, update params
		cache.add(value);
		cache_writes++;
		database.add(value);
		database_writes++;
	}

	public void display() {
		// Display number of database writes
		System.out.println("Database Writes: " + database_writes);
	}

}
