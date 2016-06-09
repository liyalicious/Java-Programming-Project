/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 02/13/2013
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class ShoppingCart {

	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("$############0.00");

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Shopping Cart");
		System.out.println("-------------\n");

		int i = 1;
		System.out.print("Enter Item " + i + " Price (0 to stop): ");
		double total = 0;
		double input = keyboard.nextDouble();

		while (input != 0) {
			i++;
			total += input;
			System.out.print("Enter Item " + i + " Price (0 to stop): ");

			input = keyboard.nextDouble();

		}
		System.out.println("Total Items Bought: " + (i - 1));
		System.out.println("Total Cost: " + formatter.format(total));
		if (i == 1) {
			System.out.println("Avg Item Cost: $0.00");
		} else {
			System.out.println("Avg Item Cost: "
					+ formatter.format(total / (i - 1)));
		}

	}
}
