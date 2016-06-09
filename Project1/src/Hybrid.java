/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 1/24/2013
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Hybrid {

	public static void main(String[] args) {
		System.out.println("Hybrid Car Value Calculator");
		System.out.println("---------------------------\n");

		Scanner keyboard = new Scanner(System.in);

		System.out.print("What is the cost of a new hybrid car?: ");
		double hcost;
		hcost = keyboard.nextDouble();

		System.out.print("What is the cost of a comparison car?: ");
		double ccost;
		ccost = keyboard.nextDouble();

		System.out.print("Estimated miles driven per year?: ");
		double miles;
		miles = keyboard.nextDouble();

		System.out.print("Estimated gas price (per gallon)?: ");
		double price;
		price = keyboard.nextDouble();

		System.out.print("Estimated miles per gallon for the hybrid?: ");
		double m_gal_h;
		m_gal_h = keyboard.nextDouble();

		System.out
				.print("Estimated miles per gallon for the comparison car?: ");
		double m_gal_c;
		m_gal_c = keyboard.nextDouble();

		System.out.println(" ");

		DecimalFormat formatter = new DecimalFormat("$#####0.00");

		System.out.println("Cost of the hybrid car after 1 / 5 / 10 years: "
				+ formatter.format(hcost + miles / m_gal_h * price) + " / "
				+ formatter.format(hcost + miles / m_gal_h * price * 5) + " / "
				+ formatter.format(hcost + miles / m_gal_h * price * 10));

		System.out
				.println("Cost of the comparison car after 1 / 5 / 10 years: "
						+ formatter.format(ccost + miles / m_gal_c * price)
						+ " / "
						+ formatter.format(ccost + miles / m_gal_c * price * 5)
						+ " / "
						+ formatter
								.format(ccost + miles / m_gal_c * price * 10));

	}

}
