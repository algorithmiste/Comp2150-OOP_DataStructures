import java.util.Iterator;

/**	@authors Casey Carr and Mun Young
 *  write a method generateKey(BSTDictionary<K, Double> d) that returns a key based on the probabilities in the dictionary d. 
 */

public class KeyGen<K extends Comparable<K>> {
	
	public K generateKey(BSTDictionary<K, Double> d) {
		
		K key = null;	
		Iterator<Entry<K, Double>> it = d.iterator();
		double normRand = Math.random() ;
		
		double counter = 0 ;
		while (it.hasNext()) {
			Entry<K, Double> nextEntry = it.next();
			counter += nextEntry.getValue();
			
			if(normRand <= counter) {
				key = nextEntry.getKey();
				break;
			}
		}
		return key ;
	}
}
