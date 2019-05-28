
public class HydroBomb extends Weapon {
	
	public HydroBomb(String name, int damage, int loadTime) {
		super(damage, loadTime);
		setName(name);
//		setDamage(damage);
//		setLoadTime(loadTime);
	}
	
	public void fire() {
		System.out.println(this.name + " fire ! ");
	}
	public void draw() {
		System.out.println("Drawing " + this.name + "!");
	}

}
