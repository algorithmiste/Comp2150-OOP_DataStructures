
public class Question1 {

	public static void main(String[] args) {
		/** Write a method that takes a string as a parameter and returns an array containing the quantity of weapons,
		 * 	potions, and dark chocolate bars in that order. 
		 * You can assume that the input string contains only the three characters above.
		 */
	String m = "@+@+**+@***";
	System.out.print(java.util.Arrays.toString(countItems(m))); // Ok to use java.util.Arrays ?
	}
	public static int[] countItems(String s) {
		int[] count = new int[3]; // array in following order: + weapon, @ potion, * dark chocolate bar
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+')
				count[0]++;
			else if (s.charAt(i) == '@')
				count[1]++;
			else
				count[2]++;
		}
		return count;
	
	}

}
