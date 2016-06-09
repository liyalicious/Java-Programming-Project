/**
 * Names:Liya Xu & Yangyou Fang
 * Computing IDs:lx2hy & yf2yn
 * Section: 102
 * Date:4/19/13
 */

import java.awt.Color;
import java.util.Scanner;

import edu.virginia.cs.cs1110.multimedia.Picture;
import edu.virginia.cs.cs1110.multimedia.Pixel;

public class RedEye {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please input the image filename(include file type):");
		String img = keyboard.nextLine();
		Picture pic = new Picture(img);

		for (int row = 0; row < pic.getHeight(); row++) {
			for (int col = 0; col < pic.getWidth(); col++) {

				Pixel p = pic.getPixel(col, row);
				double redIntensity = ((double) p.getRed() / ((p.getGreen() + p
						.getBlue()) / 2));
				if (redIntensity > 2.2) {
					p.setColor(Color.black);
				}
			}
		}
		
		pic.show();
	}
}
