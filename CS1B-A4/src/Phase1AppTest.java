
import static org.junit.Assert.fail;

import java.util.logging.Logger;

//import javax.annotation.Generated;
import javafx.scene.image.ImageView;

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
 * Tests the Phase1App class.
 */
public class Phase1AppTest
{

//	@Generated(value = "org.junit-tools-1.0.6")
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(Phase1AppTest.class.toString());

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {

    }

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {

    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception
    {

    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {

    }

    private Phase1App createTestSubject()
    {
        return new Phase1App();
    }

    /**
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    @MethodRef(name = "loadCardImages", signature = "()V")
    @Test
    public void testLoadCardImages() throws Exception
    {

        // default test
        Phase1App phase1App = createTestSubject();
        try
        {
            phase1App.main(null);
        } catch (Exception e)
        {
            String errorExplanation = "This Phase1AppTest object is a JavaFX " + "Application tester. "
                    + "It MUST be ran FIRST so JavaFX is not initialized by "
                    + "another JavaFX Application before the Phase1App (the "
                    + "class being tested) initializes JavaFX.";
            System.out.println(errorExplanation);
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
            fail(errorExplanation);
        }
        ImageView[] imageViews = Deencapsulation.getField(phase1App, "imageViews");
        Assert.assertEquals(57, imageViews.length);
    }

    /**
     * @throws Exception
     */
    @MethodRef(name = "turnIntIntoCardRankChar", signature = "(I)C")
    @Test
    public void testTurnIntIntoCardRankChar() throws Exception
    {
        int k = 0;
        char result;

        // test 1
        k = 0;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardRankChar", new Object[]
        { k });
        Assert.assertEquals(50, result);

        // test 2
        k = -1;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardRankChar", new Object[]
        { k });
        Assert.assertEquals(63, result);

        // test 3
        k = 1;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardRankChar", new Object[]
        { k });
        Assert.assertEquals(51, result);

        // test 4
        k = 13;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardRankChar", new Object[]
        { k });
        Assert.assertEquals(88, result);

        // test 5
        k = 12;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardRankChar", new Object[]
        { k });
        Assert.assertEquals(65, result);

        // test 6
        k = 14;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardRankChar", new Object[]
        { k });
        Assert.assertEquals(63, result);
    }

    /**
     * @throws Exception
     */
    @MethodRef(name = "turnIntIntoCardSuitChar", signature = "(I)C")
    @Test
    public void testTurnIntIntoCardSuitChar() throws Exception
    {
        int k = 0;
        char result;

        // test 1
        k = 0;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardSuitChar", new Object[]
        { k });
        Assert.assertEquals(67, result);

        // test 2
        k = -1;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardSuitChar", new Object[]
        { k });
        Assert.assertEquals(63, result);

        // test 3
        k = 1;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardSuitChar", new Object[]
        { k });
        Assert.assertEquals(68, result);

        // test 4
        k = 3;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardSuitChar", new Object[]
        { k });
        Assert.assertEquals(83, result);

        // test 5
        k = 2;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardSuitChar", new Object[]
        { k });
        Assert.assertEquals(72, result);

        // test 6
        k = 4;
        result = Deencapsulation.invoke(Phase1App.class, "turnIntIntoCardSuitChar", new Object[]
        { k });
        Assert.assertEquals(63, result);
    }

}