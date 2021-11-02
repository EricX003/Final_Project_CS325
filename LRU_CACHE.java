
import java.util.HashMap;
import java.util.Map;

public class LRU_CACHE implements CACHE{

	static class Node {

		int value;

		Node prev;
		Node next;

		public Node(int value) {
			this.value = value;
		}

		public Node() {}

	}

	int capacity;
	int size;
	Node head;
	Node tail;
	Map<Integer, Node> cache = new HashMap<>();

	public LRU_CACHE(int capacity) {

		size = 0;
		this.capacity = capacity;

		head = new Node();
		head.prev = null;

		tail = new Node();
		tail.next = null;

		head.next = tail;
		tail.prev = head;

	}

	void add_at_head(Node node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	void remove(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		prev.next = next;
		next.prev = prev;
	}

	Node pop() {
		Node res = tail.prev;
		remove(res);
		return res;
	}

	public int get(int key) {

		if (cache.containsKey(key)) {
			Node node = cache.get(key);

			remove(node);
			add_at_head(node);

			return node.value;
		}
		return -1;
	}

	public void add(int value) {

		Node node = cache.get(value);

		if (node == null) {

			Node newNode = new Node(value);

			cache.put(value, newNode);

			if (size == capacity) {
				Node tail = pop();
				cache.remove(tail.value);
				size--;
			}

			add_at_head(newNode);
			size++;

		} else {

			node.value = value;
			remove(node);
			add_at_head(node);

		}

	}
}
