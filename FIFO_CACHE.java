
import java.util.HashMap;
import java.util.LinkedList;

public class FIFO_CACHE implements CACHE {

	LinkedList<Integer> cache = new LinkedList<Integer>();
	HashMap<Integer, Integer> elements = new HashMap<Integer, Integer>();
	
	int count;
	int capacity;

	public FIFO_CACHE(int capacity) {
		this.capacity = capacity;
		this.count = 0;
	}

	public void add(int value) {
		if(count == capacity) {
			count--;
			cache.removeLast();
		}
		cache.addFirst(value);
		if(elements.containsKey(value)) {
			int frequency = elements.remove(value) + 1;
			elements.put(value, frequency);
			
		} else {
			elements.put(value, 1);
		}
		count++;
	}
	
	public int get(int value) {
		if(elements.containsKey(value)) {
			int frequency = elements.remove(value) - 1;
			if(frequency != 0) {
				elements.put(value, frequency);
			}
			
			return value;
		}
		return -1;
	}
	
	

}
