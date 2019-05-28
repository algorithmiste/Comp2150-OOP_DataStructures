
public abstract class Weapon implements Comparable, Drawable {
	// Instance variables
	protected String name;
	protected int damage;
	protected int loadTime;
	public static int numWeapons = 0;
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getLoadTime() {
		return this.loadTime;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setLoadTime(int loadTime) {
		this.loadTime = loadTime;
	}
	public Weapon() {
		this.damage = (int) (5 + Math.random() * 25);
		this.loadTime = (int) (1 + Math.random() * 15);
		numWeapons++;
	}
	
	public Weapon(int damage, int loadTime) {
		setDamage(damage);
		setLoadTime(loadTime);
		numWeapons++;
	}
	
	public abstract void fire();
	
	public abstract void draw();
	
	public int compareTo(Comparable c){
		if (this.loadTime ==  ((Weapon)c).loadTime)
			return 0;
		else if (this.loadTime < ((Weapon)c).loadTime)
			return -1;
		else
			return 1; 
	}
	
}
