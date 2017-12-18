package game.jewelry.hunter.objects;
public class GameMap {

	
	public static final int WIDTH=700, HEIGHT=700, MAX_WIDTH=WIDTH, MAX_HEIGHT=HEIGHT+80;
	public static final int XSIZE = 7, YSIZE = 7;
	public static final int XCENTER = 3, YCENTER =3;
	public static final String strGameTitle = "<JewelryHunter> ";

	public static boolean isCenter(int x, int y){
		if(x == XCENTER && y == YCENTER)
			return true;
		return false;
	}
}
