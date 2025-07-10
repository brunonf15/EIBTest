// Location: src/main/java/pages/EventsPage.java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EventsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for the elements on the page
    private final By eventOptionsPanel = By.id(
            "j_id1:operationCalendar:otdOneOperationCalendarEventList:filter:18:j_id1:genericCalendarEvent:dtoEventType_panel");
    private final By fifthEventItem = By
            .cssSelector("[data-item-value='GENERIC_EVENT_D1_APPROVAL_BY_ADVISORY_COMMITTEE']");
    private final By eventInputTrigger = By.id(
            "j_id1:operationCalendar:otdOneOperationCalendarEventList:filter:18:j_id1:genericCalendarEvent:dtoEventType_input");

    public EventsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickToOpenEvents() {
        driver.findElement(eventInputTrigger).click();
    }

    public void waitForEventsPanel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventOptionsPanel));
    }

    public void selectFifthEventByAttribute() {
        driver.findElement(fifthEventItem).click();
    }

    public String getSelectedEventText() {
        return driver.findElement(eventInputTrigger).getAttribute("value");
    }
}
