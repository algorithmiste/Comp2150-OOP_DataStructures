/*
 * Interface specifying the basic set operations.
 * 
 * A set is an unordered collection of unique objects.
 * 
 */
public interface Set<E> {
	// Returns whether the set contains someItem.
	boolean contains(E someItem);
	
	// Checks the set to make sure someItem doesn't already exist, and if not, adds someItem to the set.
	void add(E someItem);

	// Removes and returns someItem from the set if it exists, or returns null if someItem doesn't exist.
	E remove(E someItem);
}
