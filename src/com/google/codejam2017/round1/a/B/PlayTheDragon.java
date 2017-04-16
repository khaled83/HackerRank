package com.google.codejam2017.round1.a.B;

import java.util.HashSet;
import java.util.Scanner;

public class PlayTheDragon {
	
/**


 
*/

	private static HashSet<String> memo = new HashSet<String>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		
		for (int i=0; i<numCases; i++) {
			int H = sc.nextInt();
			int attackD = sc.nextInt();
			int healthK = sc.nextInt();
			int attackK = sc.nextInt();
			int B = sc.nextInt();
			int D = sc.nextInt();
			
			int steps = 0;
			boolean dragonTurn = true;
			
			int healthD = H;
			String action = "";
			
			System.out.println("Hd Ad Hk Ak B  D");
			System.out.println(healthD + "  " + attackD + "  " + healthK + "  " + attackK + "  " + B + "  " + D);
			
			while (healthK > 0 && healthD > 0) {
				
				if (dragonTurn) {
					dragonTurn = false;
					
					if (memo.contains(hash(healthD, attackD, healthK, attackK, action))) {
						System.out.print("found => ");
						System.out.println(healthD + " " + attackD + " " + healthK + " " + + attackK);
						System.out.println(memo);
						healthD = 0;
						break;
					}
					
					if ((healthK - attackD) <= 0) {
						System.out.println("attack *");
						healthK -= attackD;
						action = "attack";
						if ( !memo.contains(hash(healthD, attackD, healthK, attackK, action))) {
							steps++;
							break;
						}
						// retract
						else {
							healthK += attackD;
						}
					}
					
					// danger
					boolean danger = (healthD - attackK) <= 0;
					if (danger) {
						int tmp = healthD;
						healthD = H;
						action = "cure";
						if ( !memo.contains(hash(healthD, attackD, healthK, attackK, action))) {
							steps++;
							System.out.println("cure");
						}
						// retract
						else {
							healthD = tmp;
						}
					}
					else {
						
						// BUFF
						int attackSteps = healthK / attackD + (healthK % attackD > 0 ? 1 : 0);
						int buffSteps =  1 + (healthK / (attackD+B))  + (healthK % (attackD+B) > 0 ? 1 : 0);
						
						// DEBUFF
						int debuffSteps = 1 + ((healthK-D) / attackD) + (((healthK-D) % attackD) > 0 ? 1 : 0);
						
						// attack
						if (attackSteps < buffSteps && attackSteps < debuffSteps) {
							// attack
							healthK -= attackD;
							action = "attack";
							if ( !memo.contains(hash(healthD, attackD, healthK, attackK, action))) {
								System.out.println("attack");
								steps++;
							}
							// retract
							else {
								healthK += attackD;
							}
						}
						// buff
						else if (buffSteps < debuffSteps) {
							attackD += B;
							action = "buff";
							if ( !memo.contains(hash(healthD, attackD, healthK, attackK, action))) {
								steps++;
								System.out.println("buff");
							}
							// retract
							else {
								attackD -= B;
							}
						}
						// debuff
						else {
							healthK -= D;
							action = "debuff";
							if ( !memo.contains(hash(healthD, attackD, healthK, attackK, action))) {
								steps++;
								System.out.println("debuff");
							}
							// retract
							else {
								healthK += D;
							}
						}
						
					}
					
					System.out.print("cach  => ");
					System.out.println(healthD + " " + attackD + " " + healthK + " " + + attackK);
					memo.add(hash(healthD, attackD, healthK, attackK, action));
					
				}
				// knight turn
				else {
					dragonTurn = true;
					System.out.println("knight");
					healthD -=  attackK;
				}
				
			}
			
			if (healthD <= 0) {
				System.out.println("Case #" + (i+1) + ": IMPOSSIBLE");
			} else {
				System.out.println("Case #" + (i+1) + ": " + steps);
			}
				
		}
		
//		for (int i=0; i<numCases; i++) {
//			System.out.println("Case #" + (i+1) + ": ");
//		}
		sc.close();
	}
	
	private static String hash(int healthD, int attackD, int healthK, int attackK, String action) {
		return (Integer.toString(99999 * healthD) + Integer.toString(999 * healthK) + Integer.toString(9 * attackD) + Integer.toString(999999999 * attackK)) + action;
	}

}
