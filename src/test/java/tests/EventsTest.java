// Location: src/test/java/tests/EventsTest.java
package tests;

import org.junit.Test;
import pages.EventsPage;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EventsTest extends BaseTest {

    @Test
    public void testSelectFifthEventUsingSpecificLocators() throws Exception {
        // Arrange: Configure the environment
        String projectDir = System.getProperty("user.dir");
        String filePath = Paths.get(projectDir, "EventsPage.html").toUri().toURL().toString();
        driver.get(filePath);

        EventsPage eventsPage = new EventsPage(driver);

        try {
            // Arrange: Define the expected outcome.
            String expectedText = "D1 approval by Advisory Committee";

            // Act: Perform the sequence of user actions.
            eventsPage.clickToOpenEvents();
            eventsPage.waitForEventsPanel();
            eventsPage.selectFifthEventByAttribute();

            // Assert: Verify that the outcome is correct.
            String actualText = eventsPage.getSelectedEventText();

            assertEquals("The selected event text is incorrect.", expectedText, actualText);
            System.out.println("Test passed: The item '" + actualText + "' was selected correctly.");

        } catch (Exception e) {
            fail("The test failed. Cause: " + e.getMessage());
        }
    }
}
