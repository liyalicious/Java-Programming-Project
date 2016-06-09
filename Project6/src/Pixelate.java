/**
 * Names:Liya Xu & Yangyou Fang
 * Computing IDs:lx2hy & yf2yn
 * Section: 102
 * Date:4/19/13
 */

import java.util.Scanner;

import edu.virginia.cs.cs1110.multimedia.Picture;
import edu.virginia.cs.cs1110.multimedia.Pixel;

public class Pixelate {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please input the image filename(include file type):");
		String img = keyboard.nextLine();
		Picture pic = new Picture(img);
		Picture newPic = scaled(pic, 0.25);
		for (int x = 2; x < newPic.getWidth() - 2; x++) {
			for (int y = 2; y < newPic.getHeight() - 2; y++) {
				blurPixel(pic, x, y);
			}
		}
		Picture picBlurred = scaled(newPic, 4);
		picBlurred.show();

	}

	public static Pixel blurPixel(Picture in, int x, int y) {
		int blueSum = 0;
		int greenSum = 0;
		int redSum = 0;
		for (int xOffset = -2; xOffset <= 2; xOffset++) {
			for (int yOffset = -2; yOffset <= 2; yOffset++) {
				Pixel p = in.getPixel(x + xOffset, y + yOffset);
				redSum += p.getRed();
				blueSum += p.getBlue();
				greenSum += p.getGreen();
			}
		}
		Pixel blurred = new Pixel(in, x, y);
		blurred.setRed(redSum / 25);
		blurred.setBlue(blueSum / 25);
		blurred.setGreen(greenSum / 25);
		return blurred;
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
