package game.jewelry.hunter.screen;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class IntroWindow extends JFrame implements ActionListener {

	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 720;

	private Panel intro, start;
	private JLabel title;
	private CardLayout cards;
	private JButton btnStart = new JButton("����");
	private JButton btnexp = new JButton("���ӹ��");
	private JButton btnExit = new JButton("����");
	private JButton btnBack = new JButton("�ڷΰ���");

	public IntroWindow() {
		setTitle("Jewelry Finder");
		setLocationRelativeTo(null); //����� ����â�� ȭ�� �߾ӿ� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //����â ����� ���α׷��� �Բ� ����(�ݵ�� �ʿ�)
		setVisible(true);

		cards = new CardLayout(0, 0);
		setLayout(cards);

		//intro �г�
		intro = new Panel();
		start = new Panel();
		intro.setBackground(Color.RED);
		start.setBackground(Color.BLUE);
		intro.setLayout(new FlowLayout(FlowLayout.CENTER));
		start.setLayout(new FlowLayout(FlowLayout.CENTER));

		btnStart.addActionListener(this);
		btnExit.addActionListener(this);
		intro.add(btnStart);
		intro.add(btnexp);
		intro.add(btnExit);

		btnBack.addActionListener(this);
		start.add(btnBack);


		add("intro", intro);
		add("start", start);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("����")) {
			intro.setVisible(false);
			start.setVisible(true);
		}
		else if (str.equals("����")) {
			int exit = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?", "����â",
					JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Goodbye");
				System.exit(0);
			}
		}
		else if(str.equals("�ڷΰ���")) {
			start.setVisible(false);
			intro.setVisible(true);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		IntroWindow frame = new IntroWindow();
		frame.setVisible(true);
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}
}
