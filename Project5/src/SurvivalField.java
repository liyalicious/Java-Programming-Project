import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Names: Liya Xu, Yangyou Fang
 * Computing IDs: lx2hy, yf2yn
 * Section: 102
 * Date: 04/15/13
 */

/**
 * SurvivalField.java
 * 
 * The SurvivalField is the field of play for the game. It passes messages
 * between the Human and the Zombies. It also picks up the commands from the
 * mouse and does the appropriate action. This is the class that will have the
 * main method to start the game.
 * 
 * @authors
 * @compid
 * @lab
 */
public class SurvivalField {

	// The SurvivalField needs a canvas to draw on
	private SimpleCanvas canvas;
	// private Timer updater;

	// The InfoFrame that you use for output instead of System.out
	private InfoFrame output;

	// Default board size
	private final int BOARDHEIGHT = 700;
	private final int BOARDWIDTH = 700;

	// -----------------------------------------
	// Fields
	// You should setup fields to keep up with:
	// - a whole bunch of Zombies
	// - a single Human
	// - some way to know if the game is over
	// - a way to keep track of the score
	// - how many zombies you should start with
	// -----------------------------------------

	private ArrayList<Zombie> zombies;

	private Human player;
	private boolean gameOver = false;
	private Random rand = new Random();
	private int score = 0;
	private int zombieNum = 4;
	private int count = 0;
	private boolean dropBomb = false;
	private boolean useTurbo = false;
	private long bombTime;
	private long turboTime;
	private int checkFace = 0;
	private int[] checkZombieFaces;
	private boolean scorePrinted = false;

	private final int BOMB_WIDTH;
	private final int BOMB_HEIGHT;

	// -----------------------------------------
	// Methods

	/**
	 * The Constructor - This method should instantiate a new canvas, create a
	 * new player character, and create the first four zombies in random
	 * locations around the board.
	 */
	public SurvivalField() {
		// Create canvas object with 500x500 spatial dimensions.
		canvas = new SimpleCanvas(BOARDWIDTH, BOARDHEIGHT, this);

		BOMB_WIDTH = 150;
		BOMB_HEIGHT = 156;

		// Initialize your output frame
		output = new InfoFrame(this);
		output.println("OMG Zombies!\nBombs: 3 Turbos: 3\n");

		// Here is where you should create your initial zombies and your Human
		zombies = new ArrayList<Zombie>();
		player = new Human((double) BOARDWIDTH / 2.0,
				((double) BOARDHEIGHT / 2.0), 0, 0, 30, 3, 3);
		
		double randX,randY,disX,disY;
		for (int i = 0; i < zombieNum; i++) {
			do {
				randX = (double) rand.nextInt(BOARDWIDTH);
				randY = (double) rand.nextInt(BOARDHEIGHT);

				disX = randX - player.getCollisionX();
				disY = randY - player.getCollisionY();
			} while (Math.sqrt(disX * disX + disY * disY) < 75);
			zombies.add(new Zombie(rand.nextInt(700), rand.nextInt(700), 20,
					player));
		}
		checkZombieFaces = new int[50000];


		// 20 is a good speed for the human - 10 for the zombie, but experiment!
		// for(int i = 0; i<zombies.size();i++){
		// if (zombies.get(i).hitHuman(player)) {
		// gameOver = true;
		// }
		// }
		// output.println("Game Over!!!");
		// output.println("Final Score: " + score);
	}

