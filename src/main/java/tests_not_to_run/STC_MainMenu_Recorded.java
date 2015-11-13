package tests_not_to_run;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class STC_MainMenu_Recorded {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/exist/apps/hsg-shell";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSuite2MainMenu() throws Exception {
    driver.get(baseUrl + "/exist/apps/hsg-shell/open");

    driver.findElement(By.cssSelector("a > span")).click();
    driver.findElement(By.cssSelector("a > span")).click();
    driver.findElement(By.cssSelector("a.dropdown-toggle > span")).click();
    driver.findElement(By.cssSelector("em")).click();
    driver.findElement(By.cssSelector("a.dropdown-toggle > span")).click();
    driver.findElement(By.xpath("//div[@id='navbar-collapse-1']/ul/li[2]/ul/li[2]/a/em")).click();
    driver.findElement(By.cssSelector("a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Current Status of the Foreign Relations Series")).click();
    driver.findElement(By.cssSelector("a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("History of the Foreign Relations Series")).click();
    driver.findElement(By.cssSelector("a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Ebooks Initiative")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Overview")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("A Short History of the Department")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Biographies of the Secretaries of\n                                    State')]")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Principal Officers and Chiefs of\n                                    Mission')]")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Travels of the Secretary of State")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Travels of the Secretary of State")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Travels of the President")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Visits by Foreign Heads of State")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("World War I and the Department")).click();
    driver.findElement(By.cssSelector("#index_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Buildings of the Department of State")).click();
    driver.findElement(By.cssSelector("#milestones_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("1953–1960")).click();
    driver.findElement(By.cssSelector("#countries_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Guide to Country Recognition and Relations")).click();
    driver.findElement(By.cssSelector("#countries_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("World Wide Diplomatic Archives Index")).click();
    driver.findElement(By.cssSelector("#resources_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("About Us")).click();
    driver.findElement(By.cssSelector("#resources_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Browse Resources by Subject Tag")).click();
    driver.findElement(By.cssSelector("#resources_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Conferences")).click();
    driver.findElement(By.cssSelector("#resources_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Contact Us")).click();
    driver.findElement(By.cssSelector("#resources_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Developer Resources & APIs")).click();
    driver.findElement(By.cssSelector("#resources_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Educational Resources")).click();
    driver.findElement(By.cssSelector("#resources_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Frequently Asked Questions")).click();
    driver.findElement(By.cssSelector("#resources_nav > a.dropdown-toggle > span")).click();
    driver.findElement(By.linkText("Open Government Initiative")).click();
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
