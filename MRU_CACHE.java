
import java.util.HashMap;
import java.util.Map;

/*
 * Implementation by Eric Xing
 * MRU cache data strucutre with LinkedHashMap and Doubly-Linked list
 * Implements the CACHE interface
 * O(1) add, O(1) query
 */


public class MRU_CACHE implements CACHE {

	static class Node {

		int value;

		Node prev;
		Node next;

		public Node(int value) {
			this.value = value;
		}

		public Node() {}

	}

	int count;
	int capacity;
	
	// LinkedList
	Node head;
	Node tail;
	
	// HashMap for storing cache
	Map<Integer, Node> cache = new HashMap<>();

	public MRU_CACHE(int capacity) {

		count = 0;
		this.capacity = capacity;

		head = new Node();
		head.prev = null;

		tail = new Node();
		tail.next = null;

		head.next = tail;
		tail.prev = head;
	}

	
	// appends a new node at the tail of the LinkedList
	void add_at_tail(Node node) {

		node.next = tail;
		node.prev = tail.prev;

		tail.prev.next = node;
		tail.prev = node;
	}
	
	/*
	 * removes a specific node by redirecting connections
	 * of the subsequent and previous nodes
	 */

	void remove(Node node) {
		Node pre = node.prev;
		Node next = node.next;

		pre.next = next;
		next.prev = pre;
	}
	
	
	// removes and returns the last node
	Node pop() {
		Node res = tail.prev;
		remove(res);
		return res;
	}
	
	
	// finds 
	public int get(int key) {

		if(cache.containsKey(key)) {
			Node node = cache.get(key);

			remove(node);
			add_at_tail(node);

			return node.value;
		}
		
		return -1;
		
	}

	public void add(int value) {

		Node node = cache.get(value);

		if (node == null) {

			Node newNode = new Node(value);

			cache.put(value, newNode);

			if (count == capacity) {
				Node tail = pop();
				cache.remove(tail.value);
				count--;
			}

			add_at_tail(newNode);
			count++;

		} else {

			node.value = value;
			remove(node);
			add_at_tail(node);

		}

	}
}