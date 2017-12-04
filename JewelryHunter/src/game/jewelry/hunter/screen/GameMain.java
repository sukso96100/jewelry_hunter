package game.jewelry.hunter.screen;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.jewelry.hunter.objects.GameMap;
import game.jewelry.hunter.objects.GameObject;
import game.jewelry.hunter.objects.Jewelry;
import game.jewelry.hunter.objects.Player;
import game.jewelry.hunter.objects.Rock; 

public class GameMain extends JFrame { 
	public JLabel GameGround; 
	public JPanel GameMessage; 


	//오브젝트 객체 변수
	public Player User;
	public Rock[] Rocks; 

	//GUI를 위한 JLabel변수 
	/*---------------------------------
 	 Issue1) 이 부분을 Label이 아니라 그림으로 처리
 	 ---------------------------------*/
	public 	JLabel UserLabel ,JewelryR,JewelryGre, JewelryGra,JewelryY, Rockimg; 
	public JLabel UserInfo; 
	public int time;
	public JTextField UserLocation; 
	public JButton exit; 
	ArrayList<GameObject> objectsArray = new ArrayList<>();
	Map<String, ArrayList<GameObject>> objectsMap = new HashMap();


	GameMain(){ 
		//Frame 생성 
		Scanner s = new Scanner(System.in);
		setTitle(GameMap.strGameTitle); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null); 

		GameGround = new JLabel();
		JewelryR = new JLabel();
		JewelryGre= new JLabel(); 
		JewelryGra = new JLabel();
		JewelryY = new JLabel();
		Rockimg = new JLabel();
		GameMessage = new JPanel(); 
		GameGround.setLayout(null); 
		GameMessage.setLayout(null); 

		//보석 이미지
		Image red = new ImageIcon(this.getClass().getResource("/red.png")).getImage();
		JewelryR.setIcon(new ImageIcon(red));
		Image gray = new ImageIcon(this.getClass().getResource("/gray.png")).getImage();
		JewelryGra.setIcon(new ImageIcon(gray));
		Image green = new ImageIcon(this.getClass().getResource("/green.png")).getImage();
		JewelryGre.setIcon(new ImageIcon(green));
		Image yellow = new ImageIcon(this.getClass().getResource("/yellow.png")).getImage();
		JewelryY.setIcon(new ImageIcon(yellow));

		Image img = new ImageIcon(this.getClass().getResource("/wooden_chess-wallpaper.jpg")).getImage();
		GameGround.setIcon(new ImageIcon(img));

		GameGround.setBounds(0,0,GameMap.MAX_WIDTH,GameMap.HEIGHT); 
		System.out.printf("GameGround: %d, %d, \n", GameGround.getWidth(), GameGround.getHeight()); 

		GameMessage.setBounds(0,GameMap.HEIGHT,GameMap.MAX_HEIGHT,80); 
		System.out.printf("GameMessage: %d, %d, \n", GameMessage.getWidth(), GameMessage.getHeight()); 

		GameGround.setBackground(Color.WHITE); 
		GameMessage.setBackground(Color.GRAY); 

		setSize(GameMap.MAX_WIDTH,GameMap.MAX_HEIGHT); 

		//주인공 객체 생성 
		User= new Player(JewelryGre,0,0);
		System.out.printf("플레이어의 초기 위치는 (%d, %d) 입니다. \n", User.getX(), User.getY()); 

		//주인공 JLabel 객체 생성 및 Frame에 Add 
		GameGround.add(User.getObjectDisplay()); 


		//유저위치를 TextBox에 출력 
		UserInfo= new JLabel("유저 위치: (0, 0)"+ " / 점수: " + User.totalScore);  
		UserInfo.setLocation(10,20); 
		UserInfo.setSize(150,20); 
		GameMessage.add(UserInfo); 


		// 보석 무작위 위치에 추가.
		for(int i=0; i<5; i++) {
			int x = (int) (Math.random() * 4);
			int y = (int) (Math.random() * 4);
			Jewelry jewelry = new Jewelry(JewelryY,x,y,100);
			GameGround.add(jewelry.getObjectDisplay());
			// Get Array of objects of the point
			ArrayList<GameObject>objArray = objectsMap.get(x+","+y);
			boolean hasJewelry = false;
			if(objArray==null) objArray = new ArrayList<GameObject>();
			for(GameObject obj : objArray) {
				if(obj instanceof Jewelry) {
					hasJewelry = true;
					break;
				}
			}
			if(!hasJewelry) {
				objArray.add(jewelry);
			}
			objectsMap.put(x+","+y, objArray);
		}

