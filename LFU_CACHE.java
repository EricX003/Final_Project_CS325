
import java.util.LinkedHashMap;
import java.util.Map;

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
	LinkedHashMap<Integer, Node> cache = new LinkedHashMap<Integer, Node>();

	public LFU_CACHE(int capacity) {
		this.capacity = capacity;
		this.count = 0;
	}

	public void add(int value) {
		if (count != capacity) {
			cache.put(value, new Node(value));
		} else {
			cache.remove(getLFU());
			cache.put(value, new Node(value));
		}
	}

	public int getLFU() {
		int val = -1;
		int minFreq = Integer.MAX_VALUE;

		for (Map.Entry<Integer, Node> entry : cache.entrySet()) {
			if (minFreq > entry.getValue().frequency) {
				val = entry.getKey();
				minFreq = entry.getValue().frequency;
			}
		}
		return val;
	}

	public int get(int value) {
		if (cache.containsKey(value)) {
			Node node = cache.remove(value);
			node.frequency++;
			cache.put(value, node);
			return node.value;
		}
		return -1;
	}

}