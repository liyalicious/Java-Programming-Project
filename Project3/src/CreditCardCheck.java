/**
 * Name: Liya Xu
 * Computing ID: lx2hy
 * Lab Section: (100-110 or 1111) 102
 * Date: 02/13/2013
 */

import java.util.Scanner;


public class CreditCardCheck {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please enter a credit card number with no spaces: ");
		String card = keyboard.nextLine();
		
		int n = card.length();
		
		int a = 0;
		int b = 0;
		
		for(int i = 0; i < 16; i+=2){
			a += Integer.parseInt(Character.toString(card.charAt(n-1-i)));
		}
		
		String s = "";
		String t = "";
		
		for(int j = 0; j < 16; j+=2){
			s += Character.toString(card.charAt(n-2-j));
		}

		int l = s.length();
		
		for (int k = 0; k < 8; k++){
			b = 2 * (Integer.parseInt(Character.toString(s.charAt(l-1-k))));
			t += String.valueOf(b);
		}
		
		int d = t.length();
		int c = 0;
		
		for (int z =0; z < d; z++){
			c += (Integer.parseInt(Character.toString(t.charAt(d-1-z))));
		}
		
		int sum = a + c;

		int m;
		
		if (sum % 10 == 0){
			System.out.println("Valid Credit Card!");
		}else{
			m = (10 - sum%10) + (Integer.parseInt(Character.toString(card.charAt(n-1))));
			System.out.println("Invalid Credit Card!\nThe check digit should be: " + m);
			}
			
		

	}

}
