
public class Client_Spaceship {

	public static void main(String[] args) {
		Weapon scBoom = new SingularityCannon("scBoom", 10, 6);
		Weapon laserWars = new Laser("laserWars", 12, 3);
		Weapon scBang = new SingularityCannon("scBang", 25, 2);
		Weapon laserTrek = new Laser("laserTrek", 19, 1);
		Weapon laserTag = new Laser("laserTag", 25, 9);
		Weapon hBomb = new HydroBomb("hBomb", 50, 25);
		
		Spaceship spaceOvO = new Spaceship();
		spaceOvO.addWeapon(scBoom);
		spaceOvO.addWeapon(laserWars);
		spaceOvO.addWeapon(scBang);
		spaceOvO.addWeapon(laserTrek);
		spaceOvO.addWeapon(laserTag);
		spaceOvO.addWeapon(hBomb);
		
		spaceOvO.fireFastestWeapons(6);
			
		
	}

}
