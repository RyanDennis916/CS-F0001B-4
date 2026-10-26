
// Imports for the Foothill class
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// Imports for the CardTable class
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
// Imports for the GUICard class
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

/**
 * @author Micheal Loceff
 * @author Baba Kofi Weusijana
 */
public class Foothill extends Application {
	private static final boolean DEBUG = false;
	static final double APP_WIDTH = 1024;
	static final double APP_HEIGHT = 768;
	static final int NUM_CARDS_PER_HAND = 7;
	static final int NUM_PLAYERS = 2;
	GUICard[] computerGUICards = new GUICard[NUM_CARDS_PER_HAND];
	GUICard[] humanGUICards = new GUICard[NUM_CARDS_PER_HAND];
	GUICard[] playedCards = new GUICard[NUM_PLAYERS];

	/**
	 * Method for starting this application.
	 * 
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	/**
	 * Starting point for the launched JavaFX Application object.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		int k;

		// Establish the root pane for the application
		CardTable myCardTable = new CardTable();

		// EventHandler inner class for human GUICard buttons that react to ActionEvent
		// events
		EventHandler<ActionEvent> humanGUICardEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (DEBUG) {
					System.out.println("humanGUICardEventHandler.handle called with ActionEvent:" + actionEvent);
				}
				GUICard button = (GUICard) actionEvent.getSource();
				Parent parent = button.getParent();
				if (DEBUG) {
					System.out.println(button + " is inside " + parent);
				}
				if (parent == myCardTable.getHumanHand()) {
					myCardTable.getHumanHand().getChildren().remove(button);
					myCardTable.getHumanArea().getChildren().add(button);
				}
			}
		};
		/*
		 * EventHandler<ActionEvent> humanGUICardEventHandler = (actionEvent) -> { if
		 * (DEBUG) {
		 * System.out.println("humanGUICardEventHandler.handle called with ActionEvent:"
		 * + actionEvent); } GUICard button = (GUICard) actionEvent.getSource(); Parent
		 * parent = button.getParent(); if (DEBUG) { System.out.println(button +
		 * " is inside " + parent); } if (parent == myCardTable.getHumanHand()) {
		 * myCardTable.getHumanHand().getChildren().remove(button);
		 * myCardTable.getHumanArea().getChildren().add(button); } };
		 */
		// Prepare the deck of 1 pack of cards and shuffle it
		Deck deck = new Deck(1);
		deck.shuffle();

		// Prepare the human player's array of GUICards (bottom region)
		for (k = 0; k < NUM_CARDS_PER_HAND; ++k) {
			humanGUICards[k] = new GUICard(deck.dealCard());
			humanGUICards[k].setOnAction(humanGUICardEventHandler);
		}

		// Prepare the computer player's array of GUICards (all card backs, top region)
		for (k = 0; k < NUM_CARDS_PER_HAND; ++k) {
			computerGUICards[k] = new GUICard(deck.dealCard());
			computerGUICards[k].setFaceup(false);
		}

		// Prepare the playedCards GUICard array
		for (k = 0; k < NUM_PLAYERS; ++k) {
			playedCards[k] = new GUICard(deck.dealCard());
		}

		// Add GUICards to Computer and Human hand areas
		myCardTable.getComputerHand().getChildren().addAll(computerGUICards);
		myCardTable.getHumanHand().getChildren().addAll(humanGUICards);

		// Add playedCards to the play region for the computer and human players
		for (k = 0; k < NUM_PLAYERS; ++k) {
			myCardTable.getComputerArea().getChildren().add(playedCards[k]);
			++k;
			myCardTable.getHumanArea().getChildren().add(playedCards[k]);
		}

		Scene scene = new Scene(myCardTable, APP_WIDTH, APP_HEIGHT);
		stage.setTitle("CS 1B CardTable");
		stage.setWidth(APP_WIDTH);
		stage.setHeight(APP_HEIGHT);
		stage.setScene(scene);
		stage.sizeToScene();
		// Show everything to the user
		stage.show();
	}
}

/**
 * One object of this class represents the table that the card game is played on
 *  table is a vertical stack of a collapsible computer hand on top, a non-collapsible playing area in the middle where each player's played card is shown, and the human hand on the bottom, with each hand inside a scroll pane so any number of cards can be viewed
 *
 * @author Ryan Dennis
 * @author Atharv Dua
 */
