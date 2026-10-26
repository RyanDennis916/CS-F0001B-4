import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.engine.discovery.DiscoverySelectors;

import java.io.PrintWriter;

import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;

/**
 * This test suite is a home for all of the tests so that can all be run at once
 * and in the correct order.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ Phase1AppTest.class, /*
											 * Tests the Phase1App your instructor gave you. This JavaFX Application's
											 * test MUST be ran FIRST so JavaFX is not initialized by another JavaFX
											 * Application before Phase1App.
											 */
		CardInterface_SuitTest.class, // Necessary
		CardTest.class, // Necessary
		GUICardTest.class, CardTableTest.class, // Necessary tests for other JavaFX classes
//		HandTest.class, // Not necessary as the application we are testing doesn't
		// use the Hand class
		DeckTest.class, // Necessary
})
public class TestSuite {
	/**
	 * Method for starting this test suite application. It is particularly useful
	 * for running all the tests from the TheiaIDE.
	 * 
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		// Create a Launcher to execute tests
		Launcher launcher = LauncherFactory.create();

		// Listener to generate test execution summary
		SummaryGeneratingListener listener = new SummaryGeneratingListener();

		// Create a request to select all test classes
		LauncherDiscoveryRequest request = request().selectors(DiscoverySelectors.selectClass(Phase1AppTest.class))
				.selectors(DiscoverySelectors.selectClass(CardInterface_SuitTest.class))
				.selectors(DiscoverySelectors.selectClass(CardTest.class))
				.selectors(DiscoverySelectors.selectClass(GUICardTest.class))
				.selectors(DiscoverySelectors.selectClass(CardTableTest.class))
//				.selectors(DiscoverySelectors.selectClass(HandTest.class))
				.selectors(DiscoverySelectors.selectClass(DeckTest.class)).build();

		// Execute the tests
		launcher.execute(request, listener);

		// Print the test results summary to the console
		try (PrintWriter writer = new PrintWriter(System.out)) {
			listener.getSummary().printTo(writer);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			System.err.println(e.getStackTrace());
		}
		// Print the test failures to the error console
		try (PrintWriter errWriter = new PrintWriter(System.err)) {
			listener.getSummary().printFailuresTo(errWriter);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			System.err.println(e.getStackTrace());
		}
	}
}