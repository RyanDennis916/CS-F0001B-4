
import static org.junit.Assert.fail;

import java.util.logging.Logger;

//import javax.annotation.Generated;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

import mockit.Deencapsulation;

//@Generated(value = "org.junit-tools-1.0.6")
/**
 * Tests the GUICard class
 */
@SuppressWarnings("unused")
public class GUICardTest {
	private static final boolean DEBUG = false;
	static boolean getBackCardImageFailsToCallloadCardImages = false;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		JavaFXInitializerApp.initJavaFX();

		boolean imagesLoaded = true;

		// Check if getBackCardImage correctly calls GUICard.loadCardImages()
		imagesLoaded = Deencapsulation.getField(GUICard.class, "imagesLoaded");
		if (!imagesLoaded) {
			@SuppressWarnings("unused")
			Image result = GUICard.getBackCardImage();
			imagesLoaded = Deencapsulation.getField(GUICard.class, "imagesLoaded");
			getBackCardImageFailsToCallloadCardImages = !imagesLoaded;
		}
	}

	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	// @Generated(value = "org.junit-tools-1.0.6")
	private Logger logger = Logger.getLogger(GUICardTest.class.toString());

	private GUICard createTestSubject() {
		return new GUICard();
	}

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "Card", signature = "(QCard;)Z")
	@Test
	public void testCard() throws Exception {
		Card testSubject;
		Card card = new Card('A', Card.Suit.spades);

		// copy constructor test
		testSubject = new Card(card);
		Assert.assertEquals(testSubject.toString(), card.toString());
		Assert.assertTrue(testSubject.equals(card));

		// defaults test
		testSubject = new Card();
		Assert.assertEquals(testSubject.toString(), card.toString());
		Assert.assertTrue(testSubject.equals(card));
	}

	/**
	 * Test method for {@link GUICard#equals(Card)}.
	 */
	@Test
	public final void testEqualsCard() {
		GUICard testSubject;
		Card card = new Card('A', Card.Suit.spades);
		boolean result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.equals(card);
		Assert.assertTrue(result);

		// different rank test
		testSubject = createTestSubject();
		testSubject.set('J', testSubject.getSuit());
		result = testSubject.equals(card);
		Assert.assertFalse(result);

		// different suit test
		testSubject = createTestSubject();
		// testSubject.set(testSubject.getVal(), Card.Suit.clubs);
		testSubject.set(testSubject.getRank(), Card.Suit.clubs); // Student is not
		// using getVal
		result = testSubject.equals(card);
		Assert.assertFalse(result);

		// different errorFlag test
		// testSubject.errorFlag = true;
		Card badcard = new Card('*', Card.Suit.spades);
		testSubject = new GUICard(badcard);
		Deencapsulation.setField(badcard, "errorFlag", false);
		// Assert.assertTrue(testSubject.getErrorFlag());
		Assert.assertTrue(testSubject.isErrorFlag()); // Student used 'is' predicate
		// naming
		result = testSubject.equals(badcard);
		Assert.assertFalse(result); // if result is true, ERROR: Student is not checking if the errorFlag is true
	}

	/**
	 * Test method for {@link GUICard#getBackCardImage()}.
	 */
	@Test
	public final void testGetBackCardImage() {
		String filename = null;
		Image result;

		// default test
		result = GUICard.getBackCardImage();
		String expectedString = "BK";
		if (!result.toString().contains(expectedString)) {
			// try to get the URL
			String url = result.getUrl();
			Assert.assertNotNull(url);
			Assert.assertEquals(true, url.contains(expectedString));
		}

		// Check if getBackCardImage correctly calls GUICard.loadCardImages()
		Assert.assertFalse("getBackCardImage should have called GUICard.loadCardImages() and set iconsLoaded to true",
				getBackCardImageFailsToCallloadCardImages);
	}

	/**
	 * Test method for {@link GUICard#getImage()}.
	 */
	@Test
	public final void testGetImage() {
		Card card = null;
		Image result;

		// default test but on a GUICard object
		GUICard guicard = createTestSubject();
		result = guicard.getImage();
		String expectedguicardString = "AS";
		if (!result.toString().contains(expectedguicardString)) {
			// try to get the URL
			String url = result.getUrl();
			Assert.assertNotNull(url);
			Assert.assertEquals(true, url.contains(expectedguicardString));
		}
	}

	/**
	 * Test method for {@link GUICard#getImage(Card)}.
	 */
	@Test
	public final void testGetImageCard() {
		Card card = null;
		Image result;

		// default test
		card = new Card();
		result = GUICard.getImage(card);
		String expectedString = "AS";
		if (!result.toString().contains(expectedString)) {
			// try to get the URL
			String url = result.getUrl();
			Assert.assertNotNull(url);
			Assert.assertTrue(url + " should have contained " + expectedString, url.contains(expectedString));
		}

		// test 9 of clubs
		card = new Card('9', Card.Suit.clubs);
		result = GUICard.getImage(card);
		expectedString = "9C";
		if (!result.toString().contains(expectedString)) {
			// try to get the URL
			String url = result.getUrl();
			Assert.assertNotNull(url);
			Assert.assertTrue(url + " should have contained " + expectedString, url.contains(expectedString));
		}

		// test maximum rank of clubs
		card = new Card(Card.RANKS[Card.NUM_RANKS - 1], Card.Suit.clubs);
		result = GUICard.getImage(card);
		expectedString = "XC";
		if (!result.toString().contains(expectedString)) {
			// try to get the URL
			String url = result.getUrl();
			Assert.assertNotNull(url);
			Assert.assertTrue(url + " should have contained " + expectedString, url.contains(expectedString));
		}

		// test INVALID rank of clubs
		card = new Card('~', Card.Suit.clubs);
		result = GUICard.getImage(card);
		String message = "Calling GUICard.getImage(card) wuth an invalid card "
				+ "should result in a null being returned.";
		Assert.assertNull(message, result);
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "getSuit", signature = "()QSuit;")
	@Test
	public void testGetSuit() throws Exception {
		GUICard testSubject;
		Card.Suit result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getSuit();
		Assert.assertEquals(Card.Suit.spades, result);
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "getValue", signature = "()C")
	@Test
	public void testGetValue() throws Exception {
		GUICard testSubject;
		char result;

		// default test
		testSubject = createTestSubject();
		// result = testSubject.getVal();
		result = testSubject.getRank();// Student uses getValue instead of getVal
		Assert.assertEquals('A', result);
	}

	/**
	 * Test method for {@link GUICard#GUICard()}.
	 */
	@Test
	public final void testGUICard() {
		GUICard testSubject;
		GUICard guiCard = new GUICard('A', Card.Suit.spades);
		// boolean result;

		testSubject = new GUICard();
		Assert.assertEquals(testSubject.toString(), guiCard.toString());
		Assert.assertEquals(true, testSubject.toString().equals(guiCard.toString()));

		// test constructors
		testSubject = new GUICard();
		// Assert.assertEquals(Card.DEFAULT_VAL + " of " + Card.DEFAULT_SUIT.toString(),
		// testSubject.toString());
		Assert.assertEquals('A' + " of " + Card.Suit.spades.toString(), testSubject.toString());
	}

	/**
	 * Test method for {@link GUICard#GUICard(Card)}.
	 */
	@Test
	public final void testGUICardCard() {
		GUICard testSubject;
		GUICard guiCard = new GUICard('A', Card.Suit.spades);
		// boolean result;

		testSubject = new GUICard(new Card('A', Card.Suit.spades));
		Assert.assertEquals(testSubject.toString(), guiCard.toString());
		Assert.assertEquals(true, testSubject.toString().equals(guiCard.toString()));

		// test constructors
		testSubject = new GUICard(new Card('A', Card.Suit.spades));
		// Assert.assertEquals(Card.DEFAULT_VAL + " of " + Card.DEFAULT_SUIT.toString(),
		// testSubject.toString());
		Assert.assertEquals('A' + " of " + Card.Suit.spades.toString(), testSubject.toString());
	}

	/**
	 * Test method for {@link GUICard#GUICard(char, CardInterface.Suit)}.
	 */
	@Test
	public final void testGUICardCharSuit() {
		GUICard testSubject;
		GUICard guiCard = new GUICard('A', Card.Suit.spades);
		// boolean result;

		testSubject = new GUICard('A', Card.Suit.spades);
		Assert.assertEquals(testSubject.toString(), guiCard.toString());
		Assert.assertEquals(true, testSubject.toString().equals(guiCard.toString()));

		// test constructors
		testSubject = new GUICard('A', Card.Suit.spades);
		// Assert.assertEquals(Card.DEFAULT_VAL + " of " + Card.DEFAULT_SUIT.toString(),
		// testSubject.toString());
		Assert.assertEquals('A' + " of " + Card.Suit.spades.toString(), testSubject.toString());
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "isErrorFlag", signature = "()Z")
	@Test
	public void testIsErrorFlag() throws Exception {
		GUICard testSubject;
		boolean result;

		// default test
		testSubject = createTestSubject();
		// result = testSubject.getErrorFlag();
		Assert.assertFalse(testSubject.isErrorFlag()); // Student used 'is' predicate
		// naming
		// Assert.assertFalse(result); // Student used 'is' predicate naming
		testSubject.set('~', testSubject.getSuit());
		// Assert.assertTrue(testSubject.getErrorFlag());
		Assert.assertTrue(testSubject.isErrorFlag()); // Student used 'is' predicate
		// naming
	}

	/**
	 * Test method for {@link GUICard#isFaceup()}.
	 */
	@Test
	public final void testIsFaceup() {
		GUICard testSubject = createTestSubject();
		Assert.assertTrue(testSubject.isFaceup());
		testSubject.setFaceup(false);
		Assert.assertFalse(testSubject.isFaceup());
		// Invalid GUI Cards should be face down
		testSubject = new GUICard(new Card('*', Card.Suit.spades));
		Assert.assertFalse(
				"Invalid GUI Cards should be face down. "
						+ "Are you calling set(char, CardInterface.Suit) inside the GuiCard(Card) constructor?",
				testSubject.isFaceup());
		testSubject = new GUICard('*', Card.Suit.spades);
		Assert.assertFalse(
				"Invalid GUI Cards should be face down. Are you calling set(char, CardInterface.Suit) inside the "
						+ "GuiCard(char, CardInterface.Suit) constructor?",
				testSubject.isFaceup());
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "isValid", signature = "(CQSuit;)Z")
	@Test
	public void testIsValid() throws Exception {
		char rank = 'A';
		Card.Suit suit = Card.Suit.spades;
		boolean result;

		// test for detection of valid data
		// result = Card.isValid(rank, suit);
		result = Deencapsulation.invoke(Card.class, "isValid", new Object[] { rank, suit });
		Assert.assertTrue(result);
		rank = 'J';
		suit = Card.Suit.diamonds;
		// result = Card.isValid(rank, suit);
		result = Deencapsulation.invoke(Card.class, "isValid", new Object[] { rank, suit });
		Assert.assertTrue(result);
		// test for detection of invalid data cancelled because can't pass null
		// good rank, bad suit
		// rank = 'J';
		// suit = null;
		// result = Deencapsulation.invoke(Card.class, "isValid", new Object[] { rank,
		// suit });
		// Assert.assertFalse(result);
		// test for detection of invalid data
		// bad rank, good suit
		rank = ' ';
		suit = Card.Suit.clubs;
		result = Deencapsulation.invoke(Card.class, "isValid", new Object[] { rank, suit });
		Assert.assertFalse(result);
		// For GUICards only: make sure 'X' Jokers are valid
		rank = 'X';
		suit = Card.Suit.hearts;
		result = Deencapsulation.invoke(Card.class, "isValid", new Object[] { rank, suit });
		Assert.assertTrue("Card class should allow 'X' for Jokers as a valid rank", result);
	}

	/**
	 * Test method for {@link GUICard#loadCardImages()}.
	 */
	@Test
	public final void testLoadCardImages() {
		boolean imagesLoaded = true;

		// Check if getBackCardImage correctly calls GUICard.loadCardImages()
		imagesLoaded = Deencapsulation.getField(GUICard.class, "imagesLoaded");
		if (!imagesLoaded) {
			@SuppressWarnings("unused")
			Image result = GUICard.getBackCardImage();
			imagesLoaded = Deencapsulation.getField(GUICard.class, "imagesLoaded");
			Assert.assertTrue(
					"getBackCardImage should have called GUICard.loadCardImages() and set iconsLoaded to true",
					imagesLoaded);
		}

		// default test
		if (!imagesLoaded) {
			GUICard.loadCardImages();
		}
		Image[][] cardImages;
		cardImages = Deencapsulation.getField(GUICard.class, "cardImages");
		Assert.assertEquals(14, cardImages.length);
		Assert.assertEquals(4, cardImages[0].length);
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "set", signature = "(CQSuit;)Z")
	@Test
	public void testSet() throws Exception {
		GUICard testSubject;
		char rank = ' ';
		Card.Suit suit = null;
		boolean result;

		// default test
		testSubject = createTestSubject();
		// Make the testSubject invalid
		result = testSubject.set(rank, suit);
		Assert.assertEquals(false, result);
		result = testSubject.set(rank, Card.Suit.spades);
		Assert.assertEquals(false, result);
		// Make the testSubject valid
		rank = 'J';
		suit = Card.Suit.hearts;
		result = testSubject.set(rank, suit);
		Assert.assertTrue(result);
	}

	/**
	 * Test method for {@link GUICard#setFaceup(boolean)}.
	 */
	@Test
	public final void testSetFaceup() {
		GUICard testSubject = createTestSubject();
		testSubject.setFaceup(false);
		Assert.assertFalse(testSubject.isFaceup());
		testSubject.setFaceup(true);
		Assert.assertTrue(testSubject.isFaceup());
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "suitAsInt", signature = "(QCard;)I")
	@Test
	public void testSuitAsInt() throws Exception {
		Card card = null;
		int result;

		// default test
		card = new Card();
		result = Deencapsulation.invoke(GUICard.class, "suitAsInt", card);
		Assert.assertEquals(3, result);
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "toString", signature = "()QString;")
	@Test
	public void testToString() throws Exception {
		GUICard testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
		Assert.assertEquals("a of spades", result.toLowerCase());

		// make the Card bad and test again
		testSubject.set('~', Card.Suit.clubs);
		result = testSubject.toString();
		Assert.assertTrue(result.toLowerCase().contains("invalid") || result.toLowerCase().contains("illegal"));
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "turnIntIntoCardSuitChar", signature = "(I)C")
	@Test
	public void testTurnIntIntoCardSuitChar() throws Exception {
		int k = 0;
		char result;

		// test 1
		k = 0;
		result = Deencapsulation.invoke(GUICard.class, "turnIntIntoCardSuitChar", new Object[] { k });
		Assert.assertEquals(67, result);

		// test 2
		k = -1;
		result = Deencapsulation.invoke(GUICard.class, "turnIntIntoCardSuitChar", new Object[] { k });
		Assert.assertEquals(63, result);

		// test 3
		k = 1;
		result = Deencapsulation.invoke(GUICard.class, "turnIntIntoCardSuitChar", new Object[] { k });
		Assert.assertEquals(68, result);

		// test 4
		k = 3;
		result = Deencapsulation.invoke(GUICard.class, "turnIntIntoCardSuitChar", new Object[] { k });
		Assert.assertEquals(83, result);

		// test 5
		k = 2;
		result = Deencapsulation.invoke(GUICard.class, "turnIntIntoCardSuitChar", new Object[] { k });
		Assert.assertEquals(72, result);

		// test 6
		k = 4;
		result = Deencapsulation.invoke(GUICard.class, "turnIntIntoCardSuitChar", new Object[] { k });
		Assert.assertEquals(63, result);
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "turnIntIntoCardRankChar", signature = "(I)C")
	@Test
	public void testTurnIntIntoCardRankChar() throws Exception {
		int k = 0;
		char result;

		// test 1
		k = 0;
		String methodName = "turnIntIntoCardRankChar";
		result = Deencapsulation.invoke(GUICard.class, methodName, new Object[] { k });
		Assert.assertEquals(50, result);

		// test 2
		k = -1;
		result = Deencapsulation.invoke(GUICard.class, methodName, new Object[] { k });
		Assert.assertEquals(63, result);

		// test 3
		k = 1;
		result = Deencapsulation.invoke(GUICard.class, methodName, new Object[] { k });
		Assert.assertEquals(51, result);
	}

	/**
	 * @throws Exception
	 */
	@MethodRef(name = "rankAsInt", signature = "(QCard;)I")
	@Test
	public void testValueAsInt() throws Exception {
		Card card = null;
		int result;

		// default test
		card = new Card();
		// Assert.assertEquals('A', card.getVal());
		Assert.assertEquals('A', card.getRank());
		result = Deencapsulation.invoke("GUICard", "rankAsInt", card);
		Assert.assertEquals(12, result);
	}

}