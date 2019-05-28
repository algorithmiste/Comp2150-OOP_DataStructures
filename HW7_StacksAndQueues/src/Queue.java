
public interface Queue<E> {
	// Returns whether the queue is empty
	boolean isEmpty();

	// Returns (but does not remove) the front element in the queue
	E peek();

	// Adds the specified newItem to the back of the queue
	void enqueue(E newItem);

	// Removes and returns the front element in the queue
	E dequeue();

}
