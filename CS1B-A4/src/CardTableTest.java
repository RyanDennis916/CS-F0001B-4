import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import org.junit.Assert;

/**
 * @author Baba Kofi Weusijana
 *
 */
@SuppressWarnings("unused")
public class CardTableTest
{

    private static final boolean DEBUG = false;

    /**
     * @throws java.lang.Exception if the setup before this class fails.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        JavaFXInitializerApp.initJavaFX();
    }

    /**
     * @throws java.lang.Exception if the tear down after this class fails.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    /**
     * @throws java.lang.Exception if the setup before a test fails.
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * @throws java.lang.Exception if the tear down after a test fails.
     */
    @After
    public void tearDown() throws Exception
    {
    }

    private CardTable createTestSubject()
    {
        return new CardTable();
    }

    /**
     * Test method for {@link CardTable#CardTable()}.
     */
    @Test
    public final void testCardTable()
    {
        CardTable cardTable = createTestSubject();
        String actual = cardTable.toString();
        Assert.assertTrue(actual.contains("CardTable@"));
    }

    /**
     * Test method for {@link CardTable#getComputerArea()}.
     */
    @Test
    public final void testGetComputerArea()
    {
        CardTable cardTable = createTestSubject();
        StackPane computerArea = cardTable.getComputerArea();
        String actual = computerArea.toString();
        String expected = "StackPane@";
        Assert.assertTrue("This test failed because '" + actual + "' did not contain:" + expected,
                actual.contains(expected));
    }

    /**
     * Test method for {@link CardTable#getComputerHand()}.
     */
    @Test
    public final void testGetComputerHand()
    {
        CardTable cardTable = createTestSubject();
        HBox computerHand = cardTable.getComputerHand();
        String actual = computerHand.toString();
        String expected = "HBox@";
        Assert.assertTrue("This test failed because '" + actual + "' did not contain:" + expected,
                actual.contains(expected));
    }

    /**
     * Test method for {@link CardTable#getHumanArea()}.
     */
    @Test
    public final void testGetHumanArea()
    {
        CardTable cardTable = createTestSubject();
        StackPane humanArea = cardTable.getHumanArea();
        String actual = humanArea.toString();
        String expected = "StackPane@";
        Assert.assertTrue("This test failed because '" + actual + "' did not contain:" + expected,
                actual.contains(expected));
    }

    /**
     * Test method for {@link CardTable#getHumanHand()}.
     */
    @Test
    public final void testGetHumanHand()
    {
        CardTable cardTable = createTestSubject();
        HBox humanHand = cardTable.getHumanHand();
        String actual = humanHand.toString();
        String expected = "HBox@";
        Assert.assertTrue("This test failed because '" + actual + "' did not contain:" + expected,
                actual.contains(expected));
    }

    /**
     * Test method for {@link CardTable#getPlayArea()}.
     */
    @Test
    public final void testGetPlayArea()
    {
        CardTable cardTable = createTestSubject();
        HBox playArea = cardTable.getPlayArea();
        String actual = playArea.toString();
        String expected = "HBox@";
        Assert.assertTrue("This test failed because '" + actual + "' did not contain:" + expected,
                actual.contains(expected));
        Assert.assertEquals(Pos.CENTER, playArea.getAlignment());
    }

    /**
     * Test method for
     * {@link CardTable#setComputerArea(javafx.scene.layout.StackPane)}.
     */
    @Test
    public final void testSetComputerArea()
    {
        CardTable cardTable = createTestSubject();
        StackPane testComputerArea = new StackPane();
        cardTable.setComputerArea(testComputerArea);
        StackPane computerArea = cardTable.getComputerArea();
        Assert.assertEquals(testComputerArea, computerArea);
    }

    /**
     * Test method for {@link CardTable#setComputerHand(javafx.scene.layout.HBox)}.
     */
    @Test
    public final void testSetComputerHand()
    {
        CardTable cardTable = createTestSubject();
        HBox testComputerHand = new HBox();
        cardTable.setComputerHand(testComputerHand);
        HBox computerHand = cardTable.getComputerHand();
        Assert.assertEquals(testComputerHand, computerHand);
    }

    /**
     * Test method for
     * {@link CardTable#setHumanArea(javafx.scene.layout.StackPane)}.
     */
    @Test
    public final void testSetHumanArea()
    {
        CardTable cardTable = createTestSubject();
        StackPane testHumanArea = new StackPane();
        cardTable.setHumanArea(testHumanArea);
        StackPane humanArea = cardTable.getHumanArea();
        Assert.assertEquals(testHumanArea, humanArea);
    }

    /**
     * Test method for {@link CardTable#setHumanHand(javafx.scene.layout.HBox)}.
     */
    @Test
    public final void testSetHumanHand()
    {
        CardTable cardTable = createTestSubject();
        HBox testHumanHand = new HBox();
        cardTable.setHumanHand(testHumanHand);
        HBox humanHand = cardTable.getHumanHand();
        Assert.assertEquals(testHumanHand, humanHand);
    }

    /**
     * Test method for {@link CardTable#setPlayArea(javafx.scene.layout.HBox)}.
     */
    @Test
    public final void testSetPlayArea()
    {
        CardTable cardTable = createTestSubject();
        HBox testPlayArea = new HBox();
        cardTable.setPlayArea(testPlayArea);
        HBox playArea = cardTable.getPlayArea();
        Assert.assertEquals(testPlayArea, playArea);
    }

}
