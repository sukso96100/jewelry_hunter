package game.jewelry.hunter.objects;

import java.awt.Point;

public class Rock extends GameObject{
	
	private int durability;
	
	public Rock(String name, Point location, int durability){
		super(name, location);
		this.durability = durability;
	}
	
	public void hit(int power) {
		this.durability -= power;
	}
	
	public int getDurability() {
		if(this.durability < 0) {
			this.durability = 0;
		}
		return this.durability;
	}
}
