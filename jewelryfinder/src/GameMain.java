
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 

public class GameMain extends JFrame { 
	public JPanel GameGround; 
	public JPanel GameMessage; 


	//¿ÀºêÁ§Æ® °´Ã¼ º¯¼ö 
	public Player User;
	public Rock[] Rocks; 

	//GUI¸¦ À§ÇÑ JLabelº¯¼ö 
	public 	JLabel UserLabel, RockLabel[]; 
	public JLabel UserInfo, RockInfo; 
	public JTextField UserLocation; 
	public JButton exit; 


	GameMain(){ 
		//Frame »ý¼º 
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

		//ÁÖÀÎ°ø °´Ã¼ »ý¼º 
		User= new Player("ÇÃ·¹ÀÌ¾î",0,0); 
		System.out.printf("%sÀÇ ÃÊ±â À§Ä¡´Â (%d, %d) ÀÔ´Ï´Ù. \n", User.name, User.getX(), User.getY()); 
		//	System.out.printf("ÇöÀç Á¡¼ö: ", User.Score);

		//ÁÖÀÎ°ø JLabel °´Ã¼ »ý¼º ¹× Frame¿¡ Add 
		UserLabel= new JLabel(User.name); 
		UserLabel.setLocation(User.getX(),User.getY()); 
		UserLabel.setSize(GameObject.WIDTH,GameObject.HEIGHT); 
		UserLabel.setForeground(Color.BLUE); 
		GameGround.add(UserLabel); 

		//À¯ÀúÀ§Ä¡¸¦ TextBox¿¡ Ãâ·Â 
		UserInfo= new JLabel("À¯Àú À§Ä¡: (0, 0)"); 
		UserInfo.setLocation(10,20); 
		UserInfo.setSize(150,20); 
		GameMessage.add(UserInfo); 

		//Rock °´Ã¼ »ý¼º°ú Console¿¡ °á°ú Ãâ·Â
		Rocks = new Rock[Rock.RockNum];
		for(int i=0; i<Rock.RockNum; i++){
			Rocks[i] = new Rock(Rock.RockName[i],Rock.power);
			System.out.printf("%sÀÌ»ý¼ºµÇ¾ú°í, À§Ä¡´Â(%d, %d)ÀÔ´Ï´Ù. \n",Rocks[i].name,
					//À§Ä¡¯H 200À¸·Î ±×³É ÁöÁ¤ÇØÁÜ
					(200+100*i)/100,(200+100*i)/100); 
		}

		//Rock JLable °´Ã¼ »ý¼º ¹× Frame¿¡ Add
		RockLabel = new JLabel[Rock.RockNum];

		for(int i=0; i<Rock.RockNum; i++){
			RockLabel[i] = new JLabel(Rocks[i].name);
			RockLabel[i].setLocation(200+100*i, 200+100*i);
			RockLabel[i].setSize(GameObject.WIDTH,GameObject.HEIGHT);
			RockLabel[i].setForeground(Color.GREEN);
			GameGround.add(RockLabel[i]);
		}


		//Á¾·á¹öÆ° 
		exit = new JButton("Á¾·á"); 
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
			case KeyEvent.VK_TAB: User.power(+50);break;
			default: return;  
			} 
			UserLabel.setLocation(User.getX(),User.getY()); 
			for(int i =0; i<Rock.RockNum; ++i ){
				if((User.getX() == (200+100*i)) &&(User.getY() == (200+100*i))){
					System.out.println("À¯Àú°¡ ¿òÁ÷ÀÏ ¼ö ¾ø½À´Ï´Ù.");
					break;
				}else{
					System.out.printf("%s°¡ (%d,%d)·Î ÀÌµ¿Çß½À´Ï´Ù. \n", User.name, (User.getX()/100), (User.getY()/100)); 
					UserInfo.setText("À¯Àú À§Ä¡: (" + (User.getX()/100) +", " + (User.getY()/100) + ")"); 
				}
			}
			//µ¹ ºÎ¼ö±â
			for(int i=0; i<Rock.RockNum;i++){
				if(Rocks[i]!=null)
					if((User.getX() == (200+100*i))&&(User.getY() == (200+100*i))){
						int power = User.getPower() - Rocks[i].getPower();
						System.out.println(User .getPower());
						User.setPower(power);
						UserInfo.setText("±â»çÆÄ¿ö: "+User.getPower());
						System.out.println("µ¹ÀÌ ºÎ¼­Áø´Ù.");

						Rocks[i]=null;
						GameGround.remove(RockLabel[i]);
					}
			} 
		}	 
	}
	class GameActionListener implements ActionListener{ 
		public void actionPerformed(ActionEvent e){ 
			JButton b = (JButton)e.getSource(); 
			if(b.getText().equals("Á¾·á")) 
				System.exit(0); 
		} 
	} 

	public static void main(String args[]){ 
		new GameMain(); 
	} 
} 


