import java.util.Random;

public class DRIVER {
	
	static CACHE cache;
	static READER reader;
	static WRITER writer;
	
	public static READER getReader(int id) {
		switch(id) {
		case 0:
			return new READ_THROUGH(cache);
		case 1:
			return new CACHE_ASIDE(cache);
		}
		return null;
	}
	public static void main(String[] args) {
		Random random = new Random(0L);
		cache = new RR_CACHE(500000);
		reader = getReader(0);
		
		long start_time = System.nanoTime();

		for(int i = 0; i < 10000; i++) {
			cache.add(random.nextInt(1000000));
			reader.query(random.nextInt(1000000));
		}
		long end_time = System.nanoTime();
		reader.display();
		System.out.println("Run Time (ms): " + ((end_time - start_time) / 1000000));

		
		
	}
}
