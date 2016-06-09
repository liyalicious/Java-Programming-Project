/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 1/24/2013
 */

import java.util.Scanner;

public class Distance {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the first coordinate: ");
		double x0 = keyboard.nextInt();
		double y0 = keyboard.nextInt();

		System.out.print("Enter the second coordinate: ");
		double x1 = keyboard.nextInt();
		double y1 = keyboard.nextInt();

		double Rise = y1 - y0;

		double Run = x1 - x0;

		System.out.println("Rise: " + Rise);
		System.out.println("Run: " + Run);
		System.out.println("Slope: " + Rise / Run);
		System.out.println("Length: "
				+ Math.sqrt(Math.pow(Rise, 2) + Math.pow(Run, 2)));

	}

}
