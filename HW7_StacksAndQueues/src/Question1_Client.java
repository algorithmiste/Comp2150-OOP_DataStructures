import java.util.Arrays;

public class Question1_Client {
	/**
	 * Write a client program that simulates the logging in of 100,000 players by using your “bad” queue implementation as well as the “good” implementation that 
	 * we wrote in class. Measure the time needed to enqueue all 100,000 players, and then the time needed to remove them all from the queue.
	 * 
	 */
	
	public static void main(String[] args) {
		 
		
		long startTimeBadEnqueue; long endTimeBadEnqueue; long startTimeGoodEnqueue; long endTimeGoodEnqueue; //= System.currentTimeMillis();
		long startTimeBadDequeue; long endTimeBadDequeue; long startTimeGoodDequeue; long endTimeGoodDequeue = 0;
		NaughtyLLQueue<String> mmoNaughtyLLQueue = new NaughtyLLQueue<>();
		LLQueue<String> mmoLLQueue = new LLQueue<>();

		System.out.println("Calculating...\n");
		//Enqueue
		startTimeBadEnqueue = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			mmoNaughtyLLQueue.enqueue("Player " + (i + 1));
		}
		endTimeBadEnqueue = System.currentTimeMillis();
		int totalTimeBadEnqueue = ((int) (endTimeBadEnqueue - startTimeBadEnqueue));

		startTimeGoodEnqueue = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			mmoLLQueue.enqueue("Player " + (i + 1));
		}
		endTimeGoodEnqueue = System.currentTimeMillis();
		int totalTimeGoodEnqueue = ((int) (endTimeGoodEnqueue - startTimeGoodEnqueue));

		// Dequeue
		startTimeBadDequeue = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			mmoNaughtyLLQueue.dequeue();
		}
		endTimeBadDequeue = System.currentTimeMillis();
		int totalTimeBadDequeue = ((int)(endTimeBadDequeue - startTimeBadDequeue));

		startTimeGoodDequeue = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			mmoLLQueue.dequeue();
		}
		endTimeGoodDequeue = System.currentTimeMillis();
		int totalTimeGoodDequeue = ((int)(endTimeGoodDequeue - startTimeGoodDequeue));

		System.out.println("Calculation complete.");
		System.out.println("Bad enqueue implemenation takes " + totalTimeBadEnqueue + " milliseconds");
		System.out.println("Good enqueue implemenation takes " + totalTimeGoodEnqueue + " milliseconds\n");

		System.out.println("Bad dequeue implemenation takes " + totalTimeBadDequeue + " milliseconds");
		System.out.println("Good dequeue implemenation takes " + totalTimeGoodDequeue + " milliseconds");
		
		
	}

}
