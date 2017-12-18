package game.jewelry.hunter.screen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ExplainDialog extends JDialog {
	private Image background;
	private JButton okBtn;
	private JLabel explainImg;


	public ExplainDialog (JFrame frame, String title) throws IOException {
		super(frame, title);

		setLayout(null);
		explainImg = new JLabel((new ImageIcon("res/tmp_explain.png")));
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