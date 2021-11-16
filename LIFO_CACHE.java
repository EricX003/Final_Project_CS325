import java.util.HashSet;
import java.util.LinkedList;

/*
 * Implementation by Eric Xing
 * LIFO cache data structure with Java LinkedList implementation
 *
 * Implements the CACHE interface
 * O(1) add, O(1) query
 */

public class LIFO_CACHE implements CACHE {

	LinkedList<Integer> cache = new LinkedList<Integer>(); // LinkedList implementation of Queue
	HashSet<Integer> elements = new HashSet<Integer>(); // Storing the elements

	int count;
	int capacity;

	public LIFO_CACHE(int capacity) { // constructor
		this.capacity = capacity;
		this.count = 0;
	}

	public void add(int value) {
		
		if(count > capacity) {
			System.out.println("bad");
		}
		
		if (count == capacity) { // if the cache is full
			count--; // count == capacity after insertion
			Integer removed = cache.removeFirst(); // satisfies the property of LIFO data structure (queue)
			elements.remove(removed); // insert into the HashSet to maintain O(1) query times
		}
		elements.add(value);
		cache.addFirst(value); // insert into beginning, remove at beginning
		count++; // update count 
	}

	public int get(int value) {
		if (elements.contains(value)) { // use HashSet O(1) lookup
			return value; // found
		}
		return -1; // not found
	}


}
