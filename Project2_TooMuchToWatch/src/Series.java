
/** Casey Carr && Mun Young */

public class Series extends Media{
	private String seriesLengthType;

	public Series(String mediaType, String title, int startYear, int endYear, double rating, double length, String seriesLengthType) {
		super("series", title, startYear, endYear, rating, length);
		this.seriesLengthType = seriesLengthType;
	}
	public String toString() {

		String toReturn = "";
		toReturn += this.title + " (";

		if (this.startYear == this.endYear) {
			toReturn += this.startYear;
		}
		else {
			toReturn += this.startYear + "-" + this.endYear;
		}

		toReturn += ") | " + this.rating + " stars, " + length + " " + seriesLengthType; // turn length to hours and minutes

		return toReturn;
	}
}
