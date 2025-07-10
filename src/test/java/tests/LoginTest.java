// Location: src/test/java/tests/LoginTest.java
package tests;

import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import java.nio.file.Paths;
import static org.junit.Assert.*;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Before
    public void initializeTest() throws Exception {
        String projectDir = System.getProperty("user.dir");
        String filePath = Paths.get(projectDir, "login.html").toUri().toURL().toString();
        driver.get(filePath);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testFindElementsOnPageLoad() {
        assertTrue("Core login elements should be visible.", loginPage.areCoreElementsDisplayed());
    }

    @Test
    public void testVerifyTitle() {
        assertEquals("The page title is incorrect.", "Log in to VMD-SERAPIS-01", driver.getTitle());
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.performLogin("xxx", "yyy");
        String expectedUrl = "https://www.atom.com/name/Test";
        
        assertTrue(
            "Navigation to the expected URL (" + expectedUrl + ") after login failed.",
            loginPage.verifyUrlAfterRedirect(expectedUrl)
        );
    }

    @Test
    public void testLoginWithInvalidEmail() {
        loginPage.performLogin("zzz", "yyy");
        try {
            String errorMessage = loginPage.getErrorMessage();
            assertEquals("The error message for an invalid email is incorrect.", "invalid email", errorMessage);
        } catch (Exception e) {
            fail("Test failed because the error message element was not found, which is expected for this setup.");
        }
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        loginPage.performLogin("zzz", "iii");
        try {
            String errorMessage = loginPage.getErrorMessage();
            assertEquals("The error message for invalid credentials is incorrect.", "enter valid email", errorMessage);
        } catch (Exception e) {
            fail("Test failed because the error message element was not found, which is expected for this setup.");
        }
    }
}
