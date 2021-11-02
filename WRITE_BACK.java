import java.util.HashSet;
import java.util.LinkedHashSet;

public class WRITE_BACK implements WRITER {
	
	HashSet<Integer> database;
	CACHE cache;
	int database_writes;
	int cache_writes;
	static final long THRESHOLD = 60000;
	long last;
	LinkedHashSet<Integer> waiting;
	
	public WRITE_BACK(CACHE cache) {
		this.cache = cache;
		database_writes = 0;
		cache_writes = 0;
		last = System.currentTimeMillis();
		waiting = new LinkedHashSet<Integer>();
	}
	
	public void write(int value) {
		if(System.currentTimeMillis() - last > THRESHOLD) {
			for(Integer val : waiting) {
				database.add(val);
				database_writes++;
			}
		} else {
			cache.add(value);
			cache_writes++;
		}
	}
}
