/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 02/13/2013
 */

import java.util.Random;
import java.util.Scanner;

public class Mastermind {

	public static void main(String[] args) {
		
		System.out.println("Mastermind!\n ");

		Scanner keyboard = new Scanner(System.in);
		Random rn = new Random();

		System.out
				.println("Setup - Enter four integers (1-6) or four 0s for random: ");
		String inputi = keyboard.nextLine();

		String[] input;

		if (inputi.equals("0 0 0 0")) {
			String random = (String.valueOf(rn.nextInt(6) + 1) + " "
					+ String.valueOf(rn.nextInt(6) + 1) + " "
					+ String.valueOf(rn.nextInt(6) + 1) + " " + String
					.valueOf(rn.nextInt(6) + 1));
			input = random.split(" ");
		} else {
			input = inputi.split(" ");
		}

		int R = 0;
		int W = 0;

		for (int j = 0; j < 10; j++) {

			System.out.print("Guess " + (j + 1) + "/10: ");

			String[] guess = keyboard.nextLine().split(" ");

			for (int i = 0; i < 4; i++) {

				if (guess[i].equals(input[i])) {
					R++;

				} else {

					for (int k = 0; k < 4; k++) {
						if (input[i].equals(guess[k])) {
							W++;
							break;
						}

					}
				}
			}

			if (R == 4 && W == 0) {
				System.out.println("Ans: " + input[0] + " " + input[1] + " "
						+ input[2] + " " + input[3] + "\nGame over!\nYou won!");
				System.exit(0);
			} else {
				System.out.print("R: " + R);
				System.out.println(" W: " + W);
				R = 0;
				W = 0;
			}
		}
		System.out.println("Ans: " + input[0] + " " + input[1] + " " + input[2]
				+ " " + input[3] + "\nGame over!\nYou lost!");

	}

}
