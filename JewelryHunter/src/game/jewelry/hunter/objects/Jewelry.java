package game.jewelry.hunter.objects;

import java.awt.Point;

public class Jewelry extends GameObject {
	
	private int score;
	private boolean isCollected;
	private int type;

	public Jewelry(Point location) {
		super("", location);
		// TODO Auto-generated constructor stub
		this.isCollected = false;
		this.type = (int) (Math.random() * 3);
		switch(this.type) {
		case 0 : 
			this.score = 10;
			this.name = "Bronze";
			break;
		case 1 :
			this.score = 100;
			this.name = "Silver";
			break;
		case 2 :
			this.score = 200;
			this.name = "Gold";
			break;
		case 3 :
			this.score = 500;
			this.name = "Platinum";
			break;
		}
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
