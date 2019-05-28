import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class Question3 {
	public static Map<String, Integer> countMajors(String filename) {

		Map<String,Integer> majorMap = new TreeMap<>();
		Scanner in = null ;
		try {
			in = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException f) {
			System.out.println("File not found!");
			System.exit(1);
		}

		while(in.hasNext()) {
			String line = in.nextLine();
			int m ;
			String major = "";
			for(m = line.length() - 1; m >= 0; m--) {
				if(line.charAt(m) == ',') {
					break;
				}
			}
			major = line.substring(m + 1);

			if(!majorMap.containsKey(major)) {
				majorMap.put(major, 1);
			}
			else {
				int c = majorMap.get(major) + 1;
				majorMap.put(major, c);
			}	
		}
		return majorMap ;
	}

	public static void main(String[] args) {		
		System.out.println(countMajors("HW8StudentList.txt"));
	}

}
