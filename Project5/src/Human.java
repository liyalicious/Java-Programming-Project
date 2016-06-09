/**
 * Names: Liya Xu, Yangyou Fang
 * Computing IDs: lx2hy, yf2yn
 * Section: 102
 * Date: 04/15/13
 */

/**
 * Human.java
 * 
 * The player's character. It should have a location, a location to which it is
 * trying to go, a relative speed, and a number of bombs. You should create
 * appropriate methods for movement, changing the target location,
 * incrementing/decrementing bombs, and all appropriate constructors.
 * 
 * @authors
 * @compids
 * @lab
 * 
 */
public class Human {

	private double x;
	private double y;
	private final double WIDTH;
	private double HEIGHT;
	private double dx;
	private double dy;
	private double speed;
	private double animTimer;
	private double walkPeriod;
	private float mouseX;
	private float mouseY;
	private int spriteCounter;

	private int bombs;
	private int turbo;

	public Human() {
		x = y = 0;
		dx = dy = 0;
		WIDTH = 50;
		HEIGHT = 80;
		bombs = turbo = 3;
		animTimer = 0;
		mouseX = mouseY = 0;
		walkPeriod = 0.8;
	}

	public Human(double x_, double y_, double dx_, double dy_, double speed_,
			int bombs_, int turbo_) {
		WIDTH = 50;
		HEIGHT = 80;
		x = x_;
		y = y_;
		dx = dx_;
		dy = dy_;
		speed = speed_;
		bombs = bombs_;
		turbo = turbo_;
		animTimer = 0;
		mouseX = mouseY = 0;
		walkPeriod = 0.8;
	}

	public void setMouseLocation(float x_,float y_) {
		mouseX = x_;
		mouseY = y_;
	}

	public void updateDirection() {
		double xDir = (double)mouseX - (double)this.getCollisionX();
		double yDir = (double)mouseY - (double)this.getCollisionY();
		double mag = Math.sqrt(xDir * xDir + yDir * yDir);

		if (mag < 1.0) {
			dx = 0;
			dy = 0;
		}
		else {
			dx = xDir / mag;
			dy = yDir / mag;
		}
	}

	public void move(double elapsedTime) {
		double xMove = dx * speed * elapsedTime;
		double yMove = dy * speed * elapsedTime;

		//		if (xMove < 0.1 && yMove < 0.1) {
		//			xMove = 0;
		//			yMove = 0;
		//		}

		x += xMove;
		y += yMove;
	}
	
	public void updateAnimation(double elapsedTime){
		animTimer += elapsedTime;
		
		if (dx == 0 && dy == 0) {
			spriteCounter = 0;
			return;
		}
		
		
		for(double i = 0; i < 8; i+=1.0){
			if((animTimer >= i*walkPeriod/8.0) && (animTimer < (i+1.0)*walkPeriod/8.0)){
				spriteCounter = (int)i;
			}
			
			if(animTimer > walkPeriod){
				animTimer = 0;
			}
		}

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public double getCollisionX() {
		return x + WIDTH / 2.0;
	}

	public double getCollisionY() {
		return y + HEIGHT / 2.0;
	}

	public double getWidth() {
		return WIDTH;
	}

	public double getHeight() {
		return HEIGHT;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean hasBombs() {
		return bombs > 0;
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public int getTurbo() {
		return turbo;
	}

	public void setTurbo(int turbo) {
		this.turbo = turbo;
	}

	public boolean hasTurbo() {
		return turbo > 0;
	}

	public int getSpriteCounter() {
		return spriteCounter;
	}
	
	public void setWalkPeriod(double walkPeriod_){
		this.walkPeriod = walkPeriod_;
	}

}
