import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	static ArrayList<SetCard> deckList = new ArrayList<SetCard>();

	public static void shuffleDeck() {
		Collections.shuffle(deckList);
	}

	public Deck(int numberOfDecks, int shortSideSize) {
		for (int i = 0; i < numberOfDecks; i++) {
			for (int color = 1; color < 4; color++) {
				for (int shape = 1; shape < 4; shape++) {
					for (int fill = 1; fill < 4; fill++) {
						for (int amount = 1; amount < 4; amount++) {
							deckList.add(new SetCard(color, shape, fill,
									amount, shortSideSize));

						}
					}
				}
			}
		}
	}

	public void createDeck(int numberOfDecks, int shortSideSize) {
		for (int i = 0; i < numberOfDecks; i++) {
			for (int color = 1; color < 4; color++) {
				for (int shape = 1; shape < 4; shape++) {
					for (int fill = 1; fill < 4; fill++) {
						for (int amount = 1; amount < 4; amount++) {
							deckList.add(new SetCard(color, shape, fill,
									amount, shortSideSize));

						}
					}
				}
			}
		}
	}

	public void removeAll() {

		deckList.clear();

	}

	public static Component dealCard(ArrayList onTable) {

		SetCard deal;
		deal = deckList.get(0);
		deckList.remove(0);
		onTable.add(deal);
		return deal;
	}

}
