/**
 * 
 * @author casey
 *
 */
public class Tile {
	private int state;

	public Tile() {
		this.state = 0;
	}
	// state = 0 "empty", 1 "black", 2 "white"
	public Tile(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public void toggleTileState() {
		if (this.state == 1)
			this.state = 2;
		else if (this.state == 2)
			this.state = 1;
	}

	public String toString() {
		String stateToReturn = " ";
		if (this.state == 1)
			stateToReturn = "B";
		else if (this.state == 2)
			stateToReturn = "W";
		else
			stateToReturn = " ";
		return stateToReturn;
	}

}
