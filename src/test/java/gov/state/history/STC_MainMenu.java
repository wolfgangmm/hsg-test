package gov.state.history;

import static org.assertj.core.api.Assertions.*;
import org.junit.*;
import org.openqa.selenium.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gov.state.history.test_utils.AbstractSeleniumTest;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * Tests integrity of the HSG main menu. 
 */
public class STC_MainMenu extends AbstractSeleniumTest {
	//private String baseUrl = "http://localhost:8080/exist/apps/hsg-shell";
	private String baseUrl = "http://192.168.178.25:8080/exist/apps/hsg-shell";

	@Test @Ignore
	public void testHomeButton() throws Exception {
		driver.get(baseUrl + "/open");

		WebElement homeLink = driver.findElement(
				By.xpath("//div[@id='navbar-collapse-1']/ul/li[1]//a"));
		assertThat(homeLink.getText().trim()).isEqualTo("Home");
		homeLink.click();
		basicPageVerification("(Home button)");
	}

	@Test @Ignore
	public void testMainMenuNoBack() throws Exception {
		driver.get(baseUrl + "/open");

		WebElement homeLink = driver.findElement(
				By.xpath("//div[@id='navbar-collapse-1']/ul/li[1]//a"));
		assertThat(homeLink.getText().trim()).isEqualTo("Home");
		homeLink.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		checkMainMenu(false);
	}

	@Test
	public void testMainMenuBack() throws Exception {
		driver.get(baseUrl + "/open");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		checkMainMenu(true);
	}

	private void checkMainMenu(boolean goBack) {
		// how far we went in the previous round
		int howFarPrevoiusly = 0;
		
		superloop:
		for(;;) {
			// how far we went in the current round
			int howFarNow = 0;
			List<WebElement> menuGroups = driver.findElements(
				By.xpath("//div[@id='navbar-collapse-1']/ul/li[@class='dropdown']"));
				//By.cssSelector("#navbar-collapse-1 li.dropdown"));
			for(WebElement menuGroup : menuGroups) {
				List<WebElement> menuLinks = menuGroup.findElements(By.xpath("ul[@class='dropdown-menu']/li/a[@href]"));
				for (WebElement menuLink : menuLinks) {
					howFarNow++;
					if(howFarNow <= howFarPrevoiusly)
						continue;
					howFarPrevoiusly = howFarNow;
					menuGroup.click();
					menuLink.click();
					basicPageVerification("from main menu");
					// go back or stay on the new page, depending on the setting
					if(goBack)
						driver.navigate().back();
					// if we loaded a new page (even by going back) we have to search for the elements again, so we GOTO the beginning of the loop
					continue superloop;
				}
			}
			// if we haven't visited any new link, then finish testing
			break superloop;
		}
	}
}
