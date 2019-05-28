import java.util.Iterator;
// @authors Casey Carr and Mun Young

// Write a BSTDictionary<K extends Comparable<K>, V> class that uses a binary search tree to implement a dictionary.

public class BSTDictionary<K extends Comparable<K>, V> implements Iterable<Entry<K, V>>{
	private BinarySearchTree<Entry<K, V>> treeMap = new BinarySearchTree<>();

	// contains(K key) – Returns whether the specified key exists in the dictionary.
	public boolean contains(K key) {
		Entry<K, V> newEntry = treeMap.find(new Entry<K, V>(key, null));
		if (newEntry != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** add(K key, V value) – Inserts the specified key-value pair into the dictionary. If the key
	already exists, this method should replace that key’s value with the new one. */
	@SuppressWarnings("rawtypes")
	public void add(K key, V value) {
		@SuppressWarnings("unchecked")
		Entry<K, V> newEntry = new Entry(key, value);
		if (this.contains(newEntry.getKey())){
			treeMap.find(newEntry).setValue(value);
		}
		else {
			treeMap.add(newEntry);
		}
	}
	
	// getValue(K key) – Returns the value associated with the specified key.
	public V getValue(K key) {
		Entry<K, V> newEntry = treeMap.find(new Entry<K, V>(key, null));
		if (this.contains(key)) {
			return newEntry.getValue();
		}
		else 
			return null;
	}
	// iterator() – Returns an iterator over the elements in the dictionary.
	public Iterator<Entry<K, V>> iterator() {
		return treeMap.iterator();
	}
}
