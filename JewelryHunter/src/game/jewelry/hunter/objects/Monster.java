package game.jewelry.hunter.objects;

import java.awt.Point;

public class Monster extends GameObject {
	
	
	private int moveCount;

	public Monster(String name, Point location) {
		super(name, location);
		moveCount = 0;
	}
	
	public void move(User user) {
		moveCount = (moveCount+1)%10;
		
		if(moveCount == 9) {
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
		this.objectDisplay.setLocation(this.computeX(), this.computeY()); 
		
		}

	}

}
