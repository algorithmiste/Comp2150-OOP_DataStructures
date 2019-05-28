
public class CuteCreature {
	/** Each CuteCreature object has the following instance variables: */
	protected String species;
	protected int level; // positive integer that indicates how powerful the creature is
	protected int currentHitPoints; // a measure of how much damage the creature can take before being incapacitated
	protected int maximumHitPoints;
	protected int attackDamage; // a measure of how much damage this creature inflicts when it attacks another creature
	protected int experiencePoints; // a measure of how close the creature is to "leveling up" and becoming more powerful
	protected int experienceValue; // how many experience points this creature is worth when defeated by another creature
	protected boolean isEvolvable; // Evolvable creatures have a slightly different appearance and are somehow very highly prized by the players of your game
	
	// Newly created creatures should have the following attributes:
	public CuteCreature() {
		level = 1;
		currentHitPoints = maximumHitPoints;
		experiencePoints = 0;
	}
	// Constructor
	public CuteCreature(String species, int maximumHitPoints, int attackDamage, int experienceValue, boolean isEvolvable) {
		this.species = species;
		this.level = 1;
		this.maximumHitPoints = maximumHitPoints;
		this.currentHitPoints = maximumHitPoints;
		this.attackDamage = attackDamage;
		this.experienceValue = experienceValue;
		this.experiencePoints = 0;
		this.isEvolvable = isEvolvable;
	}
	
	public void setSpecies(String species) {
		this.species = species;
	}
	public void setMaximumHitPoints(int maximumHitPoints) {
		this.maximumHitPoints = maximumHitPoints;
	}
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	
	/** Method to allow the CuteCreature to take the specified amount of damage to its currentHitPoints*/
	public void takeDamage(int dmg) { 
		currentHitPoints -= dmg;
		if (currentHitPoints <= 0) {
			currentHitPoints = 0;
			System.out.println(this.species + " has been incapacitated!");
		}
	}
	
	/** Method allows the CuteCreature to "level up", increasing the creture's level by 1 with a few additional perks*/
	protected void levelUp() {
		this.level += 1;
		if (level >=2 && level <= 10) {
			currentHitPoints += 4;
			maximumHitPoints += 4;
			attackDamage += 3;
		}
		if (level >= 11) {
			currentHitPoints += 1;
			maximumHitPoints += 1;
			attackDamage += 1;
		}
		experienceValue += 10;
		System.out.println(species + " leveled to " + this.level + "!"); 
	}
	/** Method allows the CuteCreato gain the specified number of experience points, leveling up if necessary */
	public void gainExp(int exp) {
		int initialLevel = this.level;
		experiencePoints += exp;
		System.out.println(species + " gained +" + exp + " experience!\n");
		
		int[] totalExperiencePointsAccumulated = new int[200];
		for (int i = 0; i < totalExperiencePointsAccumulated.length; i++) {
			final int EXPERIENCE_POINTS_NEEDED = 250;
			if (i == 0)
				totalExperiencePointsAccumulated[i] = 250;
			else 
				totalExperiencePointsAccumulated[i] += totalExperiencePointsAccumulated[i - 1] + (EXPERIENCE_POINTS_NEEDED + (i) * 50);
		}
		for (int j = initialLevel - 1; j < totalExperiencePointsAccumulated.length; j++) {
			if (experiencePoints >= totalExperiencePointsAccumulated[j]) 
				levelUp();
			else break;
		}
		System.out.print("\n");
	}
	/** Method allows the calling CuteCreature to make a single attack against another CuteCreature. */
	public void attack(CuteCreature c) {
		int attackValue = (int) (1 + (Math.random() * 100));
		int attackPoints;
		System.out.println(this.species + " is attacking " + c.species + "!");
		
		if (attackValue >= 1 && attackValue <= 80) { // regular hit
			attackPoints = (int) ((0.8 * this.attackDamage) + (Math.random() * (1.2 * this.attackDamage - 0.8 * this.attackDamage + 1)));
			System.out.println("Hit! " + c.species + " took " + attackPoints + " damage!\n");
			c.takeDamage(attackPoints);
		}
		else if (attackValue > 80 && attackValue <= 95) { // creature misses
			System.out.println("Miss!\n");
		}
		else { //critical hit
			attackPoints = (int) ((0.8 * 2 * this.attackDamage) + (Math.random() * (1.2 * 2 * this.attackDamage - 0.8 * 2 * this.attackDamage + 1)));
			System.out.println("Critical Hit! " + c.species + " took " + attackPoints + " damage!\n");
			c.takeDamage(attackPoints);	
		}
		if (c.currentHitPoints <= 0) {
			System.out.println(this.species + " defeated " + c.species + "!");
			this.gainExp(c.experienceValue);
		}
	}
	// toString Method shows all instance variables of the CuteCreature
	public String toString() {
		String str;
		int[] totalExperiencePointsAccumulated = new int[200];
		for (int i = 0; i < totalExperiencePointsAccumulated.length; i++) {
			final int EXPERIENCE_POINTS_NEEDED = 250;
			if (i == 0)
				totalExperiencePointsAccumulated[i] = 250;
			else 
				totalExperiencePointsAccumulated[i] += totalExperiencePointsAccumulated[i - 1] + (EXPERIENCE_POINTS_NEEDED + (i * 50));
		}	
		if (isEvolvable) {
			str = "Evolvable!";
		}
		else {
			str = "Not Evolvable";
		}
		return ("Level " + this.level + " " + this.species + "\n" + "______________________" + "\n" +
				"*** " + str + " ***" + "\n" + "HP: " + this.currentHitPoints + "/" + this.maximumHitPoints + "\n" +
				"Attack Dmg: " + this.attackDamage + "\n" + "XP: " + this.experiencePoints + "/" + totalExperiencePointsAccumulated[this.level - 1]+ 
				"\n" + "XP Value: " + this.experienceValue);
}
	
	
	public static void main(String[] args) {
		CuteCreature c = new CuteCreature("Tore-Chick", 40, 6, 600, false);
		CuteCreature c2 = new CuteCreature("Bowlbasore", 40, 6, 600, false);
		
		System.out.println(c + "\n");
		System.out.println(c2 + "\n"); 
		
		while (c.currentHitPoints > 0 && c2.currentHitPoints > 0) {
			c.attack(c2);
			if (c2.currentHitPoints > 0)
				c2.attack(c);
		}
		System.out.println(c + "\n");
		System.out.println(c2); 
	}

}
