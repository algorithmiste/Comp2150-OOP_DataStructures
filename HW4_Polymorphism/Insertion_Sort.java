
public class Insertion_Sort {
	
	public static int[] insertionSort(int[] n) {
		
		for (int i = 1; i < n.length; i++) {
			int thingToInsert= n[i];
			int j = i -1;
			while (j >= 0 && n[j] > thingToInsert) {
				n[j+1] = n[j];
				j--;
			}
			n[j+1] = thingToInsert;
		}
		return n;
	}
	public static void main(String[] args) {
		int[] n = {8, 5, 1, 6, 7, 4, 2, 3, 9};
		n = insertionSort(n);
		
		for (int i = 0; i < n.length; i++)
			System.out.print(n[i] + " ");
	}

}
