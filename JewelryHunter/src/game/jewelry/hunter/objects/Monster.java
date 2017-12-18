package game.jewelry.hunter.objects;

import java.awt.Point;

public class Monster extends GameObject {
	
	
	private int moveCount;
	private boolean movable;

	public Monster(String name, Point location) {
		super(name, location);
		moveCount = 0;
		this.movable = false;
	}
	
	public void move(User user) {
		moveCount = (moveCount+1)%15;
		
		if(moveCount == 14) {
		Point next = this.getLocation();
		
		if(this.getLocation().x > user.getLocation().x) 
			next.x --;
		else if(this.getLocation().x < user.getLocation().x)
			next.x ++;
		
		if(this.getLocation().y > user.getLocation().y) 
			next.y --;
		else if(this.getLocation().y < user.getLocation().y)
			next.y ++;

		this.setLocation(next);
		
		}

	}
	
	public boolean isMovable() {
		return this.movable;
	}
	
	public void makeMovable(boolean movable) {
		this.movable = movable;
	}

}
