
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SplashScreen extends JWindow {
	private int duration;

	public SplashScreen(int d) {
		duration = d;
	}

	// A simple little method to show a title screen in the center
	// of the screen for the amount of time given in the constructor
	public void showSplash(ImageIcon image) {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.gray);

		// Set the window's bounds, centering the window
		int width = 875;
		int height = 615;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		// Build the splash screen
		JLabel label = new JLabel(image);
		JLabel copyrt = new JLabel(
				"Copyright 2012, Justin Courville & Matt Heinze",
				SwingConstants.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(label, BorderLayout.CENTER);
		content.add(copyrt, BorderLayout.SOUTH);
		Color oraRed = Color.black;
		content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

		// Display it
		setVisible(true);

		// Wait a little while, maybe while loading resources
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
		}

		setVisible(false);
	}

	public void showSplashAndExit(ImageIcon image) {
		showSplash(image);
		// System.exit(0);
	}
}
/*
 * public static void main(String[] args) { // Throw a nice little title page up
 * on the screen first SplashScreen splash = new SplashScreen(20000); //
 * Normally, we'd call splash.showSplash() and get on with the program. // But,
 * since this is only a test... splash.showSplashAndExit(); } }
 */
