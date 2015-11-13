package gov.state.history.test_patterns;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * This is the pattern of tests generated with XSLT based on bibliography entries representing volumes.
 * These tests use HTMLUnit as HTTP client side.
 */
public class BibliographyHTMLUnitTestPattern { 
  private WebClient wc;
  private String mainUrl = "http://localhost:8080/exist/apps/hsg-shell/historicaldocuments/frus1952-54v01p2";

  @Before
  public void setUp() throws Exception {
	wc = new WebClient();
  }

  @After
  public void tearDown() throws Exception {
    wc.close();
  }

  @Test
  public void checkStatusCode() {
	try {
		WebRequest req = new WebRequest(new URL(mainUrl));
		WebResponse resp = wc.loadWebResponse(req);
		assertThat(resp.getStatusCode()).isEqualTo(200);
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
}
