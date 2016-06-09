import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Names: Liya Xu, Yangyou Fang
 * Computing IDs: lx2hy, yf2yn
 * Section: 102
 * Date: 04/15/13
 */

/**
 * SimpleCanvas.java
 * 
 * The SimpleCanvas class contains the drawing methods needed to manage the
 * SurvivalField. Many of the methods in this class will not be called by the
 * programmer - instead, they will be automatically called when something
 * occurs. For instance, mouseClicked is called when someone clicks on the
 * SurvivalField.
 * 
 * @author Jason Lawrence and Mark Sherriff
 * 
 */
public class SimpleCanvas extends JPanel implements MouseListener, MouseMotionListener {

	// width and height of the window
	private int width;
	private int height;

	// lastTime that the screen was refreshed
	private long lastTime;

	// a link back to the SurvivalField for updating it
	private SurvivalField simulator;

	// BufferedImages to handle the sprite graphics
	// We've provided a 2D array for zombies and humans in case you want to do
	// animation
	private BufferedImage[][] zombieSprites;
	private BufferedImage[][] humanSprites;
	private BufferedImage boomSprite;

	/**
	 * Constructor for the SimpleCanvas
	 * 
	 * @param width_
	 *            width of the window
	 * @param height_
	 *            height of the window
	 * @param simulator_
	 *            link back to the SurvivalField
	 */
	public SimpleCanvas(int width_, int height_, SurvivalField simulator_) {
		width = width_;
		height = height_;
		simulator = simulator_;
		lastTime = -1L;

		humanSprites = loadHumanSprites("sprite.png");
		zombieSprites = loadZombieSprites("zombie.png");
		try {
			boomSprite = ImageIO.read(new File("boom.png"));
		} catch (Exception e) {
			System.err.println("Cannot load images!");
		}

	}

	/**
	 * Loads the images needed to draw the human character. Add code here to do
	 * animation or to use different sprites.
	 * 
	 * @param filename
	 *            name of file to load
	 * @return 2D array of sprites
	 */
	public BufferedImage[][] loadHumanSprites(String filename) {

		BufferedImage[][] spriteArray = new BufferedImage[4][8];
		BufferedImage spriteSheet = null;

		try {
			spriteSheet = ImageIO.read(new File(filename));
		} catch (Exception e) {
			System.err.println("Cannot load images!");
		}

		// load right facing
		for (int i = 0; i<8; i++){
		spriteArray[0][i] = spriteSheet.getSubimage(48*i, 80, 48, 80);
		}
		
//		spriteArray[0][0] = spriteSheet.getSubimage(100, 80, 50, 80);

		// load left facing
		for (int j = 0; j<8; j++){
		spriteArray[1][j] = getFlippedImage(spriteSheet.getSubimage(48*j, 80, 48, 80));
		}

		// load up facing
		for(int k = 0; k < 8; k++){
			spriteArray[2][k] = spriteSheet.getSubimage(48*k, 235, 48, 80);
		}

		// load down facing
		for(int c = 0; c < 8; c++){
		spriteArray[3][c] = spriteSheet.getSubimage(48*c, 155, 48, 80);
		}

		return spriteArray;
	}

	/**
	 * Loads the images needed to draw the zombie character. Add code here to do
	 * animation or to use different sprites.
	 * 
	 * @param filename
	 *            name of file to load
	 * @return 2D array of sprites
	 */
	public BufferedImage[][] loadZombieSprites(String filename) {

		BufferedImage[][] spriteArray = new BufferedImage[4][8];
		BufferedImage spriteSheet = null;

		try {
			spriteSheet = ImageIO.read(new File(filename));
		} catch (Exception e) {
			System.err.println("Cannot load images!");
		}

		// load right facing
		for (int i = 0; i<3; i++){
		spriteArray[0][i] = spriteSheet.getSubimage(3+75*i, 100, 75, 100);
		}
		// load left facing
		for (int j = 0; j<3; j++){
		spriteArray[1][j] = spriteSheet.getSubimage(3+75*j, 300, 75, 100);
		}
		// load up facing
		for(int k = 0; k < 3; k++){
		spriteArray[2][k] = spriteSheet.getSubimage(3+75*k, 3, 75, 100);
		}
		// load down facing
		for(int c = 0; c < 8; c++){
		spriteArray[3][c] = spriteSheet.getSubimage(3+75*c, 200, 75, 100);
		}
		return spriteArray;
	}

