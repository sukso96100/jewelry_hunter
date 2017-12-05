package game.jewelry.hunter.objects;
public class User extends GameObject { 
	
	public boolean canMove = true;
	
	public User(String name, int x, int y){
		super(name, x, y);
	}

	public void move(int x, int y){
		if((super.x + x) >= 0 && (super.x+x) <= 4)
			super.x += x;
		if((super.y + y) >= 0 && (super.y+y) <= 4)
			super.y += y;
		this.objectDisplay.setLocation(this.computeX(), this.computeY()); 
		canMove=false;
	}

}
