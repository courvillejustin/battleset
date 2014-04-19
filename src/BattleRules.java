import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Box;
import javax.swing.JFrame;

public class BattleRules implements ActionListener {
	ArrayList<SetCard> onTable = new ArrayList<SetCard>();
	ArrayList<SetCard> cheatList = new ArrayList<SetCard>();
	ArrayList<SetCard> isSetList = new ArrayList<SetCard>();
	ArrayList<SetCard> cpuIsCardList = new ArrayList<SetCard>();
	static Clip damageSound;
	static Clip laserSound;
	boolean setOntable = false;
	public SetTable table;
	Player me;
	Player cpu;
	Deck setDeck;

	public BattleRules(SetTable passedInTable, Player passedInMe,
			Player passedInCpu, Deck passedInDeck) {
		table = passedInTable;
		me = passedInMe;
		cpu = passedInCpu;
		setDeck = passedInDeck;
		table.noSetButton.addActionListener(this);
	}

	public void clearAll() {
		onTable.clear();
		cheatList.clear();
		isSetList.clear();
		cpuIsCardList.clear();
	}

	// check if this is a set based on three cards being passed in
	public static boolean isThisASet(SetCard passedInCardOne,
			SetCard passedInCardTwo, SetCard passedInCardThree) {
		int allColorValues;
		int allShapeValues;
		int allDenominationValues;
		int allShadeValues;
		Boolean bool = false;
		allColorValues = (passedInCardOne.color + passedInCardTwo.color + passedInCardThree.color) % 3;
		if (allColorValues == 0) {
			allDenominationValues = (passedInCardOne.denomination
					+ passedInCardTwo.denomination + passedInCardThree.denomination) % 3;
			if (allDenominationValues == 0) {
				allShadeValues = (passedInCardOne.fill + passedInCardTwo.fill + passedInCardThree.fill) % 3;
				if (allShadeValues == 0) {
					allShapeValues = (passedInCardOne.shape
							+ passedInCardTwo.shape + passedInCardThree.shape) % 3;
					if (allShapeValues == 0) {
						bool = true;
					}
				}
			}
		}

		else
			bool = false;
		return bool;
	}// end isThisASet

	// Cheat method for the computer to be able to choose a set on the table
	public void cheat() {
		cheatList.clear();
		int cardOne, cardTwo, cardThree;
		SetCard card1 = null, card2 = null, card3 = null;
		for (cardOne = 0; cardOne < onTable.size() - 2; cardOne++) {
			for (cardTwo = cardOne + 1; cardTwo < onTable.size() - 1; cardTwo++) {
				for (cardThree = cardTwo + 1; cardThree < onTable.size(); cardThree++) {
					if (isThisASet(onTable.get(cardOne), onTable.get(cardTwo),
							onTable.get(cardThree))) {
						card1 = onTable.get(cardOne);
						card2 = onTable.get(cardTwo);
						card3 = onTable.get(cardThree);
						cheatList.clear();
						cheatList.add(onTable.get(cardOne));
						cheatList.add(onTable.get(cardTwo));
						cheatList.add(onTable.get(cardThree));
					}// end if
				}// end cardThree
			}// end cardTwo
		}// end cardOne

	}// end cheat

	// check the status of the board
	public void checkStatusOfBoard(JFrame gameFrame, SetTable table, Player me,
			Player cpu) {

		// check if 3 cards are selected and if there are handle them and
		// consider it a set
		threeCardsSelected(table, me, cpu);

		// Clear the arraylist of selected cards yes the order of this is
		// important
		isSetList.clear();
		cpuIsCardList.clear();

		// Repaint everything so we don't have anything hanging around
		gameFrame.getContentPane().repaint();

		// Handle selected and unselected cards for the table
		for (int i = 0; i < onTable.size(); i++) {
			// If a card is selected, show it
			if (onTable.get(i).cardSelected) {
				if (isSetList.contains(onTable.get(i)) == false) {
					isSetList.add(onTable.get(i));
				}

			}// end if
			// If a card is not selected make sure the game and the player knows
			// that
			if (onTable.get(i).cardSelected == false) {
				isSetList.remove(onTable.get(i));
			}

			if (onTable.get(i).cpuCardSelected) {
				if (cpuIsCardList.contains(onTable.get(i)) == false) {
					cpuIsCardList.add(onTable.get(i));
				}

			}// end if
			// If a card is not selected make sure the game and the player knows
			// that
			if (onTable.get(i).cpuCardSelected == false) {
				cpuIsCardList.remove(onTable.get(i));
			}
		}// end for loop
		// Set the boolean for the game if there is a set on the table or not
		validateIfSetIsOnTable();
	}// end checkStatusOfBoard

	public void validateIfSetIsOnTable() {
		if (isSetOnTable()) {
			setOntable = true;
		}
		if (isSetOnTable() == false) {
			setOntable = false;
		}
	}

