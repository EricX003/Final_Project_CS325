/*
 * Implementation by Eric Xing
 */

public interface Writer {
	// Get number of Cache Writes, this has to be added to Reader Writes

	int getCacheWrites();

	// Write data
	void write(int value);

	// Display information
	void display();
}