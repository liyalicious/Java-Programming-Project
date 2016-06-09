/**
 * Names:Liya Xu & Yangyou Fang 
 * Computing IDs:lx2hy & yf2yn 
 * Section: 102
 * Date:4/19/13
 */

import java.util.Scanner;

import edu.virginia.cs.cs1110.multimedia.Picture;
import edu.virginia.cs.cs1110.multimedia.Pixel;

public class Scale {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please input the image filename(include file type):");
		String img = keyboard.nextLine();
		System.out.println("Please input a scale factor:");
		double factor = keyboard.nextDouble();
		Picture pic = new Picture(img);

		Picture scaled = scaled(pic, factor);

		scaled.show();

	}

	public static Picture scaled(Picture pic, double factor) {
		Picture dest = new Picture((int) (factor * pic.getWidth()),
				(int) (factor * pic.getHeight()));
		dest.setTitle("Scaled Picture");

		for (int col = 0; col < dest.getWidth(); col++) {
			for (int row = 0; row < dest.getHeight(); row++) {

				int rowOld = (int) (row / factor);
				int colOld = (int) (col / factor);
				Pixel pixelNew = dest.getPixel(col, row);
				Pixel pixelOld = pic.getPixel(colOld, rowOld);
				pixelNew.setColor(pixelOld.getColor());

			}
		}
		return dest;

	}
}
