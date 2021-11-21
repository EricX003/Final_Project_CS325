
import java.util.HashMap;
import java.util.Map;

/*
 * Implementation by Eric Xing
 * LRU cache data structure with LinkedHashMap and Doubly-Linked list
 * Implements the CACHE interface
 * O(1) add, O(1) query
 */

public class LRUCache implements Cache {

	// doubly linked node
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

	// attributes

	// max capacity
	int capacity;
	// current size
	int size;
	// head and tail of doubly-linked list
	Node head;
	Node tail;
	// HashMap of all values
	Map<Integer, Node> cache = new HashMap<>();

	// Constructor
	public LRUCache(int capacity) {

		size = 0;
		this.capacity = capacity;

		head = new Node();
		head.prev = null;

		tail = new Node();
		tail.next = null;

		head.next = tail;
		tail.prev = head;

	}

	// set the connections at the head to insert a new node
	void add_at_head(Node node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	// remove the node, by setting the next and prev
	void remove(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		prev.next = next;
		next.prev = prev;
	}

	// remove the tail node
	Node pop() {
		Node res = tail.prev;
		remove(res);
		return res;
	}

	// query the key using the HashMap, and put it at the head (LRU)
	public int get(int key) {
		// O(1) HashMap query
		if (cache.containsKey(key)) {
			Node node = cache.get(key);

			remove(node);
			add_at_head(node);

			return node.value;
		}

		// not found
		return -1;
	}

	// insert a new value
	public void add(int value) {

		// made a new node for the value
		Node newNode = new Node(value);

		// insert it into the HashMap
		cache.put(value, newNode);

		// Make room if needed
		if (size == capacity) {
			Node last = pop();
			cache.remove(last.value);
			size--;
		}

		// insert it
		add_at_head(newNode);
		size++;

	}
}
