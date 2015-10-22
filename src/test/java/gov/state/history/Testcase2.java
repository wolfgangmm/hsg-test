package gov.state.history;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCase2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCase2() throws Exception {
    driver.get(baseUrl + "/exist/apps/hsg-shell/");
    assertEquals("history.state.gov 3.0 shell", driver.getTitle());
    assertEquals("1750–1775", driver.findElement(By.xpath("(//a[contains(text(),'1750–1775')])[2]")).getText());
    driver.findElement(By.xpath("(//a[contains(text(),'1750–1775')])[2]")).click();
    assertEquals("Milestones: 1750–1775", driver.findElement(By.cssSelector("h1")).getText());
    assertEquals("Rivalry in Europe, between the French and the British in particular, often influenced the course of events in their North American colonies.In an effort to increase their political and economic power, the British and the French competed to acquire the better share of the available land and control over the new trading opportunities the colonies presented.", driver.findElement(By.xpath("//div[@id='foreword']/p[2]")).getText());
    assertEquals("Choose one 1750–1775 1776–1783 1784–1800 1801–1829 1830–1860 1861–1865 1866–1898 1899–1913 1914–1920 1921–1936 1937–1945 1945–1952 1953–1960 1961–1968 1969–1976 1977–1980 1981–1988 1989–1992 1993–2000 ... view all", driver.findElement(By.id("select-chapter")).getText());
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
