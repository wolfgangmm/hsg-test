package gov.state.history;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.*;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import gov.state.history.test_utils.AbstractSeleniumTest;
import gov.state.history.test_utils.HSGUtils;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * Tests integrity of a FRUS article page.
 */
public class STC_Article extends AbstractSeleniumTest {
	
	// Document coordinates. The example document should be a typical document,
	// containing person and glossary panels,
	// not the first and not the last one within a volume.
	private String volumeId = "frus1952-54v07p1";
	private String documentNo = "d68";
	
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		driver.get(HSGUtils.getApplicationUrl()
				+ String.format("/historicaldocuments/%s/%s", volumeId, documentNo));
	}
	
	@Test
	public void testPageStructure() throws Exception {
		basicPageVerification(String.format("FRUS article %s/%s", volumeId, documentNo));
		WebElement h_title = driver.findElement(By.id("navigation-title"));
		assertThat(h_title.getText()).isNotEmpty();
		WebElement contentInner = driver.findElement(By.id("content-inner"));
		WebElement content = contentInner.findElement(By.className("content"));
		assertThat(content.getText()).isNotEmpty();
		
		WebElement toc = driver.findElement(By.id("toc"));
		WebElement toc_h2 = toc.findElement(By.tagName("h2"));
		assertThat(toc_h2.getText()).isNotEmpty();
		List<WebElement> toc_elements = toc.findElements(By.tagName("li"));
		assertThat(toc_elements).isNotEmpty();
	}

	@Test
	public void testNavigation() throws Exception {
		checkArrow("nav-prev", "left");
		checkArrow("nav-next", "right");
		checkReferencePanel("person-panel", false);
		checkReferencePanel("gloss-panel", false);
	}

	private void checkArrow(String arrowClass, String text) {
		WebElement link = driver.findElement(
				By.cssSelector(".navigation-top > ." + arrowClass));
		link.click();
		basicPageVerification(String.format("Navigate %s from FRUS article %s/%s", text, volumeId, documentNo));
		driver.navigate().back();
		basicPageVerification(String.format("Came back to FRUS article %s/%s", volumeId, documentNo));		
	}
	
	private void checkReferencePanel(String elementId, boolean failOnMissing) {
		try {
			WebElement panel = driver.findElement(By.id(elementId));
			List<WebElement> items = panel.findElements(By.tagName("a"));
			if(items.size() > 0) {
				int i = items.size() / 2;
				items.get(i).click();
				basicPageVerification(String.format("Item %d of %s panel", i, elementId));
				driver.navigate().back();
			}
		} catch (ElementNotFoundException e) {
			if(failOnMissing) {
				fail("Missing element #" + elementId);
			}
		}		
	}
}
