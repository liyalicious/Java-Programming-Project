/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 1/24/2013
 */

import java.util.Scanner;

public class Add {

	public static void main(String[] args) {
		Scanner myKeyboard = new Scanner(System.in);

		System.out.print("Input a number: ");
		int x = myKeyboard.nextInt();

		System.out.print("Input another number: ");
		int y = myKeyboard.nextInt();

		int z = x + y;
		System.out.print("The sum is ");
		System.out.println(z);
	}

}
