package game.jewelry.hunter.objects;

import java.awt.Color;

import javax.swing.JLabel;

public class GameObject {

	public String name;
	public int x, y;
	protected JLabel objectDisplay;
	public static final int WIDTH=90, HEIGHT=90;
	
	public GameObject(String name, int x, int y) {
		this.name=name;
		this.x=x;
		this.y=y;
		
		this.objectDisplay = new JLabel(this.name);
		this.objectDisplay.setLocation(this.x, this.y); 
		this.objectDisplay.setSize(GameObject.WIDTH,GameObject.HEIGHT); 
		this.objectDisplay.setForeground(Color.BLUE); 
	}
	
	public String getName() {
		return name;
	}
	public void setsName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public JLabel getObjectDisplay() {
		return this.objectDisplay;
	}
	@Override
	public String toString() {
		return "GameObject [name=" + name + ", x=" + x + ", y=" + y + "]";
	}
	
	
	
}
