package gov.state.history.test_utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class SeleniumUtils {
	public static final String FF_PROFILE_NAME = "selenium";

	public static FirefoxDriver obtainFirefoxDriver() {
		ProfilesIni profiles = new ProfilesIni();
		FirefoxProfile ffprofile = profiles.getProfile(FF_PROFILE_NAME);
		System.out.println();
		FirefoxDriver driver;
		if(ffprofile != null)
			driver = new FirefoxDriver(ffprofile);
		else
			driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		return driver;
	}

}