		//바위 이미지 삽입
		Image Rock = new ImageIcon(this.getClass().getResource("/Rock.png")).getImage();
		Rockimg.setIcon(new ImageIcon(Rock));
		// 바위 무작위 위치에 추가 
		for(int i=0; i<5; i++) {
			int x = (int) (Math.random() * 4);
			int y = (int) (Math.random() * 4);
			Rock rock = new Rock(Rockimg,x,y,100);
			GameGround.add(rock.getObjectDisplay());
			// Get Array of objects of the point
			ArrayList<GameObject>objArray = objectsMap.get(x+","+y);
			boolean hasRock = false;
			if(objArray==null) objArray = new ArrayList<GameObject>();
			for(GameObject obj : objArray) {
				if(obj instanceof Rock) {
					hasRock = true;
					break;
				}
			}
			if(!hasRock) {
				objArray.add(rock);
			}
			objectsMap.put(x+","+y, objArray);
		}

		//time test
		(new TimeThread()).start();

		//종료버튼
		exit = new JButton("종료"); 
		exit.setLocation(400,15); 
		exit.setSize(80,30);  
		GameMessage.add(exit); 

		GameGround.addKeyListener(new GameKeyListener());	 
		exit.addActionListener(new GameActionListener()); 

		add(GameMessage); 
		add(GameGround); 

		setResizable(false); 
		setVisible(true); 
		GameGround.requestFocus(); 


	}

	// 키보드 이벤트 처리 
 	class GameKeyListener extends KeyAdapter{ 
 		
 		public void keyPressed(KeyEvent e){ 
 			int keyCode = e.getKeyCode(); 
 			int moveX=0, moveY=0;
 			switch(keyCode){ 
	 			case KeyEvent.VK_UP: moveY= -1; break; 
	 			case KeyEvent.VK_DOWN: moveY= +1; break; 
	 			case KeyEvent.VK_LEFT: moveX= -1; break; 
	 			case KeyEvent.VK_RIGHT: moveX= +1; break; 
	 			default: return;  
 			} 
 			User.move(moveX, moveY);
 			//System.out.printf("%s가 (%d,%d)로 이동했습니다. \n", User.name, (User.getX()), (User.getY())); 
 			UserInfo.setText("남은 시간: " + time + " / 유저 위치: (" + (User.getX()) +", " + (User.getY()) + ")" + " / 점수: " + User.totalScore);  

			ArrayList<GameObject>objArray = objectsMap.get(User.x+","+User.y);
			// 유저 오브젝트 상호 작용 감지 
			if(objArray!=null) {
				for(GameObject obj : objArray) {

	 				if(obj instanceof Rock) {
	 					((Rock) obj).hit(10);
	 					if(((Rock) obj).getDurability() <= 0) {
	 						objArray.remove(obj);
	 						GameGround.remove(obj.getObjectDisplay());
	 					}
	 					User.move(-moveX, -moveY);
	 					return;
	 				}
	 			}
				for(GameObject obj : objArray) {
	 				if(obj instanceof Jewelry) {
	 					User.increaseScore(((Jewelry)obj).getScore());
	 					objArray.remove(obj);
	 					GameGround.remove(obj.getObjectDisplay());
	 					break;
	 				}
	 			}
			}

			//보석 감지 Test
			boolean detected=false;

			for(int i = User.x-1; i <= User.x+1; i++)
				for(int j = User.y-1; j <= User.y+1; j++){
					if(!detected){
						objArray = objectsMap.get(i+","+j);
						if(objArray!=null) {
							for(GameObject obj : objArray)
								if(obj instanceof Jewelry){
									System.out.println("매우 가까움: 초록색");
									detected=true;
								}
						}
					}
				}
			for(int i = User.x-2; i <= User.x+2; i++)
				for(int j = User.y-2; j <= User.y+2; j++){
					if(!detected){
						objArray = objectsMap.get(i+","+j);
						if(objArray!=null) {
							for(GameObject obj : objArray)
								if(obj instanceof Jewelry){
									System.out.println("가까움: 노란색");
									detected = true;
								} 
						}
					}
				}

 		} 
 	}

 	class GameActionListener implements ActionListener{ 
 		public void actionPerformed(ActionEvent e){ 
 			JButton b = (JButton)e.getSource(); 
 			if(b.getText().equals("종료")) 
 				System.exit(0); 
 		} 
 	} 
 
 	class TimeThread extends Thread{
 		public void run(){
 			for(int i=30; i>=0; i--){
 				try {Thread.sleep(1000);}
 				catch (InterruptedException e)
 				{ e.printStackTrace(); }	
 				time=i;
 				UserInfo.setText("남은 시간: " + time + " / 유저 위치: (" + (User.getX()) +", " + (User.getY()) + ")" + " / 점수: " + User.totalScore); 
 			}
 			System.out.println("시간 초과");
 		}
 	}
 	
 	public static void main(String args[]){ 
 		new GameMain(); 
 	} 
 }