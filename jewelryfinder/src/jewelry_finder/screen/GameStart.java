package jewelry_finder.screen;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStart extends JPanel {
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 720;
	
	private JLabel label;
	
	public GameStart() {
		setBackground(Color.BLACK);
		setLayout(null);
		
		label = new JLabel("gamestart?");
		add(label);
		setVisible(true);
	}
}
