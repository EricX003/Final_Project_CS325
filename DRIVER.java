import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DRIVER {
	
	static CACHE cache;
	static READER reader;
	static WRITER writer;
	static int CAPACITY = 100;
	static String cache_type;
	static String reader_type;
	static String writer_type;
	
	public static void dispAll() {
		System.out.println(cache_type + " cache, " + reader_type + " reader, " + writer_type + " writer");
		reader.display();
		writer.display();
	}
	
	public static WRITER getWriter(int id) {
		switch(id) {
		case 0:
			writer_type = "Write Back";
			return new WRITE_BACK(cache);
		case 1:
			writer_type = "Write Through";
			return new WRITE_THROUGH(cache);
		}
		return null;
	}
	
	public static CACHE getCache(int id) {
		switch(id) {
		case 0:
			cache_type = "MRU";
			return new MRU_CACHE(CAPACITY);
		case 1:
			cache_type = "LRU";
			return new LRU_CACHE(CAPACITY);
		case 2:
			cache_type = "FIFO";
			return new FIFO_CACHE(CAPACITY);
		case 3:
			cache_type = "LIFO";
			return new LIFO_CACHE(CAPACITY);
		case 4:
			cache_type = "RR";
			return new RR_CACHE(CAPACITY);
		case 5:
			cache_type = "LFU";
			return new LFU_CACHE(CAPACITY);
		}
		return null;
	}
	
	public static READER getReader(int id) {
		switch(id) {
		case 0:
			reader_type = "Read Through";
			return new READ_THROUGH(cache);
		case 1:
			reader_type = "Cache Aside";
			return new CACHE_ASIDE(cache);
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		
		int cnt = 1;
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					
					BufferedReader br = new BufferedReader(new FileReader(new File("test_data.in")));
					
					System.out.println(cnt++);
					
					cache = getCache(i);
					reader = getReader(j);
					writer = getWriter(k);
					
					long start_time = System.nanoTime();
					
					for(int idx = 0; idx < 100000; idx++) {
						String[] line = br.readLine().split(" ");
						if(line[0].equals("q")) {
							reader.query(Integer.parseInt(line[1]));
						} else {
							writer.write(Integer.parseInt(line[1]));
						}
					}
					
					long end_time = System.nanoTime();
					
					dispAll();
					
					br.close();
				}
			}
		}

	}
}
