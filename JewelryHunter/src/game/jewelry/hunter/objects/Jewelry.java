package game.jewelry.hunter.objects;

import javax.swing.JLabel;

public class Jewelry extends GameObject {
	private int score;
	private boolean isCollected;
	
	public Jewelry(JLabel img, int x, int y, int score) {
		super(img, x, y);
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
	
	
}
