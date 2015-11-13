package gov.state.history.test_patterns;

import static org.assertj.core.api.Assertions.*;
import org.junit.*;
import org.openqa.selenium.*;
import gov.state.history.test_utils.AbstractSeleniumTest;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * This is the pattern of tests generated with XSLT based on bibliography entries representing volumes.
 * These tests use Selenium as HTTP client side.
 */
public class BibliographySeleniumTestPattern extends AbstractSeleniumTest { 
  private String mainUrl = "http://localhost:8080/exist/apps/hsg-shell/historicaldocuments/frus1952-54v01p2";

  @Test
  public void mainTest() {
    driver.get(mainUrl);
    assertThat(driver.getTitle()).isEqualToIgnoringWhitespace("history.state.gov 3.0 shell");
    final String titleText = driver.findElement(By.cssSelector("h1.titleStmt1")).getText();
    assertThat(titleText).isEqualToIgnoringWhitespace("Foreign Relations of the United States, 1952–1954, General: Economic and Political Matters, Volume I, Part 2".toUpperCase());
  }
}
