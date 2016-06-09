/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 02/03/2013
 */

import java.util.Scanner;

public class Roman {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out
				.print("Please enter an integer greater than 0 and less than 4000: ");
		int number = keyboard.nextInt();

		if (number <= 0 || number >= 4000) {
			System.out.println("Not a valid entry!");
			System.exit(0);
		}

		int number1 = number % 10;
		int number2 = number % 100 - number % 10;
		int number3 = number % 1000 - number % 100;
		int number4 = number % 10000 - number % 1000;

		String n4;
		String n3;
		String n2;
		String n1;

		if (number4 == 3000) {
			n4 = "MMM";
		} else if (number4 == 2000) {
			n4 = "MM";
		} else {
			n4 = "M";
		}

		if (number3 == 900) {
			n3 = "CM";
		} else if (number3 == 800) {
			n3 = "DCCC";
		} else if (number3 == 700) {
			n3 = "DCC";
		} else if (number3 == 600) {
			n3 = "DC";
		} else if (number3 == 500) {
			n3 = "D";
		} else if (number3 == 400) {
			n3 = "CD";
		} else if (number3 == 300) {
			n3 = "CCC";
		} else if (number3 == 200) {
			n3 = "CC";
		} else {
			n3 = "C";
		}

		if (number2 == 90) {
			n2 = "XC";
		} else if (number2 == 80) {
			n2 = "LXXX";
		} else if (number2 == 70) {
			n2 = "LXX";
		} else if (number2 == 60) {
			n2 = "LX";
		} else if (number2 == 50) {
			n2 = "L";
		} else if (number2 == 40) {
			n2 = "XL";
		} else if (number2 == 30) {
			n2 = "XXX";
		} else if (number2 == 20) {
			n2 = "XX";
		} else {
			n2 = "X";
		}

		if (number1 == 9) {
			n1 = "IX";
		} else if (number1 == 8) {
			n1 = "VIII";
		} else if (number1 == 7) {
			n1 = "VII";
		} else if (number1 == 6) {
			n1 = "VI";
		} else if (number1 == 5) {
			n1 = "V";
		} else if (number1 == 4) {
			n1 = "IV";
		} else if (number1 == 3) {
			n1 = "III";
		} else if (number1 == 2) {
			n1 = "II";
		} else {
			n1 = "I";
		}

		if (number >= 1000) {
			System.out.println(n4 + n3 + n2 + n1);
		} else if (number >= 100 && number < 1000) {
			System.out.println(n3 + n2 + n1);
		} else if (number >= 10 && number < 100) {
			System.out.println(n2 + n1);
		} else {
			System.out.println(n1);
		}

	}

}