class CardTable extends VBox {
	private static final int SCROLLPANES_HEIGHT = 280;
	private static final int SCROLLPANES_WIDTH = 768;
	static final int MAX_CARDS_PER_HAND = 57;
	static final int MAX_PLAYERS = 2; // for now, we only allow 2 person games

	private StackPane computerArea;
	private HBox computerHand;
	private StackPane humanArea;
	private HBox humanHand;
	private HBox playArea;

	/**
	 * constructs a table with an empty computer hand, playing area, and human hand
	 */
	public CardTable() {
		computerHand = new HBox();
		TitledPane computerHandPane = new TitledPane("Computer's Hand", buildScrollPane(computerHand));
		computerHandPane.setExpanded(false);

		computerArea = new StackPane();
		humanArea = new StackPane();
		playArea = new HBox(buildFixedTitledPane("Computer", computerArea), buildFixedTitledPane("You", humanArea));
		playArea.setAlignment(Pos.CENTER);
		TitledPane playAreaPane = buildFixedTitledPane("Playing Area", playArea);

		humanHand = new HBox();
		TitledPane humanHandPane = new TitledPane("Your Hand", buildScrollPane(humanHand));

		getChildren().addAll(computerHandPane, playAreaPane, humanHandPane);
	}

	private static ScrollPane buildScrollPane(HBox hand) {
		hand.setAlignment(Pos.CENTER);
		ScrollPane scrollPane = new ScrollPane(hand);
		scrollPane.setPrefSize(SCROLLPANES_WIDTH, SCROLLPANES_HEIGHT);
		return scrollPane;
	}

	private static TitledPane buildFixedTitledPane(String title, Node content) {
		TitledPane titledPane = new TitledPane(title, content);
		titledPane.setCollapsible(false);
		return titledPane;
	}

	/**
	 * @return the pane holding the card played by the computer
	 */
	public StackPane getComputerArea() {
		return computerArea;
	}

	/**
	 * @param computerArea the pane to hold the card played by the computer
	 */
	public void setComputerArea(StackPane computerArea) {
		this.computerArea = computerArea;
	}

	/**
	 * @return the box holding the computer's face-down cards
	 */
	public HBox getComputerHand() {
		return computerHand;
	}

	/**
	 * @param computerHand the box to hold the computer's face-down cards
	 */
	public void setComputerHand(HBox computerHand) {
		this.computerHand = computerHand;
	}

	/**
	 * @return the pane holding the card played by the human
	 */
	public StackPane getHumanArea() {
		return humanArea;
	}

	/**
	 * @param humanArea the pane to hold the card played by the human
	 */
	public void setHumanArea(StackPane humanArea) {
		this.humanArea = humanArea;
	}

	/**
	 * @return the box holding the human's face-up cards
	 */
	public HBox getHumanHand() {
		return humanHand;
	}

	/**
	 * @param humanHand the box to hold the human's face-up cards
	 */
	public void setHumanHand(HBox humanHand) {
		this.humanHand = humanHand;
	}

	/**
	 * @return the centered box holding the computer and human play areas
	 */
	public HBox getPlayArea() {
		return playArea;
	}

	/**
	 * @param playArea the box to hold the computer and human play areas
	 */
	public void setPlayArea(HBox playArea) {
		this.playArea = playArea;
	}
}

/**
 * One object of this class represents a clickable playing card button that shows either the card's face image or the card-back image
 * Every card image is read from the images folder once into a static two dimensional array indexed by rank and suit the first time any image is needed, and each object wraps a private card that does the real work of the card interface methods
 *
 * @author Ryan Dennis
 * @author Atharv Dua
 */
class GUICard extends Button implements CardInterface {
	private static final boolean DEBUG = false;

	private static Image[][] cardImages = new Image[NUM_RANKS][NUM_SUITS];
	private static Image backCardImage;
	private static boolean imagesLoaded = false;

	private Card card = new Card();
	private boolean faceup;

	/**
	 * constructs a face-up card button with the default rank and suit
	 */
	public GUICard() {
		set(DEFAULT_RANK, DEFAULT_SUIT);
	}

	/**
	 * constructs a card button with the same rank and suit as the given card, face-down if the card is invalid
	 *
	 * @param card the card to copy
	 */
	public GUICard(Card card) {
		set(card.getRank(), card.getSuit());
	}

	/**
	 * constructs a card button with the given rank and suit, face-down if they are invalid
	 *
	 * @param rank the rank for the new card
	 * @param suit the suit for the new card
	 */
	public GUICard(char rank, Suit suit) {
		set(rank, suit);
	}

