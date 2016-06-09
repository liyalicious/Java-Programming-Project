/**
 * Names: Liya Xu, Yangyou Fang
 * Computing IDs: lx2hy, yf2yn
 * Section: 102
 * Date: 04/15/13
 */

/**
 * Zombie.java
 * 
 * The class representing the Zombie object in the game. This class needs to
 * have some representation of current location, a reference to its target (aka
 * the Human) and a speed. You should create methods for checking to see if the
 * Zombie is colliding with another Zombie, checking to see if the Zombie is
 * close enough to eat the Human's brains, and movement.
 * 
 * @authors
 * @compids
 * @lab
 */
public class Zombie {
	private double x;
	private double y;
	private final double WIDTH;
	private final double HEIGHT;
	private double speed;
	private Human humanTarget;
	private int radius;
	private boolean hasCollided;
	private long collisionTime;
	private double animTimer;
	private double walkPeriod;
	private int zombieCounter;

	public Zombie() {
		hasCollided = false;
		x = y = 0;
		speed = 20;
		WIDTH = 75;
		HEIGHT = 100;
		walkPeriod = 0.8;

	}

	public Zombie(double x_, double y_, double speed_, Human h) {
		hasCollided = false;
		WIDTH = 75;
		HEIGHT = 100;
		x = x_;
		y = y_;
		speed = speed_;
		humanTarget = h;
		walkPeriod = 0.8;
	}

	public double getWidth() {
		return WIDTH;
	}

	public double getHeight() {
		return HEIGHT;
	}
	
	public void setCollisionTrue() {
		if (hasCollided)
			return;
		hasCollided = true;
		collisionTime = System.currentTimeMillis();
	}
	
	public void setCollisionFalse() {
		hasCollided = false;
	}

	public void move(double elapsedTime) {
		
		if (hasCollided) {
			if ((System.currentTimeMillis() - collisionTime) > 100) {
				hasCollided = false;
			}
			
			return;
		}

		 double xDir = humanTarget.getX()-this.x;
		 double yDir = humanTarget.getY()-this.y;
		 double mag = Math.sqrt(xDir*xDir+yDir*yDir);
		
		 xDir = xDir/mag;
		 yDir = yDir/mag;
		
		 x = x + xDir * speed * (elapsedTime);
		 y = y + yDir * speed * (elapsedTime);
	}

	public boolean hitHuman(Human humanTarget) {
		
		double targetX = humanTarget.getX() + humanTarget.getWidth()/2.0;
		double targetY = humanTarget.getY() + humanTarget.getHeight()/2.0;

		double distance = Math.sqrt(Math.pow((humanTarget.getCollisionX() - this.getCollisionX()), 2)
				+ (Math.pow((humanTarget.getCollisionY() - this.getCollisionY()), 2)));
		if (distance < humanTarget.getWidth()/2.0) {
			return true;

		}
		return false;
	}

	public boolean collision(Zombie zombie) {
		double distance = Math.sqrt(Math.pow((this.x - zombie.getX()), 2)
				+ (Math.pow((this.y - zombie.getY()), 2)));
		if (distance < 50.0) {
			return true;
			
		}
		return false;

	}
	
	public void updateZombieAnimation(double elapsedTime){
		animTimer += elapsedTime;
		
		 double xDir = humanTarget.getX()-this.x;
		 double yDir = humanTarget.getY()-this.y;
		 double mag = Math.sqrt(xDir*xDir+yDir*yDir);
		
		 xDir = xDir/mag;
		 yDir = yDir/mag;
		
		if (xDir == 0 && yDir == 0) {
			zombieCounter = 0;
			return;
		}
		
		
		for(double i = 0; i < 3; i+=1.0){
			if((animTimer >= i*walkPeriod/3.0) && (animTimer < (i+1.0)*walkPeriod/3.0)){
				zombieCounter = (int)i;
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
		return x + WIDTH/2.0;
	}
	
	public double getCollisionY() {
		return y + HEIGHT/2.0;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Human getHumanTarget() {
		return humanTarget;
	}

	public void setHumanTarget(Human humanTarget) {
		this.humanTarget = humanTarget;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getZombieCounter() {
		return zombieCounter;
	}
	
	public void setWalkPeriod(double walkPeriod_){
		this.walkPeriod = walkPeriod_;
	}

}
