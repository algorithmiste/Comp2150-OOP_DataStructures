/* Implements the Queue<E> interface using a linked list as the underlying data structure.
 * We maintain both head and tail references in the list, and we let the head of the list
 * correspond to the front of the queue.  This allows easy peek and dequeue methods, since
 * both just involve interacting with the head node.  Meanwhile, enqueue simply involves
 * adding a new node after the tail node.
 * 
 * All three are O(1) operations, which is like, totally awesome.
 * 
 */
public class LLQueue<E> implements Queue<E> {

	// This is the same nested Node class that we used in LinkedList
	private static class Node<E> {	// "static" means that Node does *not* have access to the instance variables of LinkedList
		private E data;
		private Node<E> next;

		// Constructor (generated automatically through Eclipse)
		public Node(E data, Node<E> next) {
			super();
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head, tail;
	private int size;
	
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E peek() {
		if (!isEmpty())
			return head.data;
		else
			return null;
	}

	// Enqueue just needs to add a new node after the tail node
	@Override
	public void enqueue(E newItem) {
		if (!isEmpty()) {
			tail.next = new Node<>(newItem, null);
			tail = tail.next;	// Need to update the tail reference to point to the new node
		} else	// Special case of adding to an empty queue - both head and tail should be pointing to the same node after adding
			head = tail = new Node<>(newItem, null);
		size++;
	}

	// Dequeue just needs to remove the head node from the list
	@Override
	public E dequeue() {
		if (!isEmpty()) {
			E toReturn = head.data;
			head = head.next;
			
			if (head == null)	// If the list is now empty...
				tail = null;
			
			size--;
			return toReturn;
		} else
			return null;
	}

	
	public String toString() {
		String r = "LLQueue object (size = " + size + "), containing (front to back): ";
		for (Node<E> temp =  head; temp != null; temp = temp.next)
			r += temp.data + "; ";
		return r;
	}
	
	public static void main(String[] args) {
		Queue<String> q = new LLQueue<>();
		q.enqueue("dark chocolate");
		q.enqueue("darker chocolate");
		q.enqueue("even darker chocolate");
		q.enqueue("the darkest chocolate");
		q.enqueue("inferior (milk) chocolate");
		q.enqueue("more dark chocolate");
		
		System.out.println(q);
		System.out.println(q.peek());
		
		while (!q.isEmpty()) {
			System.out.println(q.dequeue());
			System.out.println(q);
		}
	}

}
