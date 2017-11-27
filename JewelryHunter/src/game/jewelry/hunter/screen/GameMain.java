package game.jewelry.hunter.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
 import java.awt.event.*; 
 import java.awt.*; 
 import javax.swing.*;

import game.jewelry.hunter.objects.GameMap;
import game.jewelry.hunter.objects.GameObject;
import game.jewelry.hunter.objects.Jewelry;
import game.jewelry.hunter.objects.Player;
import game.jewelry.hunter.objects.Rock; 
 
 public class GameMain extends JFrame { 
 	public JPanel GameGround; 
 	public JPanel GameMessage; 
 
 
 	//오브젝트 객체 변수
 	public Player User;
 	public Rock[] Rocks; 
 	 
 	//GUI를 위한 JLabel변수 
 	/*---------------------------------
 	 Issue1) 이 부분을 Label이 아니라 그림으로 처리
 	 ---------------------------------*/
 	public 	JLabel UserLabel; 
 	public JLabel UserInfo; 
 	public JLabel time;
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
 		 
 		GameGround = new JPanel(); 
 		GameMessage = new JPanel(); 
 		GameGround.setLayout(null); 
 		GameMessage.setLayout(null); 
 		
 		
 		 
 		GameGround.setBounds(0,0,GameMap.MAX_WIDTH,GameMap.HEIGHT); 
 		System.out.printf("GameGround: %d, %d, \n", GameGround.getWidth(), GameGround.getHeight()); 
 		 
 		GameMessage.setBounds(0,GameMap.HEIGHT,GameMap.MAX_HEIGHT,80); 
 		System.out.printf("GameMessage: %d, %d, \n", GameMessage.getWidth(), GameMessage.getHeight()); 
 		 
 		GameGround.setBackground(Color.WHITE); 
 		GameMessage.setBackground(Color.GRAY); 
 		 
 		setSize(GameMap.MAX_WIDTH,GameMap.MAX_HEIGHT); 
 		 
 		//주인공 객체 생성 
 		User= new Player("플레이어",0,0); 
 		System.out.printf("%s의 초기 위치는 (%d, %d) 입니다. \n", User.name, User.getX(), User.getY()); 
 		 
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
 			Jewelry jewelry = new Jewelry("보석"+i,x,y,100);
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
 		
 		// 바위 무작위 위치에 추가 
 		for(int i=0; i<5; i++) {
 			int x = (int) (Math.random() * 4);
 			int y = (int) (Math.random() * 4);
 			Rock rock = new Rock("바위"+i,x,y,100);
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
 		time= new JLabel("time");
 		time.setLocation(180,180);
 		time.setSize(200,200);
 		time.setFont(new Font("Serif", Font.BOLD, 200));
 		GameGround.add(time);
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
 			switch(keyCode){ 
 			case KeyEvent.VK_UP: User.move(0, -1); break; 
 			case KeyEvent.VK_DOWN: User.move(0, +1); break; 
 			case KeyEvent.VK_LEFT: User.move(-1, 0); break; 
 			case KeyEvent.VK_RIGHT: User.move(+1, 0); break; 
 			default: return;  
 			} 
			System.out.printf("%s가 (%d,%d)로 이동했습니다. \n", User.name, (User.getX()), (User.getY())); 
			UserInfo.setText("유저 위치: (" + (User.getX()) +", " + (User.getY()) + ")" + " / 점수: " + User.totalScore ); 
			
			ArrayList<GameObject>objArray = objectsMap.get(User.x+","+User.y);
			// 해당 위치 항목 처리 
			if(objArray!=null) {
				for(GameObject obj : objArray) {
	 				if(obj instanceof Rock) {
	 					((Rock) obj).hit(100);
	 					if(((Rock) obj).getDurability() <= 0) {
	 						objArray.remove(obj);
	 					}
	 					User.moveBack();
	 					break;
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
 			for(int i=5; i>=0; i--){
 				try {Thread.sleep(1000);}
 				catch (InterruptedException e)
 				{ e.printStackTrace(); }	
 				time.setText(i + " ");
 			}
 			System.out.println("시간초과");
 		}
 	}
 	
 	public static void main(String args[]){ 
 		new GameMain(); 
 	} 
 } 

