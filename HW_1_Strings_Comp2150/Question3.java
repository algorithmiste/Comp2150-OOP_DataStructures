
public class Question3 {

	public static void main(String[] args) {
	/** You’re working on a website that collects student information, and you want to make sure that the email addresses 
* they provide are valid university emails. BUE’s emails are case-insensitive and in this format:
* [first initial].[last name][1 or more digits (optional)]@ [1 or more subdomains, each followed by a dot (optional)]bue.edu
	*/
	
	String s = "T.stArK420@cs.bue.edu";
	System.out.print(isValidEmail(s));
	}
	
	public static boolean isValidEmail(String s) {
		s = s.toLowerCase();
		char firstInitial = s.charAt(0);
		char firstDot = s.charAt(1);
		
		if (s.length() < 11) // email must contain >= 11 chars to be legitimate
			return false;
		
		String emailEnding = s.substring(s.length() - 8);
		char[] symbols = {'.', '~', '`', '!', '!', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '[', ']', '{', '}', '|', '\\', '/', ',', ';', ':', '<', '>', '?', '+', '='};
		
		if (!(firstInitial >= 'a') && !(firstInitial <= 'z') || firstDot != '.') {
			return false;
		}
		if (emailEnding.equals("@bue.edu") != true && emailEnding.equals(".bue.edu") != true) {
			return false;
		}	
		int count1 = 0;
		for (int j = 2; j < s.length() - 1; j++) {
			char ch = s.charAt(j);
			char ch1 = s.charAt(j+1);
			
			if (count1 == 0) {
				if ((j == 2) && (ch < 'a' && ch > 'z')) // Not valid if s.charAt(2) is not a Letter !
					return false;
				
				for (int i = 0; i < symbols.length; i++) 
					if (symbols[i] == ch) 
						return false;
				
				else if ((j > 2) && (ch >= 'a' && ch <= 'z')) { // Not valid if char after letter Not Number or Letter !
					for (int k = 0; k < symbols.length; k++) 
						if (symbols[k] == ch1) 
							return false;	
				}
				else if ((j > 2) && (ch >= '0' && ch <= '9')) { // ONLY Numbers allowed after NUMBERS until @ char
					if (ch1 >= 'a' && ch1 <= 'z') 
						return false;
					for (int m = 0; m < symbols.length; m++) {
						if (symbols[m] == ch1) 
							return false;
					}
				}
				if (ch == '@') {
					if (ch1 == '.')
						return false;
					else
						count1++;
				}
			}
			if (count1 == 1 && ch != '@') {
				if (ch >= 'a' && ch <= 'z') {
					if ((ch1 < 'a' && ch1 > 'z') && ch1 != '.')
						return false;
					if (ch1 >= '0' && ch1 <= '9')
						return false;
				}
				else if (ch == '.') {
					if (ch1 == '.')
						return false;
					else if (ch1 >= '0' && ch1 <= '9')
						return false;
				}
			}
		}
		return true;  
	}

}