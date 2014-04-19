import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SetTable extends JPanel implements MouseListener, ActionListener {
	public boolean setOntable = false;
	public int numberOfCardsOnTable = 13;

	// JPanels all in one place
	BufferedImage boardBackground = null;
	BufferedImage bottomBackground = null;
	BufferedImage topBackground = null;

	JPanel tablePanel = new JPanel();
	BackgroundPanel topPanel = new BackgroundPanel(topBackground,
			BackgroundPanel.TILED);

	BackgroundPanel boardPanel = new BackgroundPanel(boardBackground,
			BackgroundPanel.TILED);

	BackgroundPanel bottomPanel = new BackgroundPanel(bottomBackground,
			BackgroundPanel.TILED);
	JPanel buttonPanel = new JPanel();
	JPanel discardPanelCpu = new JPanel();
	JPanel discardPanelPlayer = new JPanel();

	Font robo;
	Clip clickSound;

	// JLabels just for text to display on the panels
	JLabel label = new JLabel("This is the opponentPanel");
	JLabel label1 = new JLabel("This is the playerPanel");
	JLabel wasASet = new JLabel("That was a set! ");
	JLabel wasNotASet = new JLabel("That was not a set! ");
	JLabel cpuTakeOverLabel = new JLabel("CPU taking over ");

	JButton noSetButton = new JButton("No Set!");
	JButton attackButton = new JButton("Attack");

	public SetTable(Container contentPane) throws IOException {
		boardBackground = ImageIO.read(new File("images/diamondblue.png"));
		topBackground = ImageIO.read(new File("images/lines.png"));
		bottomBackground = ImageIO.read(new File("images/lines.png"));
		boardPanel.setImage(boardBackground);
		topPanel.setImage(topBackground);
		bottomPanel.setImage(bottomBackground);
		// Sets the layout for boardPanel
		boardPanel.setLayout(new GridLayout(0, 9));
		topPanel.setLayout(new GridLayout(0, 2));
		bottomPanel.setLayout(new GridLayout(0, 2));
		buttonPanel.setLayout(new GridLayout(0, 1));

		// HARDCODED dimension to set the preferred size for the top and bottom
		// panels
		Dimension d = new Dimension(contentPane.getSize().width, 140);
		topPanel.setPreferredSize(d);
		bottomPanel.setPreferredSize(d);

		// Add the panels to the contentpane (actual game)
		contentPane.add(boardPanel, BorderLayout.CENTER);
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		//
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2,
				true));
		boardPanel.setBackground(Color.DARK_GRAY);
		topPanel.setBackground(Color.black);
		bottomPanel.setBackground(Color.black);
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3,
				true));
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3,
				true));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3, true));
		topPanel.setForeground(Color.green);
		bottomPanel.setForeground(Color.green);
		discardPanelCpu.setBackground(Color.black);
		discardPanelCpu.setForeground(Color.green);
		discardPanelCpu.setBorder(BorderFactory.createLineBorder(Color.green,
				3, true));
	}

	public void validateAllPanels() {
		topPanel.validate();
		boardPanel.validate();
		bottomPanel.validate();
	}

	public void reDrawBoard(ArrayList onTable) {
		boardPanel.removeAll();
		for (int q = 0; q < onTable.size(); q++) {
			boardPanel.add(Box.createHorizontalGlue());
			boardPanel.add((SetCard) onTable.get(q));
		}
		boardPanel.validate();

	}

	public void addPlayerToPanel(Player passedInPlayer, Boolean isCpu) {

	}

	public void addPlayerToPanel(Player passedInPlayer, boolean isCpu) {

		if (isCpu) {
			topPanel.add(discardPanelCpu);
			topPanel.add(passedInPlayer);
			// Border border = new
			// Border(BorderFactory.createLineBorder(Color.black)) ;
			topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			discardPanelCpu.setBorder(BorderFactory
					.createLineBorder(Color.black));
		} else if (isCpu == false) {

			bottomPanel.add(passedInPlayer);
			bottomPanel.add(buttonPanel);
			buttonPanel.add(noSetButton);
			// buttonPanel.add(attackButton);
			noSetButton.addActionListener(this);
			bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			noSetButton.setBackground(Color.black);
			noSetButton.setForeground(Color.green);
			try {
				robo = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(
						"fonts/ROBOM.ttf"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Font robo42 = robo.deriveFont(42.0f);
			noSetButton.setFont(robo42);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
