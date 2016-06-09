/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 1/24/2013
 */

import java.util.Scanner;
import java.text.DecimalFormat;

public class TVSize {

	public static void main(String[] args) {

		System.out.println("TVSize Calculator");
		System.out.println("-----------------\n");

		Scanner keyboard = new Scanner(System.in);

		System.out.print("Diagonal length (in inches): ");
		double diagonal;
		diagonal = keyboard.nextDouble();
		System.out.print("Width ratio: ");
		double width;
		width = keyboard.nextDouble();
		System.out.print("Height ratio: ");
		double height;
		height = keyboard.nextDouble();

		DecimalFormat formatter = new DecimalFormat("#0.0");

		System.out.println("Width: "
				+ formatter.format(width
						* Math.sqrt(Math.pow(diagonal, 2)
								/ (Math.pow(width, 2) + Math.pow(height, 2)))));
		System.out.println("Height: "
				+ formatter.format(height
						* Math.sqrt(Math.pow(diagonal, 2)
								/ (Math.pow(width, 2) + Math.pow(height, 2)))));

	}

}
