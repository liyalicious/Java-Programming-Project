/**
 * Names:Liya Xu & Yangyou Fang
 * Computing IDs:lx2hy & yf2yn
 * Section: 102
 * Date:4/19/13
 */

import java.util.Scanner;

import edu.virginia.cs.cs1110.multimedia.Picture;
import edu.virginia.cs.cs1110.multimedia.Pixel;


public class GreenScreen {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please input the background(include file type): ");
		String img1 = keyboard.nextLine();
		System.out.println("Please input the green screen source picture(include file type): ");
		String img2 = keyboard.nextLine();
		
		Picture pic1 = new Picture(img1);
		Picture pic2 = new Picture(img2);
		
		Picture dest = new Picture(pic2.getWidth(), pic2.getHeight());
		
		for(int row=0; row<pic2.getHeight(); row++){
			for(int col=0; col<pic2.getWidth();col++){
				Pixel p = pic2.getPixel(col, row);
				Pixel back = pic1.getPixel(col, row);
				Pixel d = dest.getPixel(col, row);
					if(p.getGreen()>p.getRed()+p.getBlue()){
						d.setColor(back.getColor());
				}else{
					d.setColor(p.getColor());
				}
			}
		}

		Picture finished = new Picture(3*pic2.getWidth(), pic2.getHeight());
		
		for (int row = 0; row < pic1.getHeight(); row++) {
			for (int col = 0; col < pic1.getWidth(); col++) {
				Pixel source = pic1.getPixel(col, row);
				Pixel d = finished.getPixel(col, row);
				d.setColor(source.getColor());
			}
		}
		
		for (int row = 0; row < pic2.getHeight(); row++) {
			for (int col = 0; col < pic2.getWidth(); col++) {
				Pixel source = pic2.getPixel(col, row);
				Pixel d = finished.getPixel(pic1.getWidth()+col, row);
				d.setColor(source.getColor());
			}
		}
		
		for (int row = 0; row < dest.getHeight(); row++) {
			for (int col = 0; col < dest.getWidth(); col++) {
				Pixel source = dest.getPixel(col, row);
				Pixel d = finished.getPixel(pic1.getWidth()+pic2.getWidth()+col, row);
				d.setColor(source.getColor());
			}
		}
		
		finished.show();
	}

}
