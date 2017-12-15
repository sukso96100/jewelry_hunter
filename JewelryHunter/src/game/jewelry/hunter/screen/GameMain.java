package game.jewelry.hunter.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.net.URL;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.jewelry.hunter.objects.GameMap;
import game.jewelry.hunter.objects.GameObject;
import game.jewelry.hunter.objects.Jewelry;
import game.jewelry.hunter.objects.Monster;

import game.jewelry.hunter.objects.User;
import game.jewelry.hunter.objects.Rock; 

public class GameMain extends JFrame { 
	//UI
	private URL btnImgURL = getClass().getResource("img/PixelArt.png");
	private JLayeredPane layeredPane;
	private JLayeredPane gameMain;
	private JButton start;
	private JButton explan;
	private JButton introExit;
	private ExplainDialog explainDialog; 
	private boolean change = false;

	public static JPanel GameGround; 
	public JPanel GameMessage; 

	//오브젝트 객체 변수
	public User user;
	public Rock[] rocks;
	public Monster monster;

	//GUI를 위한 JLabel변수 ->Repaint로 대체할 예정
	public JLabel userInfo; 
	public JButton exit; 

	//Info에 들어 갈 정보들
	public int time;
	public int jewelLeft;
	public int score;
	public static int highScore;
	public static String highScoreName;
	public String detector = "off";

	Map<String, ArrayList<GameObject>> objectsMap = new HashMap();

	GameMain(){ 
		//Frame 생성 
		setTitle(GameMap.strGameTitle); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null); 

		setSize(1000 ,700); 
		setLocationRelativeTo(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1000, 700);
		layeredPane.setLayout(null);

		JPanel intro = null;

