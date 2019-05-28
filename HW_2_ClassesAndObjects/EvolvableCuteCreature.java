
public class EvolvableCuteCreature extends CuteCreature {
	protected String attunedElement;
	protected boolean hasEvolved;
	
	public EvolvableCuteCreature() {
		super();
		hasEvolved = (this.level >= 20);
	}
	
	public EvolvableCuteCreature(String species, int maximumHitPoints, int attackDamage, int experienceValue, boolean isEvolvable) {
		super(species, maximumHitPoints, attackDamage, experienceValue, isEvolvable);
		
		hasEvolved = (this.level >= 20);
		if (species.charAt(0) >= 'A' && species.charAt(0) <= 'G') 
			attunedElement = "fire";
		else if (species.charAt(0) >= 'H' && species.charAt(0) <= 'M') 
			attunedElement = "water";
		else if (species.charAt(0) >= 'N' && species.charAt(0) <= 'S') 
				attunedElement = "air";
		else if (species.charAt(0) >= 'T' && species.charAt(0) <= 'Z') 
				attunedElement = "earth";	
		}
	public void attackResisted(CuteCreature c) {
		double attackPoints = 0.25 * this.attackDamage;
		System.out.println("Elemental Hit Resisted! " + c.species + " took " + attackPoints + " damage!\n");
		((EvolvableCuteCreature)c).takeDamage(attackPoints);
		
		if (c.currentHitPoints <= 0) {
			System.out.println(this.species + " defeated " + c.species + "!");
			super.gainExp(((EvolvableCuteCreature)c).experienceValue);
		}
	}
	public void attackVulnerable(CuteCreature c) {
		int attackPoints = 4 * this.attackDamage;
		System.out.println("Hardcore Elemental Hit! " + c.species + " took " + attackPoints + " damage!\n");
		((EvolvableCuteCreature)c).takeDamage(attackPoints);
		
		if (c.currentHitPoints <= 0) {
			System.out.println(this.species + " defeated " + c.species + "!");
			super.gainExp(((EvolvableCuteCreature)c).experienceValue);
		}
	}
	public void attackNormal(CuteCreature c) {
		int attackPoints = this.attackDamage;
		System.out.println("Normal Elemental Hit! " + c.species + " took " + attackPoints + " damage!\n");
		((EvolvableCuteCreature)c).takeDamage(attackPoints);
		if (c.currentHitPoints <= 0) {
			System.out.println(this.species + " defeated " + c.species + "!");
			super.gainExp(((EvolvableCuteCreature)c).experienceValue);
		}
	}
	public void takeDamage(double dmg) { 
		currentHitPoints -= dmg;
		if (currentHitPoints <= 0) {
			currentHitPoints = 0;
			System.out.println(this.species + " has been incapacitated!");
		}
	}
	
	public void elementalAttack(CuteCreature c) {
		if (!(this.hasEvolved)) {
			System.out.println(this.species + " hasn't evolved yet!");
		}
		else {
			EvolvableCuteCreature e = (EvolvableCuteCreature)c;
			//System.out.println(this.species + " is attacking " + c.species + "!");
			if (this.attunedElement == "fire") {
				if (!(c instanceof EvolvableCuteCreature) && !e.hasEvolved || e.attunedElement == "earth")
					attackNormal(e);
				else if (e.attunedElement.equals(this.attunedElement))
					super.attack(e);
				else if (e.attunedElement == "water")
					attackResisted(e);
				else if (e.attunedElement == "air")
					attackVulnerable(e);
			}
			if (this.attunedElement == "water") {
				if (!(c instanceof EvolvableCuteCreature) && !e.hasEvolved || e.attunedElement == "air") 
					attackNormal(e);
				else if (e.attunedElement.equals(this.attunedElement))
					super.attack(e);
				else if (e.attunedElement == "earth")
					attackResisted(e);
				else if (e.attunedElement == "fire")
					attackVulnerable(e);
			}
			if (this.attunedElement == "air") {
				if (!(c instanceof EvolvableCuteCreature) && !e.hasEvolved || e.attunedElement == "water")
						attackNormal(e);
					else if (e.attunedElement.equals(this.attunedElement))
						super.attack(e);
					else if (e.attunedElement == "fire")
						attackResisted(e);
					else if (e.attunedElement == "earth")
						attackVulnerable(e);
			}
			if (this.attunedElement == "earth") {
				if (!(c instanceof EvolvableCuteCreature) && !e.hasEvolved || e.attunedElement == "fire")
					attackNormal(e);
				else if (e.attunedElement.equals(this.attunedElement))
					super.attack(e);
				else if (e.attunedElement == "air")
					attackResisted(e);
				else if (e.attunedElement == "water")
					attackVulnerable(e);	
			}
		}
	}
	
	protected void levelUp() {
		if (this.level < 20) {
			super.levelUp();
		}
		else {
			if (this.level == 20) {
				this.hasEvolved = true;
				System.out.println(this.species + " has evolved! Attuned element: " + this.attunedElement);
				this.maximumHitPoints += 15;
				this.currentHitPoints += 15;
				this.attackDamage += 5;
			}
			super.levelUp();	
		}
	}

	public String toString() {
		String element = this.attunedElement;
		element = this.attunedElement.toUpperCase();
		String str = "Attuned to " + element;
		
		int[] totalExperiencePointsAccumulated = new int[200];
		for (int i = 0; i < totalExperiencePointsAccumulated.length; i++) {
			final int EXPERIENCE_POINTS_NEEDED = 250;
			if (i == 0)
				totalExperiencePointsAccumulated[i] = 250;
			else 
				totalExperiencePointsAccumulated[i] += totalExperiencePointsAccumulated[i - 1] + (EXPERIENCE_POINTS_NEEDED + (i * 50));
		}	
			
		return ("Level " + this.level + " " + this.species + "\n" + "________________________" + "\n" +
				"*** " + str + " ***" + "\n" + "HP: " + this.currentHitPoints + "/" + this.maximumHitPoints + "\n" +
				"Attack Dmg: " + this.attackDamage + "\n" + "XP: " + this.experiencePoints + "/" + totalExperiencePointsAccumulated[this.level - 1]+ 
				"\n" + "XP Value: " + this.experienceValue);
	}
	

}
