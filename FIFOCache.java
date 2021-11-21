import java.util.HashSet;
import java.util.LinkedList;

/*
 * Implementation by Eric Xing
 * FIFO cache data structure with Java LinkedList implementation
 * Implements the CACHE interface
 * O(1) add, O(1) query
 */

public class FIFOCache implements Cache {

	LinkedList<Integer> cache = new LinkedList<Integer>(); // LinkedList implementation of Queue
	HashSet<Integer> elements = new HashSet<Integer>(); // Storing the elements

	int count;
	int capacity;

	public FIFOCache(int capacity) { // constructor
		this.capacity = capacity;
		this.count = 0;
	}

	public void add(int value) {

		if (count == capacity) { // if the cache is full
			count--; // count == capacity after insertion
			Integer removed = cache.removeLast(); // satisfies the property of FIFO data structure (queue)
			elements.remove(removed); // remove from set
		}
		elements.add(value);
		cache.addFirst(value); // insert into beginning, remove at end
		count++; // update count
	}

	public int get(int value) {
		if (elements.contains(value)) { // use HashSet O(1) lookup
			return value; // found
		}
		return -1; // not found
	}

}
