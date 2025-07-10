// Location: src/test/java/tests/QuestionnaireTest.java
package tests;

import org.junit.Test;
import pages.QuestionnairePage;
import java.nio.file.Paths;
import static org.junit.Assert.assertTrue;

// The class now extends BaseTest, which handles the setUp and tearDown methods.
public class QuestionnaireTest extends BaseTest {

    @Test
    public void testFillQuestionnaireRandomly() throws Exception {
    	// Arrange: Pre-condition check to ensure the page is ready.
        String projectDir = System.getProperty("user.dir");
        String filePath = Paths.get(projectDir, "QuestionnairePage.html").toUri().toURL().toString();
        driver.get(filePath);
        QuestionnairePage questionnairePage = new QuestionnairePage(driver);
        
        assertTrue("The questionnaire page was not loaded or contains no radio buttons.",
                questionnairePage.isPageCorrect());

        // Act: Perform the main action of the test.
        System.out.println("Filling out the questionnaire with random answers...");
        questionnairePage.answerAllQuestionsRandomly();
        System.out.println("Filling complete. Starting verification...");

        // Assert: Verify that the action had the expected outcome.
        assertTrue("Verification failed: one or more questions were not answered.",
                questionnairePage.areAllQuestionsAnswered());

        System.out.println("Verification successful: All questions have been answered!");
    }
}
