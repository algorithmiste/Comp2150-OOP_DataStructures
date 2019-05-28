/**
 * 
 * @author casey
 *
 */
public class Othello {

	public static void main(String[] args) {
		int counter = 0;
		System.out.print("Enter a value (n = n x n) for the board size: ");
		java.util.Scanner size = new java.util.Scanner(System.in);
		int n = size.nextInt(); 
		if (n != 8) n = 8;

		GameBoard game = new GameBoard(n, n);
		
	
		
		while (counter < 2 && !game.isGameOver()) {
			System.out.println(game);

			if (!game.forfeitTurn()) {
				System.out.println("Enter the coordinates of your desired move: ");
				@SuppressWarnings("resource")
				java.util.Scanner in = new java.util.Scanner(System.in);
				int i = in.nextInt(); int j = in.nextInt();

				while (i < 0 || j < 0 || i >= n || j >= n) {
					System.out.println("Move out of bounds! Stay within the dimensions of the board!");
					System.out.println("Enter a a valid set of coordinates for your move: ");
					i = in.nextInt(); j = in.nextInt();
				}
				while (!game.isValidMove(i, j)) {
					System.out.println("Try Again! That is not a valid move.");
					System.out.println("Enter a different set of coordinates for your move: ");
					i = in.nextInt(); j = in.nextInt();
				}

				game.placeTile(i, j);
				if (counter > 0) {
					counter--;
				}
				game.changeTurn();

			}
			else if (game.forfeitTurn()) {
				if (GameBoard.count % 2 == 0) {
					System.out.println("No move exists for white! Player 2's turn forfeited.");
				}
				else {
					System.out.println("No move exists for black! Player 1's turn forfeited.");
				}
				game.changeTurn();
				counter++;
				continue;
			}

		}
		size.close();
		System.out.println(game.getGameStatus()); 

	}
}