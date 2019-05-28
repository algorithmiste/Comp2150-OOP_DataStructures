
public class Spaceship implements Drawable {
	private Weapon[] weapons = new Weapon[Weapon.numWeapons];
	private int numWeapon = 0;
	
	public Spaceship() {
		
	}
	// Adds the specified weapon to the weapons array
	public void addWeapon(Weapon w) {
		weapons[numWeapon] = w;
		numWeapon++;
	}	
	public void draw() {
		System.out.println("drawing spaceship...");
	}

	// This method should allow the Spaceship to fire its n fastest weapons, in order of increasing load time.
	public void fireFastestWeapons(int n) {
		
		/** Insertion Sort */
		for (int i = 1; i < weapons.length; i++){
			Weapon thingToInsert = weapons[i];
			int j = i - 1;
			while (j >= 0 && weapons[j].compareTo(thingToInsert) > 0) {
				weapons[j + 1] = weapons[j];
				j--;
			}
			weapons[j+1] = thingToInsert;
		}
		/** Bubble Sort version (less efficient)
		int count = -1;
		while (count < 0) {
			count = 0;
			for (int j = 1; j < weapons.length; j++){
				if (weapons[j].compareTo(weapons[j-1]) < 0) {
					Weapon temp = weapons[j];
					weapons[j] = weapons[j - 1];
					weapons[j - 1] = temp;
					count--;
				}
			}
		} */
		if (n > weapons.length) {
			for (int i = 0; i < weapons.length; i++) 
				weapons[i].fire();
		}
		else {
			for (int i = 0; i < n; i++) {
				weapons[i].fire();
			}
		}
	}

}
