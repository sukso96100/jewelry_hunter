package game.jewelry.hunter.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameObject extends JPanel{

//	public String name;
	public JLabel img;
	public int x, y;
	protected JLabel objectDisplay;
	public static final int WIDTH=90, HEIGHT=90;
	public static final int MOVING_UNIT=100;
	
//	BufferedImage img;
//	final String imgPath = "C:\\Users\\JiEun\\Desktop\\red.png";
	
//	public GameObject(){
//		try{
//			img = ImageIO.read(new File(imgPath));
//		}catch(Exception e){
//			System.out.println("Exception in Loading Image: "+ e.toString());
//		}
		
//	}
//	@Override
//	public void paint(Graphics g){
//		super.paint(g);
//		g.drawImage(img, 50, 50, null);
//	}
	public GameObject( JLabel img, int x, int y) {
		this.img=img;
		this.x=x;
		this.y=y;
		
		this.objectDisplay = new JLabel();
		this.objectDisplay.setLocation(this.computeX(), this.computeY()); 
		this.objectDisplay.setSize(GameObject.WIDTH,GameObject.HEIGHT); 
		this.objectDisplay.setForeground(Color.BLUE); 
	}
	
	public JLabel getImage() {
		return img;
	}
	public void setsName(JLabel img) {
		this.img = img;
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
	
	public int computeX() {
		return 2 + this.x * GameObject.MOVING_UNIT;
	}
	
	public int computeY() {
		return 2 + this.y * GameObject.MOVING_UNIT;
	}
	
	public JLabel getObjectDisplay() {
		return this.objectDisplay;
	}
	@Override
	public String toString() {
		return "GameObject [ x=" + x + ", y=" + y + "]";
	}
	
}
