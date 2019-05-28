import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
//import java.util.ArrayList;
import java.util.Scanner;

/**
 *  @authors Casey Carr and Mun Young
 *  
 *Within a new DataFileReader class, write a method createDictWordLengths(String fileName) that reads the specified file and returns a dictionary 
 *associating word lengths with the frequency at which each length appears in the list. The keys are the lengths, while the values are the frequencies 
 *at which those lengths appear.  
 */
public class DataFileReader {

	public BSTDictionary<Integer, Double> createDictWordLengths(String fileName) throws FileNotFoundException {
		BSTDictionary<Integer, Double> wordLengthDict = new BSTDictionary<>();

		double[] wordPercentageFrequency = new double[30];
		int[] wordNominalFrequency = new int[30]; // array of the number of each possible word lengths in the list
		int numberWords = 0;

		java.util.Scanner in = null ;
		try {
			in = new java.util.Scanner(new FileReader(fileName));
		} catch (FileNotFoundException f) {
			System.out.println("File not found!");
			System.exit(1);
		}
		while(in.hasNext()) {
			String line = in.nextLine();
			numberWords++;
			int wordCount = line.length();
			wordNominalFrequency[wordCount]++;
		}
		for (int i = 0; i < wordNominalFrequency.length; i++) {
			if (wordNominalFrequency[i] != 0) {
				double frequency = (double) wordNominalFrequency[i] / numberWords;
				wordPercentageFrequency[i] = frequency;
			}
		}
		for (int j = 0; j < wordPercentageFrequency.length; j++) {
			if (wordPercentageFrequency[j] != 0) {
				wordLengthDict.add(j, wordPercentageFrequency[j]);
			}
		}
		return wordLengthDict;
	}

	/** This method should read the specified file and return a dictionary of dictionaries. The outer dictionary associates the previous n letters 
	 * of a word with an inner dictionary, which indicates the frequency at which the next letter appears based on those previous n letters */

	public BSTDictionary<String, BSTDictionary<String, Double>> createDictLetterFrequencies(String fileName, int n) throws FileNotFoundException {
		BSTDictionary<String, Double> innerDict = new BSTDictionary<>();
		BSTDictionary<String, BSTDictionary<String, Double>> outerDict = new BSTDictionary<>();

		Scanner in = null;
		try {
			in = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException f) {
			System.out.println("File not found!");
			System.exit(1);
		}
		while (in.hasNext()) {
			String line = in.nextLine();
			if (line.length() <= n)
				continue;
			for (int i = 0; i <= line.length() - n - 1; i++) {
				String stringEntry = line.substring(i, i + n);
				String nextLetter = line.charAt(i + n) + "";

				if (outerDict.contains(stringEntry)) {
					if (outerDict.getValue(stringEntry).contains(nextLetter)) {
						outerDict.getValue(stringEntry).add(nextLetter, outerDict.getValue(stringEntry).getValue(nextLetter) + 1.0);
					} 
					else {
						outerDict.getValue(stringEntry).add(nextLetter, 1.0);
					}
				} else {
					innerDict = new BSTDictionary<>();
					innerDict.add(nextLetter, 1.0);
					outerDict.add(stringEntry, innerDict);
				}
			}

			Iterator<Entry<String, BSTDictionary<String, Double>>> it = outerDict.iterator();
			while (it.hasNext()) {
				Entry<String, BSTDictionary<String, Double>> outerTempDict = it.next();
				double total = 0.0;

				Iterator<Entry<String, Double>> innerItStart = outerTempDict.getValue().iterator();
				while (innerItStart.hasNext()) {
					Entry<String, Double> innerTempDict = innerItStart.next();
					total += innerTempDict.getValue(); // calculates the total nominal value of the next letters of a particular string Entry
				}
				Iterator<Entry<String, Double>> innerItFinish = outerTempDict.getValue().iterator();
				while (innerItFinish.hasNext()) {
					Entry<String, Double> innerTempDict = innerItFinish.next();
					double percentage = innerTempDict.getValue() / total ; // calculates the percentage appearance of the next letters of a particular string Entry
					innerTempDict.setValue(percentage);
				}
			}
		}
		return outerDict;
	}
}
