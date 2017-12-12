package game.jewelry.hunter.objects;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class Monster extends GameObject {
	
	private Queue<Point> path;
	private int power;
	private int count = 5;

	public Monster(String name, Point location, int power) {
		super(name, location);
		// TODO Auto-generated constructor stub
		this.path = new LinkedList<>();
	}
	
	public void addPath(Point location) {
		this.path.offer(location);
	}
	
	public void move() {
		if(this.count>0) {
			this.count--;
		}else {
			Point next = this.path.poll();
			this.setLocation(next);
			this.objectDisplay.setLocation(this.computeX(), this.computeY()); 
		}
	}
	
	public int getPower() {
		return this.power;
	}

}
