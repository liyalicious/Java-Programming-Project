import java.util.Scanner;

import edu.virginia.cs.cs1110.multimedia.Picture;
import edu.virginia.cs.cs1110.multimedia.Pixel;

public class PixelateHighLevel {

	private static RGB[][] blurred;

	private static void Run(Picture pic) {
		for (int row = 0; row < pic.getHeight(); row++) {
			for (int col = 0; col < pic.getWidth(); col++) {
				int sumRed = 0;
				int sumBlue = 0;
				int sumGreen = 0;
				int numSurroundingPixels = 0;

				if (row - 1 >= 0 && col - 1 >= 0) {
					Pixel p1 = pic.getPixel(col - 1, row - 1);
					Pixel p2 = pic.getPixel(col, row - 1);
					Pixel p4 = pic.getPixel(col - 1, row);

					sumRed += p1.getRed() + p2.getRed() + p4.getRed();
					sumBlue += p1.getBlue() + p2.getBlue() + p4.getBlue();
					sumGreen += p1.getGreen() + p2.getGreen() + p4.getGreen();

					numSurroundingPixels += 3;

				}
				else if (row - 1 >= 0) {
					Pixel p2 = pic.getPixel(col, row - 1);

					sumRed += p2.getRed();
					sumGreen += p2.getGreen();
					sumBlue += p2.getBlue();

					numSurroundingPixels += 1;
				}
				else if (col - 1 >= 0) {
					Pixel p4 = pic.getPixel(col - 1, row);
					sumRed += p4.getRed();
					sumGreen += p4.getGreen();
					sumBlue += p4.getBlue();

					numSurroundingPixels += 1;
				}

				if (row + 1 < pic.getHeight() && col + 1 < pic.getWidth()) {
					Pixel p5 = pic.getPixel(col + 1, row);
					Pixel p7 = pic.getPixel(col, row + 1);
					Pixel p8 = pic.getPixel(col + 1, row + 1);

					sumRed += p5.getRed() + p7.getRed() + p8.getRed();
					sumGreen += p5.getGreen() + p7.getGreen() + p8.getGreen();
					sumBlue += p5.getBlue() + p7.getBlue() + p8.getBlue();

					numSurroundingPixels += 3;
				}
				else if (row + 1 < pic.getHeight()) {
					Pixel p7 = pic.getPixel(col, row + 1);

					sumRed += p7.getRed();
					sumGreen += p7.getGreen();
					sumBlue += p7.getBlue();

					numSurroundingPixels += 1;
				}
				else if (col + 1 < pic.getWidth()) {
					Pixel p5 = pic.getPixel(col + 1, row);

					sumRed += p5.getRed();
					sumGreen += p5.getGreen();
					sumBlue += p5.getBlue();

					numSurroundingPixels += 1;
				}

				if (row + 1 < pic.getHeight() && col - 1 >= 0) {
					Pixel p6 = pic.getPixel(col - 1, row + 1);

					sumRed += p6.getRed();
					sumGreen += p6.getGreen();
					sumBlue += p6.getBlue();

					numSurroundingPixels += 1;
				}

				if (row - 1 >= 0 && col + 1 < pic.getWidth()) {
					Pixel p3 = pic.getPixel(col + 1, row - 1);

					sumRed += p3.getRed();
					sumGreen += p3.getGreen();
					sumBlue += p3.getBlue();

					numSurroundingPixels += 1;
				}


				int averageRed = sumRed/numSurroundingPixels;
				int averageGreen = sumGreen/numSurroundingPixels;
				int averageBlue = sumBlue/numSurroundingPixels;


				//averaged.setRed(averageRed);

				blurred[col][row] = new RGB(averageRed, averageGreen, averageBlue);

			}
		}

		for (int row = 0; row < pic.getHeight() - 3; row+=4) {
			for (int col = 0;col < pic.getWidth() - 3; col+=4) {
				
				Pixel p = pic.getPixel(col, row);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 1, row);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 2, row);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 3, row);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col, row + 1);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 1, row + 1);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 2, row + 1);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 3, row + 1);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col, row + 2);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 1, row + 2);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 2, row + 2);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 3, row + 2);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col, row + 3);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 1, row + 3);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 2, row + 3);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
				
				p = pic.getPixel(col + 3, row + 3);
				p.setRed(blurred[col][row].red);
				p.setGreen(blurred[col][row].green);
				p.setBlue(blurred[col][row].blue);
			}
		}
	}

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please input a image filename: ");
		String img = keyboard.nextLine();

		Picture pic = new Picture(img + ".png");

		blurred = new RGB[pic.getWidth()][pic.getHeight()];
		
		Run(pic);

		pic.show();

	}
}