		try {
			intro = new IntroPanel("img/lava-anim-dribbble.png");
			explainDialog = new ExplainDialog(this, "Explain");
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.add(intro);
		intro.setBounds(0, 0, 1000, 700);

		start = new JButton(new ImageIcon("img/PixelArt.png"));
		start.setBounds(400, 330, 200, 78);
		layeredPane.add(start);

		explan = new JButton(new ImageIcon("img/PixelArt.png"));
		explan.setBounds(400, 430, 200, 78);
		layeredPane.add(explan);

		explainDialog.setLocationRelativeTo(this);

		introExit = new JButton(new ImageIcon("img/PixelArt.png"));
		introExit.setBounds(400, 530, 200, 78);
		layeredPane.add(introExit);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change = true;
				getContentPane().removeAll();
				getContentPane().add(GameGround);
				getContentPane().add(GameMessage);
				revalidate();
				repaint();
				
				//백그라운드 쓰레드 실행 Start Timer
				( new BackGroundThread() ).start();

			}
		});

		explan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				explainDialog.setVisible(true); //다이얼로그 출력
			}
		});

		introExit.addActionListener(new ExitActionListener());

		layeredPane.add(intro);
		add(layeredPane);
		setVisible(true);

		GameGround = new JPanel(); 
		GameMessage = new JPanel(); 
		GameGround.setLayout(null); 
		GameMessage.setLayout(null); 


		GameGround.setBounds(0,0,GameMap.MAX_WIDTH,GameMap.HEIGHT); 
		System.out.printf("GameGround: %d, %d, \n", GameGround.getWidth(), GameGround.getHeight()); 

		GameMessage.setBounds(GameMap.WIDTH,0,300,GameMap.HEIGHT); 
		System.out.printf("GameMessage: %d, %d, \n", GameMessage.getWidth(), GameMessage.getHeight()); 

		GameGround.setBackground(Color.WHITE); 
		GameMessage.setBackground(Color.GRAY); 


		//주인공 객체 생성 
		user= new User("플레이어", new Point(GameMap.XCENTER,GameMap.YCENTER));
		System.out.printf("%s의 초기 위치는 (%d, %d) 입니다. \n", user.name, user.getLocation().x, user.getLocation().y); 

		//  몬스터 객체 생성
		monster = new Monster("Monster", new Point(GameMap.XCENTER,GameMap.YCENTER), 10);
		GameGround.add(monster.getObjectDisplay());

		//주인공 JLabel 객체 생성 및 Frame에 Add 
		GameGround.add(user.getObjectDisplay()); 

		//유저위치를 TextBox에 출력 
		userInfo= new JLabel(updatedInfo());  
		userInfo.setLocation(10,20); 
		userInfo.setSize(200,200);
		GameMessage.add(userInfo); 

		//종료버튼
		exit = new JButton("종료"); 
		exit.setLocation(200, 600); 
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

		
	} 
	class ExitActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int exit = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "종료창",
					JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Goodbye");
				System.exit(0);
			}
		}
	}


	class IntroPanel extends JPanel {
		private Image background;

		public IntroPanel(String fileName) throws IOException {
			background = ImageIO.read(new File(fileName));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(background, 0, 0, null);

		}
	}

	class ExplainDialog extends JDialog {
		private Image background;
		private JButton okBtn;
		private JLabel explainImg;


		public ExplainDialog (JFrame frame, String title) throws IOException {
			super(frame, title);

			setLayout(null);
			explainImg = new JLabel((new ImageIcon("img/tmp_explain.png")));
			explainImg.setBounds(0, 0, 500, 500);
			add(explainImg);
			okBtn = new JButton("OK");
			okBtn.setBounds(220, 400, 60, 30);
			add(okBtn);
			setSize(500, 500);

			okBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponents(g);

			g.drawImage(background, 0, 0, null);

		}
	}

	public void newStage() {

		// 보석 무작위 위치에 추가.
		for(int i=0; i<5; i++) {
			boolean overLapError = true;
			while(overLapError){ //overLapError가 생기면 다시
				int x = (int) (Math.random() * GameMap.XSIZE-1);
				int y = (int) (Math.random() * GameMap.YSIZE-1);
				if(GameMap.isCenter(x, y))
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
						jewelry = new Jewelry("플레티넘" ,new Point(x, y), 500);
					else if( type < 4)
						jewelry = new Jewelry("골드" ,new Point(x, y), 200);
					else if ( type < 7)
						jewelry = new Jewelry("실버" ,new Point(x, y), 100);
					else
						jewelry = new Jewelry("브론즈" ,new Point(x, y), 10);
					//^보석 타입 결정
					objArray.add(jewelry);
					GameGround.add(jewelry.getObjectDisplay());
					objectsMap.put(x+","+y, objArray);
					jewelLeft ++;
					overLapError = false;

				}
			}
		}

		for(int x=0; x<GameMap.XSIZE; x++){
			for(int y=0; y<GameMap.YSIZE; y++){
				//중심을 제외한 모든 곳을 바위로 채운다.
				if(!GameMap.isCenter(x,y)){
					Rock rock = new Rock("바위",new Point(x,y),1);
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

	public String updatedInfo() { 
		StringBuilder s = new StringBuilder();
		s.append("<html>남은 시간: " + time/10);
		s.append("<br>유저 위치: (" + (user.getLocation().x) +", " + (user.getLocation().y) + ")");
		s.append("<br> 점수: " + score);
		s.append("<br> 남은 보석: " + jewelLeft);
		s.append("<br> 보석감지: " + detector);
		s.append("<br> 최고기록: '" + highScoreName +"'/ " + highScore + "</html>");
		return s.toString();
	}
	
	public void refreshStage() { 

		GameGround.removeAll();
		GameGround.revalidate();
		GameGround.repaint();
		objectsMap.clear();
		user.getLocation().x = GameMap.XCENTER;
		user.getLocation().y = GameMap.YCENTER;
		GameGround.add(user.getObjectDisplay());
		user.getObjectDisplay().setLocation(user.computeX(), user.computeY()); 
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
				userInfo.setText(updatedInfo());

				ArrayList<GameObject>objArray = objectsMap.get(user.getLocation().x+","+user.getLocation().y);
				// 유저 오브젝트 상호 작용 감지 
				if(objArray!=null) {
					for(GameObject obj : objArray) {

						if(obj instanceof Rock) {
							((Rock) obj).hit(1);
							if(((Rock) obj).getDurability() <= 0) {
								objArray.remove(obj);
								GameGround.remove(obj.getObjectDisplay());
								objectsMap.put(user.getLocation().x+","+user.getLocation().y, objArray);
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
							objectsMap.put(user.getLocation().x+","+user.getLocation().y, objArray);
							//남은 보석의 갯수가 0일때 뉴 스테이지
							if(jewelLeft == 0){
								refreshStage(); 
								newStage();
								time += 100; score += 500; //클리어시 시간과 점수 추가
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
			int exit = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "종료창",
					JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Goodbye");
				System.exit(0);
			}
		} 
	} 


	//0.1초마다 한번씩 실행
	class BackGroundThread extends Thread{
		public void run(){
			for(time = 300; time>=0; time--){
				try {Thread.sleep(100);}
				catch (InterruptedException e)
				{ e.printStackTrace(); }	
				userInfo.setText(updatedInfo());
				//repaint
				user.canMove=true;
				GameGround.requestFocus(); 
			}
			saveHighScore();
		}
	}

	//보석 감지 메소드
	public void detect(ArrayList<GameObject>objArray) {
		boolean detected=false;

		for(int i = user.getLocation().x-1; i <= user.getLocation().x+1; i++)
			for(int j = user.getLocation().y-1; j <= user.getLocation().y+1; j++){
				if(!detected){
					objArray = objectsMap.get(i+","+j);
					if(objArray!=null) {
						for(GameObject obj : objArray)
							if(obj instanceof Jewelry){
								detector="GREEN";
								detected=true;
							}
					}
				}
			}
		for(int i = user.getLocation().x-2; i <= user.getLocation().x+2; i++)
			for(int j = user.getLocation().y-2; j <= user.getLocation().y+2; j++){
				if(!detected){
					objArray = objectsMap.get(i+","+j);
					if(objArray!=null) {
						for(GameObject obj : objArray)
							if(obj instanceof Jewelry){
								detector="YELLOW";
								detected = true;
							} 
					}
				}
			}
		if(!detected)
			detector="OFF";
	}

	public static void checkFile(){
		File HighScore = new File("HighScore");
		
		if(!HighScore.exists()){
			System.out.println("최고 점수 파일이 저장되어 있지 않습니다.");
			String filename = "HighScore";
			highScore = 0;
			try {
				FileWriter out = new FileWriter(filename);
				PrintWriter out2 = new PrintWriter(out);
				out2.printf("%s %d","None",0);
				System.out.println("최고점수 파일이 생성 되었습니다!");
				out.close(); out2.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(GameGround,"최고점수 파일 로딩에 실패했습니다.");
			}
		}
		else {
			FileReader in = null;
			BufferedReader in2 = null; 
			
			try{
				in = new FileReader("HighScore");
				in2 = new BufferedReader(in);
				String line = in2.readLine();

				in.close(); in2.close(); 
				String[] value = line.split(" ");

				highScoreName = value[0];
				highScore = Integer.parseInt(value[1]);
			}
			catch (Exception e){
				JOptionPane.showMessageDialog(GameGround, "최고 점수 저장에 실패했습니다.");
			}

		}
	}

	public void saveHighScore(){
		File HighScore = new File("HighScore");
		if(HighScore.exists()){
			if(score>highScore){

				JFrame scoreFrame = new JFrame();
				scoreFrame.setBounds(100, 100, 300, 200);
				scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JPanel scorePanel = new JPanel();
				scoreFrame.getContentPane().add(scorePanel, BorderLayout.SOUTH);

				JTextField name = new JTextField();
				scorePanel.add(name);
				name.setColumns(10);

				JButton btnButton1 = new JButton("저장");
				btnButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							String s = name.getText();
							String[] value = s.split(" ");
							highScoreName = value[0];
							String filename = "HighScore";
							HighScore.delete();
							FileWriter out = new FileWriter(filename);
							PrintWriter out2 = new PrintWriter(out);
							out2.printf("%s %d", highScoreName, score);
							JOptionPane.showMessageDialog(scoreFrame, "최고점수가 저장되었습니다.");
							out.close(); out2.close();
							scoreFrame.setVisible(false);
						} catch (Exception error){
							JOptionPane.showMessageDialog(GameGround, "최고 점수 저장에 실패했습니다.");
						}
					}
				});
				scorePanel.add(btnButton1);
				JLabel scoreLabel = new JLabel("<html>축하드립니다!<br>당신은 최고점수를 달성하셨습니다!<br>당신의 이름을 입력해주세요!!</html>", SwingConstants.CENTER);	
				scoreFrame.add(scoreLabel);

				scoreFrame.setVisible(true);

			}
			else{
				JOptionPane.showMessageDialog(GameGround, "최고 기록 갱신 실패, 다시 도전하세요!");
			} 
		} 
	}

	public static void main(String args[]){ 
		checkFile();
		new GameMain(); 
	} 

} 

