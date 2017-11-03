
public class GameObject {

	public String name;
	public int x, y;
	public static final int WIDTH=90, HEIGHT=90;
	
	public String getName() {
		return name;
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
	@Override
	public String toString() {
		return "GameObject [name=" + name + ", x=" + x + ", y=" + y + "]";
	}
	
	
	
}
