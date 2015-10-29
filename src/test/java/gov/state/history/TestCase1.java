package gov.state.history;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCase1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCase1() throws Exception {
    driver.get(baseUrl + "/exist/apps/hsg-shell/");
    assertEquals("history.state.gov 3.0 shell", driver.getTitle());
    driver.findElement(By.cssSelector("div.col-md-6 > ul > li > ul > li > a")).click();
    assertEquals("Historical Documents", driver.findElement(By.cssSelector("h1")).getText());
    assertEquals("Truman Administration \n 1945–1952", driver.findElement(By.cssSelector("td > a")).getText());
    driver.findElement(By.cssSelector("td > a")).click();
    assertEquals("Truman Administration", driver.findElement(By.cssSelector("h1.titleStmt1")).getText());
    assertEquals("General: The United Nations, Volume I", driver.findElement(By.cssSelector("ol > li > a > span")).getText());
    driver.findElement(By.cssSelector("ol > li > a > span")).click();
    assertEquals("Not found", driver.findElement(By.cssSelector("h1")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
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
