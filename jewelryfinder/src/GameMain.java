import java.util.Scanner;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;


public class GameMain extends JFrame {
	public JPanel GameGround;
	public JPanel GameMessage;

	//오브젝트 객체 변수
	public Player User;
	
	//GUI를 위한 JLabel변수
	public 	JLabel UserLabel;
	public JLabel UserInfo;
	public JTextField UserLocation;
	public JButton exit;

	GameMain(){
		//Frame 생성
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
		User= new Player("플레이어",2,2);
		System.out.printf("%s의 초기 위치는 (%d, %d) 입니다. \n", User.name, User.getX(), User.getY());
		
		//주인공 JLabel 객체 생성 및 Frame에 Add
		UserLabel= new JLabel(User.name);
		UserLabel.setLocation(User.getX(),User.getY());
		UserLabel.setSize(GameObject.WIDTH,GameObject.HEIGHT);
		UserLabel.setForeground(Color.BLUE);
		GameGround.add(UserLabel);
		
		//유저위치를 TextBox에 출력
		UserInfo= new JLabel("유저 위치: (0, 0)");
		UserInfo.setLocation(10,20);
		UserInfo.setSize(150,20);
		GameMessage.add(UserInfo);
		
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
			UserLabel.setLocation(User.getX(),User.getY());
			System.out.printf("%s가 (%d,%d)로 이동했습니다. \n", User.name, (User.getX()/100), (User.getY()/100));
			UserInfo.setText("유저 위치: (" + (User.getX()/100) +", " + (User.getY()/100) + ")");
		}
	}
	
	class GameActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("종료"))
				System.exit(0);
		}
	}

	public static void main(String args[]){
		new GameMain();
	}
}
