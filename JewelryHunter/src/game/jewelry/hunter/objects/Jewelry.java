package game.jewelry.hunter.objects;

import java.awt.Point;

public class Jewelry extends GameObject {
	
	private int score;
	private boolean isCollected;
	private int type;

	public Jewelry(String name, Point location, int score, int type) {
		super(name, location);
		// TODO Auto-generated constructor stub
		this.score = score;
		this.isCollected = false;
	}
	
	public int getScore() {
		return score;
	}

	public boolean isCollected() {
		return isCollected;
	}
	
	public void collect() {
		this.isCollected = true;
	}
	
	public int getType() {
		return this.type;
	}
	
}
