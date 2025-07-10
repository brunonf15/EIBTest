// Location: src/main/java/pages/QuestionnairePage.java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuestionnairePage {

    private final WebDriver driver;
    private final By allRadioButtons = By.xpath("//input[@type='radio']");

    public QuestionnairePage(WebDriver driver) {
        this.driver = driver;
    }

    public void answerAllQuestionsRandomly() {
        List<WebElement> radioButtons = driver.findElements(allRadioButtons);
        Random random = new Random();

        radioButtons.stream()
                // Step 1: Group the buttons into a Map. The key is the 'name' (the question),
                // and the value is the list of buttons (the 'Yes'/'No' options).
                .collect(Collectors.groupingBy(button -> button.getAttribute("name")))

                // Step 2: From the Map, get only the values, which are the lists of options.
                .values()

                // Step 3: For each list of options (each question), execute the following action.
                .forEach(options -> { // 'options' here is a list of buttons for ONE question, e.g., [btn_yes, btn_no].
                    // Choose a random index (e.g., 0 or 1) from the list of options.
                    int randomIndex = random.nextInt(options.size());
                    WebElement optionToSelect = options.get(randomIndex);

                    // Click the chosen button to answer the question.
                    optionToSelect.click();
                });
    }

    public boolean isPageCorrect() {
        return !driver.findElements(allRadioButtons).isEmpty();
    }

    public boolean areAllQuestionsAnswered() {
        return driver.findElements(allRadioButtons)
                // Start the first stream with all individual buttons.
                .stream()

                // Group the buttons by question, just like in the previous function.
                .collect(Collectors.groupingBy(button -> button.getAttribute("name")))

                // Get only the lists of options (the question groups).
                .values()

                // Start the second stream. Now, each item in the stream is a GROUP of buttons.
                .stream()

                // Check if ALL groups in the stream meet the following condition.
                .allMatch(options -> // 'options' is a group of buttons (one question).
                        // The condition is: check if WITHIN this group...
                        options.stream()
                                // ...there is AT LEAST ONE button that is selected.
                                .anyMatch(WebElement::isSelected)
                );
    }
}
