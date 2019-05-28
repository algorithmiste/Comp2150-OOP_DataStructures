
public class Question2 {

	public static void main(String[] args) {
	/** Punctuation marks to be removed: . , ; : ! ? ( ) -- Words to be removed: {a, an, the, is, am, are, and, or} */
		
	String s1 =	"I provide this example: a, b, or c...";
	String s2 = "Omg, so like, the fox, like, totally jumped over the lazy dog!";
	
	System.out.println(cleanText(s1));
	System.out.println(cleanText(s2));
	}
	
	public static String cleanText(String s) {
		s = s.toLowerCase(); // return lowercase version of String s
		String cleanTxt = ""; // put result in a newString
		char[] punctuationRemoved = {'.', ',', ';', ':', '!', '?', '(', ')'};
		// Create array of words to remove (with word boundary construct "\\b" )
		String[] wordsRemoved = {"\\ba\\b", "\\ban\\b", "\\bthe\\b", "\\bis\\b", "\\bam\\b", "\\bare\\b", "\\band\\b", "\\bor\\b"}; 

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (c >= 'a' && c <= 'z') 
				cleanTxt += c;
			else if (c == ' ') 
				cleanTxt += " ";	
			else 
				for (int j = 0; j < punctuationRemoved.length; j++)  // Remove unwanted punctuation chars
					if (c == punctuationRemoved[j] && i != s.length() - 1) 
						cleanTxt += "";
		}
		
		for (int i = 0; i < wordsRemoved.length; i++) { // Remove unwanted words
			String[] str = cleanTxt.split(wordsRemoved[i]);
			cleanTxt = "";
			for (int k = 0; k < str.length; k++) {
				cleanTxt += str[k];
			}
			cleanTxt = cleanTxt.trim();  // Tidy up the ends
			cleanTxt = cleanTxt.replaceAll("\\s+", " "); // "\\s+" to remove all double spaces, double tabs etc.
		}
		
		return cleanTxt;	
	}
}	



