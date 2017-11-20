import java.util.Random;

public  class Rock extends GameObject{
	//돌의 개수를 지정
	public static final int RockNum=2;
	public static int x=200,y=200,power;
	//돌의 이름을 지정
	public static String RockName[]={"돌1","돌2"};
	//돌을 부셨을 경우 얻는 능력치값을 지정
	public static int RockPower =100;
	
	public Rock(){
	}
	public Rock(String name){
		super.name = name;
		
	}
	
	public Rock(String name, int power){
		super.name=name;
		super.power=power;
		Random r = new Random();
//		this.x=200;
//		this.y=200;
//		this.x = (r.nextInt(GameMap.WIDTH-this.WIDTH/10*10));
//		this.y = (r.nextInt(GameMap.WIDTH-this.WIDTH/10*10));
	}
	
}
