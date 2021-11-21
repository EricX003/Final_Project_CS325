//Implementation by Eric Xing
//Interface for the 6 cache replacement policies tested

public interface Cache {

	// Insert a value into the cache, evicting an element if needed
	void add(int value);

	// Query if a value is present in the cache
	// Returns value if present, -1 if not
	int get(int value);

}
