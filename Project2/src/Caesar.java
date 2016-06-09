/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 02/03/2013
 */

import java.util.Scanner;

public class Caesar {


	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please give me the ciphertext: ");
		String cipher = keyboard.nextLine();

		char c1 = cipher.charAt(0);
		char c2 = cipher.charAt(1);
		char c3 = cipher.charAt(2);
		char c4 = cipher.charAt(3);
		char c5 = cipher.charAt(4);
		char c6 = cipher.charAt(5);
		char c7 = cipher.charAt(6);
		char c8 = cipher.charAt(7);
		char c9 = cipher.charAt(8);
		char c10 = cipher.charAt(9);
		char c11 = cipher.charAt(10);
		char c12 = cipher.charAt(11);

		System.out.println("The decoded phrase is: " + (char) (c1 - 3)
				+ (char) (c2 - 3) + (char) (c3 - 3) + (char) (c4 - 3)
				+ (char) (c5 - 3) + (char) (c6 - 3) + (char) (c7 - 3)
				+ (char) (c8 - 3) + (char) (c9 - 3) + (char) (c10 - 3)
				+ (char) (c11 - 3) + (char) (c12 - 3));

	}

}
