package fishTank;

/**
 * This class represents all variables which make up a Fish object. 
 * It draws, moves, checks for other Fish objects to eat and 
 * kills Fish thread and removes if from the shoal.
 * @author Ashley Crawford 
 *
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Fish implements Runnable {

	private int x, dx;
	private int y, dy;
	private double size;
	public static int worldH = 500;
	public static int worldW = 500;
	private Color color;
	private FishShoal shoal;
	public boolean isAlive;

	/**
	 * Class constructor for Fish Sets size, color
	 * 
	 * @param shoal
	 */
	public Fish(FishShoal shoal) {

		Random rand = new Random();
		this.size = rand.nextInt(5) + 10;

		float r = rand.nextFloat();
		float b = rand.nextFloat();
		float g = rand.nextFloat();
		this.color = new Color(r, g, b);

		this.x = this.worldW / 2;
		this.y = this.worldH / 2;

		this.dx = rand.nextInt(50) - 5;
		this.dy = rand.nextInt(50) - 5;

		this.isAlive = true;
		this.shoal = shoal;
		shoal.add(this);
	}

	/**
	 * A method which runs the Fish movement at set time intervals.Also checks
	 * shoal.canEat() method for other Fish objects
	 */
	public void run() {

		while (isAlive) {
			move();
			if (shoal.canEat(this) != null) {
				eat(shoal.canEat(this));
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {

			}
		}
	}

	/**
	 * A method which gets the integer for the x coordinate
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * A method which gets the integer for the y coordinate
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * A method which gets the value for the size of Fish
	 * 
	 * @return double
	 */
	public double getSize() {
		return size;
	}

	public void setSize(double d) {
		this.size = d;
	}

	/**
	 * A method in which the Fish thread is ended and the Fish object is removed
	 * from the shoal
	 */
	public synchronized void kill() {
		System.out.println("In kill");
		this.isAlive = false;
		shoal.remove(this);

	}

	/**
	 * A method in which the Fish moves about the FishTank.
	 */
	private void move() {
		if (x + dx + (size / 2) > worldW || x + dx + (size / 2) < 0) {
			dx = -dx;
		}
		if (y + dy + (size / 2) > worldH || y + dy + (size / 2) < 0) {
			dy = -dy;
		}

		this.x += dx;
		this.y += dy;

	}

	/**
	 * A method which takes in a target fish to remove from Shoal. Adds size to fish
	 * 
	 * @param target
	 */
	public void eat(Fish target) {

		target.kill();

	}

	/**
	 * This method draws Fish
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.drawLine(x, y, x - dx + dy, y - dx - dy);
		g.drawLine(x, y, x - 2 * dx, y - 2 * dy);
		g.drawLine(x, y, x - dx - dy, y + dx - dy);

	}

}
