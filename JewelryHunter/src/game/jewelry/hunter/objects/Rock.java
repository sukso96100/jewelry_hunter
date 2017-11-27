package game.jewelry.hunter.objects;
public class Rock extends GameObject{
	
	private int durability;
	
	public Rock(String name, int x, int y, int durability){
		super(name, x, y);
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
	public void move(int x, int y){ 
		if((super.x + x) >= 0 && (super.x+x) <= (GameMap.WIDTH-GameObject.WIDTH)) 
			super.x += x; 
		if((super.y + y) >= 0 && (super.y+y) <= (GameMap.HEIGHT-GameObject.HEIGHT)) 
			super.y += y; 
	} 
}
