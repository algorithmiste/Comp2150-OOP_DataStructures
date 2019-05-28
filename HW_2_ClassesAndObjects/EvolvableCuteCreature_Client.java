import java.util.*;

public class EvolvableCuteCreature_Client {
	/** If EvolvableCuteCreature attuned to same element use super.attack
	 * 	Else elemental attack: Normal, Resisted, Hardcore */
	
	public static void main(String[] args) {
		EvolvableCuteCreature III = new EvolvableCuteCreature("Ioared", 575, 4, 18000, true);
		EvolvableCuteCreature MMM = new EvolvableCuteCreature("MildBoared", 575, 5, 18000, true);
		
		System.out.println(III + "\n");
		System.out.println(MMM + "\n"); 
		
		while (MMM.currentHitPoints > 0 && III.currentHitPoints > 0) {
			if (!III.hasEvolved && !MMM.hasEvolved ) {
				for (int i = 0; i < 20; i++) {
					III.levelUp();
					MMM.levelUp();
				}
			} 
			MMM.elementalAttack(III);
			if (III.currentHitPoints > 0)
				III.elementalAttack(MMM);
			if (!III.hasEvolved && !MMM.hasEvolved)
				break;
		
		}
		System.out.println(III + "\n");
		System.out.println(MMM); 
		
		EvolvableCuteCreature JJJ = new EvolvableCuteCreature("Joared", 1000, 4, 25000, true);
		EvolvableCuteCreature ZZZ = new EvolvableCuteCreature("ZildBoared", 1000, 5, 25000, true);
		
		System.out.println(JJJ + "\n");
		System.out.println(ZZZ + "\n"); 
		
		while (JJJ.currentHitPoints > 0 && ZZZ.currentHitPoints > 0) {
			if (!JJJ.hasEvolved && !ZZZ.hasEvolved ) {
				for (int i = 0; i < 20; i++) {
					JJJ.levelUp();
					ZZZ.levelUp();
				}
			} 
			JJJ.elementalAttack(ZZZ);
			if (ZZZ.currentHitPoints > 0)
				ZZZ.elementalAttack(JJJ);
			if (!JJJ.hasEvolved && !ZZZ.hasEvolved)
				break;
		}
		System.out.println(JJJ + "\n");
		System.out.println(ZZZ);
		
		EvolvableCuteCreature PPP = new EvolvableCuteCreature("Poared", 575, 4, 25000, true);
		EvolvableCuteCreature BBB = new EvolvableCuteCreature("BildBoared", 575, 5, 25000, true);
		
		System.out.println(PPP + "\n");
		System.out.println(BBB + "\n"); 
		
		while (BBB.currentHitPoints > 0 && PPP.currentHitPoints > 0) {
			if (!PPP.hasEvolved && !BBB.hasEvolved ) {
				for (int i = 0; i < 20; i++) {
					PPP.levelUp();
					BBB.levelUp();
				}
			} 
			BBB.elementalAttack(PPP);
			if (PPP.currentHitPoints > 0)
				PPP.elementalAttack(BBB);
			if (!PPP.hasEvolved && !BBB.hasEvolved)
				break;
		
		}
		System.out.println(PPP + "\n");
		System.out.println(BBB); 
		
		EvolvableCuteCreature HHH = new EvolvableCuteCreature("Hoared", 575, 4, 25000, true);
		EvolvableCuteCreature NNN = new EvolvableCuteCreature("NildBoared", 575, 5, 25000, true);
		
		System.out.println(HHH + "\n");
		System.out.println(NNN + "\n"); 
		
		while (NNN.currentHitPoints > 0 && HHH.currentHitPoints > 0) {
			if (!HHH.hasEvolved && !NNN.hasEvolved ) {
				for (int i = 0; i < 20; i++) {
					HHH.levelUp();
					NNN.levelUp();
				}
			} 
			NNN.elementalAttack(HHH);
			if (HHH.currentHitPoints > 0)
				HHH.elementalAttack(NNN);
			if (!HHH.hasEvolved && !NNN.hasEvolved)
				break;
		
		}
		System.out.println(HHH + "\n");
		System.out.println(NNN);
		
	
	}

}
