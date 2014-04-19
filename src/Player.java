import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Player extends JComponent {
	public String name;
	public int health;
	public int money;
	final Dimension preferredSize;
	public Component label;
	BufferedImage image;

	public Player(String passedInName, int passedInHealth, int shortSideSize,
			String passedInPath) {
		name = passedInName;
		health = passedInHealth;
		preferredSize = new Dimension(shortSideSize, shortSideSize
				+ shortSideSize / 3);
		// myPicture = ImageIO.read(new File(passedInPath));
		try {
			image = ImageIO.read(new File(passedInPath));
		} catch (IOException ex) {
			// handle exception...
		}

		money = 10;

	}

	public void setImage(String passedInPath) {
		try {
			image = ImageIO.read(new File(passedInPath));
		} catch (IOException ex) {
			// handle exception...
		}
	}

	@Override
	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int passedInMoney) {
		money = passedInMoney;
	}

	@Override
	public void setName(String passedInName) {
		name = passedInName;
	}

	public void setHealth(int passedInHealth) {
		health = passedInHealth;
	}

	@Override
	public void paint(Graphics g) {
		// Local variables used for painting
		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		super.paintComponent(g2);
		g2.drawImage(image, 0, 0, null);
		Font k1 = null;
		try {
			k1 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(
					"fonts/k1.ttf"));
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
		k1 = k1.deriveFont(28.0f);
		g2.setFont(k1);

		g2.drawString("   Name: ", width / 2, height / 4);

		g2.setColor(Color.WHITE);
		g2.drawString("   	   " + name, width / 2, height / 2);

		g2.setColor(getForeground());
		g2.drawString("   Health: ", width / 2, (height / 4) * 3);
		g2.setColor(getForeground());
		g2.setColor(Color.white);
		g2.drawString("   	   " + health, width / 2, height - 5);

	}

}
