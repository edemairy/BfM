/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfm.combat;

import static bfm.combat.CombatTable.Outcome.*;
import java.util.Random;

/**
 *
 * @author edemairy
 */
public class CombatTable {

	public enum Outcome {
		AL, DR, DRL, DE, NE, EX
	};
	private String[] odds = {
		"1:1", "2:1", "3:1", "4:1", "5:1", "6:1"
	};
	private Outcome[][] table = {
		{DR, DR, DR, DR, DR, DRL},
		{EX, DR, DR, DR, DRL, DRL},
		{EX, EX, DR, EX, DRL, DE},
		{NE, EX, EX, DRL, DRL, DE},
		{NE, NE, EX, DRL, DE, DE},
		{AL, NE, DRL, DE, DE, DE}
	};

	public Outcome fight(int attack, int defense) {
		Outcome result;
		assert(attack > 0);
		assert(defense > 0);
		int dice = (int) (Math.random() * 6);
		int ratio = attack / defense;
		if (ratio == 0) {
			result = NE;
		} else {
			ratio = Math.max(ratio, 6);
			result = table[dice][ratio];
		}
		return result;
	}
}
