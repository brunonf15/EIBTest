// Location: src/main/java/pages/LoginPage.java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

	private final WebDriver driver;
	private final WebDriverWait wait;

	private final By usernameInput = By.id("username");
	private final By passwordInput = By.id("password");
	private final By loginButton = By.id("kc-login");
	private final By errorMessageContainer = By.id("fake-error-message");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void enterUsername(String username) {
		WebElement usernameField = driver.findElement(usernameInput);
		usernameField.clear();
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordField = driver.findElement(passwordInput);
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}

	public void performLogin(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
	}

	public boolean areCoreElementsDisplayed() {
		return driver.findElement(usernameInput).isDisplayed() && driver.findElement(passwordInput).isDisplayed()
				&& driver.findElement(loginButton).isDisplayed();
	}

	public boolean verifyUrlAfterRedirect(String expectedUrl) {
		try {
			wait.until(ExpectedConditions.urlToBe(expectedUrl));
			return true;
		} catch (TimeoutException e) {
			System.out.println("Timed out after 10 seconds waiting for URL: " + expectedUrl);
			System.out.println("Current URL is: " + driver.getCurrentUrl());
			return false;
		}
	}

	public String getErrorMessage() {
		WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageContainer));
		return errorElement.getText();
	}
}
