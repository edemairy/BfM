/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfm.combat;

import bfm.combat.CombatTable.Outcome;
import static bfm.combat.CombatTable.Outcome.*;
import org.testng.annotations.Test;

/**
 *
 * @author edemairy
 */
public class CombatTableTest {
	
	Outcome[][] possibleResultArray = {
		{NE},
		{DR,EX,NE,AL},
		{DR,EX,NE},
		{DR,EX,DRL},
		{DR,EX,DRL,DE},
		{DR,DRL,DE},
		{DRL,DE}
	};
//	@Test(groups = { "slow" })
	public CombatTableTest() {
		int[] nbOdds = new int[7];
		CombatTable table = new CombatTable();
		for (int i=0; i<10000; i++){
			int defense = (int) (Math.random() * 100) + 1;
			int attack = (int) (Math.random() * 1000) + 1;
			int odds = attack / defense;	
			odds = Math.min(odds, 6);
			Outcome combatResult = table.fight(attack, defense);
			assert(linearFind(combatResult, possibleResultArray[odds]));	
		}
		
	}
	public<T> boolean linearFind(T x, T[] a) {
		assert(a != null);
		if (a.length == 0) {
			return false;
		}	
		for (int cpt = 0; cpt <a.length; cpt++) {
			if (a[cpt].equals(x)) {
				return true;
			}
		}
		return false;
	}
	
}
