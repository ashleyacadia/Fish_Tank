/**
 * This class creates the blue print for creating a shoal of Fish objects in an
 * array list.
 * 
 * @author Ashley Crawford
 */
package fishTank;

import java.lang.Math;
import java.awt.Graphics;
import java.util.*;

public class FishShoal {
	private ArrayList<Fish> fishList;
/**
 * Constructor for class
 */
	public FishShoal() {
		this.fishList = new ArrayList<Fish>();

	}

	/**
	 * Method which adds a Fish object to the shoal array list.
	 * 
	 * @param fish
	 */
	public void add(Fish fish) {
		fishList.add(fish);

	}

	/**
	 * Method which removes a Fish from the fishList.
	 * 
	 * @param fish
	 */
	public synchronized void remove(Fish fish) {
		fishList.remove(fish);
	}

	/**
	 * Draws the Shoal of Fish by calling individual Fish draw methods.
	 * 
	 * @param g
	 */
	public void drawShoal(Graphics g) {

		for (Fish b : fishList) {

			b.draw(g);

		}
	}

	/**
	 * Checks for a target fish of appropriate size or position for eating.
	 * 
	 * @param fish
	 * @return Fish
	 */
	public synchronized Fish canEat(Fish fish) {
		System.out.println(fishList.size());
		for (Fish i : fishList) {
			if (fish.getSize() >= (i.getSize() * 1.4)) {
				double avgSize = (fish.getSize() + i.getSize()) / 2;
				double xDist = Math.abs(fish.getX() - i.getX());
				double yDist = Math.abs(fish.getY() - i.getY());

				double dist = Math.sqrt(xDist * xDist + yDist * yDist);

				if (dist <= avgSize) {

					System.out.println(" snack found");
					fish.setSize(fish.getSize() + 5);
					return i;
				}

			}
		}

		return null;
	}

	/**
	 * Gets the fish list Array
	 * 
	 * @return ArrayList<Fish>
	 */
	public ArrayList<Fish> getFishList() {
		return fishList;
	}

	/**
	 * Sets the fish list.
	 * 
	 * @param fishList
	 */
	public void setFishList(ArrayList<Fish> fishList) {
		this.fishList = fishList;
	}
}
