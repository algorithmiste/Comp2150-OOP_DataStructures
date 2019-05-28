

public class ArrayList<E> implements List<E> {	// The type parameter E is a placeholder for what kind of data will be stored in this list
    @SuppressWarnings("unchecked")
	private E[] data = (E[])(new Object[3]);  // The array where the data is stored -- Java doesn't allow generic array creation, so we just create an array of Objects and cast it to E[]
    private int size = 0;   // The number of elements actually stored in the list
    
    // Returns the item stored at the specified list index
    // Big-O: O(1)
    public E get(int index) {
        if (index >= 0 && index < size)
            return data[index];
        else {
            System.out.println("You can't do that!");
            throw new IndexOutOfBoundsException();
        }
    }
    
    // Replaces the item at the specified list index with newValue
    // Big-O: O(1)
    public void set(int index, E newValue) {
        if (index >= 0 && index < size)
            data[index] = newValue;
        else {
            System.out.println("You can't do that!");
            throw new IndexOutOfBoundsException();
        }
    }
    
    // Adds the newValue to the end of the list
    // Big-O: O(1) if an array resize is not needed
    //        O(n) if an array resize is needed
    // If we double the array length every time a resize is needed, then this becomes "amortized constant time"
    //  (basically, "constant time on average")
    @SuppressWarnings("unchecked")
	public void add(E newValue) {
        if (size == data.length) {    // Array has reached its capacity
            E[] newData = (E[])(new Object[data.length * 2]); // Create a new, larger array
            for (int i = 0; i < data.length; i++)           // Copy the old elements to the new array
                newData[i] = data[i];
            data = newData;                                 // Point data at the new array
        }
        data[size] = newValue;  // Add the newValue at the end of the array
        size++;
    }
    
    // Adds all the elments in anotherList to the back of the calling list.
    @SuppressWarnings("unchecked")
	public void addAll(ArrayList<E> anotherList) {
    	// data array is automatically resized via the add method
//    	for (int i = 0; i < anotherList.size; i++) {
//			this.add(anotherList.get(i));
//		}
    	// make a manual version using the data array
    	if (data.length < (this.size + anotherList.size)) {
    		E[] newData = ((E[]) new Object[this.size + anotherList.size]);
    		for (int j = 0; j < data.length; j++) {
    			newData[j] = data[j];
    		}
    		int tempSize = this.size;
    		data = newData;
    		for (int k = 0; k < anotherList.size; k++) {
    			data[k + tempSize] = anotherList.get(k);
    			size++;
    		}
    	}
    }
    @SuppressWarnings("unchecked")
	public void trimToSize() {
    	if (this.size != 0) {
    		E[] trimmedArray = ((E[]) new Object[this.size]);
    		for (int i = 0; i < this.size; i++) {
    			trimmedArray[i] = data[i];
    		}
    		data = trimmedArray;
    	}
    	else {
    		System.out.println("Cannot trim! The size of this array is zero!");
    	}
    }
    // Returns a new ArrayList<E> object containing the elements of the calling list between
    // beginIndex(inclusive) and endIndex(exclusive)
    public ArrayList<E> slice(int beginIndex, int endIndex) {
    	ArrayList<E> arrayToReturn = new ArrayList<>();;
    	if (!(beginIndex < endIndex)) {
    		System.out.println("No object returned since beginIndex is greater than or equal to the endIndex!");
    		throw new IndexOutOfBoundsException();
    		
    	}
    	else if (beginIndex >= 0 && endIndex < this.size) {
    		for (int r = beginIndex; r < endIndex; r++) {
    			arrayToReturn.add(data[r]);
    		}
    	}
    	else {
    		System.out.println("Error: beginIndex and/or endIndex out of bounds!");
    		throw new IndexOutOfBoundsException();
    	}
    	return arrayToReturn;
    }
    
    // Removes and returns the list item at the specified index
    // Big-O: O(n)
    public E remove(int index) {
        if (index >= 0 && index < size) {
            E thingToReturn = data[index];
            
            for (int i = index; i < size - 1; i++)  // Shift (to the left) all array elements starting from index
                data[i] = data[i+1];
            size--;
            
            return thingToReturn;
        } else {
            System.out.println("You can't do that!");
            throw new IndexOutOfBoundsException();
        }
    }
    
    public String toString() {
        String r = "ArrayList (size = " + size + ", capacity = " + data.length + "), containing the following:\n";
        for (int i = 0; i < size; i++)
            r += data[i] + "\n";
        return r;
    }
    
    
    public static void main(String[] args) {
    	// When we create the ArrayList object, replace E with the data type to be
    	//  stored in the list (in this case, String)
        ArrayList<Integer> theList = new ArrayList<>();
        System.out.println(theList);
        theList.add(-3);
        System.out.println(theList);
        theList.add(0);
        System.out.println(theList);
        theList.add(43);
        System.out.println(theList);
        theList.add(812903218);
        System.out.println(theList);
        theList.remove(1);
        System.out.println(theList);
        
        ArrayList<Integer> anotherList = new ArrayList<>();
        anotherList.add(10);
        System.out.println(anotherList);
        anotherList.add(32);
        anotherList.add(44);
        anotherList.add(58);
        anotherList.add(67);
        System.out.println(anotherList);
        theList.addAll(anotherList);
        System.out.println(theList);
        theList.trimToSize();
        System.out.println(theList);
        System.out.print(theList.slice(3 , 3));
        
        
       
    }
}
