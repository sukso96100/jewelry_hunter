package game.jewelry.hunter.objects;

public class Jewelry extends GameObject {
	private int score;
	private boolean isCollected;
	public Jewelry(String name, int x, int y, int score) {
		super(name, x, y);
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
