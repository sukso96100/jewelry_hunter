package game.jewelry.hunter.screen;

import java.util.Arrays;
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
 
 
 	//������Ʈ ��ü ���� 
 	public Player User;
 	public Rock[] Rocks; 
 	 
 	//GUI�� ���� JLabel���� 
 	public 	JLabel UserLabel, RockLabel[]; 
 	public JLabel UserInfo, RockInfo; 
 	public JTextField UserLocation; 
 	public JButton exit; 
 
 
 	GameMain(){ 
 		//Frame ���� 
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
 		 
 		//���ΰ� ��ü ���� 
 		User= new Player("�÷��̾�",2,2); 
 		System.out.printf("%s�� �ʱ� ��ġ�� (%d, %d) �Դϴ�. \n", User.name, User.getX(), User.getY()); 
 		 
 		//���ΰ� JLabel ��ü ���� �� Frame�� Add 
 		GameGround.add(User.getObjectDisplay()); 
 		 
 		//������ġ�� TextBox�� ��� 
 		UserInfo= new JLabel("���� ��ġ: (0, 0)"); 
 		UserInfo.setLocation(10,20); 
 		UserInfo.setSize(150,20); 
 		GameMessage.add(UserInfo); 
 		
 		//Rock ��ü ������ Console�� ��� ���
// 		Rocks = new Rock[Rock.RockNum];
 		
// 		for(int i=0; i<Rock.RockNum;i++){
// 			Rocks[i] = new Rock();
// 			System.out.printf("%s�̻����Ǿ���, ��ġ��(%3d, %3d)�Դϴ�. \n", Rocks[i].RockName[i], Rocks[i].getX(), Rocks[i].getY());
// 		}
 		
 		//Rock JLable ��ü ���� �� Frame�� Add
// 		RockLabel = new JLabel[Rock.RockNum];
// 		for(int i =0; i<Rock.RockNum; i++){
// 			RockLabel[i] = new JLabel(Rocks[i].name);
// 			RockLabel[i].setLocation(Rocks[i].getX(),Rocks[i].getY());
// 			RockLabel[i].setSize(GameObject.WIDTH, GameObject.HEIGHT);
// 			RockLabel[i].setForeground(Color.darkGray);
// 		}
 		//Rock�� TextBox�� ��� 
// 		RockInfo= new JLabel("���� ��ġ: (0, 0)"); 
// 		RockInfo.setLocation(10,20); 
// 		RockInfo.setSize(150,20); 
// 		GameMessage.add(RockInfo); 
 		
 		Jewelry jewelry = new Jewelry("보석1",2,2,100);
 		GameGround.add(jewelry.getObjectDisplay());
 		
 		//�����ư 
 		exit = new JButton("����"); 
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
 
 
 	//KeyEvent 
 	class GameKeyListener extends KeyAdapter{ 
 		public void keyPressed(KeyEvent e){ 
 			int keyCode = e.getKeyCode(); 
 			switch(keyCode){ 
 			case KeyEvent.VK_UP: User.move(0, -User.MOVING_UNIT); break; 
 			case KeyEvent.VK_DOWN: User.move(0, +User.MOVING_UNIT); break; 
 			case KeyEvent.VK_LEFT: User.move(-User.MOVING_UNIT, 0); break; 
 			case KeyEvent.VK_RIGHT: User.move(+User.MOVING_UNIT, 0); break; 
 			default: return;  
 			} 
 			System.out.printf("%s�� (%d,%d)�� �̵��߽��ϴ�. \n", User.name, (User.getX()/100), (User.getY()/100)); 
 			UserInfo.setText("���� ��ġ: (" + (User.getX()/100) +", " + (User.getY()/100) + ")"); 
 		} 
 	} 
 	 
 	class GameActionListener implements ActionListener{ 
 		public void actionPerformed(ActionEvent e){ 
 			JButton b = (JButton)e.getSource(); 
 			if(b.getText().equals("����")) 
 				System.exit(0); 
 		} 
 	} 
 
 	public static void main(String args[]){ 
 		new GameMain(); 
 	} 
 } 

