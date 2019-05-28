
/** Casey Carr && Mun Young */

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class DataFileReader {
	public static ArrayList<Media> masterList;
	public static ArrayList<Media> currentList ;

	// made exception for title with no ratings i.e. if no rating, instantiate to 0;	
	@SuppressWarnings("resource")
	public static ArrayList<Media> readMediaData(String filePath) throws FileNotFoundException, ArrayIndexOutOfBoundsException, NumberFormatException {	
		Scanner s = null;
		try {
			s = new Scanner(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(1);
		}
		int rows = 0;
		while (s.hasNextLine()) {
			rows++;
			s.nextLine();
		}
		s = new Scanner(new FileReader(filePath));
		masterList = new ArrayList<Media>(rows);
		while (s.hasNextLine()) {
			for (int i = 0; i < rows; i++) {
				String line = s.nextLine();
				line = line.trim();
				// Grab data from String line
				// YEAR
				int yearEndIndex; int yearStartIndex; int startYear; int endYear;
				for (yearEndIndex = line.length() - 1; line.charAt(yearEndIndex) != ')'; yearEndIndex--);
				for (yearStartIndex = line.length() - 1; line.charAt(yearStartIndex) != '('; yearStartIndex--);

				// to check if a year is present within the parentheses
				boolean yearMatch = false; 
				for (int e = yearStartIndex; line.charAt(e) != ')'; e++) {
					if ((line.charAt(e) >= '0' && line.charAt(e) <= '9')) {
						yearMatch = true;
					}
					else {
						yearMatch = false;
					}
				}
				if (yearMatch) {
					if (line.substring(yearStartIndex + 1, yearEndIndex).length() > 4) {

						String[] doubleYear = line.substring(yearStartIndex + 1, yearEndIndex).split("-");
						//yearSnatch = doubleYear[0] + "-" + doubleYear[1];
						startYear = Integer.parseInt(doubleYear[0]); endYear = Integer.parseInt(doubleYear[1]);
					}
					else {
						startYear = Integer.parseInt(line.substring(yearStartIndex + 1, yearEndIndex)); 

						endYear = Integer.parseInt(line.substring(yearStartIndex + 1, yearEndIndex));
					}
				}
				else {
					startYear = Integer.parseInt("0"); 
					endYear= Integer.parseInt("0");
				}
				// TITLE
				String[] yearSplit = line.split("\\|"); // yearSplit[0] contains title
				String titleStringWithYear = yearSplit[0].trim();

				String titleSnatch; 
				if (startYear == Integer.parseInt("0")) { 
					titleSnatch = titleStringWithYear.substring(0, titleStringWithYear.length() - 3).trim();
				}
				else {
					int start;
					for (start = titleStringWithYear.length() - 1; titleStringWithYear.charAt(start) != '('; start--) {}	
					String strToRemove = titleStringWithYear.substring(start, titleStringWithYear.length());
					String titleYearRemoved = titleStringWithYear.replace(strToRemove, "");
					titleSnatch = titleYearRemoved.trim();
				}
				// RATING
				double ratingSnatch; 
				int ratingEndIndex; int ratingStartIndex;
				// to check if a rating is present within the parentheses
				boolean ratingMatch = false; 
				int comma;
				for (comma = line.length() - 1; line.charAt(comma) != ','; comma--) {}
				if ((line.charAt(comma - 1) != 's')) {
					ratingMatch = false;
				}
				else {
					ratingMatch = true;
				}
				if (ratingMatch) {
					for (ratingEndIndex = line.length() - 1; !line.substring(ratingEndIndex).contains(" stars"); ratingEndIndex--);
					for (ratingStartIndex = ratingEndIndex - 1; !(line.charAt(ratingStartIndex) == ' '); ratingStartIndex--);
					ratingSnatch = Double.parseDouble(line.substring(ratingStartIndex + 1, ratingEndIndex));  
				}
				else {
					ratingSnatch = 0;
				}
				// LENGTH
				String[] lengthSplit = yearSplit[1].split(",");
				String lengthString;
				double lengthSnatch = 0;
				String seriesLengthType = "";

				if (lengthSplit.length < 2) 
					lengthString = " ";
				else 
					lengthString = lengthSplit[1];

				boolean seriesMatch = lengthString.contains("Season")  || lengthString.contains("Episode") || lengthString.contains("Volume")    ||
						lengthString.contains("Part")    || lengthString.contains("Series")  || lengthString.contains("Collection")||
						lengthString.contains("Chapter") || lengthString.contains("Special") || lengthString.contains("Set");	

				boolean movieMatch = lengthString.charAt(lengthString.length() - 1) == 'm' || lengthString.contains("hr");

				if (!movieMatch && !seriesMatch) {
					lengthSnatch = 0;
				}

				if (seriesMatch) {
					String[] splitLength = lengthString.split(" ");
					seriesLengthType = splitLength[2];
					lengthSnatch = Integer.parseInt(splitLength[1]);
				}
				else if (movieMatch) {

					if (lengthString.charAt(lengthString.length() - 1) == 'm' && lengthString.contains("hr")) {  // if we have hr and m
						String[] splitLength = lengthString.split(" "); 

						String hr = splitLength[1]; 
						String[] hrFinal = hr.split("hr");
						int hourSnatch = Integer.parseInt(hrFinal[0]);

						String min = splitLength[2];
						String[] minFinal = min.split("m");
						int minuteSnatch = Integer.parseInt(minFinal[0]);

						lengthSnatch = (double) hourSnatch + ((double) minuteSnatch / 60) ;
					}
					else if (lengthString.charAt(lengthString.length() - 1) != 'm' && lengthString.contains("hr")) { // if we have ONLY hr
						String[] splitLength = lengthString.split(" "); 

						String hr = splitLength[1];
						String[] hrFinal = hr.split("hr");
						int hourSnatch = Integer.parseInt(hrFinal[0]);

						lengthSnatch = (double) (hourSnatch);
					}
					else if (lengthString.charAt(lengthString.length() - 1) == 'm' && !lengthString.contains("hr")) { // if we have ONLY m
						String[] splitLength = lengthString.split(" ");

						String min = splitLength[1];
						String[] minFinal = min.split("m");
						int minuteSnatch = Integer.parseInt(minFinal[0]);

						lengthSnatch = (double) minuteSnatch;
					}
				} 
				if (seriesMatch) {
					masterList.add(new Series("series", titleSnatch, startYear, endYear, ratingSnatch, lengthSnatch, seriesLengthType)); //= new ArrayList<Series>();
				}
				else {
					masterList.add(new Movie("movie", titleSnatch, startYear, endYear, ratingSnatch, lengthSnatch)); //= new ArrayList<Movie>();
				}
			}
		}
		return masterList;    // masterList contains all of the Media objects from the original data file
	}

	public static void errorPrinter() {
		System.out.println("Filter Formatting Error: Be sure to follow the filter format specified below!\n");
		System.out.println("**FILTER FORMAT** - add a filter by entering the following: field relation target, e.g. year > 2003\n");
		System.out.println("Possible fields: genre, rating, title, year"); 
		System.out.println("Possible relation for genre/title: ="); 
		System.out.println("Possible relations for rating: <, >"); 
		System.out.println("Possible relations for year: <, >,"); 
		System.out.println("Possible targets: string name (for genre or title), double value (for rating), integer value (for year)\n"); //exceptions
	}

	public static void addFilter(Filter f) {

		Client_TooMuchToWatch.filterList.add(f);
	} 
	// for remove: remove index of filter list containing filter to be removed then EXECUTE again

	public static ArrayList<Media> getCurrentList(ArrayList<Filter> f) {
		currentList = new ArrayList<>();

		currentList.addAll(masterList);
		// if field == genre then add corresponding target to the currentList
		for (int i = 0; i < f.size(); i++) {
			ArrayList<Media> temp = new ArrayList<>(); 
			if (f.get(i).getField().equals("genre")) {
				if (f.get(i).getTarget().equals("series")) {
					for (int j = 0; j < currentList.size(); j++) {
						if (currentList.get(j).getMediaType().equals("series")) {
							temp.add(currentList.get(j));
						}
					}
				}
				else if (f.get(i).getTarget().equals("movie")) {
					for (int j = 0; j < currentList.size(); j++) {
						if (currentList.get(j).getMediaType().equals("movie")) {
							temp.add(currentList.get(j));
						}
					}
				}
			}
			// if field == title then add corresponding target to the currentList
			else if (f.get(i).getField().equals("title")) {
				for(int j = 0 ; j < currentList.size(); j++){ 
					if(currentList.get(j).getTitle().equals(f.get(i).getTarget())){
						temp.add(currentList.get(j)); 
					}
				}
			}
			else if (f.get(i).getField().equals("rating")) {
				for (int j = 0; j < currentList.size(); j++) {
					if (f.get(i).getRelation().equals("<")) {
						// if rating < target
						if (currentList.get(j).getRating() < Double.parseDouble(f.get(i).getTarget())) {
							temp.add(currentList.get(j));
						}
					}
					else if (f.get(i).getRelation().equals("<=")) {
						// if rating <= target
						if (currentList.get(j).getRating() <= Double.parseDouble(f.get(i).getTarget())) {
							temp.add(currentList.get(j));
						}
					}
					else if (f.get(i).getRelation().equals(">")) {
						// if rating > target
						if (currentList.get(j).getRating() > Double.parseDouble(f.get(i).getTarget())) {
							temp.add(currentList.get(j));
						}
					}
					else if (f.get(i).getRelation().equals(">=")) {
						// if rating >= target
						if (currentList.get(j).getRating() >= Double.parseDouble(f.get(i).getTarget())) {
							temp.add(currentList.get(j));
						}
					}
					else if (f.get(i).getRelation().equals("=")) {
						/** if rating == targetRating, then add this media object to the current list via temp */
						if (currentList.get(j).getRating() == Double.parseDouble(f.get(i).getTarget())) {
							temp.add(currentList.get(j));
						}
					}
				}
			}
			else if (f.get(i).getField().equals("year")) {
				for (int j = 0; j < currentList.size(); j++) {

					if (currentList.get(j).getStartYear() != currentList.get(j).getEndYear()) {
						// Check each relation against the jth element of the currentList
						if (f.get(i).getRelation().equals("<")) {
							// if start year < target
							if (currentList.get(j).getStartYear() < Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
						else if (f.get(i).getRelation().equals("<=")) {
							// if start year <= target
							if (currentList.get(j).getStartYear() <= Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
						else if (f.get(i).getRelation().equals(">")) {
							// if end year > target
							if (currentList.get(j).getEndYear() > Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
						else if (f.get(i).getRelation().equals(">=")) {
							// if end year >= target
							if (currentList.get(j).getEndYear() >= Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
						else if (f.get(i).getRelation().equals("=")) {
							/** if targetYear == y, then add this media object to the current list via temp */
							for (int y = currentList.get(j).getStartYear(); y != currentList.get(j).getEndYear() + 1; y++) {
								if (y == Integer.parseInt(f.get(i).getTarget())) {
									temp.add(currentList.get(j));
								}
							}
						}
					}
					else {
						// Check each relation against the jth element of the currentList
						if (f.get(i).getRelation().equals("<")) {
							// if start year < target
							if (currentList.get(j).getStartYear() < Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
						else if (f.get(i).getRelation().equals("<=")) {
							// if start year <= target
							if (currentList.get(j).getStartYear() <= Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
						else if (f.get(i).getRelation().equals(">")) {
							// if start year > target
							if (currentList.get(j).getStartYear() > Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
						else if (f.get(i).getRelation().equals(">=")) {
							// if start year >= target
							if (currentList.get(j).getStartYear() >= Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
						else if (f.get(i).getRelation().equals("=")) {
							/** if start year == targetYear, then add this media object to the current list via temp */
							if (currentList.get(j).getStartYear() == Integer.parseInt(f.get(i).getTarget())) {
								temp.add(currentList.get(j));
							}
						}
					}
				}
			}
			currentList = temp; 
		}
		return currentList;

	} 
}

