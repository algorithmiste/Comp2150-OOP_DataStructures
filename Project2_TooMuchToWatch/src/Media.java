
/** Casey Carr && Mun Young */

public abstract class Media {
	protected String title;
	protected int startYear;
	protected int endYear;
	protected double rating;
	protected double length;
	protected String mediaType;

	public Media() {

	}

	public Media(String mediaType, String title, int startYear, int endYear, double rating, double length) {
		this.title = title;
		this.startYear = startYear;
		this.endYear = endYear;
		this.rating = rating;
		this.length = length;
		this.mediaType = mediaType;
	}

	public String getMediaType() {
		return mediaType;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public int getStartYear() {
		return startYear;
	}
	public int getEndYear() {
		return endYear;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getRating() {
		return rating;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getLength() {
		return length;
	}

	public String toString() {
		String toReturn = "This is media! If you see me, then something went WRONG!";
		return toReturn;

	}
}
