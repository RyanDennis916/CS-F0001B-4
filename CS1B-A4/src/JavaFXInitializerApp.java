import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Use the public static initJavaFX method to ensure that JavaFX has been
 * initialized so JavaFX related unit tests will run in isolation.
 * 
 * @author Baba Kofi Weusijana
 *
 */
public class JavaFXInitializerApp extends Application
{
    private static final boolean DEBUG = false;
    private static final double APP_WIDTH = 0;
    private static final double APP_HEIGHT = 0;
    @SuppressWarnings("unused")
    private static JavaFXInitializerApp self;

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
        Scene scene = new Scene(new FlowPane(), APP_WIDTH, APP_HEIGHT);
        stage.setTitle("JavaFXInitializerApp");
        stage.setWidth(APP_WIDTH);
        stage.setHeight(APP_HEIGHT);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        stage.hide();
        this.stop();
    }

    /**
     * Assume JavaFX has not been initialized and run a JavaFXInitializerApp's main
     * method. Exceptions are not thrown.
     */
    public static void initJavaFX()
    {
        try
        {
            self = new JavaFXInitializerApp();
            main(null);
        } catch (Exception e)
        {
            if (DEBUG)
            {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}