	/**
	 * This method should control all of your mouse actions. The mouse activity
	 * is picked up by the SimpleCanvas and then it should call this method,
	 * passing either the button that was pressed or some other flag.
	 */
	public void mouseAction(float x, float y, int button) {
		if (button == -1) {
			// output.println("Mouse: " + x + "," + y);
			player.setMouseLocation(x, y);
			double relativeX = x - player.getCollisionX();
			double relativeY = y - player.getCollisionY();
			if (Math.abs(relativeX) > Math.abs(relativeY) && relativeX > 0) {
				checkFace = 1;
			}
			if (Math.abs(relativeX) > Math.abs(relativeY) && relativeX < 0) {
				checkFace = 2;
			}
			if (Math.abs(relativeX) < Math.abs(relativeY) && relativeY > 0) {
				checkFace = 3;
			}
			if (Math.abs(relativeX) < Math.abs(relativeY) && relativeY < 0) {
				checkFace = 4;
			}

			for (int k = 0; k < zombies.size(); k++) {
				double relativeZombieX = player.getCollisionX()
						- zombies.get(k).getCollisionX();
				double relativeZombieY = player.getCollisionY()
						- zombies.get(k).getCollisionY();

				if (Math.abs(relativeZombieX) > Math.abs(relativeZombieY)
						&& relativeZombieX > 0) {
					checkZombieFaces[k] = 1;
				}

				if (Math.abs(relativeZombieX) > Math.abs(relativeZombieY)
						&& relativeZombieX < 0) {
					checkZombieFaces[k] = 2;
				}

				if (Math.abs(relativeZombieX) < Math.abs(relativeZombieY)
						&& relativeZombieY > 0) {
					checkZombieFaces[k] = 3;
				}

				if (Math.abs(relativeZombieX) < Math.abs(relativeZombieY)
						&& relativeZombieY < 0) {
					checkZombieFaces[k] = 4;
				}

			}
		}

		if (button == 1) {
			// output.println("You clicked the left mouse button!");
			if (player.hasBombs()) {
				detonateBomb(player.getCollisionX(), player.getCollisionY());
				dropBomb = true;
				bombTime = System.currentTimeMillis();
				output.println("BOOM!!YOU DROPPED A BOMB!!\nRemaining bombs: "
						+ player.getBombs());
			} else {
				output.println("OUT OF BOMBS!");
			}
			//			output.println("(" + x + "," + y + ")");
		}

		if (button == 3) {
			//			output.println("You clicked the right mouse button!");
			if(player.hasTurbo()){
				player.setSpeed(200);
				player.setWalkPeriod(0.4);
				useTurbo = true;
				turboTime = System.currentTimeMillis();
				player.setTurbo(player.getTurbo()-1);
				output.println("WWWHHHEEEE TURBO!!\nRemaining turbos: "+player.getTurbo()); 

			}else{
				output.println("OUT OF TURBOS!!");
			}
		}

	}

	/**
	 * This method controls the bomb action. It should ask the player if it has
	 * any bombs. If so, that count should be decremented by one. Then every
	 * zombie within a 50 pixel radius of the player should be eliminated.
	 */
	public void detonateBomb(double xBomb, double yBomb) {

		// ArrayList<Zombie> removedZombies = new ArrayList<Zombie>();

		double dx;
		double dy;

		player.setBombs(player.getBombs() - 1);
		for (int i = 0; i < zombies.size(); i++) {
			dx = xBomb - zombies.get(i).getCollisionX();
			dy = yBomb - zombies.get(i).getCollisionY();

			if (Math.sqrt(dx * dx + dy * dy) <= 80) {
				zombies.remove(i);
			}

		}

	}

	/**
	 * This is the main drawing function that is automatically called whenever
	 * the canvas is ready to be redrawn. The 'elapsedTime' argument is the
	 * time, in seconds, since the last time this function was called.
	 * 
	 * Other things this method should do: - draw the zombies and the human on
	 * the screen - tell the zombies and human to move - check to see if the
	 * game is over after they move - halt the game if the game is over - update
	 * the score for every cycle that the user is alive - add a new zombie every
	 * 5000 cycles - add a new bomb every 50000 cycles
	 * 
	 * 
	 */

