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
import game.jewelry.hunter.objects.User;
import game.jewelry.hunter.objects.Rock; 
 
 public class GameMain extends JFrame { 
 	public JPanel GameGround; 
 	public JPanel GameMessage; 
 
 	//오브젝트 객체 변수 ->몬스터 추가 예정
 	public User user;
 	public Rock[] rocks; 
 	 
 	//GUI를 위한 JLabel변수 ->Repaint로 대체할 예정
 	public JLabel UserInfo; 
 	public JButton exit; 
 	
 	//Info에 들어 갈 정보들
 	public int time;
 	public int jewelLeft;
 	public int score;
 	//public int detector; 보석 감지 정보 저장 예정
 	
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
 		 
 		GameMessage.setBounds(0,GameMap.HEIGHT,GameMap.MAX_WIDTH,200); 
 		System.out.printf("GameMessage: %d, %d, \n", GameMessage.getWidth(), GameMessage.getHeight()); 
 		 
 		GameGround.setBackground(Color.WHITE); 
 		GameMessage.setBackground(Color.GRAY); 
 		 
 		setSize(GameMap.MAX_WIDTH,GameMap.MAX_HEIGHT); 
 		 
 		//주인공 객체 생성 
 		user= new User("플레이어",2,2); 
 		System.out.printf("%s의 초기 위치는 (%d, %d) 입니다. \n", user.name, user.getX(), user.getY()); 
 		 
		//주인공 JLabel 객체 생성 및 Frame에 Add 
 		GameGround.add(user.getObjectDisplay()); 

 		//유저위치를 TextBox에 출력 
 		UserInfo= new JLabel("남은 시간: " + time/10 + " / 유저 위치: (" + (user.getX()) +", " + (user.getY()) + ")" + " / 점수: " + score + "/ 남은 보석: " + jewelLeft);
 		UserInfo.setLocation(0,10); 
 		UserInfo.setSize(500,20); 
 		GameMessage.add(UserInfo); 

 		//종료버튼
 		exit = new JButton("종료"); 
 		exit.setLocation(400,5); 
 		exit.setSize(80,30);  
 		GameMessage.add(exit);
 		 
 		GameGround.addKeyListener(new GameKeyListener());	 
 		exit.addActionListener(new GameActionListener()); 
 		 
 		add(GameMessage); 
 		add(GameGround); 
 		 
 		//스테이지 오브젝트 생성
 		newStage();
 		
 		setResizable(false); 
 		setVisible(true); 
 		GameGround.requestFocus(); 

 		//백그라운드 쓰레드 실행
 		( new BackGroundThread() ).start();

 	} 

 	public void newStage() {

 		// 보석 무작위 위치에 추가.
 		for(int i=0; i<5; i++) {
 			boolean overLapError = true;
 			while(overLapError){ //overLapError가 생기면 다시
 				int x = (int) (Math.random() * 4);
 				int y = (int) (Math.random() * 4);
 				if( x==2 && y==2 )
 					continue;//플레이어 위치에는 보석을 생성할 수 없다
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
 					int type = (int) (Math.random() * 10);
 						Jewelry jewelry;
 					if( type == 0)
 						jewelry = new Jewelry("플레티넘" ,x, y, 500);
 					else if( type < 4)
 						jewelry = new Jewelry("골드" ,x, y, 200);
 					else if ( type < 7)
 						jewelry = new Jewelry("실버" ,x, y, 100);
 					else
 						jewelry = new Jewelry("브론즈" ,x, y, 10);
 					//^보석 타입 결정
 					objArray.add(jewelry);
 					GameGround.add(jewelry.getObjectDisplay());
 					objectsMap.put(x+","+y, objArray);
 					jewelLeft ++;
 					overLapError = false;
 				}
 			}
 		}

 		for(int x=0; x<5; x++){
 			for(int y=0; y<5; y++){
 				//중심을 제외한 모든 곳을 바위로 채운다.
 				if(x != 2 || y != 2){
 					Rock rock = new Rock("바위",x,y,1);
 					// Get Array of objects of the point
 					ArrayList<GameObject>objArray = objectsMap.get(x+","+y);
 					if(objArray==null) objArray = new ArrayList<GameObject>();
 					objArray.add(rock);
 					GameGround.add(rock.getObjectDisplay());
 					objectsMap.put(x+","+y, objArray);
 				}
 			}
 		}
 		
 	}

 	public void refreshStage() { 
 		//맵을 순회하면서 모든 바위를 삭제함
 		for(int x=0; x<5; x++){
 			for(int y=0; y<5; y++){
 				ArrayList<GameObject>objArray = objectsMap.get(x+","+y);
 				if(objArray!=null)
 					for(GameObject obj : objArray)
 						if(obj instanceof Rock){ 
 							//이 안이 실행되지 않음!
 							//objArray.remove(obj);
 							//GameGround.remove(obj.getObjectDisplay());
 							//objectsMap.put(x+","+y, objArray);
 						}
 			}
 		}

 	}
 	
 	// 키보드 이벤트 처리 
 	class GameKeyListener extends KeyAdapter{ 
 		
 		public void keyPressed(KeyEvent e){ 
 			int keyCode = e.getKeyCode(); 
 			int moveX=0, moveY=0;

 			if(user.canMove){
 				switch(keyCode){ 
 				case KeyEvent.VK_UP: moveY= -1; break; 
 				case KeyEvent.VK_DOWN: moveY= +1; break; 
 				case KeyEvent.VK_LEFT: moveX= -1; break; 
 				case KeyEvent.VK_RIGHT: moveX= +1; break; 
 				default: return;  
 				} 
 				user.move(moveX, moveY);
 				//System.out.printf("%s가 (%d,%d)로 이동했습니다. \n", User.name, (User.getX()), (User.getY())); 
 				UserInfo.setText("남은 시간: " + time/10 + " / 유저 위치: (" + (user.getX()) +", " + (user.getY()) + ")" + " / 점수: " + score + "/ 남은 보석: " + jewelLeft);  

 				ArrayList<GameObject>objArray = objectsMap.get(user.x+","+user.y);

 				// 유저 오브젝트 상호 작용 감지 
 				if(objArray!=null) {
 					for(GameObject obj : objArray) {

 						if(obj instanceof Rock) {
 							((Rock) obj).hit(1);
 							if(((Rock) obj).getDurability() <= 0) {
 								objArray.remove(obj);
 								GameGround.remove(obj.getObjectDisplay());
 								objectsMap.put(user.x+","+user.y, objArray);
 							}
 							user.move(-moveX, -moveY);
 							return;
 						}
 					}
 					for(GameObject obj : objArray) {
 						if(obj instanceof Jewelry) {
 							jewelLeft --;
 							score += ((Jewelry)obj).getScore();
 							objArray.remove(obj);
 							GameGround.remove(obj.getObjectDisplay());
 							objectsMap.put(user.x+","+user.y, objArray);
 							//남은 보석의 갯수가 0일때 뉴 스테이지
 							if(jewelLeft == 0){
 								System.out.println("뉴 스테이지");
 								refreshStage(); 
 								//newStage();
 								//user.x=2; user.y=2;
 								//time=300;//Jframe 형식이라서 생기지 않음 
 							}
 							break;
 						}
 					}
 				}

 				//보석 감지 
 				detect(objArray);
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

 	class BackGroundThread extends Thread{
 		public void run(){
 			for(time = 300; time>=0; time--){
 				try {Thread.sleep(200);}
 				catch (InterruptedException e)
 				{ e.printStackTrace(); }	
 				UserInfo.setText("남은 시간: " + time/10 + " / 유저 위치: (" + (user.getX()) +", " + (user.getY()) + ")" + " / 점수: " + score + "/ 남은 보석: " + jewelLeft);
 				//repaint
 				user.canMove=true;
 				GameGround.requestFocus(); 
 			}
 			System.out.println("시간 초과");
 		}
 	}

 	//보석 감지 메소드
 	public void detect(ArrayList<GameObject>objArray) {
 		boolean detected=false;

 		for(int i = user.x-1; i <= user.x+1; i++)
 			for(int j = user.y-1; j <= user.y+1; j++){
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
 		for(int i = user.x-2; i <= user.x+2; i++)
 			for(int j = user.y-2; j <= user.y+2; j++){
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
 		if(!detected)
 			System.out.println("주위에 보석 없음");
 	}


 	public static void main(String args[]){ 
 		new GameMain(); 
 	} 

 } 

