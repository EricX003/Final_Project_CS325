import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

public class DATA_GENERATION {
	
	static LinkedHashSet<Integer> universe = new LinkedHashSet<Integer>();
	static Random random = new Random(0L);
	
	
	public static Integer get() {
		
		Iterator<Integer> itr = universe.iterator();
		int threshold = (int) (random.nextDouble() * universe.size());
		int idx = 0;
		
		while(idx < threshold - 1) {
			itr.next();
			idx++;
		}
		
		return itr.next();
		
	}
	
	public static void main(String[] args) throws IOException {
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("test_data.in")));
		
		
		for(int i = 0; i < 1000000; i++) {
			if(random.nextDouble() < 0.50000) {
				pw.println("q " + get());
			} else {
				
				int num = random.nextInt(10000000);
				
				while(universe.contains(num)) {
					num = random.nextInt(10000000);
				}
				
				pw.println("a " + num);
				universe.add(num);
			}
		}
		
		pw.close();
	}
}
