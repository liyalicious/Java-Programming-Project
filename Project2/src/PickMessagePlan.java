/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 02/03/2013
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class PickMessagePlan {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Input monthly rate for plan 1: ");
		double rate1 = keyboard.nextDouble();
		System.out.print("Included messages for plan 1: ");
		double msg1 = keyboard.nextDouble();
		System.out.print("Charge per message over 300: ");
		double charge1 = keyboard.nextDouble();

		System.out.print("\nInput monthly rate for plan 2: ");
		double rate2 = keyboard.nextDouble();
		System.out.print("Included messages for plan 2: ");
		double msg2 = keyboard.nextDouble();
		System.out.print("Charge per message over 400: ");
		double charge2 = keyboard.nextDouble();

		System.out.print("\nOverall Discount Percentage (0-50): ");
		double discount = keyboard.nextDouble();

		System.out.print("\nAverage Messages Used Per Month: ");
		double msg_m = keyboard.nextDouble();

		DecimalFormat formatter = new DecimalFormat("$#00.00");

		double plan1 = rate1 * (1 - (discount * .01));
		double plan2 = rate2 * (1 - (discount * .01));

		if (msg_m <= msg1) {
			plan1 = plan1 * 1;
			plan2 = plan2 * 1;
		} else if (msg_m >= msg1 && msg_m <= msg2) {
			plan1 = (rate1 + (msg_m - msg1) * charge1) * (1 - (discount * .01));
			plan2 = rate2 * (1 - (discount * .01));

		} else {
			plan1 = (rate1 + (msg_m - msg1) * charge1) * (1 - (discount * .01));
			plan2 = (rate2 + (msg_m - msg2) * charge2) * (1 - (discount * .01));

		}

		System.out.println("\nAverage Cost for Plan 1: "
				+ formatter.format(plan1));
		System.out.println("Average Cost for Plan 2: "
				+ formatter.format(plan2));

		if (plan1 < plan2) {
			System.out.println("\nPlan 1 is cheaper!");
		} else if (plan1 > plan2) {
			System.out.println("\nPlan 2 is cheaper!");
		} else {
			System.out.println("\nThe plans cost the same amount!");
		}

	}

}
