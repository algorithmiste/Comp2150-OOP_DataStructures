

/*
 * This class implements the List<E> interface using a linked list
 * as the underlying data structure.  A linked list is a collection of nodes,
 * each of which contains data and a reference to the next node.
 * 
 * Since each node knows how to get to the next node, the nodes need not be
 * stored contiguously in memory.  We need to maintain the location of only
 * the head node; from there, we can get to any other node in the list.
 */
public class LinkedList<E> implements List<E> {
	
	// Node is written as a private nested class of LinkedList, since we don't
	//  intend to use Node outside of this class.  This way, LinkedList has
	//  direct access to the instance variables of Node.
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
	
	private Node<E> head;	// Maintains the location of the head node
	private int size = 0;	// Number of elements in the list

	// Big-O: O(n) due to the call to nodeAt
	@Override
	public E get(int index) {
		return nodeAt(index).data;
	}

	// Big-O: O(n) due to the call to nodeAt
	@Override
	public void set(int index, E newValue) {
		nodeAt(index).data = newValue;
	}

	// Big-O: O(1) if adding to the head of the list, O(n) if adding to the tail
	@Override
	public void add(E newValue) {
		if (size == 0)
			head = new Node<>(newValue, null);
		else
			nodeAt(size - 1).next = new Node<>(newValue, null);
		size++;
	}

	// Big-O: O(1) if removing from the head of the list, O(n) for other locations
	@Override
	public E remove(int index) {
		E temp;
		if (index == 0) {
			temp = head.data;
			head = head.next;
		} else {
			Node<E> nodeBefore = nodeAt(index - 1);
			temp = nodeBefore.next.data;
			nodeBefore.next = nodeBefore.next.next;
		}
		size--;
		return temp;
	}
	
	// Removes and returns the first item in the list that is equivalent to the specified object
	public E remove(E item) {
		if (size == 0) {
			return null;
		}
		
		E temp = head.data;
		E thingToReturn = null;
	
		if (temp == item) {
			thingToReturn = temp;
			head = head.next;
			size--;
		}
		else {
			for (int i = 0; i < size - 1; i++) {
				Node<E> nodeBefore = nodeAt(i);
				temp = nodeBefore.next.data;
				if (temp == item) {
					thingToReturn = temp;
					nodeBefore.next = nodeBefore.next.next;
					size--;
					return thingToReturn;
				}
			}
		}	
		return thingToReturn;	
	}
	
	// Reverses the order of nodes in the calling list
	public void reverse() {
		
		if (this.size == 0) {
			System.out.println("Cannot reverse an empty list!");
		}
		else if (this.size == 1) {
			return;	
		}
		else {
			Node<E> nodeBefore = null; Node<E> nodeCurrent = head; Node<E> nodeAfter;
			
			while (nodeCurrent != null) {
				nodeAfter = nodeCurrent.next;
				nodeCurrent.next = nodeBefore;
			
				nodeBefore = nodeCurrent;
				nodeCurrent = nodeAfter;		
			}
			head = nodeBefore;
		}
	}
	
	// Returns an ArrayList<E> object containing all elements in the calling, in the same order
	public ArrayList<E> toArrayList() {
		ArrayList<E> arrayListToReturn = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			arrayListToReturn.add(nodeAt(i).data);
		}
		return arrayListToReturn;
	}
	// Returns the Node object at the specified index in the list.
	// Throws an IndexOutOfBoundsException if the index is invalid.
	// Big-O: O(n)
	private Node<E> nodeAt(int index) {
		if (index >= 0 && index < size) {
			Node<E> temp = head;
			for (int i = 0; i < index; i++)
				temp = temp.next;
			return temp;
		} else
			throw new IndexOutOfBoundsException();
	}
	public String toString() {
		String r = "LinkedList (size = " + size + "), containing: head -> ";
		for (Node<E> temp = head; temp != null; temp = temp.next)
			r += temp.data + " -> ";
		r += "null";
		return r;
	}
	
	public static void main(String[] args) {
		LinkedList<String> myList = new LinkedList<>();
//		System.out.println(myList);
		myList.add("stuff");
		myList.add("four");
		myList.add("for");
		myList.add("fore");
		myList.add("fo");
		myList.add("faux");
		myList.add("4");
		System.out.println(myList);
		//myList.remove(4);
	
//		System.out.println(myList.remove("four"));
//		System.out.println(myList);
//		System.out.println(myList.remove("stuff"));
//		System.out.println(myList);
//		System.out.println(myList.remove("for"));
//		System.out.println(myList);
//		System.out.println(myList.remove("fo"));
//		System.out.println(myList);
//		System.out.println(myList.remove("fore"));
//		System.out.println(myList);
//		System.out.println(myList.remove("faux"));
//		System.out.println(myList);
//		System.out.println(myList.remove("4"));
//		System.out.println(myList);
//		System.out.println(myList.remove("four"));
//		System.out.println(myList);
		myList.reverse();
		System.out.println(myList);
		System.out.print(myList.toArrayList());
	}
}

