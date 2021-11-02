
import java.util.ArrayList;

public class RR_CACHE implements CACHE{
	ArrayList<Integer> cache;
	int capacity;
	
	public RR_CACHE(int capacity) {
		cache = new ArrayList<Integer>(capacity);
		this.capacity = capacity;
	}
	
	public void add(int value) {
		if(cache.size() == capacity) {
			cache.remove((int) Math.random() * capacity);
		}
		
		cache.add(value);
	}
	
	public int get(int value) {
		if(cache.contains(value)) {
			return value;
		}
		return -1;
	}
	
}
