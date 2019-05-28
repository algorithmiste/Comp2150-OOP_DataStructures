/*
 * This class represents one node in a linked list.
 * Each node contains two parts: data, and a reference to the next node in the list
 */
public class Node<E> {
	private E data;
	private Node<E> next;

	// Getters and setters (generated automatically through Eclipse)
	public E getData() {
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public Node<E> getNext() {
		return next;
	}
	
	public void setNext(Node<E> next) {
		this.next = next;
	}

	// Constructor (generated automatically through Eclipse)
	public Node(E data, Node<E> next) {
		super();
		this.data = data;
		this.next = next;
	}
}

