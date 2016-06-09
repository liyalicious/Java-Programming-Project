/**
 * Names:Liya Xu & Yangyou Fang 
 * Computing IDs:lx2hy & yf2yn 
 * Section: 102
 * Date:4/19/13
 */

import java.util.Scanner;

import edu.virginia.cs.cs1110.multimedia.Picture;
import edu.virginia.cs.cs1110.multimedia.Pixel;

public class Collage {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please input 4 image file names(include file type): ");
		String img1 = keyboard.nextLine();
		String img2 = keyboard.nextLine();
		String img3 = keyboard.nextLine();
		String img4 = keyboard.nextLine();

		Picture pic1 = new Picture(img1);
		Picture pic2 = new Picture(img2);
		Picture pic3 = new Picture(img3);
		Picture pic4 = new Picture(img4);

		int maxWidth, maxHeight;

		if (Math.max(pic1.getWidth(), pic2.getWidth()) >= Math.max(
				pic3.getWidth(), pic4.getWidth())) {
			maxWidth = Math.max(pic1.getWidth(), pic2.getWidth());
		} else {
			maxWidth = Math.max(pic3.getWidth(), pic4.getWidth());
		}

		if (Math.max(pic1.getHeight(), pic2.getHeight()) >= Math.max(
				pic3.getHeight(), pic4.getHeight())) {
			maxHeight = Math.max(pic1.getHeight(), pic2.getHeight());
		} else {
			maxHeight = Math.max(pic3.getHeight(), pic4.getHeight());
		}

		Picture dest = new Picture((int) (maxWidth * 1.5),
				(int) (maxHeight * 1.5));

		for (int row = 0; row < pic1.getHeight(); row++) {
			for (int col = 0; col < pic1.getWidth(); col++) {
				Pixel source = pic1.getPixel(col, row);
				Pixel d = dest.getPixel(col, row);
				d.setColor(source.getColor());
			}
		}

		for (int row = 0; row < pic2.getHeight(); row++) {
			for (int col = 0; col < pic2.getWidth(); col++) {
				Pixel source2 = pic2.getPixel(col, row);
				Pixel d2 = dest.getPixel(
						(int) (maxWidth * 1.5) - pic2.getWidth() + col, row);
				d2.setColor(source2.getColor());
			}
		}

		for (int row = 0; row < pic3.getHeight(); row++) {
			for (int col = 0; col < pic3.getWidth(); col++) {
				Pixel source3 = pic3.getPixel(col, row);
				Pixel d3 = dest.getPixel(col,
						(int) (maxHeight * 1.5) - pic3.getHeight() + row);
				d3.setColor(source3.getColor());
			}
		}

		for (int row = 0; row < pic4.getHeight(); row++) {
			for (int col = 0; col < pic4.getWidth(); col++) {
				Pixel source4 = pic4.getPixel(col, row);
				Pixel d4 = dest.getPixel(
						(int) (maxWidth * 1.5) - pic4.getWidth() + col,
						(int) (maxHeight * 1.5) - pic4.getHeight() + row);
				d4.setColor(source4.getColor());
			}
		}

		dest.show();

	}

}
