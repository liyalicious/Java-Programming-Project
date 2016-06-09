/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 02/13/2013
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class PredatorPrey {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		DecimalFormat formatter = new DecimalFormat("###0.00");

		System.out.print("Rate Prey Birth Exceeds Natural Death: ");
		double BEDeath = keyboard.nextDouble();
		System.out.print("Rate of Predation: ");
		double predation = keyboard.nextDouble();
		System.out
				.print("Rate at which Predator Death Exceeds Birth Without Food: ");
		double DeathEBnofood = keyboard.nextDouble();
		System.out.print("Predator Birth Rate with Food: ");
		double Bratefood = keyboard.nextDouble();
		System.out.print("Initial Prey Population: ");
		double preypop = keyboard.nextDouble();
		System.out.print("Initial Predator Population: ");
		double predpop = keyboard.nextDouble();
		System.out.print("Number of Periods: ");
		double period = keyboard.nextDouble();

		System.out
				.println("Period  |  Prey  |  Predator\n----------------------------");

		double a;
		double b;

		for (int i = 0; i < period; i++) {
			System.out.println((i) + "        " + formatter.format(preypop)
					+ "      " + formatter.format(predpop));
			a = preypop;
			preypop = preypop * (1 + BEDeath - predation * predpop);
			predpop = predpop * (1 - DeathEBnofood + Bratefood * a);

		}

	}

}
