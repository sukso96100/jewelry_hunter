package jewelryfinder;

public class Rock extends GameObject{
	//돌의 개수를 지정
	public static final int RockNum=2;
	public static int x,y;
	//돌의 이름을 지정
	public static String RockName[]={"돌1","돌2"};
	//돌을 부셨을 경우 얻는 능력치값을 지정
	public static int RockPower[] = {100,120};
	
	public Rock(){
	}
	
	public Rock(String r){
		super(r);
	}
	public void move(int x, int y){ 
		if((super.x + x) >= 0 && (super.x+x) <= (GameMap.WIDTH-GameObject.WIDTH)) 
			super.x += x; 
		if((super.y + y) >= 0 && (super.y+y) <= (GameMap.HEIGHT-GameObject.HEIGHT)) 
			super.y += y; 
	} 
}
