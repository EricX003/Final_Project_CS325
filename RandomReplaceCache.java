public class RandomReplaceCache implements Cache {

	// Array for the elements
	int[] cache;

	// Size vars
	int capacity;
	int size;

	// Initialize everything
	public RandomReplaceCache(int capacity) {
		cache = new int[capacity];
		this.capacity = capacity;
		this.size = 0;
	}

	// remove a random element to make room
	public void removeRandom() {

		// find a random index to remove
		int index = (int) (Math.random() * size);

		// shift everything after the removed element left by 1,
		for (int i = index; i < size - 1; i++) {
			cache[i] = cache[i + 1];
		}
		// update size
		size--;
	}

	// insert a value
	public void add(int value) {

		// if full
		if (size == capacity) {
			removeRandom();
		}

		// shift everything over
		int i = size - 1;

		// while we haven't passed the insertion point, shift
		while ((i >= 0) && (cache[i] > value)) {
			// shifting
			cache[i + 1] = cache[i--];
		}
		// insert
		cache[i + 1] = value;

		// update size
		size++;
	}

	// Standard binary search
	public int get(int value) {
		// left and right
		int left = 0;
		int right = size - 1;

		// while hasn't crossed
		while (left <= right) {
			// Find middle
			int mid = left + (right - left) / 2;
			// Found
			if (cache[mid] == value) {
				return value;
			}
			if (cache[mid] < value) {
				// Search the right sub-array
				left = mid + 1;
			} else {
				// search the left subarray
				right = mid - 1;
			}
		}
		// not found
		return -1;
	}

}
