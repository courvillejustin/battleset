import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Controller extends TimerTask implements MouseListener,
		ActionListener {
	// Primitive variables
	int secondCount = 0;
	int shortSideSize = 100;
	private int timerCount = 0;
	final int ONE_SECOND = 1000 / 50;
	int numberOfDecks = 1;
	int numberOfCardsOnTable = 13;
	int playerInitialHealth = 10;
	int cpuInitialHealth = 10;
	int cpuTimeLevel = 5;
	String levelString = "1";
	// Booleans
	boolean setOntable = false;
	private boolean battleIsReady = false;
	boolean mouseActive = true;
	boolean cpuTakeOver = false;
	// Variables for computer making a move
	Random r = new Random();
	int timeToCpuMove = (r.nextInt(cpuTimeLevel) + cpuTimeLevel / 2);
	int timeOfNextMove = secondCount + timeToCpuMove;
	// Deck variable
	Deck setDeck;
	// PLayers
	Player me = new Player("Me", playerInitialHealth, shortSideSize,
			"images/player-health-10.png");
	Player cpu = new Player("cpu", cpuInitialHealth, shortSideSize, "images/"
			+ levelString + "-10.png");
	// JFrames necessary for the game
	static JFrame gameFrame = new JFrame("BattleSet");
	// Panels
	static BackgroundPanel titlePanel = null;
	static JPanel gamePanel = new JPanel();
	static JPanel menuPanel = new JPanel();
	static JPanel playerMenuPanel = new JPanel();
	static JPanel levelPanel = new JPanel();
	static JPanel menuMenuPanel = new JPanel();
	static JPanel levelMenuPanel = new JPanel();
	static BackgroundPanel playerImagePanel = null;
	static BackgroundPanel levelImagePanel = null;
	// Create the table graphics
	public SetTable table;
	// JButtons for the game all in one place
	static JButton menuPlayButton = new JButton("PLAY GAME");
	// The timer for the game e.g gametimer
	private static java.util.Timer gameTimer = new java.util.Timer();
	String menutext;
	// Rules
	BattleRules rules;
	// Menu screen components
	// JLabels
	static JLabel cpuTakeOverLabel = new JLabel("my turn");
	static JLabel playerNameLabel = new JLabel(
			" Enter your name, choose a level, press play ");
	// TextField
	static TextField menuTextField = new TextField(1);
	// JRadio Buttons
	static JRadioButton levelOneButton = new JRadioButton("Level One: ");
	static JRadioButton levelTwoButton = new JRadioButton("Level Two: ");
	static JRadioButton levelThreeButton = new JRadioButton("Level Three: ");
	static JRadioButton levelFourButton = new JRadioButton("Level Four: ");
	static JRadioButton levelFiveButton = new JRadioButton("Level Five: ");
	static JRadioButton levelSixButton = new JRadioButton("Level Six: ");
	static JRadioButton levelSevenButton = new JRadioButton("Level Seven: ");
	static JRadioButton levelEightButton = new JRadioButton("Level Eight: ");
	static JRadioButton levelNineButton = new JRadioButton("Level Nine: ");
	// Helper arrays for the radio buttons
	static Component[] levels = { levelOneButton, levelTwoButton,
			levelThreeButton, levelFourButton, levelFiveButton, levelSixButton,
			levelSevenButton, levelEightButton, levelNineButton };
	static int[] levelTimes = { 100, 90, 80, 60, 50, 40, 25, 10, 5 };
	static ButtonGroup group = new ButtonGroup();
	// Images
	static BufferedImage bgImage = null;
	static BufferedImage cpuBgImage = null;
	static BufferedImage titleBgImage = null;
	// Font
	static Font robo = null;
	// Sounds for ambiance
	static Clip titleSound;
	static Clip clickSound;
	static Clip battleSound;
	static Clip takeoverSound;
	static Clip damageSound;
	static Clip laserSound;
	static Clip menuSound;

	/**
	 * END OF VARIABLE DECLARATIONS
	 * ----------------------------------------------
	 * -----------------------------------
	 **/

	/**
	 * -----MAIN
	 * --------------------------------------------------------------------
	 * 
	 * @throws IOException
	 * @throws FontFormatException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 **/
	public static void main(String[] args) throws FontFormatException,
			IOException, UnsupportedAudioFileException,
			LineUnavailableException {

		// Create and set up the window.
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = gameFrame.getContentPane();
		// Create and set up the content pane.
		Controller setController = new Controller();
		setController.initializePlay(gamePanel);
		// Display the window
		content.add(gamePanel);
		content.add(menuPanel);
		content.add(titlePanel);
		try {
			titleSound = AudioSystem.getClip();
			titleSound.open(AudioSystem.getAudioInputStream(new File(
					"music/title.wav")));
			titleSound.loop(Clip.LOOP_CONTINUOUSLY);
			titleSound.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
		// Title panel stuff
		titlePanel.setLayout(new BorderLayout(0, 1));
		titlePanel.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				showMenuScreen();
				titleSound.stop();
			}
		});
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setSize(800, 700);
	}

	/**
	 * ------------------END MAIN
	 * --------------------------------------------------------------------
	 * 
	 * @throws IOException
	 **/

	public void initializeVariables() throws IOException {
		table = new SetTable(gameFrame.getContentPane());
		rules = new BattleRules(table, me, cpu, setDeck);
		setDeck = new Deck(numberOfDecks, shortSideSize);
		table.noSetButton.setBackground(Color.black);
		table.noSetButton.setForeground(Color.green);
		Font robo42 = robo.deriveFont(42.0f);
		table.noSetButton.setFont(robo42);
		table.noSetButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				table.noSetButton.setForeground(Color.red);
				try {
					clickSound = AudioSystem.getClip();
					clickSound.open(AudioSystem.getAudioInputStream(new File(
							"music/click.wav")));
					clickSound.start();
				} catch (Exception exc) {
					exc.printStackTrace(System.out);
				}
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				table.noSetButton.setForeground(Color.green);
			}
		});

	}

	public void initializePlay(Container gamePanel) {
		// playButton.addActionListener(this);
		menuPlayButton.addActionListener(this);
		menuTextField.addActionListener(this);
		for (int i = 0; i < 9; i++) {
			((AbstractButton) levels[i]).addActionListener(this);
		}
		// Setup panels and images
		try {
			bgImage = ImageIO.read(new File(("images/big-player.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cpuBgImage = ImageIO.read(new File("images/cpu-robot.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			titleBgImage = ImageIO.read(new File("images/TITLE.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerImagePanel = new BackgroundPanel(bgImage, BackgroundPanel.SCALED,
				1.0f, 0.5f);
		levelImagePanel = new BackgroundPanel(cpuBgImage,
				BackgroundPanel.SCALED, 1.0f, 0.5f);
		titlePanel = new BackgroundPanel(titleBgImage, BackgroundPanel.SCALED,
				1.0f, 0.5f);

		gameTimer.schedule(this, 0, 50);
	}

	public void initializeGame(Container contentPane) throws IOException {
		// Initialize the game timer
		battleIsReady = false;
		initializeVariables();
		// shuffle the deck
		Deck.shuffleDeck();
		// deal the initial amount of cards onto the table
		dealSetAmount(numberOfCardsOnTable);
		// Add players to the panels
		table.addPlayerToPanel(cpu, true);
		table.addPlayerToPanel(me, false);
		// Add actionlisteners to the buttons themselves so they are listening
		// and can perform the actions
		table.noSetButton.addActionListener(this);
		table.attackButton.addActionListener(this);

	}

	// required Mouse stuff
	@Override
	public void mousePressed(MouseEvent evt) {
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
	}

	@Override
	public void mouseExited(MouseEvent evt) {
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		if (mouseActive) {
			Object source = evt.getSource();

			if (source == menuTextField) {
				menutext = menuTextField.getText();
				me.setName(menutext);

			}

			for (int i = 0; i < 9; i++) {
				if (source == levels[i]) {
					cpu.setName("CPU" + (i + 1));
					cpuTimeLevel = levelTimes[i];
					try {
						cpuBgImage = ImageIO.read(new File("images/Level-"
								+ (i + 1) + ".png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					levelImagePanel.setImage(cpuBgImage);
					levelImagePanel.validate();
					levelString = Integer.toString((i + 1));
				}
			}

			if (source == menuPlayButton) {

				gameFrame.getContentPane().removeAll();
				gamePanel.removeAll();
				titlePanel.setVisible(false);
				gamePanel.setVisible(true);
				try {
					initializeGame(gamePanel);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				timeToCpuMove = (r.nextInt(cpuTimeLevel) + cpuTimeLevel / 2);
				timeOfNextMove = secondCount + timeToCpuMove;
				gamePanel.add(table.boardPanel);
				table.reDrawBoard(rules.onTable);
				gameFrame.pack();
				gameFrame.setVisible(true);
				gameFrame.setSize(800, 700);
				gameFrame.getContentPane().add(table.boardPanel);
				menutext = menuTextField.getText();
				me.setName(menutext);
				battleIsReady = true;
				cpu.setImage("images/" + levelString + "-" + cpu.getHealth()
						+ ".png");
				me.setImage("images/cpu-robot-health-" + cpu.getHealth()
						+ ".png");
				menuSound.stop();
				try {
					battleSound = AudioSystem.getClip();
					battleSound.open(AudioSystem.getAudioInputStream(new File(
							"music/battle1.wav")));
					battleSound.loop(Clip.LOOP_CONTINUOUSLY);
					battleSound.start();
				} catch (Exception exc) {
					exc.printStackTrace(System.out);
				}

			}

		}// end if mouseactive
		else {
			// do nothing
		}
	}// end actionPerformed

	@Override
	public void run() {
		if (battleIsReady) {
			// Increment the timer count
			timerCount++;
			// check to see if a second has passed based on the timercount
			secondCount();
			// check to see if it's time for the cpu to make a move
			checkNextCpuMove();
			// check the size of the cheatlist so we can highlight and play the
			// game and everything
			checkCheatList();
			// check the status of the cpus health
			checkCpuHealth();
			// check the player's health
			checkPlayerHealth();
			// Check to see if any cards are selected and how to handle all of
			// that etc, check if there is a set on table
			rules.checkStatusOfBoard(gameFrame, table, me, cpu);
			// Update the title bar of the game for debugging output
			gameFrame.setTitle("BattleSet!  " + secondCount);
			// Update the panels
			table.validateAllPanels();
		}// end IF gameIsready

	}// end run

	// check if there is a set on the table and set the global variable for it
	public void secondCount() {
		if (timerCount >= ONE_SECOND) {
			secondCount++;
			timerCount = 0;
			if (rules.cheatList.size() > 0) {
				table.bottomPanel.removeAll();
				table.bottomPanel.add(cpuTakeOverLabel);
				highlightCards();

			}
			if (rules.cheatList.size() == 0) {
				table.bottomPanel.removeAll();
				table.addPlayerToPanel(me, false);
			}
			if (rules.cheatList.size() == 3) {

			}

		} else
			; // do nothing
	}

	// Check to see when the computer is going to make the next move
	public void checkNextCpuMove() {
		if (secondCount == timeOfNextMove) {
			try {
				computerMakesMove();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				takeoverSound = AudioSystem.getClip();
				takeoverSound.open(AudioSystem.getAudioInputStream(new File(
						"music/takeover.wav")));
				takeoverSound.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}

		}
	}

	// Deal a certain amount of cards
	public void dealSetAmount(int passedInAmount) {
		// for loop to deal a specified amount of cards
		for (int i = 0; i < passedInAmount; i++) {
			table.boardPanel.add(Box.createHorizontalGlue());
			table.boardPanel.add(Deck.dealCard(rules.onTable));
		}
	}

	// re draw the board and make sure everything is being displayed properly
	public void reDrawBoard() {
		table.reDrawBoard(rules.onTable);

	}

	// Computer makes move
	public void computerMakesMove() throws InterruptedException {
		timeToCpuMove = (r.nextInt(cpuTimeLevel) + 10);
		timeOfNextMove = secondCount + timeToCpuMove;
		if (rules.setOntable) {
			for (int i = 0; i < rules.onTable.size(); i++) {
				rules.onTable.get(i).setCardClicked(2);
				rules.onTable.get(i).setCardSelected(false);
			}
			rules.cheat();

		}
		if (rules.setOntable == false) {
			me.setHealth(me.getHealth() - 2);
			dealSetAmount(3);
		}
	}

	public void highlightCards() {
		// Highlight first card in arrayList
		rules.onTable.get(rules.onTable.indexOf(rules.cheatList.get(0)))
				.setCpuCardSelected(true);
		// Add that first card to arraylist that is going to be checked by run()
		rules.cpuIsCardList.add(rules.cheatList.get(0));
		// Remove that first element in arrylist, the beauty of this is that the
		// other cards shift to the left and shrink the arraylist
		rules.cheatList.remove(0);

	}

	public void checkCheatList() {
		if (rules.cheatList.size() == 0) {
			mouseActive = true;
			for (int i = 0; i < rules.onTable.size(); i++) {

				rules.onTable.get(i).setMouseActive(true);
			}
		}
		if (rules.cheatList.size() > 0) {
			mouseActive = false;
			for (int i = 0; i < rules.onTable.size(); i++) {
				rules.onTable.get(i).setMouseActive(false);
			}
		}
	}

	public void checkCpuHealth() {
		if (cpu.getHealth() <= 0) {

			// Exit the battle and clean up the variables and settings on your
			// way out :)
			exitBattleGracefully();

			// Show the splash screen for winning
			gameFrame.setVisible(false);
			SplashScreen win = new SplashScreen(8000);
			win.showSplashAndExit(new ImageIcon("images/win.gif"));

			// Build and show the menu screen
			showMenuScreen();

		}
		if (cpu.getHealth() >= 0) {
			cpu.setImage("images/" + levelString + "-" + cpu.getHealth()
					+ ".png");
		}
	}

	public void checkPlayerHealth() {
		if (me.getHealth() <= 0) {

			// Exit the battle and clean up the variables and settings on your
			// way out :)
			exitBattleGracefully();

			// Show the splash screen for winning
			gameFrame.setVisible(false);
			SplashScreen gameover = new SplashScreen(8000);
			gameover.showSplashAndExit(new ImageIcon("images/gameover.gif"));

			// Build and show the menu screen
			showMenuScreen();
		}
		if (me.getHealth() >= 0) {
			me.setImage("images/player-health-" + me.getHealth() + ".png");
		}
	}

	public static void showMenuScreen() {
		// Style the menu panel
		menuPanel.setBackground(Color.black);
		playerMenuPanel.setBackground(Color.black);
		menuMenuPanel.setBackground(Color.black);
		levelPanel.setBackground(Color.black);
		menuMenuPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3,
				true));
		levelPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3,
				true));
		menuPlayButton.setBackground(Color.black);
		menuTextField.setBackground(Color.DARK_GRAY);
		menuPlayButton.setForeground(Color.green);
		menuTextField.setForeground(Color.green);
		playerMenuPanel.setForeground(Color.green);
		playerNameLabel.setForeground(Color.green);
		levelOneButton.setIconTextGap(10);
		// end styling the menu panel
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
		Font bladeline = null;
		try {
			bladeline = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream("fonts/blade.ttf"));
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
		bladeline = bladeline.deriveFont(52.0f);
		Font robo52 = robo.deriveFont(52.0f);
		Font robo12 = robo.deriveFont(12.0f);
		Font robo42 = robo.deriveFont(42.0f);
		menuTextField.setFont(robo52);
		playerNameLabel.setFont(robo12);
		menuPlayButton.setFont(robo42);
		cpuTakeOverLabel.setForeground(Color.red);
		cpuTakeOverLabel.setFont(bladeline);
		// layouts
		menuPanel.setLayout(new GridLayout(0, 2));
		playerMenuPanel.setLayout(new GridLayout(0, 1));
		levelPanel.setLayout(new GridLayout(0, 1));
		menuMenuPanel.setLayout(new GridLayout(0, 1));
		// adding components to menu
		menuPanel.add(playerMenuPanel);
		menuPanel.add(levelPanel);
		menuTextField.setMinimumSize(new Dimension(800, 20));
		menuTextField.setMaximumSize(new Dimension(800, 20));
		menuTextField.setText("Puny Human");
		for (int i = 0; i < 9; i++) {
			levelPanel.add(levels[i]);
			group.add((AbstractButton) levels[i]);
		}

		levelPanel.add(levelImagePanel);

		levelOneButton.doClick();
		levelOneButton.setSelected(true);
		try {
			menuSound = AudioSystem.getClip();
			menuSound.open(AudioSystem.getAudioInputStream(new File(
					"music/game.wav")));
			menuSound.start();
			menuSound.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}

		gameFrame.getContentPane().removeAll();

		playerMenuPanel.removeAll();
		playerMenuPanel.add(playerImagePanel);
		Font bitwise = null;
		try {
			bitwise = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(
					"fonts/bitwise.ttf"));
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
		bitwise = bitwise.deriveFont(55.0f);
		((JComponent) playerMenuPanel.getComponent(0)).setBorder(BorderFactory
				.createLineBorder(Color.black, 3, true));
		for (int i = 0; i < levelPanel.getComponentCount(); i++) {
			levelPanel.getComponent(i).setBackground(Color.black);
			levelPanel.getComponent(i).setForeground(Color.green);
			levelPanel.getComponent(i).setFont(bitwise);
		}
		playerMenuPanel.add(menuMenuPanel);
		menuMenuPanel.add(playerNameLabel);
		menuMenuPanel.add(menuTextField);
		menuMenuPanel.add(menuPlayButton);
		gameFrame.getContentPane().add(menuPanel);
		titlePanel.setVisible(false);
		menuPanel.setVisible(true);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setSize(800, 700);

	}

	public void exitBattleGracefully() {
		battleIsReady = false;
		battleSound.stop();
		battleSound.close();
		cpu.setHealth(cpuInitialHealth);
		me.setHealth(playerInitialHealth);
		timeToCpuMove = (r.nextInt(cpuTimeLevel) + 1);
		timeOfNextMove = secondCount + timeToCpuMove;
		secondCount = 0;
		setDeck.removeAll();
		rules.clearAll();
	}

}// end Controller
