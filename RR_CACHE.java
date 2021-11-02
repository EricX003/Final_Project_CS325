public class RR_CACHE implements CACHE {
	int[] cache;
	int capacity;
	int size;

	public RR_CACHE(int capacity) {
		cache = new int[capacity];
		this.capacity = capacity;
		this.size = 0;
	}

	public void add(int value) {
		
		int[] newCache = new int[capacity];
		
		if (size == capacity) {
			int removed = (int) Math.random() * size;
			while (removed < capacity - 1) {
				cache[removed] = cache[++removed];
			}
		}
		
		int index = 0;
		while(cache[index] <= value && index < capacity - 1) {
			newCache[index] = cache[index];
			index++;
		}
		
		newCache[index++] = value;
		
		while(index < capacity) {
			newCache[index] = cache[index - 1];
			index++;
		}
		
		cache = newCache;

	}

	public int get(int value) {
		int left = 0;
		int right = cache.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (cache[mid] == value) {
				return mid;
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
