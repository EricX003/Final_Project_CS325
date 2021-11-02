import java.util.HashSet;

public class WRITE_THROUGH implements WRITER {
	
	HashSet<Integer> database;
	CACHE cache;
	int database_writes;
	int cache_writes;
	
	public WRITE_THROUGH(CACHE cache) {
		this.cache = cache;
		database_writes = 0;
		cache_writes = 0;
	}
	
	public void write(int value) {
		cache.add(value);
		cache_writes++;
		database.add(value);
		database_writes++;
	}
		
}
