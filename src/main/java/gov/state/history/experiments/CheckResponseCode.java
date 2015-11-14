package gov.state.history.experiments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.IOException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;

public class CheckResponseCode {
	  private String mainUrl = "http://localhost:8080/exist/apps/hsg-shell/historicaldocuments/frus1952-54v01p2";

	  @Test
	  public void checkResponseCode() {
		  WebClient wc = new WebClient();
	  	try {
	        int code;
	  	  try {
	        code = wc.getPage(mainUrl).getWebResponse().getStatusCode();
	  	  } catch(FailingHttpStatusCodeException e) {
			code = e.getStatusCode();
	      }
	      assertThat(code).isEqualTo(200);
		} catch (IOException e) {
		  fail("Exception while getting the page", e);
		}
	  }
	  
}
