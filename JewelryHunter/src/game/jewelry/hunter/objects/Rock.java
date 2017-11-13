package game.jewelry.hunter.objects;
public class Rock extends GameObject{
	
	private int drability;
	
	public Rock(String name, int x, int y, int durability){
		super(name, x, y);
		this.drability = durability;
	}
	
	public void hit(int power) {
		this.drability -= power;
	}
	public void move(int x, int y){ 
		if((super.x + x) >= 0 && (super.x+x) <= (GameMap.WIDTH-GameObject.WIDTH)) 
			super.x += x; 
		if((super.y + y) >= 0 && (super.y+y) <= (GameMap.HEIGHT-GameObject.HEIGHT)) 
			super.y += y; 
	} 
}