	/**
	 * Called to start the game
	 */
	public void setupAndDisplay() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new JScrollPane(this));
		f.setSize(width, height);
		f.setLocation(100, 100);
		f.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	/**
	 * This method is NEVER called by the programmer. This method is called
	 * whenever the screen refreshes. Consequently, it calls the draw() method
	 * in SurvivalField, telling it to update its components.
	 */
	protected void paintComponent(Graphics g) {
		boolean first = (lastTime == -1L);
		long elapsedTime = System.nanoTime() - lastTime;
		lastTime = System.nanoTime();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		simulator.draw((Graphics2D) g, (first ? 0.0f : (float) elapsedTime / 1e9f));
		repaint();
	}

	/**
	 * drawDot does just what it says - it puts a dot on the screen.
	 * 
	 * @param g
	 *            Graphics engine passed from paintComponent() into
	 *            SurivalField.draw()
	 * @param x
	 *            x coordinate of dot
	 * @param y
	 *            y coordinate of dot
	 * @param color
	 *            Color instance of color of dot
	 */
	public void drawDot(Graphics2D g, double x, double y, Color color) {
		g.setColor(color);
		g.fillOval((int) (x + 0.5f), (int) (y + 0.5f), 8, 8);
	}

	/**
	 * This method loads the proper graphic from the BufferedImage 2D array and
	 * draws it on the screen. Change the code here to make the character point
	 * in the correct direction.
	 * 
	 * @param g
	 *            Graphics engine passed from paintComponent() into
	 *            SurivalField.draw()
	 * @param z
	 *            Zombie to draw
	 */
	public void drawZombie(Graphics2D g, Zombie z) {	
		// Change this! Right now this draws a zombie at 100,100
		g.drawImage(zombieSprites[0][z.getZombieCounter()], (int) z.getX(), (int) z.getY(), null);
	}
	
	public void drawZombieLeft(Graphics2D g, Zombie z) {
		g.drawImage(zombieSprites[1][z.getZombieCounter()], (int) z.getX(), (int) z.getY(), null);
	}
	
	public void drawZombieUp(Graphics2D g, Zombie z) {
		g.drawImage(zombieSprites[2][z.getZombieCounter()], (int) z.getX(), (int) z.getY(), null);
	}
	
	public void drawZombieDown(Graphics2D g, Zombie z) {
		g.drawImage(zombieSprites[3][z.getZombieCounter()], (int) z.getX(), (int) z.getY(), null);
	}

	/**
	 * This method loads the proper graphic from the BufferedImage 2D array and
	 * draws it on the screen. Change the code here to make the character point
	 * in the correct direction.
	 * 
	 * @param g
	 *            Graphics engine passed from paintComponent() into
	 *            SurivalField.draw()
	 * @param h
	 *            Human to draw
	 */
	public void drawHumanRight(Graphics2D g, Human h) {
		// Change this! Right now this draws a human at 300,300
		g.drawImage(humanSprites[0][h.getSpriteCounter()], null, (int)h.getX(), (int)h.getY());

		//g.drawImage(humanSprites[0][6],null,(int)h.getX(),(int)h.getY());
		
	}
	
	public void drawHumanLeft(Graphics2D g, Human h) {
		g.drawImage(humanSprites[1][h.getSpriteCounter()], null, (int)h.getX(), (int)h.getY());
	}
	
	public void drawHumanUp(Graphics2D g, Human h) {
		g.drawImage(humanSprites[2][h.getSpriteCounter()], null, (int)h.getX(), (int)h.getY());
	}
	
	public void drawHumanDown(Graphics2D g, Human h) {
		g.drawImage(humanSprites[3][h.getSpriteCounter()], null, (int)h.getX(), (int)h.getY());
	}

	/**
	 * This method should draw the explosion graphic on the screen on top of the
	 * Human character.
	 * 
	 * @param g
	 *            Graphics engine passed from paintComponent() into
	 *            SurivalField.draw()
	 * @param x
	 *            x coordinate to draw
	 * @param y
	 *            y coordinate to draw
	 */
	public void drawBoom(Graphics2D g, int x, int y) {
		
		g.drawImage(boomSprite, null, x, y);
	}

	/**
	 * Flips a BufferedImage. If you need it.
	 * 
	 * @param bi
	 *            image to flip
	 * @return flipped image
	 */
	public BufferedImage getFlippedImage(BufferedImage bi) {
		BufferedImage flipped = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
		AffineTransform tran = AffineTransform.getTranslateInstance(bi.getWidth(), 0);
		AffineTransform flip = AffineTransform.getScaleInstance(-1d, 1d);
		tran.concatenate(flip);

		Graphics2D g = flipped.createGraphics();
		g.setTransform(tran);
		g.drawImage(bi, 0, 0, null);
		g.dispose();

		return flipped;
	}

	/**
	 * Whenever the mouse is moved on the SurvivalField, this method gets
	 * called.
	 */
	public void mouseMoved(MouseEvent e) {
		simulator.mouseAction((float) e.getX(), (float) e.getY(), -1);
	}

	/**
	 * Whenever the mouse is clicked on the SurvivalField, this method gets
	 * called.
	 */
	public void mouseClicked(MouseEvent e) {
		simulator.mouseAction((float) e.getX(), (float) e.getY(), e.getButton());
	}

	/**
	 * Whenever the mouse enters the SurvivalField, this method gets called.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Whenever the mouse leaves the SurvivalField, this method gets called.
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Whenever the mouse is pressed (yes, it's just barely different than
	 * clicked) on the SurvivalField, this method gets called.
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Whenever the mouse press is released on the SurvivalField, this method
	 * gets called.
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Whenever the mouse clicked and dragged on the SurvivalField, this method
	 * gets called.
	 */
	public void mouseDragged(MouseEvent arg0) {
	}

}