	public void threeCardsSelected(SetTable table, Player me, Player cpu) {
		if (isSetList.size() == 3) {
			// if the selected three cards are a set handle it
			if (isThisASet(isSetList.get(0), isSetList.get(1), isSetList.get(2))) {
				onTable.remove(isSetList.get(0));
				onTable.remove(isSetList.get(1));
				onTable.remove(isSetList.get(2));

				/*
				 * for (int i = 0; i< 3 ;i++) { dealCard(); }
				 */
				table.boardPanel.removeAll();
				for (int q = 0; q < onTable.size(); q++) {
					table.boardPanel.add(Box.createHorizontalGlue());
					table.boardPanel.add(onTable.get(q));

				}
				try {
					laserSound = AudioSystem.getClip();
					laserSound.open(AudioSystem.getAudioInputStream(new File(
							"music/laserdamage.wav")));
					laserSound.start();
				} catch (Exception exc) {
					exc.printStackTrace(System.out);
				}
				cpu.setHealth(cpu.getHealth() - 1);
			}// end if the selected cards are a set!

			// IF the selected three cards are not a set handle it
			if (isThisASet(isSetList.get(0), isSetList.get(1), isSetList.get(2)) == false) {

				for (int i = 0; i < onTable.size(); i++) {
					onTable.get(i).setCardClicked(2);
					onTable.get(i).setCardSelected(false);
				}
				try {
					damageSound = AudioSystem.getClip();
					damageSound.open(AudioSystem.getAudioInputStream(new File(
							"music/damage.wav")));
					damageSound.start();
				} catch (Exception exc) {
					exc.printStackTrace(System.out);
				}
				me.setHealth(me.getHealth() - 1);

			}// end if

		}// end if 3 cards are selected
		if (cpuIsCardList.size() == 3) {
			if (isThisASet(cpuIsCardList.get(0), cpuIsCardList.get(1),
					cpuIsCardList.get(2))) {

				// try a for loop to put a card in the discard panel and remove
				// it simultaneously
				cheatList.clear();
				table.discardPanelCpu.removeAll();
				cpuIsCardList.get(0).setCpuCardSelected(false);
				cpuIsCardList.get(1).setCpuCardSelected(false);
				cpuIsCardList.get(2).setCpuCardSelected(false);
				table.discardPanelCpu.add(cpuIsCardList.get(0));
				table.discardPanelCpu.add(cpuIsCardList.get(1));
				table.discardPanelCpu.add(cpuIsCardList.get(2));
				onTable.remove(cpuIsCardList.get(0));
				onTable.remove(cpuIsCardList.get(1));
				onTable.remove(cpuIsCardList.get(2));
				/*
				 * for (int i = 0; i< 3 ;i++) { dealCard(); }
				 */
				table.boardPanel.removeAll();
				for (int q = 0; q < onTable.size(); q++) {
					table.boardPanel.add(Box.createHorizontalGlue());
					table.boardPanel.add(onTable.get(q));

				}
				try {
					damageSound = AudioSystem.getClip();
					damageSound.open(AudioSystem.getAudioInputStream(new File(
							"music/damage.wav")));
					damageSound.start();
				} catch (Exception exc) {
					exc.printStackTrace(System.out);
				}
				me.setHealth(me.getHealth() - 1);
			}// end if the selected cards are a set!

		}
	}

	public boolean isSetOnTable() {
		int cardOne, cardTwo, cardThree;
		for (cardOne = 0; cardOne < onTable.size() - 2; cardOne++) {
			for (cardTwo = cardOne + 1; cardTwo < onTable.size() - 1; cardTwo++) {
				for (cardThree = cardTwo + 1; cardThree < onTable.size(); cardThree++) {
					if (isThisASet(onTable.get(cardOne), onTable.get(cardTwo),
							onTable.get(cardThree))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == table.noSetButton) {
			if (setOntable) {
				me.setHealth(me.getHealth() - 1);
				try {
					damageSound = AudioSystem.getClip();
					damageSound.open(AudioSystem.getAudioInputStream(new File(
							"music/damage.wav")));
					damageSound.start();
				} catch (Exception exc) {
					exc.printStackTrace(System.out);
				}
			}
			if (setOntable == false) {
				try {
					laserSound = AudioSystem.getClip();
					laserSound.open(AudioSystem.getAudioInputStream(new File(
							"music/laserdamage.wav")));
					laserSound.start();
				} catch (Exception exc) {
					exc.printStackTrace(System.out);
				}
				cpu.setHealth(cpu.getHealth() - 2);
				dealSetAmount(3);
			}

		}// end if
	}

	public void dealSetAmount(int passedInAmount) {
		// for loop to deal a specified amount of cards
		for (int i = 0; i < passedInAmount; i++) {
			table.boardPanel.add(Box.createHorizontalGlue());
			table.boardPanel.add(Deck.dealCard(onTable));
		}
	}

}
