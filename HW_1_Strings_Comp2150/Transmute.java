
public class Transmute {
	
	public static String transmute(String s) {
		char z = s.charAt(s.length()-1);
		char a = s.charAt(0);
		String mid = s.substring(1, s.length()-1);
		
		if (s.length() <= 1)
			return s;
		else
			return ("" + z + mid + a);
			
	}
	public static void main(String[] args) {
		String s = "POTUS AND FLOTUS";
		System.out.print(transmute(s));
	}

}
