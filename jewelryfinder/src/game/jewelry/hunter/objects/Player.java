package game.jewelry.hunter.objects;
public class Player extends GameObject {
	public static final int MOVING_UNIT=100; 
	
	public Player(){
	}
	
	public Player(String name, int x, int y){
		super.name=name;
		super.x=x;
		super.y=y;
	}

	public void move(int x, int y){
		if((super.x + x) >= 0 && (super.x+x) <= (GameMap.WIDTH-GameObject.WIDTH))
			super.x += x;
		if((super.y + y) >= 0 && (super.y+y) <= (GameMap.HEIGHT-GameObject.HEIGHT))
			super.y += y;
	}
	
	
}
