/** 
 * @authors Casey Carr and Mun Young
 * 
 * Write a client program that allows the user to enter a data file, a value for n (from part 3 above), and a desired number of words to generate. 
 * Your program should then generate and display that number of words.
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientProgram {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter your dictionary data file: ");
		String fileName = input.nextLine();

		int subSearchLength;

		System.out.print("Enter a value for n (i.e. the subString search length for the dictionary): ");
		subSearchLength = input.nextInt();

		int numberWords;
		System.out.print("Enter the number of words you would like to generate: ");
		numberWords = input.nextInt();

		DataFileReader fileReader = new DataFileReader();
		BSTDictionary<Integer, Double> dictWordLengths = null;
		ArrayList<BSTDictionary<String, BSTDictionary<String, Double>>> subSearchLengthList = new ArrayList<>();
		ArrayList<String> word = new ArrayList<>();

		for (int i = 1; i <= subSearchLength; i++) {
			try {
				subSearchLengthList.add(fileReader.createDictLetterFrequencies(fileName, i));
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
				System.exit(1);
			}
		}
		try {
			dictWordLengths = fileReader.createDictWordLengths(fileName);
		} catch (FileNotFoundException f) {
			System.out.println("File not found!");
			System.exit(1);
		}
		KeyGen<Integer> keyGenerator1 = new KeyGen<>();
		KeyGen<String> keyGenerator2 = new KeyGen<>();

		for (int i = 0; i < numberWords; i++) {

			String randomLetter = "" + (char) ((int) (Math.random() * 26) + 'a');
			int wordLength = keyGenerator1.generateKey(dictWordLengths);

			String toReturn = randomLetter;
			BSTDictionary<String, Double> innerDictionary = null;

			for (int j = 0; j < wordLength - 1; j++) {
				if (j < subSearchLength) {
					if (subSearchLengthList.get(j).contains(toReturn)) {
						innerDictionary = subSearchLengthList.get(j).getValue(toReturn);
					} 
					else break;
					String genKey = keyGenerator2.generateKey(innerDictionary);
					toReturn += genKey;
				} 
				else {
					String strip = toReturn.substring(toReturn.length() - subSearchLength);
					if (subSearchLengthList.get(subSearchLength - 1).contains(strip)) {
						innerDictionary = subSearchLengthList.get(subSearchLength - 1).getValue(strip); 
					} 
					else 
						break;
					String genKey = keyGenerator2.generateKey(innerDictionary);
					toReturn += genKey;
				}
			}
			word.add(toReturn);
		}

		System.out.println(word);
	}


}


