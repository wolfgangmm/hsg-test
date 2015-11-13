
import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

@RunWith(Parameterized.class)
public class TestAllVolumesAvailable {
	private final static String LIST_FILE = "volumes_list.txt";
	private String baseUrl = "http://localhost:8080/exist/apps/hsg-shell/historicaldocuments/";
	private WebClient wc;
	private String docId;

	public TestAllVolumesAvailable(String docId) {
		this.docId = docId;
	}

	@Parameters
	// should be available in JUnit 4.13: @Parameters(name = "{index}: volume
	// {0}")
	public static Iterable<Object[]> data() {
		List<String> docIds = docIds();
		ArrayList<Object[]> data = new ArrayList<>(docIds.size());
		for (String s : docIds) {
			data.add(new Object[] { s });
		}
		return data;
	}

	private static List<String> docIds() {
		// return Arrays.asList(new String[] {"frus1948v06",
		// "frus1952-54v01p2"});
		LinkedList<String> list = new LinkedList<>();
		InputStream resourceAsStream = TestAllVolumesAvailable.class.getResourceAsStream(LIST_FILE);
		if (resourceAsStream == null) {
			System.err.println("WARNING: Cannot read volumes list. Availability test skipped.");
		} else {
			try (InputStreamReader isr = new InputStreamReader(resourceAsStream);
					BufferedReader br = new BufferedReader(isr)) {
				String line;
				while ((line = br.readLine()) != null) {
					list.add(line.trim());
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		return list;
	}

	@Before
	public void setUp() throws Exception {
		wc = new WebClient();
	}

	@After
	public void tearDown() throws Exception {
		wc.close();
	}

	@Test
	public void checkStatusCode() throws Exception {
		WebRequest req = new WebRequest(new URL(baseUrl + docId));
		WebResponse resp = wc.loadWebResponse(req);
		assertThat(resp.getStatusCode()).isEqualTo(200);
	}
}
