//Implementation by Eric Xing

public interface CACHE { // Implementation of a cache replacement policy
	void add(int value); // Insert a value into the cache
	int get(int value); // Query if a value is present in the cache
}
