
/** Casey Carr && Mun Young */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Client_TooMuchToWatch {
	public Filter f; 
	public static ArrayList<Filter> filterList = new ArrayList<>();
	public static void main(String[] args) {
		// Read the data file and create a new Media object for each line in the file
		try {
			System.out.println();
			DataFileReader.readMediaData("NetflixUSA_Oct15_cleaned.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(1);
		}
		/** Allow the user to specify any filters: */ 
		System.out.println("Given that you have \"TooMuchToWatch\", I have specified the following filters to facilitate your search:\n");
		System.out.println("**FILTER FORMAT** - add a filter by entering the following: field relation target, e.g. year > 2003\n");
		System.out.println("Possible fields: genre, rating, title, year"); 
		System.out.println("Possible relation for genre/title: ="); 
		System.out.println("Possible relations for rating: <, >"); 
		System.out.println("Possible relations for year: <, >,"); 
		System.out.println("Possible targets: string name (for genre or title), double value (for rating), integer value (for year)\n"); //exceptions
		@SuppressWarnings("resource")

		//*** Note change answer to String and in.NextInt() to in.NextLine to ensure that Integer.parseint(answer) does Not throw a NumberFormatException
		Scanner in = new Scanner(System.in); String answer = "4"; int answer1 = 4;
		String[] filterArray = new String[3];
		do { 
			String filter; 
			try {
				System.out.println("Menu: type \"0\" to REMOVE an existing filter, \"1\" to ADD a filter, \"2\" to ACCEPT the filters added or removed, or \"3\" to view your CURRENT FILTERS.");
				answer = in.nextLine(); //need InputMismatchException thrown here for non integer input
				answer1 = Integer.parseInt(answer);
				// to advance the Scanner past answer

			} catch (InputMismatchException i) {
				System.out.println("\nInput Error!");
				continue;
			} catch (NumberFormatException n) {
				System.out.println("\nInput Error!");
				continue;
			}
			if (answer1 != 0 && answer1 != 1 && answer1 != 2 && answer1 != 3) {
				System.out.println("\nInput Error: READ the MENU!");
			}
			if (answer1 == 1) {
				System.out.println("Enter a filter:");
				try {
					filter = in.nextLine();
					filter = filter.trim();

					if (filter.split(" ").length == 3) {
						filterArray = filter.split(" ");
						filterArray[0] = filterArray[0].toLowerCase().trim();
						filterArray[1] = filterArray[1].trim();
						filterArray[2] = filterArray[2].trim();
						// To prevent invalid filter inputs, I have implemented the following Error handlers before constructing the filter object
						if (!filterArray[0].equals("genre") && !filterArray[0].equals("rating") && !filterArray[0].equals("title") && !filterArray[0].equals("year")) {
							DataFileReader.errorPrinter();
							continue;
						}
						if ((filterArray[0].equals("genre") && !filterArray[1].equals("=")) || (filterArray[0].equals("title") && !filterArray[1].equals("="))){
							DataFileReader.errorPrinter();
							continue;
						}
						if (!filterArray[1].equals("=") && !filterArray[1].equals("<") && !filterArray[1].equals(">")) {
							DataFileReader.errorPrinter();
							continue;
						}
						if ((filterArray[0].equals("rating") && !filterArray[1].equals("<")) && (filterArray[0].equals("rating") && !filterArray[1].equals(">")) ||
								(filterArray[0].equals("year") && !filterArray[1].equals("<")) && (filterArray[0].equals("year") && !filterArray[1].equals(">"))) {
							DataFileReader.errorPrinter();
							continue;
						}
						if (filterArray[0].equals("year")) {
							try {
								Integer.parseInt(filterArray[2]);
							} catch (NumberFormatException n) {
								DataFileReader.errorPrinter();
								continue;
							}
						}
						if (filterArray[0].equals("rating")) {
							try {
								Double.parseDouble(filterArray[2]);
							} catch (NumberFormatException n) {
								DataFileReader.errorPrinter();
								continue;
							}
						}
						Filter f = new Filter(filterArray[0], filterArray[1], filterArray[2]);
						filterList.add(f);
					}
					else if (filter.split(" ").length != 3){ // potentially multiple word titles / titres bizarres

						if (filter.contains("<=") || filter.contains(">=")) {
							int targetIndex; 
							for (targetIndex = filter.length() - 1; filter.charAt(targetIndex) != '='; targetIndex--) {}
							int tempTargetIndex = targetIndex + 1;
							filterArray[2] = filter.substring(tempTargetIndex).trim(); // get target
							filterArray[1] = filter.substring(targetIndex - 1, tempTargetIndex).trim(); // get relation
							filterArray[0] = filter.substring(0, targetIndex - 1).toLowerCase().trim(); // get field 

							// To prevent invalid filter inputs, I have implemented the following Error handlers before constructing the filter object
							if (!filterArray[0].equals("genre") && !filterArray[0].equals("rating") && !filterArray[0].equals("title") && !filterArray[0].equals("year")) {
								DataFileReader.errorPrinter();
								continue;
							}
							if ((filterArray[0].equals("genre") && !filterArray[1].equals("=")) && (filterArray[0].equals("title") && !filterArray[1].equals("="))){
								DataFileReader.errorPrinter();
								continue;
							}
							if (!filterArray[1].equals("=") && !filterArray[1].equals("<") && !filterArray[1].equals(">")) {
								DataFileReader.errorPrinter();
								continue;
							}
							if (((filterArray[0].equals("rating") && !filterArray[1].equals("<")) && (filterArray[0].equals("rating") && !filterArray[1].equals(">"))) ||
									((filterArray[0].equals("year") && !filterArray[1].equals("<")) && (filterArray[0].equals("year") && !filterArray[1].equals(">")))) {
								DataFileReader.errorPrinter();
								continue;
							}
							if (filterArray[0].equals("year")) {
								try {
									Integer.parseInt(filterArray[2]);
								} catch (NumberFormatException n) {
									DataFileReader.errorPrinter();
									continue;
								}
							}
							if (filterArray[0].equals("rating")) {
								try {
									Double.parseDouble(filterArray[2]);
								} catch (NumberFormatException n) {
									DataFileReader.errorPrinter();
									continue;
								}
							}
							Filter f = new Filter(filterArray[0], filterArray[1], filterArray[2]);
							filterList.add(f);
						}

						else {
							int relationIndex; int targetIndex; 
							for (targetIndex = filter.length() - 1; filter.charAt(targetIndex) != '<' && filter.charAt(targetIndex) != '>' && filter.charAt(targetIndex)!= '='; targetIndex--) {}
							targetIndex = targetIndex + 1;
							filterArray[2] = filter.substring(targetIndex).trim(); // get target
							for (relationIndex = filter.length() - 1; filter.charAt(relationIndex) != '<' && filter.charAt(relationIndex) != '>' && filter.charAt(relationIndex) != '='; relationIndex--) {}
							filterArray[1] = "" + filter.charAt(relationIndex); // get relation
							filterArray[0] = filter.substring(0, relationIndex).toLowerCase().trim(); // get field

							// To prevent invalid filter inputs, I have implemented the following Error handlers before constructing the filter object
							if (!filterArray[0].equals("genre") && !filterArray[0].equals("rating") && !filterArray[0].equals("title") && !filterArray[0].equals("year")) {
								DataFileReader.errorPrinter();
								continue;
							}
							if ((filterArray[0].equals("genre") && !filterArray[1].equals("=")) || (filterArray[0].equals("title") && !filterArray[1].equals("="))){
								DataFileReader.errorPrinter();
								continue;
							}
							if (!filterArray[1].equals("=") && !filterArray[1].equals("<") && !filterArray[1].equals(">")) {
								DataFileReader.errorPrinter();
								continue;
							}
							if (((filterArray[0].equals("rating") && !filterArray[1].equals("<")) && (filterArray[0].equals("rating") && !filterArray[1].equals(">"))) ||
									((filterArray[0].equals("year") && !filterArray[1].equals("<")) && (filterArray[0].equals("year") && !filterArray[1].equals(">")))) {
								DataFileReader.errorPrinter();
								continue;
							}
							if (filterArray[0].equals("year")) {
								try {
									Integer.parseInt(filterArray[2]);
								} catch (NumberFormatException n) {
									DataFileReader.errorPrinter();
									continue;
								}
							}
							if (filterArray[0].equals("rating")) {
								try {
									Double.parseDouble(filterArray[2]);
								} catch (NumberFormatException n) {
									DataFileReader.errorPrinter();
									continue;
								}
							}
							Filter f = new Filter(filterArray[0], filterArray[1], filterArray[2]);
							filterList.add(f);
						}
					}

				} catch (NullPointerException n) {
					System.out.println("\nInvalid Input!");
					continue;
				} catch (IndexOutOfBoundsException x) {
					System.out.println("\nInvalid Input!");
					continue;
				}
				System.out.println();
				DataFileReader.getCurrentList(filterList);
				if (!DataFileReader.currentList.isEmpty()) {
					for (int c = 0; c < DataFileReader.currentList.size(); c++) {
						System.out.println(DataFileReader.currentList.get(c));
					}
					System.out.println("\nNumber of matches found: " + DataFileReader.currentList.size());
					System.out.println();
				}
				else {
					System.out.println("Sorry, no matches found for that particular filter. Try a different one.");
					filterList.remove(filterList.size() - 1); // remove the last filter added
					System.out.println("Current Filters: ");
					for (int k = 0; k < filterList.size(); k++) {
						System.out.println("(" + k + ") " + filterList.get(k));
					}
					System.out.println();
					continue;
				}
			}
			else if (answer1 == 2) {
				System.out.println();
				DataFileReader.getCurrentList(filterList);
				if (DataFileReader.currentList.size() == DataFileReader.masterList.size()) {
					System.out.println("You have not added any filters yet! Be sure to add a filter before entering \"2\"\nTry Again by entering \"1\" to add a filter.\n");
					answer = "1";
				}
				else if (!DataFileReader.currentList.isEmpty()) {
					for (int c = 0; c < DataFileReader.currentList.size(); c++) {
						System.out.println(DataFileReader.currentList.get(c));
					}
					System.out.println("\nFilter complete. Number of matches found: " + DataFileReader.currentList.size());
				}
				else {
					System.out.println("Sorry, no match found! Try a different filter.");
					System.out.println("Note: Be sure not to use \"<\" or \">\" with genre or title filters!\n");
					answer = "1";
				}
			}
			// for remove: remove index of filter list containing filter to be removed then EXECUTE again
			else if (answer1 == 0) {
				//Specify filter removal then display filters to remove
				if (!filterList.isEmpty()) {
					System.out.println("Type the number from the following list indicating the filter that you would like to remove:");
					for (int k = 0; k < filterList.size(); k++) {
						System.out.println("(" + k + ") " + filterList.get(k));
					}
					// Add Index out of bounds exception/ input mismatch exception
					int indexToRemove = 0;
					try {
						indexToRemove = in.nextInt();
						in.nextLine();
						filterList.remove(indexToRemove);
					} catch (InputMismatchException i) {
						System.out.println("\nInput Error 1! You must enter one of the numbers corresponding to the filter to be removed.\nTry Again by entering \"0\".\n");
						in.nextLine();
						continue;
					} catch (IndexOutOfBoundsException e) {
						System.out.println("\nInput Error 2! You must enter one of the numbers corresponding to the filter to be removed.\nTry Again by entering \"0\".\n");
						in.nextLine();
						continue;
					}					
					DataFileReader.getCurrentList(filterList);
					if (!DataFileReader.currentList.isEmpty()) {
						for (int c = 0; c < DataFileReader.currentList.size(); c++) {
							System.out.println(DataFileReader.currentList.get(c));
						}
						System.out.println("\nNumber of matches found after filter removed: " + DataFileReader.currentList.size());
					}
					System.out.println();
				}
				else {
					System.out.println("\nNo filters have been added yet! You cannot remove filters that do not exist!\n");
				}
			}
			else if (answer1 == 3) {
				if (!filterList.isEmpty()) {
					System.out.println("Current Filters: ");
					for (int k = 0; k < filterList.size(); k++) {
						System.out.println("(" + k + ") " + filterList.get(k));
					}
					System.out.println();
				}
				else {
					System.out.println("\nNo filters have been added yet! You cannot view filters that do not exist!\n");
				}
			}

		} while (answer1 != 2);

	}
}
