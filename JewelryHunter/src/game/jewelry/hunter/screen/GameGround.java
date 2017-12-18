package game.jewelry.hunter.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.jewelry.hunter.objects.GameMap;
import game.jewelry.hunter.objects.GameObject;
import game.jewelry.hunter.objects.Jewelry;
import game.jewelry.hunter.objects.Monster;
import game.jewelry.hunter.objects.Rock;
import game.jewelry.hunter.objects.User;

public class GameGround extends JPanel{
	Map<Point, ArrayList<GameObject>> objMap;
	User user;
	Monster monster;
	
	Image diamonds[];
	Image rocks[];
	Image userImg;
	Image monsterImg;
	Image background;
	
	public GameGround(Map<Point, ArrayList<GameObject>> objMap,
			User user, Monster monster) {
		this.objMap = objMap;
		this.user = user;
		this.monster = monster;
		this.diamonds = new Image[4];
		this.rocks = new Image[2];
		try {
			// diamonds
			this.diamonds[0] = ImageIO.read(new FileInputStream("res/diamondb.png"));
			this.diamonds[1] = ImageIO.read(new FileInputStream("res/diamondg.png"));
			this.diamonds[2] = ImageIO.read(new FileInputStream("res/diamondr.png"));
			this.diamonds[3] = ImageIO.read(new FileInputStream("res/diamondy.png"));
			
			//rocks
			this.rocks[0] = ImageIO.read(new FileInputStream("res/rocka.png"));
			this.rocks[1] = ImageIO.read(new FileInputStream("res/rockb.png"));
			this.userImg = ImageIO.read(new FileInputStream("res/pickax.png"));
			
			this.monsterImg = ImageIO.read(new FileInputStream("res/monster.png"));
			this.background = ImageIO.read(new FileInputStream("res/background.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, GameMap.WIDTH, GameMap.HEIGHT, null);
		for(ArrayList<GameObject> objs : this.objMap.values()) {
			for(GameObject obj : objs) {
				if(obj instanceof Jewelry) {
					g2d.drawImage(diamonds[((Jewelry) obj).getType()], obj.computeX(), obj.computeY(),
							GameObject.WIDTH, GameObject.HEIGHT, null);
				}
			}
			for(GameObject obj : objs) {
				if(obj instanceof Rock) {
					g2d.drawImage(rocks[((Rock) obj).getType()], obj.computeX(), obj.computeY(),
							GameObject.WIDTH, GameObject.HEIGHT, null);
				}
			}
		}
		if(this.monster.isMovable()) {
			g2d.drawImage(monsterImg, this.monster.computeX(), this.monster.computeY(), GameObject.WIDTH, GameObject.HEIGHT, null);

		}
		g2d.drawImage(userImg, this.user.computeX(), this.user.computeY(), GameObject.WIDTH, GameObject.HEIGHT, null);

	}
	

}
