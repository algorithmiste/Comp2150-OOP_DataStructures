
/** Casey Carr && Mun Young */

public class Movie extends Media {


	public Movie(String mediaType, String title, int startYear, int endYear, double rating, double length) {
		super("movie", title, startYear, endYear, rating, length);

	}


	public String toString() {
		int hour = 0; int minute = 0;

		if (length < 10 && length % 1 != 0) { // hr m format
			hour = (int) (length / 1);
			minute = (int) ((length % 1) * 60);
		}
		else if (length < 10 && length % 1 == 0){ // hr format only
			hour = (int) length;
		}
		else if (length > 9 && length % 1 == 0) { // m format only
			minute = (int) length;
		}

		String toReturn = "";	
		if (minute != 0 && hour != 0) {
			toReturn += this.title + " (" + this.startYear + ") | " + this.rating + " stars, " + hour + "hr " + minute + "m";
		}
		else if (minute == 0 && hour != 0) {
			toReturn += this.title + " (" + this.startYear + ") | " + this.rating + " stars, " + hour + "hr";
		}
		else { // (hour == 0 && minute != 0)
			toReturn += this.title + " (" + this.startYear + ") | " + this.rating + " stars, " + minute + "m";
		}

		return toReturn;
	}
}
