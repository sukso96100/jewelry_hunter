package game.jewelry.hunter.objects;

import java.awt.Point;

public class Player extends GameObject { 
	public int totalScore=0;
	
	public Player(String name, Point location){
		super(name, location);
	}

	public void move(int x, int y){
		Point current = this.getLocation();
		Point next = current;
		if((current.x + x) >= 0 && (current.x+x) <= 4)
			current.x += x;
		if((current.y + y) >= 0 && (current.y+y) <= 4)
			current.y += y;
		this.setLocation(current);
		this.objectDisplay.setLocation(this.computeX(), this.computeY()); 
	}

	
	public void increaseScore(int score) {
		this.totalScore += score;
	}
	
}