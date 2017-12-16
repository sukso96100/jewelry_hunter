package game.jewelry.hunter.objects;

import java.awt.Point;

public class User extends GameObject { 
	public int totalScore=0;
	public boolean canMove = true;
	public int life;
	public boolean dead;
	
	public User(String name, Point location){
		super(name, location);
		life = 3;
		dead = false;
	}

	public void move(int x, int y){
		Point current = this.getLocation();
		if((current.x + x) >= 0 && (current.x+x) <= GameMap.XSIZE-1)
			current.x += x;
		if((current.y + y) >= 0 && (current.y+y) <= GameMap.YSIZE-1)
			current.y += y;
		this.setLocation(current);
		this.objectDisplay.setLocation(this.computeX(), this.computeY()); 
		canMove=false;
	}

}
