import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

/* Implements the Set interface using a linked list as the underlying data structure.  The
 * Set methods are very simple since they can just be delegated to existing LinkedList methods.
 * 
 * Since all of these methods involve doing a linear search of the list, they are O(n).
 */

public class ListSet<E> implements Set<E> {
	private LinkedList<E> data = new LinkedList<>();	// Stores the elements of this set
	
	@Override
	public boolean contains(E someItem) {
		return data.contains(someItem);
	}

	@Override
	public void add(E someItem) {
		if (!contains(someItem))	// Prevent adding duplicate elements to the set
			data.add(someItem);
	}

	@Override
	public E remove(E someItem) {
		if (data.remove(someItem))
			return someItem;
		return null;
	}
	
	/** Implement the methods union(ListSet<E> s) and intersection(ListSet<E> s). 
	 * These methods should return the resulting ListSet<E> of those operations between the calling set and 
	 * the argument. Do not modify either the calling set or the argument. Feel free to call the existing class methods (add, contains, remove) if needed. 
	 * (Hint: You can use the enhanced for loop syntax to visit each element of a java.util.ArrayList or java.util.LinkedList.) 
	 */
	public ListSet<E> intersection(ListSet<E> s) {
		ListSet<E> listSetToReturn = new ListSet<>();
		if (!data.isEmpty() && !s.data.isEmpty()) {
			for (E i : data) {
				if (s.contains(i)) {
					listSetToReturn.add(i);
				}	
			}
		}
		return listSetToReturn;
	}
	public ListSet<E> union(ListSet<E> s) {
		ListSet<E> listSetToReturn = new ListSet<>();
		if (!data.isEmpty()) {
			for (E d : data)
				listSetToReturn.add(d);	
		}
		if (!s.data.isEmpty()) {
			for (int i = 0; i < s.data.size(); i++) {
				if (!listSetToReturn.contains(s.data.get(i)))
					listSetToReturn.add(s.data.get(i));
			}
		}
		return listSetToReturn;
	}

	public static void main(String[] args) {
		ListSet<String> mySet = new ListSet<>();
		mySet.add("pancakes");
		mySet.add("yogurt");
		mySet.add("toaster strudels");
		mySet.add("French toast");
		mySet.add("waffles");
		mySet.add("Cinnamon Toast Crunch");
		mySet.add("Honey Bunches of Oats");
		mySet.add("Great Value cereal that costs about 1/2 as much");
		mySet.add("omelettes");

		ListSet<String> myBreakfast = new ListSet<>();
		myBreakfast.add("French toast");
		myBreakfast.add("waffles");
		myBreakfast.add("Cinnamon Toast Crunch");
		myBreakfast.add("Honey Bunches of Oats");
		myBreakfast.add("Croissants");
		myBreakfast.add("Pain au chocolat");
		
		ListSet<String> mySetBreakfastAND = mySet.intersection(myBreakfast);
		ListSet<String> mySetBreakfastOR = mySet.union(myBreakfast);
		System.out.println("mySetBreafastAND:");
		for (int i = 0; i < mySetBreakfastAND.data.size(); i++) {
			System.out.println(mySetBreakfastAND.data.get(i));
		}
		System.out.println("mySetBreafastOR:");
		for (int i = 0; i < mySetBreakfastOR.data.size(); i++) {
			System.out.println(mySetBreakfastOR.data.get(i));
		}
		
	}

}

