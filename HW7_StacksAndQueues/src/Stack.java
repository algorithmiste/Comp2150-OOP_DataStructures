	/*
	 * This interface species the basic stack operations.
	 * 
	 */
public interface Stack<E> {

	// Returns whether the stack is empty
	boolean isEmpty();

	// Adds the specified newData to the top of the stack
	void push(E newData);

	// Removes and returns the data at the top of the stack
	E pop();

	// Returns (but does not remove) the data at the top of the stack
	E peek();


}
