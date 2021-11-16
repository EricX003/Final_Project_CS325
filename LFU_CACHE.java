import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Implementation by Eric Xing: O(1) query, O(N) insertion
 * Evicts the data that is least frequently used when cache capacity is met
 */

public class LFU_CACHE implements CACHE {

	static class Node {

		int value;
		int frequency;

		public Node(int value) {
			this.value = value;
			this.frequency = 0;
		}

	}

	int capacity;
	int count;
	
	// LinkedHashMap to store cache
	LinkedHashMap<Integer, Node> cache = new LinkedHashMap<Integer, Node>();

	public LFU_CACHE(int capacity) {
		this.capacity = capacity;
		this.count = 0;
	}

	public void add(int value) {

		if (count != capacity) { // if not full
			cache.put(value, new Node(value)); // just add the value in
			count++;
		} else { // full
			cache.remove(getLFU()); // remove the LFU
			cache.put(value, new Node(value)); // add the new value in
		}

	}

	public int getLFU() {
		
		int val = -1;
		int minFreq = Integer.MAX_VALUE;

		for (Map.Entry<Integer, Node> entry : cache.entrySet()) { // iterate through all entries, and find the LFU
			if (minFreq > entry.getValue().frequency) {
				val = entry.getKey();
				minFreq = entry.getValue().frequency;
			}
		}
		return val; // return LFU value
	}

	public int get(int value) {
		if (cache.containsKey(value)) { // O(1) query, because of HashMap
			// increase the frequency by 1 and put it back in the cache:
			Node node = cache.remove(value);
			node.frequency++;
			cache.put(value, node); 
			return node.value;
		}
		return -1;
	}

}