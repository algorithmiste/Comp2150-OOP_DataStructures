
public class NaughtyLLQueue<E> implements Queue<E> {
	/** 
	 * Your class should implement a queue using a single linked list. However, it should use the tail of the list as the front of the queue, 
	 * meaning that when a dequeue is performed, a traversal from the head node is required. Implement all four methods from the Queue<E> interface 
	 * (isEmpty, peek, enqueue, and dequeue) for this class. Calling dequeue or peek on an empty queue should return null.
	 */
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
	private Node<E> head, tail; // head == back, tail == front
	private int size;

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E peek() {
		if (!isEmpty()) {
			return tail.data;
		}
		else {
			return null;
		}

	}

	@Override
	public void enqueue(E newItem) {
		if (!isEmpty()) {
			head = new Node<E>(newItem, head);
		}
		else {
			head = tail = new Node<E>(newItem, null);
		}
		size++;

	}

	@Override
	public E dequeue() {
		if (!isEmpty()) {
			E toReturn = tail.data;
			Node<E> temp = head;
			if (size == 1) {
				head = head.next;
			}
			else if (size == 2) {
				head.next = null;
				tail = head;
			}
			else if (size >= 3) {
				for (int i = 0; i < size - 2; i++) {
					temp = temp.next;
				}
				tail = temp;
				tail.next = null;	
			}
			if (head == null)
				tail = null;
			size--;
			return toReturn;
		}
		else 
			return null;

	}
	public String toString() {
		String r = "NaughtyLLQueue object (size = " + size + "), containing (back to front): ";
		for (Node<E> temp =  head; temp != null; temp = temp.next)
			r += " -> " + temp.data ;
		return r;
	}

	public static void main(String[] args) {
		NaughtyLLQueue<Integer> ints = new NaughtyLLQueue<>();
		ints.enqueue(1);
		ints.enqueue(2);
		ints.enqueue(3);
		System.out.println(ints.peek());
		ints.enqueue(4);
		ints.enqueue(5);
		System.out.println(ints);
		ints.enqueue(6);
		System.out.println(ints);
		ints.dequeue();
		ints.dequeue();
		ints.dequeue();
		System.out.println(ints.peek());
		System.out.println(ints);
		ints.dequeue();
		ints.dequeue();
		ints.dequeue();
		System.out.println(ints.peek());
		System.out.println(ints);
	}
}
