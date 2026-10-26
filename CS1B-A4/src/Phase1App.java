import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Micheal Loceff
 * @author Baba Kofi Weusijana
 */
public class Phase1App extends Application
{
    private static final boolean DEBUG = true;
    private static final double APP_WIDTH = 1024;
    private static final double APP_HEIGHT = 315;
    static int NUM_CARD_IMAGES = CardInterface.NUM_RANKS * CardInterface.NUM_SUITS + 1; // added 1 for the back
                                                                                         // ImageView
    static ImageView[] imageViews = new ImageView[NUM_CARD_IMAGES];
    static Label[] labels = new Label[NUM_CARD_IMAGES];

    static void loadCardImages()
    {
        String pathToImageFolder = "file:" + System.getProperty("user.dir") + "/images/";
        if (DEBUG)
        {
            System.out.println("pathToImageFolder:");
            System.out.println(pathToImageFolder);
        }
        String imageFileName = "";
        try
        {
            int intSuit, intVal;

            for (intSuit = 0; intSuit < CardInterface.NUM_SUITS; intSuit++)
            {
                for (intVal = 0; intVal < CardInterface.NUM_RANKS; intVal++)
                {
                    // card image files stored in images folder with names like
                    // "AC.gif", "3H.gif","XD.gif", etc.
                    imageFileName = pathToImageFolder + turnIntIntoCardRankChar(intVal)
                            + turnIntIntoCardSuitChar(intSuit) + ".gif";
                    Image image = new Image(imageFileName);
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageViews[intSuit * CardInterface.NUM_RANKS + intVal] = imageView;
                }
            }
            imageFileName = pathToImageFolder + "BK.gif";
            Image bkImage = new Image(imageFileName);
            ImageView bkImageView = new ImageView();
            bkImageView.setImage(bkImage);
            imageViews[imageViews.length - 1] = bkImageView;
        } catch (java.lang.IllegalArgumentException e)
        {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            System.out.println("imageFileName:" + imageFileName);
        }
    }

    // turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
    static char turnIntIntoCardRankChar(int k)
    {
        if (k < 0 || k > (CardInterface.NUM_RANKS - 1))
            return '?';
        return CardInterface.RANKS[k];
    }

    // turns 0 - 3 into 'C', 'D', 'H', 'S'
    static char turnIntIntoCardSuitChar(int k)
    {
        if (k < 0 || k > (CardInterface.NUM_SUITS - 1))
            return '?';
        return CardInterface.Suit.values()[k].name().toUpperCase().charAt(0);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    /**
     * @param stage The Stage object for this Application
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        // prepare the imageViews array
        loadCardImages();

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(imageViews);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hBox);
        TitledPane titledPane = new TitledPane("Notable Women in Computing Cards", scrollPane);

        Scene scene = new Scene(titledPane, APP_WIDTH, APP_HEIGHT);
        stage.setTitle("Card Room");
        stage.setWidth(APP_WIDTH);
        stage.setHeight(APP_HEIGHT);
        stage.setScene(scene);
        stage.sizeToScene();
        // show everything to the user
        stage.show();
    }
}