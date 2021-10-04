import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MRU_Cache {

	static class Node {
		
		int key;
		int value;

		Node prev;
		Node next;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
		
		public Node( ) {}

	}

	private int count;
	private int capacity;
	private Node head;
	private Node tail;
	private Map<Integer, Node> cache = new HashMap<>();

    public MRU_Cache(int capacity) {
        count = 0;
        this.capacity = capacity;

        head = new Node();
        head.prev = null;

        tail = new Node();
        tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

	private void addNode(Node node) {

		node.next = tail;
		node.prev = tail.prev;

		tail.prev.next = node;
		tail.prev = node;
	}

	private void removeNode(Node node) {
		Node pre = node.prev;
		Node next = node.next;

		pre.next = next;
		next.prev = pre;
	}


	private Node popTail() {
		Node res = tail.prev;
		removeNode(res);
		return res;
	}

	public int get(int key) {
		
		Node node = cache.get(key);

		removeNode(node);
		addNode(node);

		return node.value;
	}

	public void put(int key, int value) {
		Node node = cache.get(key);

		if (node == null) {

			Node newNode = new Node(key, value);

			cache.put(key, newNode);

			if (count == capacity) {
				Node tail = popTail();
				cache.remove(tail.key);
				count--;
			}

			addNode(newNode);

			count++;
		} else {
			node.value = value;
			removeNode(node);
			addNode(node);
		}

	}
}

class test {
	public static void main(String[] args) throws IOException {
		System.out.println("Hello, World!");
	}
}