	/**
	 * reads every card image and the card-back image from the images folder the first time it is called and does nothing on any later call
	 */
	static void loadCardImages() {
		if (imagesLoaded) {
			return;
		}
		String pathToImageFolder = "file:" + System.getProperty("user.dir") + "/images/";
		for (int intRank = 0; intRank < NUM_RANKS; intRank++) {
			for (int intSuit = 0; intSuit < NUM_SUITS; intSuit++) {
				cardImages[intRank][intSuit] = new Image(pathToImageFolder + turnIntIntoCardRankChar(intRank)
						+ turnIntIntoCardSuitChar(intSuit) + ".gif");
			}
		}
		backCardImage = new Image(pathToImageFolder + "BK.gif");
		imagesLoaded = true;
	}

	/**
	 * @param card the card whose image is wanted
	 * @return the face image for the card or null if the card is invalid
	 */
	static public Image getImage(Card card) {
		loadCardImages();
		if (card.isErrorFlag()) {
			return null;
		}
		return cardImages[rankAsInt(card)][suitAsInt(card)];
	}

	/**
	 * @return the card-back image
	 */
	static public Image getBackCardImage() {
		loadCardImages();
		return backCardImage;
	}

	private static int rankAsInt(Card card) {
		for (int k = 0; k < NUM_RANKS; k++) {
			if (RANKS[k] == card.getRank()) {
				return k;
			}
		}
		return -1;
	}

	private static int suitAsInt(Card card) {
		return card.getSuit().ordinal();
	}

	private static char turnIntIntoCardRankChar(int k) {
		if (k < 0 || k > NUM_RANKS - 1) {
			return '?';
		}
		return RANKS[k];
	}

	private static char turnIntIntoCardSuitChar(int k) {
		if (k < 0 || k > NUM_SUITS - 1) {
			return '?';
		}
		return Suit.values()[k].name().toUpperCase().charAt(0);
	}

	/**
	 * @return the face image for this card or null if this card is invalid
	 */
	public Image getImage() {
		return getImage(card);
	}

	/**
	 * @return true if this card is showing its face image, false if it is showing the card-back image
	 */
	public boolean isFaceup() {
		return faceup;
	}

	/**
	 * Calls this.setGraphic passing it the correct ImageView depending if faceup is
	 * true (using the correct front Image)or false (using the back card Image).
	 *
	 * @param faceup is the state of the card being displayed faceup or not
	 */
	public void setFaceup(boolean faceup) {
		this.faceup = faceup;
		if (this.faceup) {
			this.setGraphic(new ImageView(this.getImage()));
		} else {
			this.setGraphic(new ImageView(getBackCardImage()));
		}
	}

	/**
	 * sets the rank and suit of the card, showing it face-up if the values were valid and face-down otherwise
	 *
	 * @param rank the rank value being set to the card
	 * @param suit the suit value being set to the card
	 * @return true if the values that were just set were valid, false otherwise
	 */
	@Override
	public boolean set(char rank, Suit suit) {
		boolean successful = this.card.set(rank, suit);
		if (successful) {
			setFaceup(true);
		} else {
			if (DEBUG) {
				System.out.println("set was NOT successful");
			}
			setFaceup(false);
		}
		return successful;
	}

	/**
	 * @return the card's current rank
	 */
	@Override
	public char getRank() {
		return card.getRank();
	}

	/**
	 * @return the card's current suit
	 */
	@Override
	public Suit getSuit() {
		return card.getSuit();
	}

	/**
	 * @return true if the card has any invalid internal property values, false otherwise
	 */
	@Override
	public boolean isErrorFlag() {
		return card.isErrorFlag();
	}

	/**
	 * @param card a card object
	 * @return true if the card objects have the same internal property values, false otherwise
	 */
	@Override
	public boolean equals(Card card) {
		return this.card.equals(card);
	}

	/**
	 * @return a non-null string representation of the card object
	 */
	@Override
	public String toString() {
		return card.toString();
	}
}

 /*-------------Phase 2 output
No console output (DEBUG is false), this is a GUI run.
The "CS 1B CardTable" window opens with:
Computer's Hand (collapsed, click to expand) = 7 face-down card-back GUICards
Playing Area = one face-up GUICard under "Computer" and one face-up GUICard under "You"
Your Hand = 7 face-up GUICards dealt from the shuffled deck
Clicking a card in Your Hand moves it from Your Hand onto the "You" play area.

*/
