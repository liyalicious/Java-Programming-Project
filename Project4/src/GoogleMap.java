import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * DO NOT CHANGE ANY CODE IN THIS FILE!!!!!!!!!!!!!
 * 
 * GoogleMap
 * 
 * This class allows the user to generate a Google map based on a URL provided
 * by the user in the CS 1110/1111 HW4 assignment.
 * 
 * You do not need to change any code in this file.
 * 
 * How to build a correct URL -
 * 
 * The URL should be formatted as such:
 * 
 * 1. Start with
 * "http://maps.googleapis.com/maps/api/staticmap?zoom=16&size=1000x1000&maptype=roadmap"
 * 
 * 2. For each coordinate you want to put on the map, concatenate the following
 * at the end of the URL "&markers=color:green|lat,lon" replacing lat and lon
 * with your latitude and longitude (you can also change the color if you want)
 * 
 * 3. When you are done, you must end with "&sensor=false"
 * 
 * 4. An example URL might look like:
 * http://maps.googleapis.com/maps/api/staticmap
 * ?zoom=16&size=1000x1000&maptype=roadmap
 * &markers=38.034,-78.5109&markers=38.032068
 * ,-78.510969&markers=38.032068,-78.510969&sensor=false
 * 
 * @author Mark Sherriff
 * 
 */
public class GoogleMap extends JPanel {

	// If you want to save the image to your project directory
	// with a random name, switch this to true
	private static final boolean SAVETODISK = false;

	// If you want the map image to display to the screen,
	// set this to true
	private static final boolean DISPLAYONSCREEN = true;

	// Position of the image on the screen when it displays
	int XLOC = 100;
	int YLOC = 100;
	int LARGESTHEIGHT = 0;
	private static final long serialVersionUID = 1L;

	BufferedImage image = null;
	Dimension size = new Dimension();

	/**
	 * Pass a URL to an image here (like a Google map URL) to make the image
	 * appear on screen! Technically it works with any image URL on the
	 * Internet.
	 * 
	 * @param url
	 *            web address of the image you want to show
	 * 
	 * @param title
	 *            text that you want to be at the top of the window when image
	 *            is displayed on the screen
	 */
	public void createImage(String url, String title) {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		try {
			image = ImageIO.read(new URL(url));
			size.setSize(image.getWidth(), image.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (DISPLAYONSCREEN) {

			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.add(new JScrollPane(this));
			f.setSize(size.width + 50, size.height + 50);
			f.setLocation(XLOC, YLOC);
			XLOC += image.getWidth() + 50;
			if (image.getHeight() > LARGESTHEIGHT) {
				LARGESTHEIGHT = image.getHeight() + 75;
			}
			if (XLOC > dim.getWidth() - image.getWidth() - 50) {
				YLOC += LARGESTHEIGHT;
				XLOC = 100;
			}
			f.setTitle(title);
			f.setVisible(true);
		}
		if (SAVETODISK) {

			String imageName = title + ".png";

			File outputFile = new File(imageName);
			System.out.println("Saving to disk as: " + outputFile.getAbsolutePath());
			try {
				ImageIO.write(image, "PNG", outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * Called by the graphics library - not called by the user
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		// Center image in this component.
		int x = (getWidth() - size.width) / 2;
		int y = (getHeight() - size.height) / 2;
		g.drawImage(image, x, y, this);
	}

}
