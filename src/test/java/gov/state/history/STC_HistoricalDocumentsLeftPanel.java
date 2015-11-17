package gov.state.history;

import static org.assertj.core.api.Assertions.*;
import org.junit.*;
import org.openqa.selenium.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gov.state.history.test_utils.AbstractSeleniumTest;
import gov.state.history.test_utils.HSGUtils;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * Tests the left panel and sites referenced from it. 
 */
public class STC_HistoricalDocumentsLeftPanel extends AbstractSeleniumTest {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		driver.get(HSGUtils.getApplicationUrl() + "/historicaldocuments");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	/**
	 * Tests a few example links  to volumes from the left panel select field (options of #select-volume).
	 */
	@Test
	public void testVolumeLinks() throws Exception {
		/** Left panel contains a select field #select-volume with a large number
		    of options - for subsequent FRUS volumes; the following table
		    defines positions for which a test will be performed.
		    Negative indexes are counted from the end of the list (-1 is the last option). */
		int[] volumeOptionsToCheck = {0, 1, 25, -1};
				
		for(int i : volumeOptionsToCheck) {
			WebElement selectVolume = driver.findElement(By.id("select-volume"));
			List<WebElement> options = selectVolume.findElements(By.tagName("option"));
			//System.out.printf("Found %d volume options%n", options.size());

			if(i < 0)
				i = options.size() + i;
			options.get(i).click();
			basicPageVerification("volume entry entered from #select-volume option no "+i);
			System.out.println(driver.getCurrentUrl());
			driver.navigate().back();
		}
	}
	
	/**
	 * This one tests ALL links to volumes from the left panel select field (all options of #select-volume).
	 */
	@Test @Ignore
	public void testAllVolumeLinks() throws Exception {
		checkAllSelectOptions("select-volume", "volume entry entered from #select-volume");
	}
	
	/**
	 * This test verifies all the links to adminstration sites from the left panel (#select-administration select field).
	 */
	@Test
	public void testAllAdministrationLinks() throws Exception {
		checkAllSelectOptions("select-administration", "administration site entered from #select-administration ");
	}

	private void checkAllSelectOptions(String selectFieldId, String identMessage) {
		// how far we went in the previous round
		int howFarPrevoiusly = 0;
		superloop:
		for(;;){
			// how far we went in the current round
			int howFarNow = 0;
			WebElement selectVolume = driver.findElement(By.id(selectFieldId));
			List<WebElement> options = selectVolume.findElements(By.tagName("option"));
			//System.out.printf("Found %d options%n", options.size());
			for(WebElement option : options) {
				howFarNow++;
				if(howFarNow <= howFarPrevoiusly)
					continue;
				howFarPrevoiusly++;
				option.click();
				basicPageVerification(identMessage + " (option no "+howFarNow + ")");
				//System.out.println(driver.getCurrentUrl());
				driver.navigate().back();
				continue superloop;
			}
			break superloop;
		}
	}
}
