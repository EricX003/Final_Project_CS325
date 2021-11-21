import java.util.HashMap;
import java.util.Map;

/*
 * Implementation by Eric Xing
 * MRU cache data structure with LinkedHashMap and Doubly-Linked list
 * Implements the CACHE interface
 * O(1) add, O(1) query
 */

public class MRUCache implements Cache {

	// doubly-linked node
	static class Node {

		int value;

		Node prev;
		Node next;

		public Node(int value) {
			this.value = value;
		}

		public Node() {
		}

	}

	// Size variables
	int count;
	int capacity;

	// LinkedList
	Node head;
	Node tail;

	// HashMap for storing cache
	Map<Integer, Node> cache = new HashMap<>();

	// Constructor
	public MRUCache(int capacity) {

		// Initialize attributes
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
	 * removes a specific node by redirecting connections of the subsequent and
	 * previous nodes
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

		// use hashmap to get O(1) query
		if (cache.containsKey(key)) {
			Node node = cache.get(key);

			remove(node);
			add_at_tail(node);

			return node.value;
		}

		// not found
		return -1;

	}

	public void add(int value) {

		// make a new node for the value
		Node newNode = new Node(value);

		// insert it into the HashMap
		cache.put(value, newNode);

		// Remove a value if neccesary to make space
		if (count == capacity) {
			Node tail = pop();
			cache.remove(tail.value);
			count--;
		}

		// Append it and update size accordingly
		add_at_tail(newNode);
		count++;

	}
}