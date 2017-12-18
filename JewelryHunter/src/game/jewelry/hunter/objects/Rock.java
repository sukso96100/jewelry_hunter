package game.jewelry.hunter.objects;

import java.awt.Point;

public class Rock extends GameObject{
	
	private int durability;
	private int type;
	
	public Rock(String name, Point location){
		super(name, location);
		
		int random = (int) (Math.random()*10);
		if(random<3) {
			this.type=1;
			this.durability = 3;
		}else {
			this.type=0;
			this.durability = 1;
		}
			

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
	
	public int getType() {
		return this.type;
	}
}
