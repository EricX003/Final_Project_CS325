
import java.util.HashMap;
import java.util.LinkedList;

public class LIFO_CACHE implements CACHE {

	LinkedList<Integer> cache = new LinkedList<Integer>();
	HashMap<Integer, Integer> elements = new HashMap<Integer, Integer>();

	int count;
	int capacity;

	public LIFO_CACHE(int capacity) {
		this.capacity = capacity;
		this.count = 0;
	}

	public void add(int value) {
		if (get(value) != value) {
			if (count == capacity) {
				count--;
				int removed = cache.removeFirst();
				int frequency = (int) elements.remove(removed) - 1;
				if (frequency != 0) {
					elements.put(removed, frequency);
				}
			}
			cache.addFirst(value);
			if (elements.containsKey(value)) {
				int frequency = elements.remove(value) + 1;
				elements.put(value, frequency);

			} else {
				elements.put(value, 1);
			}
			count++;
		}
	}

	public int get(int value) {
		if (elements.containsKey(value)) {
			return value;
		}
		return -1;
	}

}
