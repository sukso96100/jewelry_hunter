import java.util.Random;
public class GameObject {

	public String name;
	public int x, y, score,point,power;
	public static final int WIDTH=90, HEIGHT=90;

	public GameObject(){
	}
	public GameObject(String name){
		this.name = name;
		Random r = new Random();
		this.x = (r.nextInt(GameMap.WIDTH-this.WIDTH/10*10));
		this.y = (r.nextInt(GameMap.WIDTH-this.WIDTH/10*10));
	}
	
	public String getName() {
		return this.name;
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
	public int Score(){
		return score;
	}
	public int Point(){
		return point;
	}
	public int getPower(){
		return this.power;
	}
	public void setPower(int power){
		this.power =power;
	}

	@Override
	public String toString() {
		return "GameObject [name=" + name + ", x=" + x + ", y=" + y + "]";
	}
}