	public void draw(Graphics2D g, float elapsedTime) {
		count++;

		if (gameOver) {
			player.setSpeed(0);
			canvas.drawHumanRight(g, player);

			for (int i = 0; i < zombies.size(); i++) {
				zombies.get(i).setSpeed(0);
				canvas.drawZombie(g, zombies.get(i));
			}
			if (!scorePrinted) {
				output.println("\nGame Over!!!");
				output.println("Final Score: " + score);

				scorePrinted = true;
			}
		}

		if (!gameOver) {
			player.updateDirection();
			player.updateAnimation(elapsedTime);
			player.move(elapsedTime);
			// This is how you draw the Human, replacing the null with the human
			// object
			if (checkFace == 1) {
				canvas.drawHumanRight(g, player);
			}
			if (checkFace == 2) {
				canvas.drawHumanLeft(g, player);
			}
			if (checkFace == 4) {
				canvas.drawHumanUp(g, player);
			}
			if (checkFace == 3) {
				canvas.drawHumanDown(g, player);
			}
			// This is how you draw the Zombies, replacing the null with a
			// zombie
			// object

			if (count % 5000 == 0) {
				double randX, randY;
				double disX, disY;

				do {
					randX = (double) rand.nextInt(BOARDWIDTH);
					randY = (double) rand.nextInt(BOARDHEIGHT);

					disX = randX - player.getCollisionX();
					disY = randY - player.getCollisionY();
				} while (Math.sqrt(disX * disX + disY * disY) < 75);

				zombies.add(new Zombie(rand.nextInt(BOARDWIDTH), rand
						.nextInt(BOARDWIDTH), 10, player));
			}
			
			
			for (int k = 0; k < zombies.size(); k++) {
				zombies.get(k).updateZombieAnimation(elapsedTime);
				zombies.get(k).move(elapsedTime);
			}

			
			for (int i = 0; i < zombies.size() - 1; i++) {
				double tempX = zombies.get(i).getX();
				double tempY = zombies.get(i).getY();

				if (zombies.get(i).hitHuman(player)) {
					gameOver = true;
				}
				
				
				for (int j = i + 1; j < zombies.size(); j++) {
					Zombie zombie1 = zombies.get(i);
					Zombie zombie2 = zombies.get(j);

					if (zombie1.collision(zombie2)) {
						double disX1 = zombie1.getCollisionX()
								- player.getCollisionX();
						double disY1 = zombie1.getCollisionY()
								- player.getCollisionY();
						double distance1 = Math.sqrt(disX1 * disX1 + disY1
								* disY1);

						double disX2 = zombie2.getCollisionX()
								- player.getCollisionX();
						double disY2 = zombie2.getCollisionY()
								- player.getCollisionY();
						double distance2 = Math.sqrt(disX2 * disX2 + disY2
								* disY2);

						if (distance1 <= distance2) {
							zombie1.setCollisionFalse();
							zombie2.setCollisionTrue();
						} else {
							zombie2.setCollisionFalse();
							zombie1.setCollisionTrue();
						}

					}
				}

			}

			for (int f = 0; f < zombies.size(); f++) {

				if (checkZombieFaces[f] == 1) {
					canvas.drawZombie(g, zombies.get(f));
				}
				if (checkZombieFaces[f] == 2) {
					canvas.drawZombieLeft(g, zombies.get(f));
				}
				if (checkZombieFaces[f] == 4) {
					canvas.drawZombieUp(g, zombies.get(f));
				}
				if (checkZombieFaces[f] == 3) {
					canvas.drawZombieDown(g, zombies.get(f));
				}

			}

			if (count % 100 == 0 && count >= 100) {
				score += 1;
			}

			if (count % 40000 == 0) {
				player.setBombs(player.getBombs() + 1);
				output.println("BONUS! You get a new bomb!\nRemaining bombs: "
						+ player.getBombs());
			}

			if(count % 60000 == 0){
				player.setTurbo(player.getTurbo() +1);
				output.println("BONUS! You get a new turbo!\nRemaining turbos: " + player.getTurbo());
			}

			if (dropBomb) {
				canvas.drawBoom(g, (int) player.getCollisionX() - BOMB_WIDTH
						/ 2, (int) player.getCollisionY() - BOMB_HEIGHT / 2);

				if ((System.currentTimeMillis() - bombTime) > 200) {
					dropBomb = false;
				}

			}

			if(useTurbo){

				if(System.currentTimeMillis()-turboTime>2500){
					player.setSpeed(40);
					useTurbo = false;
					player.setWalkPeriod(0.8);
				}
			}
		}
	}

	public void displayGameOver() {
		output.println("Game Over!!!");
		output.println("Final Score: " + score);
	}

	/**
	 * Your standard main method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SurvivalField simulator = new SurvivalField();
		simulator.play();
	}

	/**
	 * This method starts the game.
	 */
	public void play() {
		canvas.setupAndDisplay();
	}
}
