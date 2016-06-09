/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 02/03/2013
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {


	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("Input 3 decimal numbers separated by 2 operators: ");
		double num1 = keyboard.nextDouble();
		String oper1 = keyboard.next();
		char op1 = oper1.charAt(0);
		double num2 = keyboard.nextDouble();
		String oper2 = keyboard.next();
		char op2 = oper2.charAt(0);
		double num3 = keyboard.nextDouble();

		double ans;

		if (op1 == '+') {
			if (op2 == '+') {
				ans = num1 + num2 + num3;
			} else if (op2 == '-') {
				ans = num1 + num2 - num3;
			} else if (op2 == '*') {
				ans = num2 * num3 + num1;
			} else {
				ans = num2 / num3 + num1;
			}

		} else if (op1 == '-') {
			if (op2 == '+') {
				ans = num1 - num2 + num3;
			} else if (op2 == '-') {
				ans = num1 - num2 - num3;
			} else if (op2 == '*') {
				ans = num1 - num2 * num3;
			} else {
				ans = num1 - num2 / num3;
			}
		} else if (op1 == '*') {
			if (op2 == '+') {
				ans = num1 * num2 + num3;
			} else if (op2 == '-') {
				ans = num1 * num2 - num3;
			} else if (op2 == '*') {
				ans = num1 * num2 * num3;
			} else {
				ans = num1 * num2 / num3;
			}

		} else {
			if (op2 == '+') {
				ans = num1 / num2 + num3;
			} else if (op2 == '-') {
				ans = num1 / num2 - num3;
			} else if (op2 == '*') {
				ans = num1 / num2 * num3;
			} else {
				ans = num1 / num2 / num3;
			}
		}

		DecimalFormat formatter = new DecimalFormat("#####0.00");
		System.out.println("The result is: " + formatter.format(ans));

	}

}
