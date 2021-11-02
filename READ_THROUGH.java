import java.util.HashSet;

public class READ_THROUGH implements READER {
	
	HashSet<Integer> database;
	CACHE cache;
	int hits;
	int misses;
	int database_reads;
	int cache_reads;
	
	public READ_THROUGH(CACHE cache) {
		this.cache = cache;
		hits = misses = database_reads = 0;
	}
	
	public void query(int value) {
		if(cache.get(value) == value) {
			hits++;
		} else {
			cache.add(value);
			misses++;
			database_reads++;
		}
		cache_reads++;
	}
	
	
}
