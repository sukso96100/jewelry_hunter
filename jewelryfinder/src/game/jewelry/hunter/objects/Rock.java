package game.jewelry.hunter.objects;
public class Rock extends GameObject{
	//���� ������ ����
	public static final int RockNum=2;
	public static int x,y;
	//���� �̸��� ����
	public static String RockName[]={"��1","��2"};
	//���� �μ��� ��� ��� �ɷ�ġ���� ����
	public static int RockPower[] = {100,120};
	
	public Rock(){
	}
//	
//	public Rock(String r){
//		super(r);
//	}
	public void move(int x, int y){ 
		if((super.x + x) >= 0 && (super.x+x) <= (GameMap.WIDTH-GameObject.WIDTH)) 
			super.x += x; 
		if((super.y + y) >= 0 && (super.y+y) <= (GameMap.HEIGHT-GameObject.HEIGHT)) 
			super.y += y; 
	} 
}
