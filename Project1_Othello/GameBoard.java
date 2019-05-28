/**
 * 
 * @author casey
 *
 */ 

public class GameBoard {

	private Tile[][] tile;
	private Tile t2 = new Tile (1);

	protected int numRows, numColumns;

	private int numBlack, numWhite, numEmpty;
	private String gameStatus = " ";
	public static int count = 0;

	public GameBoard(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;

		// Parameterize dimensions of game board 
		this.tile = new Tile[numRows][numColumns];
		// Fill game board with tile objects
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				tile[i][j] = new Tile();
			}	
		}
		numBlack = 0; numWhite = 0; // Initialized to keep up with gameStatus
		// Initialize game
		tile[(this.numRows / 2) - 1][(this.numColumns / 2)].setState(1);
		tile[(this.numRows / 2)][(this.numColumns / 2) - 1].setState(1);
		tile[(this.numRows / 2)][(this.numColumns / 2)].setState(2);
		tile[(this.numRows / 2) - 1][(this.numColumns / 2) - 1].setState(2); 
	}

	public void changeTurn() {
		t2.toggleTileState();
	}	

	public void placeTile(int i, int j) {
		tile[i][j].setState(t2.getState());

		boolean isFlippable = false;
		int m = i; int n = j; int k = i; int r = j;
		if (i != 0 && j != 0) {
			if (tile[i - 1][j - 1].getState() != 0 && tile[i - 1][j - 1].getState() != t2.getState()) {
				while (k > 1 && r > 1) {
					k--; r--;
					if (tile[k][r].getState() != 0 && tile[k][r].getState() != t2.getState()) {
						if (tile[k - 1][r - 1].getState() == t2.getState()) {
							isFlippable = true;							
						}
						continue; 
					}
				}
				while (isFlippable && tile[m - 1][n -1].getState() != 0 && tile[m - 1][n -1].getState() != t2.getState()) {
					tile[m - 1][n - 1].setState(t2.getState());
					m--; n--;
				}
			}
		}
		isFlippable = false;
		m = i; n = j; k = i; r = j;
		if (i != 0) {
			if (tile[i - 1][j].getState() != 0 && tile[i - 1][j].getState() != t2.getState()) {
				while (k > 1) {
					k--;
					if (tile[k][r].getState() != 0 && tile[k][r].getState() != t2.getState()) {
						if (tile[k - 1][r].getState() == t2.getState()) {
							isFlippable = true;							
						}
						continue;
					}
				}
				while (isFlippable && tile[m - 1][n].getState() != 0 && tile[m - 1][n].getState() != t2.getState()) {
					tile[m - 1][n].setState(t2.getState());
					m--;
				}
			}
		}
		isFlippable = false;
		m = i; n = j; k = i; r = j;
		if (i != 0 && j != numColumns - 1) {
			if (tile[i - 1][j + 1].getState() != 0 && tile[i - 1][j + 1].getState() != t2.getState()) {
				while (k > 1 && r < numColumns - 2) {
					k--; r++;
					if (tile[k][r].getState() != 0 && tile[k][r].getState() != t2.getState()) {
						if (tile[k - 1][r + 1].getState() == t2.getState()) {
							isFlippable = true;							
						}
						continue; 
					}
				}
				while (isFlippable && tile[m - 1][n + 1].getState() != 0 && tile[m - 1][n + 1].getState() != t2.getState()) {
					tile[m - 1][n + 1].setState(t2.getState());
					m--;
					n++;
				}
			}
		}
		isFlippable = false;
		m = i; n = j; k = i; r = j;
		if (j != numColumns - 1) {
			if (tile[i][j + 1].getState() != 0 && tile[i][j + 1].getState() != t2.getState()) {
				while (r < numColumns - 2) {
					r++;
					if (tile[k][r].getState() != 0 && tile[k][r].getState() != t2.getState()) {
						if (tile[k][r + 1].getState() == t2.getState()) {
							isFlippable = true;	
						}
						continue; 
					}
				}
				while (isFlippable && tile[m][n + 1].getState() != 0 && tile[m][n + 1].getState() != t2.getState()) {
					tile[m][n + 1].setState(t2.getState());
					n++;
				}
			}
		}
		isFlippable = false;
		m = i; n = j; k = i; r = j;
		if (i != numRows - 1 && j != numColumns - 1) {
			if (tile[i + 1][j + 1].getState() != 0 && tile[i + 1][j + 1].getState() != t2.getState()) {
				while (k < numRows - 2 && r < numColumns - 2) {
					k++; r++;
					if (tile[k][r].getState() != 0 && tile[k][r].getState() != t2.getState()) {
						if (tile[k + 1][r + 1].getState() == t2.getState()) {
							isFlippable = true;	
						}
						continue; 
					}
				}
				while (isFlippable && tile[m + 1][n + 1].getState() != 0 && tile[m + 1][n + 1].getState() != t2.getState()) {
					tile[m + 1][n + 1].setState(t2.getState());
					m++; n++;
				}
			}
		}
		isFlippable = false;
		m = i; n = j; k = i; r = j;
		if (i != numRows - 1) {
			if (tile[i + 1][j].getState() != 0 && tile[i + 1][j].getState() != t2.getState()) {
				while (k < numRows - 2) {
					k++; 
					if (tile[k][r].getState() != 0 && tile[k][r].getState() != t2.getState()) {
						if (tile[k + 1][r].getState() == t2.getState()) {
							isFlippable = true;							
						}
						continue;
					}
				}
				while (isFlippable && tile[m + 1][n].getState() != 0 && tile[m + 1][n].getState() != t2.getState()) {
					tile[m + 1][n].setState(t2.getState());
					m++;
				}
			}
		}
		isFlippable = false;
		m = i; n = j; k = i; r = j;
		if (i != numRows - 1 && j != 0) {
			if (tile[i + 1][j - 1].getState() != 0 && tile[i + 1][j - 1].getState() != t2.getState()) {
				while (k < numRows - 2 && r > 1) {
					k++; r--; 
					if (tile[k][r].getState() != 0 && tile[k][r].getState() != t2.getState()) {
						if (tile[k + 1][r - 1].getState() == t2.getState()) {
							isFlippable = true;						
						}
						continue; 
					}
				}
				while (isFlippable && tile[m + 1][n - 1].getState() != 0 && tile[m + 1][n - 1].getState() != t2.getState()) {
					tile[m + 1][n - 1].setState(t2.getState());
					m++; n--;
				}
			}
		}
		isFlippable = false;
		m = i; n = j; k = i; r = j;
		if (j != 0) {
			if (tile[i][j - 1].getState() != 0 && tile[i][j - 1].getState() != t2.getState()) { // changed i + 1 to i
				while (r > 1) {
					r--; 
					if (tile[k][r].getState() != 0 && tile[k][r].getState() != t2.getState()) {
						if (tile[k][r - 1].getState() == t2.getState()) {
							isFlippable = true;							
						}
						continue; 
					}
				}
				while (isFlippable && tile[m][n - 1].getState() != 0 && tile[m][n - 1].getState() != t2.getState()) {
					tile[m][n - 1].setState(t2.getState());
					n--;
				}
			}
		}
	}
	public void countColorsPresent() {
		numBlack = 0; numWhite = 0; numEmpty = 0;

		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile.length; j++) {
				if (tile[i][j].getState() == 1)
					numBlack++;
				else if (tile[i][j].getState() == 2)
					numWhite++;
				else
					numEmpty++;
			}
		}
	}
	public boolean isGameOver() {
		countColorsPresent();
		if (numBlack == 0 || numWhite == 0 || numEmpty == 0) {
			return true;
		}
		return false;
	}
	public boolean isValidMove(int i, int j) {
		if (i < 0 || j < 0 || i >= numRows || j >= numColumns) {
			return false;
		}	
		if (tile[i][j].getState() != 0) {
			return false;
		}

		int m = i, n = j;
		while (m > 1 && n > 1) {
			m--; n--;
			if (tile[i - 1][j - 1].getState() == 0 || tile[i - 1][j - 1].getState() == t2.getState()) {
				break;
			}
			if (tile[m][n].getState() != 0 && tile[m][n].getState() != t2.getState()) {
				if (tile[m - 1][n - 1].getState() == t2.getState()) {
					return true;	
				}
				continue; 
			}
		}
		m = i; n = j;
		while (n > 1){
			n--;
			if (tile[i][j-1].getState() == t2.getState() || tile[i][j-1].getState() == 0) 
				break;
			if (tile[m][n].getState() != t2.getState() && tile[m][n].getState() != 0){
				if(tile[m][n-1].getState() == t2.getState()) {
					return true;
				}
				continue;
			}
		}
		m = i; n = j;
		while (m > 1){
			m--;
			if (tile[i-1][j].getState() == t2.getState() || tile[i-1][j].getState() == 0) 
				break;
			if (tile[m][n].getState() != t2.getState() && tile[m][n].getState() != 0) {
				if(tile[m-1][n].getState() == t2.getState()) {
					return true;
				}
				continue;
			}
		}
		m = i; n = j;
		while (m > 1 && n < numColumns - 2) {
			m--; n++;
			if (tile[i-1][j+1].getState()==t2.getState() || tile[i-1][j+1].getState()==0) 
				break;
			if (tile[m][n].getState() != t2.getState() && tile[m][n].getState() != 0) {
				if (tile[m-1][n+1].getState() == t2.getState()) {
					return true;
				}
				continue;
			}
		}
		m = i; n = j;
		while (n < numColumns - 2) {
			n++;
			if (tile[i][j+1].getState() == t2.getState() || tile[i][j+1].getState() == 0) 
				break;
			if (tile[m][n].getState() != t2.getState() && tile[m][n].getState() != 0) {
				if (tile[m][n+1].getState() == t2.getState()) {
					return true;
				}
				continue;
			}
		}
		m = i; n = j;
		while (m < numRows - 2 && n < numColumns - 2) {
			m++; n++;
			if (tile[i+1][j+1].getState() == t2.getState() || tile[i+1][j+1].getState() == 0) 
				break;
			if (tile[m][n].getState() != t2.getState() && tile[m][n].getState() != 0){
				if (tile[m+1][n+1].getState() == t2.getState()) {
					return true;
				}
				continue;
			}
		}
		m = i; n = j;
		while (m < numRows - 2) {
			m++;
			if (tile[i+1][j].getState() == t2.getState() || tile[i+1][j].getState() == 0) 
				break;
			if (tile[m][n].getState() != t2.getState() && tile[m][n].getState() != 0){
				if (tile[m+1][n].getState() == t2.getState()) {
					return true;
				}
				continue;
			}
		} 
		m = i; n = j;
		while (m < numRows - 2 && n > 1) {
			m++; n--;
			if (tile[i+1][j-1].getState() == t2.getState() || tile[i+1][j-1].getState() == 0) 
				break;
			if (tile[m][n].getState() != t2.getState() && tile[m][n].getState() != 0){
				if (tile[m+1][n-1].getState() == t2.getState()) {
					return true;
				}
				continue;
			}
		}
		return false;
	}

	public boolean forfeitTurn() {
		boolean forfeit = true;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (this.isValidMove(i, j)) {
					forfeit = false;
				}
			}
		}
		return forfeit;
	}

	public String toString() {
		whoseTurn();
		String columnIndices = " ", rowIndices = "", frame = " +";

		for (int k = 0 ; k < tile.length; k++){
			columnIndices += "   " + k;
		}
		for(int j = 0 ; j < tile.length; j++){
			frame += "---+";
		} 
		for (int i= 0; i < tile.length; i++) {
			rowIndices += i +" |";
			for (int j = 0; j < tile[i].length ; j++) {
				rowIndices += " " + tile[i][j] + " |"; 
			}
			rowIndices += "\n"+ " "+ frame + "\n";
		}
		String status = "Number of blacks: " + numBlack + "\nNumber of whites: " + numWhite + "\n";
		return columnIndices + "\n " + frame + "\n" + rowIndices + "\n" + status;
	}
	public String getGameStatus() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (tile[i][j].getState() == 1)
					numBlack++;
				else if (tile[i][j].getState() == 2) {
					numWhite++;
				}
			}
		}
		if (numBlack > numWhite)
			gameStatus = "Black wins!";
		else if (numBlack < numWhite)
			gameStatus = "White wins!";
		else 
			gameStatus = "It's a draw!";
		return "Game Over! " + gameStatus;
	}

	public static int whoseTurn() {
		count++;
		if (count % 2 != 0) {
			System.out.println("\nPlayer 1, it's your turn! Place a black tile, if possible.\n");
			return 1;
		}
		else {
			System.out.println("\nPlayer 2, it's your turn! Place a white tile, if possible.\n");
			return 2;
		}
	}
	public static void main(String[] args) {

	}
}
