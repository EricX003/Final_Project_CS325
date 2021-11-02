import java.util.HashSet;

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
		if(cache.get(value) == value) {
			hits++;
		} else {
			misses++;
			
			database_reads++;
			cache_writes++;
			cache.add(value);
		}
		cache_reads++;
	}
	
	public void display() {
		System.out.println("Hit Rate: " + (hits / (hits + misses)));
		System.out.println("Cache_Writes: " + cache_writes);
		System.out.println("Database Reads: " + database_reads);
		System.out.println("Cache Reads: " + cache_reads);
	}
	
}
