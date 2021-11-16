public class RR_CACHE implements CACHE {
	int[] cache;
	int capacity;
	int size;

	public RR_CACHE(int capacity) {
		cache = new int[capacity];
		this.capacity = capacity;
		this.size = 0;
	}

	public void removeRandom() {
		int index = (int) (Math.random() * size);

		for (int i = index; i < capacity - 1; i++) {
			cache[i] = cache[i + 1];
		}
	}

	public void add(int value) {

		if (size == capacity) {
			removeRandom();
			size--;
		}
		
		int i = size - 1;

		while ((i > -1) && (cache[i] > value)) {
			cache[i + 1] = cache[i--];
		}
		cache[i + 1] = value;

		size++;
	}

	public int get(int value) {
		int left = 0;
		int right = cache.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (cache[mid] == value) {
				return value;
			}
			if (cache[mid] < value) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

}
