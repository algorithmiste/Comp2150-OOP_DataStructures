

public interface List<E> {	// The type parameter E is a placeholder for what kind of data will be stored in this list
    // Returns the item stored at the specified list index
    E get(int index);
    
    // Replaces the item at the specified list index with newValue
    void set(int index, E newValue);
    
    // Adds the newValue to the end of the list
    void add(E newValue);
    
    // Removes and returns the list item at the specified index
    E remove(int index);
}
