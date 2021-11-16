// READER interface

public interface READER {
	
	// Query values in the cache, returns the value if it is contained in the cache, otherwise, -1
	void query(int value);
	
	//Display the important information about the reader 
	void display();
	
}
