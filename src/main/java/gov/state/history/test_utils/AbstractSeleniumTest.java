package gov.state.history.test_utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * A superclass for typical (in the scope of our app) Selenium tests.
 * Defines a standard setUp / tearDown and some common helper methods. 
 */
public abstract class AbstractSeleniumTest {
	protected WebDriver driver;
	protected boolean acceptNextAlert = true;
	protected  StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = SeleniumUtils.obtainFirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		assertThat(verificationErrorString).overridingErrorMessage(verificationErrorString).isEmpty();
	}
	
	protected void basicPageVerification(String descr) {
		if(!isElementPresent(By.id("content-inner"))) {
			String errorMsg;
			try {
				WebElement errorH1 = driver.findElement(By.xpath("//div[@data-template='app:handle-error']/h1"));
				errorMsg = "Error page \"" + errorH1.getText() + "\" for: ";
			} catch(NoSuchElementException e) {
				errorMsg = "Page does not contain required element #content-inner: ";
			}
			
			verificationErrors.append(errorMsg)
				.append(driver.getCurrentUrl()).append(" ").append(descr).append("\n");
		}
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
