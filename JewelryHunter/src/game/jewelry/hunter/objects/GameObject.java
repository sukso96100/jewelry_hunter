package game.jewelry.hunter.objects;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JLabel;

public class GameObject {

	public String name;

	private Point location;

	public static final int WIDTH=90, HEIGHT=90;
	public static final int MOVING_UNIT=100;
	
	public GameObject(String name, Point location) {
		this.name=name;
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	public void setsName(String name) {
		this.name = name;
	}
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public void setLocation(int x, int y) {
		this.location = new Point(x, y);
	}
	
	public int computeX() {
		return 2 + this.location.x * GameObject.MOVING_UNIT;
	}
	
	public int computeY() {
		return 2 + this.location.y * GameObject.MOVING_UNIT;
	}
	
	
	@Override
	public String toString() {
		return "GameObject [name=" + name + ", x=" + this.location.x + ", y=" + this.location.y + "]";
	}
	
}
