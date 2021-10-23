/**
 * Fish tank class draws GUI and button to add Fish objects to the window and list.
 * 
 * 
 * @author Ashley Crawford
 */
package fishTank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


import java.awt.geom.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FishTank extends JPanel implements ActionListener {
	private DrawPanel drawPanel;
	private JButton addFish;
	private FishShoal shoal = new FishShoal();
/**
 * constructor for class FishTank
 */
	public FishTank() {
		super(new BorderLayout());

		drawPanel = new DrawPanel();
		add(drawPanel, BorderLayout.CENTER);

		JPanel button = new JPanel();
		addFish = new JButton("Add Fish");
		addFish.addActionListener(this);

		button.add(addFish);
		add(button, BorderLayout.SOUTH);

		Timer time = new Timer(25, this);
		time.start();
	}
/**
 * Actions to be preformed when 'add button' is pressed
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == addFish) {
			System.out.println("Blub blub... fish added");
			Fish fish = new Fish(shoal);

			Thread t = new Thread(fish);
			t.start();
		}
		drawPanel.repaint();
	}
/**
 * class which draws panel
 * @author Ashley Crawford
 *
 */
	private class DrawPanel extends JPanel {
		public DrawPanel() {
			super();
			setPreferredSize(new Dimension(500, 500));
			setBackground(Color.WHITE);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			shoal.drawShoal(g);
		}

	}
/**
 * Program's main 
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Fish Tank");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new FishTank());
		frame.pack();

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenDimension = kit.getScreenSize();
		Dimension frameDimension = frame.getSize();

		frame.setLocation((screenDimension.width - frameDimension.width) / 2,
				(screenDimension.height - frameDimension.height) / 2);
		frame.setVisible(true);

	}

}
